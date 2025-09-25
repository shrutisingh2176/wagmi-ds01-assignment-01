package test.java.com.wagmi.finance.alg;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.alg.Sorters;
import main.java.com.wagmi.finance.model.Transaction;

public class SortersTest {

    private Transaction t(String id, double amount, String cat, String date) {
        return new Transaction(id, "d" + id, amount, cat, LocalDate.parse(date), false);
    }

    @Test
    void quickSortByAmount_sorts_ascending_and_handles_edges() {
        Transaction[] arr = new Transaction[] {
                t("a", 20, "Bills", "2024-01-02"),
                t("b", 10, "Food", "2024-01-01"),
                t("c", 10, "Food", "2024-01-03"),
                t("d", 5, "Fun", "2024-01-04")
        };
        Sorters.quickSortByAmount(arr);
        assertEquals(5, arr[0].getAmount(), 0.0);
        assertEquals(10, arr[1].getAmount(), 0.0);
        assertEquals(10, arr[2].getAmount(), 0.0);
        assertEquals(20, arr[3].getAmount(), 0.0);
    }

    @Test
    void mergeSortByDate_sorts_by_date_ascending() {
        Transaction[] arr = new Transaction[] {
                t("a", 20, "Bills", "2024-01-02"),
                t("b", 10, "Food", "2024-01-01"),
                t("c", 15, "Food", "2023-12-31")
        };
        Sorters.mergeSortByDate(arr);
        assertEquals("2023-12-31", arr[0].getDate().toString());
        assertEquals("2024-01-01", arr[1].getDate().toString());
        assertEquals("2024-01-02", arr[2].getDate().toString());
    }

    @Test
    void insertionSortByCategory_uses_custom_comparator() {
        Transaction[] arr = new Transaction[] {
                t("a", 1, "C", "2024-01-01"),
                t("b", 1, "A", "2024-01-01"),
                t("c", 1, "B", "2024-01-01")
        };
        Sorters.insertionSortByCategory(arr, Comparator.naturalOrder());
        assertEquals("A", arr[0].getCategory());
        assertEquals("B", arr[1].getCategory());
        assertEquals("C", arr[2].getCategory());
    }

    @Test
    void quickSortByAmount_handles_empty_array() {
        Transaction[] arr = new Transaction[0];
        Sorters.quickSortByAmount(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void quickSortByAmount_handles_single_element() {
        Transaction[] arr = new Transaction[] { t("a", 100, "Food", "2024-01-01") };
        Sorters.quickSortByAmount(arr);
        assertEquals(100, arr[0].getAmount(), 0.0);
    }

    @Test
    void quickSortByAmount_handles_duplicate_amounts() {
        Transaction[] arr = new Transaction[] {
                t("a", 100, "Food", "2024-01-01"),
                t("b", 100, "Bills", "2024-01-02"),
                t("c", 50, "Fun", "2024-01-03")
        };
        Sorters.quickSortByAmount(arr);
        assertEquals(50, arr[0].getAmount(), 0.0);
        assertEquals(100, arr[1].getAmount(), 0.0);
        assertEquals(100, arr[2].getAmount(), 0.0);
    }

    @Test
    void quickSortByAmount_handles_negative_amounts() {
        Transaction[] arr = new Transaction[] {
                t("a", -100, "Refund", "2024-01-01"),
                t("b", 50, "Food", "2024-01-02"),
                t("c", -50, "Return", "2024-01-03")
        };
        Sorters.quickSortByAmount(arr);
        assertEquals(-100, arr[0].getAmount(), 0.0);
        assertEquals(-50, arr[1].getAmount(), 0.0);
        assertEquals(50, arr[2].getAmount(), 0.0);
    }

    @Test
    void mergeSortByDate_handles_empty_array() {
        Transaction[] arr = new Transaction[0];
        Sorters.mergeSortByDate(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void mergeSortByDate_handles_single_element() {
        Transaction[] arr = new Transaction[] { t("a", 100, "Food", "2024-01-01") };
        Sorters.mergeSortByDate(arr);
        assertEquals("2024-01-01", arr[0].getDate().toString());
    }

    @Test
    void mergeSortByDate_handles_duplicate_dates() {
        Transaction[] arr = new Transaction[] {
                t("a", 100, "Food", "2024-01-01"),
                t("b", 200, "Bills", "2024-01-01"),
                t("c", 300, "Fun", "2024-01-02")
        };
        Sorters.mergeSortByDate(arr);
        assertEquals("2024-01-01", arr[0].getDate().toString());
        assertEquals("2024-01-01", arr[1].getDate().toString());
        assertEquals("2024-01-02", arr[2].getDate().toString());
    }

    @Test
    void mergeSortByDate_handles_year_boundaries() {
        Transaction[] arr = new Transaction[] {
                t("a", 100, "Food", "2024-01-01"),
                t("b", 200, "Bills", "2023-12-31"),
                t("c", 300, "Fun", "2024-12-31")
        };
        Sorters.mergeSortByDate(arr);
        assertEquals("2023-12-31", arr[0].getDate().toString());
        assertEquals("2024-01-01", arr[1].getDate().toString());
        assertEquals("2024-12-31", arr[2].getDate().toString());
    }

    @Test
    void insertionSortByCategory_handles_empty_array() {
        Transaction[] arr = new Transaction[0];
        Sorters.insertionSortByCategory(arr, Comparator.naturalOrder());
        assertEquals(0, arr.length);
    }

    @Test
    void insertionSortByCategory_handles_single_element() {
        Transaction[] arr = new Transaction[] { t("a", 100, "Food", "2024-01-01") };
        Sorters.insertionSortByCategory(arr, Comparator.naturalOrder());
        assertEquals("Food", arr[0].getCategory());
    }

    @Test
    void insertionSortByCategory_handles_duplicate_categories() {
        Transaction[] arr = new Transaction[] {
                t("a", 100, "Food", "2024-01-01"),
                t("b", 200, "Food", "2024-01-02"),
                t("c", 300, "Bills", "2024-01-03")
        };
        Sorters.insertionSortByCategory(arr, Comparator.naturalOrder());
        assertEquals("Bills", arr[0].getCategory());
        assertEquals("Food", arr[1].getCategory());
        assertEquals("Food", arr[2].getCategory());
    }

    @Test
    void insertionSortByCategory_with_reverse_comparator() {
        Transaction[] arr = new Transaction[] {
                t("a", 1, "A", "2024-01-01"),
                t("b", 1, "B", "2024-01-01"),
                t("c", 1, "C", "2024-01-01")
        };
        Sorters.insertionSortByCategory(arr, Comparator.reverseOrder());
        assertEquals("C", arr[0].getCategory());
        assertEquals("B", arr[1].getCategory());
        assertEquals("A", arr[2].getCategory());
    }
}
