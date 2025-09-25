package test.java.com.wagmi.finance.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.budget.BudgetManager;
import main.java.com.wagmi.finance.ds.DoublyLinkedTransactions;
import main.java.com.wagmi.finance.history.OperationStack;
import main.java.com.wagmi.finance.model.Transaction;
import main.java.com.wagmi.finance.processing.PendingQueue;
import main.java.com.wagmi.finance.recursion.RecursionUtils;
import main.java.com.wagmi.finance.strings.StringUtils;

public class FinanceSystemIntegrationTest {

    private DoublyLinkedTransactions transactionList;
    private BudgetManager budgetManager;
    private OperationStack operationStack;
    private PendingQueue pendingQueue;

    @BeforeEach
    void setup() {
        transactionList = new DoublyLinkedTransactions();
        budgetManager = new BudgetManager();
        operationStack = new OperationStack(10);
        pendingQueue = new PendingQueue(5);
    }

    private Transaction createTransaction(String id, double amount, String category, String date) {
        return new Transaction(id, "Transaction " + id, amount, category, LocalDate.parse(date), false);
    }

    @Test
    void complete_transaction_workflow() {
        // Setup budget categories
        budgetManager.addValidCategory("Food");
        budgetManager.addValidCategory("Bills");
        budgetManager.setBudgetLimit("Food", 200.0);
        budgetManager.setBudgetLimit("Bills", 500.0);

        // Add transactions to pending queue
        pendingQueue.enqueue(createTransaction("1", 50.0, "Food", "2024-01-01"));
        pendingQueue.enqueue(createTransaction("2", 100.0, "Bills", "2024-01-02"));
        pendingQueue.enqueue(createTransaction("3", 75.0, "Food", "2024-01-03"));

        // Process pending transactions
        while (!pendingQueue.isEmpty()) {
            Transaction tx = pendingQueue.dequeue();
            transactionList.add(tx);
            budgetManager.applyTransaction(tx);
            operationStack.push("ADD:" + tx.getId());
        }

        // Verify results
        assertEquals(3, transactionList.size());
        assertEquals(125.0, budgetManager.getSpending("Food"), 0.0);
        assertEquals(100.0, budgetManager.getSpending("Bills"), 0.0);
        assertEquals(3, operationStack.size());
    }

    @Test
    void transaction_deletion_with_undo() {
        // Setup
        budgetManager.addValidCategory("Food");
        budgetManager.setBudgetLimit("Food", 200.0);

        Transaction tx1 = createTransaction("1", 50.0, "Food", "2024-01-01");
        Transaction tx2 = createTransaction("2", 75.0, "Food", "2024-01-02");

        transactionList.add(tx1);
        transactionList.add(tx2);
        budgetManager.applyTransaction(tx1);
        budgetManager.applyTransaction(tx2);
        operationStack.push("ADD:1");
        operationStack.push("ADD:2");

        // Delete transaction
        assertTrue(transactionList.deleteById("1"));
        operationStack.push("DELETE:1");

        // Verify deletion
        assertEquals(1, transactionList.size());
        assertNull(transactionList.findById("1"));

        // Verify undo operation
        assertEquals("DELETE:1", operationStack.pop());
        assertEquals("ADD:2", operationStack.pop());
        assertEquals("ADD:1", operationStack.pop());
    }

    @Test
    void budget_limit_enforcement() {
        budgetManager.addValidCategory("Food");
        budgetManager.setBudgetLimit("Food", 100.0);

        Transaction tx1 = createTransaction("1", 60.0, "Food", "2024-01-01");
        Transaction tx2 = createTransaction("2", 50.0, "Food", "2024-01-02");

        transactionList.add(tx1);
        budgetManager.applyTransaction(tx1);

        assertTrue(budgetManager.isApproachingLimit("Food")); // 60% of limit

        transactionList.add(tx2);
        budgetManager.applyTransaction(tx2);

        assertTrue(budgetManager.isOverLimit("Food")); // 110% of limit
    }

    @Test
    void recursive_analysis_integration() {
        // Add transactions
        Transaction[] transactions = {
                createTransaction("1", 100.0, "Food", "2024-01-01"),
                createTransaction("2", 200.0, "Bills", "2024-01-02"),
                createTransaction("3", 50.0, "Food", "2024-01-03"),
                createTransaction("4", 150.0, "Bills", "2024-01-04")
        };

        for (Transaction tx : transactions) {
            transactionList.add(tx);
        }

        Transaction[] arr = transactionList.toArray();

        // Test recursive category calculation
        assertEquals(150.0, RecursionUtils.categoryTotalRecursive(arr, "Food"), 0.0);
        assertEquals(350.0, RecursionUtils.categoryTotalRecursive(arr, "Bills"), 0.0);

        // Test recursive report generation
        String report = RecursionUtils.generateBudgetReportRecursive(arr);
        assertNotNull(report);
        assertFalse(report.isBlank());
    }

    @Test
    void string_validation_integration() {
        // Test date validation
        assertTrue(StringUtils.matchesDatePattern("2024-01-01"));
        assertFalse(StringUtils.matchesDatePattern("invalid-date"));

        // Test description sanitization
        String sanitized = StringUtils.sanitizeDescription("  Clean   Description  ");
        assertEquals("Clean Description", sanitized);

        // Create transaction with validated inputs
        Transaction tx = new Transaction("1", sanitized, 100.0, "Food", LocalDate.parse("2024-01-01"), false);
        assertNotNull(tx);
    }

    @Test
    void error_handling_integration() {
        // Test invalid category
        assertFalse(budgetManager.isValidCategory("InvalidCategory"));

        // Test null transaction
        assertThrows(Exception.class, () -> pendingQueue.enqueue(null));

        // Test stack overflow
        OperationStack smallStack = new OperationStack(1);
        smallStack.push("OP1");
        assertThrows(UnsupportedOperationException.class, () -> smallStack.push("OP2"));

        // Test queue overflow
        PendingQueue smallQueue = new PendingQueue(1);
        smallQueue.enqueue(createTransaction("1", 100.0, "Food", "2024-01-01"));
        assertThrows(UnsupportedOperationException.class,
                () -> smallQueue.enqueue(createTransaction("2", 200.0, "Food", "2024-01-02")));
    }

    @Test
    void performance_with_large_dataset() {
        budgetManager.addValidCategory("Food");
        budgetManager.setBudgetLimit("Food", 10000.0);

        // Add many transactions
        for (int i = 0; i < 100; i++) {
            Transaction tx = createTransaction(String.valueOf(i), 10.0, "Food", "2024-01-01");
            transactionList.add(tx);
            budgetManager.applyTransaction(tx);
            operationStack.push("ADD:" + i);
        }

        // Verify system handles large dataset
        assertEquals(100, transactionList.size());
        assertEquals(1000.0, budgetManager.getSpending("Food"), 0.0);
        assertEquals(100, operationStack.size());

        // Test recursive operations on large dataset
        Transaction[] arr = transactionList.toArray();
        assertEquals(1000.0, RecursionUtils.categoryTotalRecursive(arr, "Food"), 0.0);

        String report = RecursionUtils.generateBudgetReportRecursive(arr);
        assertNotNull(report);
    }
}
