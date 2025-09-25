package test.java.com.wagmi.finance.recursion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.model.Transaction;
import main.java.com.wagmi.finance.recursion.RecursionUtils;

public class RecursionUtilsTest {

    @Test
    void isValidDateRecursive_validates_yyyy_mm_dd() {
        assertTrue(RecursionUtils.isValidDateRecursive("2024-12-31"));
        assertFalse(RecursionUtils.isValidDateRecursive("2024-13-01"));
        assertFalse(RecursionUtils.isValidDateRecursive("2024/01/01"));
        assertFalse(RecursionUtils.isValidDateRecursive(""));
    }

    @Test
    void categoryTotalRecursive_sums_matching_category() {
        Transaction[] arr = new Transaction[] {
                new Transaction("1", "a", 10, "Food", LocalDate.parse("2024-01-01"), false),
                new Transaction("2", "b", 20, "Bills", LocalDate.parse("2024-01-02"), false),
                new Transaction("3", "c", 5, "Food", LocalDate.parse("2024-01-03"), false)
        };
        assertEquals(15.0, RecursionUtils.categoryTotalRecursive(arr, "Food"), 0.0);
        assertEquals(20.0, RecursionUtils.categoryTotalRecursive(arr, "Bills"), 0.0);
        assertEquals(0.0, RecursionUtils.categoryTotalRecursive(new Transaction[0], "Food"), 0.0);
    }

    @Test
    void generateBudgetReportRecursive_returns_non_empty_string() {
        Transaction[] arr = new Transaction[] {
                new Transaction("1", "a", 10, "Food", LocalDate.parse("2024-01-01"), false)
        };
        String report = RecursionUtils.generateBudgetReportRecursive(arr);
        assertNotNull(report);
        assertFalse(report.isBlank());
    }

    @Test
    void isValidDateRecursive_handles_edge_dates() {
        assertTrue(RecursionUtils.isValidDateRecursive("2024-02-29")); // Leap year
        assertFalse(RecursionUtils.isValidDateRecursive("2023-02-29")); // Non-leap year
        assertTrue(RecursionUtils.isValidDateRecursive("2024-01-01")); // New Year
        assertTrue(RecursionUtils.isValidDateRecursive("2024-12-31")); // New Year's Eve
        assertFalse(RecursionUtils.isValidDateRecursive("2024-04-31")); // April has 30 days
    }

    @Test
    void isValidDateRecursive_handles_invalid_formats() {
        assertFalse(RecursionUtils.isValidDateRecursive("2024/01/01"));
        assertFalse(RecursionUtils.isValidDateRecursive("01-01-2024"));
        assertFalse(RecursionUtils.isValidDateRecursive("2024-1-1"));
        assertFalse(RecursionUtils.isValidDateRecursive("24-01-01"));
        assertFalse(RecursionUtils.isValidDateRecursive("2024-13-01"));
        assertFalse(RecursionUtils.isValidDateRecursive("2024-01-32"));
    }

    @Test
    void isValidDateRecursive_handles_boundary_values() {
        assertTrue(RecursionUtils.isValidDateRecursive("0001-01-01"));
        assertTrue(RecursionUtils.isValidDateRecursive("9999-12-31"));
        assertFalse(RecursionUtils.isValidDateRecursive("0000-01-01"));
        assertFalse(RecursionUtils.isValidDateRecursive("10000-01-01"));
    }

    @Test
    void categoryTotalRecursive_handles_empty_array() {
        assertEquals(0.0, RecursionUtils.categoryTotalRecursive(new Transaction[0], "Food"), 0.0);
    }

    @Test
    void categoryTotalRecursive_handles_null_category() {
        Transaction[] arr = new Transaction[] {
                new Transaction("1", "a", 10, "Food", LocalDate.parse("2024-01-01"), false)
        };
        assertEquals(0.0, RecursionUtils.categoryTotalRecursive(arr, null), 0.0);
    }

    @Test
    void categoryTotalRecursive_handles_large_array() {
        Transaction[] arr = new Transaction[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = new Transaction(String.valueOf(i), "desc", 1.0, "Food", LocalDate.parse("2024-01-01"), false);
        }
        assertEquals(1000.0, RecursionUtils.categoryTotalRecursive(arr, "Food"), 0.0);
    }

    @Test
    void categoryTotalRecursive_handles_mixed_categories() {
        Transaction[] arr = new Transaction[] {
                new Transaction("1", "a", 10, "Food", LocalDate.parse("2024-01-01"), false),
                new Transaction("2", "b", 20, "Bills", LocalDate.parse("2024-01-02"), false),
                new Transaction("3", "c", 5, "Food", LocalDate.parse("2024-01-03"), false),
                new Transaction("4", "d", 15, "Bills", LocalDate.parse("2024-01-04"), false),
                new Transaction("5", "e", 8, "Fun", LocalDate.parse("2024-01-05"), false)
        };
        assertEquals(15.0, RecursionUtils.categoryTotalRecursive(arr, "Food"), 0.0);
        assertEquals(35.0, RecursionUtils.categoryTotalRecursive(arr, "Bills"), 0.0);
        assertEquals(8.0, RecursionUtils.categoryTotalRecursive(arr, "Fun"), 0.0);
        assertEquals(0.0, RecursionUtils.categoryTotalRecursive(arr, "Unknown"), 0.0);
    }

    @Test
    void generateBudgetReportRecursive_handles_empty_array() {
        String report = RecursionUtils.generateBudgetReportRecursive(new Transaction[0]);
        assertNotNull(report);
        assertFalse(report.isBlank());
    }

    @Test
    void generateBudgetReportRecursive_handles_large_array() {
        Transaction[] arr = new Transaction[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = new Transaction(String.valueOf(i), "desc", i, "Category" + (i % 5), LocalDate.parse("2024-01-01"),
                    false);
        }
        String report = RecursionUtils.generateBudgetReportRecursive(arr);
        assertNotNull(report);
        assertFalse(report.isBlank());
    }

    @Test
    void generateBudgetReportRecursive_includes_category_breakdown() {
        Transaction[] arr = new Transaction[] {
                new Transaction("1", "Food", 100, "Food", LocalDate.parse("2024-01-01"), false),
                new Transaction("2", "Bills", 200, "Bills", LocalDate.parse("2024-01-02"), false),
                new Transaction("3", "More Food", 50, "Food", LocalDate.parse("2024-01-03"), false)
        };
        String report = RecursionUtils.generateBudgetReportRecursive(arr);
        assertTrue(report.contains("Food") || report.contains("Bills"));
    }

    @Test
    void isValidDateRecursive_handles_whitespace() {
        assertFalse(RecursionUtils.isValidDateRecursive(" 2024-01-01 "));
        assertFalse(RecursionUtils.isValidDateRecursive("2024-01-01 "));
        assertFalse(RecursionUtils.isValidDateRecursive(" 2024-01-01"));
    }
}
