package test.java.com.wagmi.finance.budget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.budget.BudgetManager;
import main.java.com.wagmi.finance.model.Transaction;

public class BudgetManagerTest {
    private BudgetManager bm;

    @BeforeEach
    void setup() {
        bm = new BudgetManager();
    }

    private Transaction expense(String id, double amount, String category) {
        return new Transaction(id, "d" + id, amount, category, LocalDate.parse("2024-01-01"), false);
    }

    @Test
    void category_validation_and_limits_work() {
        bm.addValidCategory("Food");
        assertTrue(bm.isValidCategory("Food"));
        assertFalse(bm.isValidCategory("Invalid"));
        bm.setBudgetLimit("Food", 100.0);
        assertEquals(100.0, bm.getBudgetLimit("Food"), 0.0);
    }

    @Test
    void spending_updates_and_limit_checks() {
        bm.addValidCategory("Food");
        bm.setBudgetLimit("Food", 100.0);
        bm.applyTransaction(expense("1", 40.0, "Food"));
        assertEquals(40.0, bm.getSpending("Food"), 0.0);
        assertTrue(bm.isApproachingLimit("Food")); // depending on threshold e.g., 40% or 80%
        bm.applyTransaction(expense("2", 70.0, "Food"));
        assertTrue(bm.isOverLimit("Food"));
    }

    @Test
    void getBudgetLimit_returns_zero_for_unknown_category() {
        assertEquals(0.0, bm.getBudgetLimit("Unknown"), 0.0);
    }

    @Test
    void getSpending_returns_zero_for_unknown_category() {
        assertEquals(0.0, bm.getSpending("Unknown"), 0.0);
    }

    @Test
    void isApproachingLimit_returns_false_for_unknown_category() {
        assertFalse(bm.isApproachingLimit("Unknown"));
    }

    @Test
    void isOverLimit_returns_false_for_unknown_category() {
        assertFalse(bm.isOverLimit("Unknown"));
    }

    @Test
    void setBudgetLimit_with_negative_value() {
        bm.addValidCategory("Food");
        bm.setBudgetLimit("Food", -50.0);
        assertEquals(-50.0, bm.getBudgetLimit("Food"), 0.0);
    }

    @Test
    void applyTransaction_with_income_updates_spending_correctly() {
        bm.addValidCategory("Salary");
        Transaction income = new Transaction("1", "Salary", 1000.0, "Salary", LocalDate.parse("2024-01-01"), true);
        bm.applyTransaction(income);
        assertEquals(0.0, bm.getSpending("Salary"), 0.0); // Income shouldn't count as spending
    }

    @Test
    void applyTransaction_with_multiple_categories() {
        bm.addValidCategory("Food");
        bm.addValidCategory("Bills");
        bm.setBudgetLimit("Food", 200.0);
        bm.setBudgetLimit("Bills", 500.0);

        bm.applyTransaction(expense("1", 50.0, "Food"));
        bm.applyTransaction(expense("2", 100.0, "Bills"));
        bm.applyTransaction(expense("3", 75.0, "Food"));

        assertEquals(125.0, bm.getSpending("Food"), 0.0);
        assertEquals(100.0, bm.getSpending("Bills"), 0.0);
    }

    @Test
    void isApproachingLimit_with_exact_threshold() {
        bm.addValidCategory("Food");
        bm.setBudgetLimit("Food", 100.0);
        bm.applyTransaction(expense("1", 80.0, "Food")); // Assuming 80% threshold
        assertTrue(bm.isApproachingLimit("Food"));
    }

    @Test
    void isOverLimit_with_exact_limit() {
        bm.addValidCategory("Food");
        bm.setBudgetLimit("Food", 100.0);
        bm.applyTransaction(expense("1", 100.0, "Food"));
        assertTrue(bm.isOverLimit("Food"));
    }

    @Test
    void applyTransaction_with_zero_amount() {
        bm.addValidCategory("Food");
        bm.applyTransaction(expense("1", 0.0, "Food"));
        assertEquals(0.0, bm.getSpending("Food"), 0.0);
    }

    @Test
    void addValidCategory_with_null_category() {
        assertThrows(Exception.class, () -> bm.addValidCategory(null));
    }

    @Test
    void isValidCategory_with_null_category() {
        assertFalse(bm.isValidCategory(null));
    }
}
