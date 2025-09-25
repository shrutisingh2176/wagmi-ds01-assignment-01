package test.java.com.wagmi.finance.ds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.ds.DoublyLinkedTransactions;
import main.java.com.wagmi.finance.model.Transaction;

public class DoublyLinkedTransactionsTest {
    private DoublyLinkedTransactions list;

    @BeforeEach
    void setup() {
        list = new DoublyLinkedTransactions();
    }

    private Transaction tx(String id, double amount, String category, String date) {
        return new Transaction(id, "desc" + id, amount, category, LocalDate.parse(date), false);
    }

    @Test
    void add_increases_size_and_toArray_contains_items_in_order() {
        assertEquals(0, list.size());
        list.add(tx("1", 10, "Food", "2024-01-01"));
        list.add(tx("2", 20, "Bills", "2024-01-02"));
        assertEquals(2, list.size());
        Transaction[] arr = list.toArray();
        assertEquals("1", arr[0].getId());
        assertEquals("2", arr[1].getId());
    }

    @Test
    void deleteById_removes_node_and_updates_size() {
        list.add(tx("1", 10, "Food", "2024-01-01"));
        list.add(tx("2", 20, "Bills", "2024-01-02"));
        list.add(tx("3", 30, "Fun", "2024-01-03"));
        assertTrue(list.deleteById("2"));
        assertEquals(2, list.size());
        Transaction[] arr = list.toArray();
        assertEquals("1", arr[0].getId());
        assertEquals("3", arr[1].getId());
        assertFalse(list.deleteById("999"));
    }

    @Test
    void findById_returns_correct_transaction_or_null() {
        list.add(tx("1", 10, "Food", "2024-01-01"));
        list.add(tx("2", 20, "Bills", "2024-01-02"));
        assertEquals("2", list.findById("2").getId());
        assertNull(list.findById("999"));
    }

    @Test
    void reverse_inverts_order() {
        list.add(tx("1", 10, "Food", "2024-01-01"));
        list.add(tx("2", 20, "Bills", "2024-01-02"));
        list.add(tx("3", 30, "Fun", "2024-01-03"));
        list.reverse();
        Transaction[] arr = list.toArray();
        assertEquals("3", arr[0].getId());
        assertEquals("2", arr[1].getId());
        assertEquals("1", arr[2].getId());
    }

    @Test
    void toArray_on_empty_returns_empty_array() {
        Transaction[] arr = list.toArray();
        assertNotNull(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void add_single_element_works_correctly() {
        Transaction tx = tx("1", 100, "Food", "2024-01-01");
        list.add(tx);
        assertEquals(1, list.size());
        assertEquals(tx, list.findById("1"));
        Transaction[] arr = list.toArray();
        assertEquals(1, arr.length);
        assertEquals(tx, arr[0]);
    }

    @Test
    void deleteById_from_single_element_list() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        assertTrue(list.deleteById("1"));
        assertEquals(0, list.size());
        assertNull(list.findById("1"));
    }

    @Test
    void deleteById_from_head_of_list() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        list.add(tx("2", 200, "Bills", "2024-01-02"));
        list.add(tx("3", 300, "Fun", "2024-01-03"));
        assertTrue(list.deleteById("1"));
        assertEquals(2, list.size());
        Transaction[] arr = list.toArray();
        assertEquals("2", arr[0].getId());
        assertEquals("3", arr[1].getId());
    }

    @Test
    void deleteById_from_tail_of_list() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        list.add(tx("2", 200, "Bills", "2024-01-02"));
        list.add(tx("3", 300, "Fun", "2024-01-03"));
        assertTrue(list.deleteById("3"));
        assertEquals(2, list.size());
        Transaction[] arr = list.toArray();
        assertEquals("1", arr[0].getId());
        assertEquals("2", arr[1].getId());
    }

    @Test
    void reverse_empty_list_does_nothing() {
        list.reverse();
        assertEquals(0, list.size());
        Transaction[] arr = list.toArray();
        assertEquals(0, arr.length);
    }

    @Test
    void reverse_single_element_does_nothing() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        list.reverse();
        assertEquals(1, list.size());
        Transaction[] arr = list.toArray();
        assertEquals("1", arr[0].getId());
    }

    @Test
    void reverse_twice_returns_to_original_order() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        list.add(tx("2", 200, "Bills", "2024-01-02"));
        list.add(tx("3", 300, "Fun", "2024-01-03"));
        list.reverse();
        list.reverse();
        Transaction[] arr = list.toArray();
        assertEquals("1", arr[0].getId());
        assertEquals("2", arr[1].getId());
        assertEquals("3", arr[2].getId());
    }

    @Test
    void findById_with_null_id_returns_null() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        assertNull(list.findById(null));
    }

    @Test
    void deleteById_with_null_id_returns_false() {
        list.add(tx("1", 100, "Food", "2024-01-01"));
        assertFalse(list.deleteById(null));
    }

    @Test
    void add_multiple_elements_maintains_order() {
        Transaction[] transactions = {
                tx("1", 100, "Food", "2024-01-01"),
                tx("2", 200, "Bills", "2024-01-02"),
                tx("3", 300, "Fun", "2024-01-03"),
                tx("4", 400, "Gas", "2024-01-04"),
                tx("5", 500, "Rent", "2024-01-05")
        };

        for (Transaction tx : transactions) {
            list.add(tx);
        }

        assertEquals(5, list.size());
        Transaction[] arr = list.toArray();
        for (int i = 0; i < transactions.length; i++) {
            assertEquals(transactions[i].getId(), arr[i].getId());
        }
    }
}
