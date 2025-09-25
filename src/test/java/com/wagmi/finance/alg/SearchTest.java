package test.java.com.wagmi.finance.alg;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.alg.Search;
import main.java.com.wagmi.finance.model.Transaction;

public class SearchTest {

    private Transaction t(String id) {
        return new Transaction(id, "d", 1.0, "Cat", LocalDate.parse("2024-01-01"), false);
    }

    @Test
    void binarySearchById_finds_index_or_returns_negative_one() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2"), t("3"), t("4") };
        assertEquals(0, Search.binarySearchById(sorted, "1"));
        assertEquals(2, Search.binarySearchById(sorted, "3"));
        assertEquals(-1, Search.binarySearchById(sorted, "9"));
    }

    @Test
    void binarySearchById_handles_empty_array() {
        Transaction[] sorted = new Transaction[0];
        assertEquals(-1, Search.binarySearchById(sorted, "1"));
    }

    @Test
    void binarySearchById_handles_single_element() {
        Transaction[] sorted = new Transaction[] { t("1") };
        assertEquals(0, Search.binarySearchById(sorted, "1"));
        assertEquals(-1, Search.binarySearchById(sorted, "2"));
    }

    @Test
    void binarySearchById_handles_two_elements() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2") };
        assertEquals(0, Search.binarySearchById(sorted, "1"));
        assertEquals(1, Search.binarySearchById(sorted, "2"));
        assertEquals(-1, Search.binarySearchById(sorted, "3"));
    }

    @Test
    void binarySearchById_handles_large_array() {
        Transaction[] sorted = new Transaction[100];
        for (int i = 0; i < 100; i++) {
            sorted[i] = t(String.valueOf(i));
        }
        assertEquals(0, Search.binarySearchById(sorted, "0"));
        assertEquals(49, Search.binarySearchById(sorted, "49"));
        assertEquals(99, Search.binarySearchById(sorted, "99"));
        assertEquals(-1, Search.binarySearchById(sorted, "100"));
    }

    @Test
    void binarySearchById_with_null_id_returns_negative_one() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2"), t("3") };
        assertEquals(-1, Search.binarySearchById(sorted, null));
    }

    @Test
    void binarySearchById_with_null_array_throws_exception() {
        assertThrows(NullPointerException.class, () -> Search.binarySearchById(null, "1"));
    }

    @Test
    void binarySearchById_searches_middle_element() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2"), t("3"), t("4"), t("5") };
        assertEquals(2, Search.binarySearchById(sorted, "3"));
    }

    @Test
    void binarySearchById_searches_first_element() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2"), t("3"), t("4"), t("5") };
        assertEquals(0, Search.binarySearchById(sorted, "1"));
    }

    @Test
    void binarySearchById_searches_last_element() {
        Transaction[] sorted = new Transaction[] { t("1"), t("2"), t("3"), t("4"), t("5") };
        assertEquals(4, Search.binarySearchById(sorted, "5"));
    }
}
