package main.java.com.wagmi.finance.recursion;

/*
 TODO[Student]: Recursive utilities
 - `isValidDateRecursive`: parse YYYY-MM-DD without regex; handle ranges + leap years.
 - `categoryTotalRecursive`: sum amounts for a category (expenses only) recursively.
 - `generateBudgetReportRecursive`: build a non-empty string summarizing items recursively.
 - See `RecursionUtilsTest` for behavior and edge cases.
*/

import main.java.com.wagmi.finance.model.Transaction;

public final class RecursionUtils {
    private RecursionUtils() {
    }

    public static boolean isValidDateRecursive(String date) {
        // stub: validate YYYY-MM-DD recursively
        throw new UnsupportedOperationException("Not implemented");
    }

    public static double categoryTotalRecursive(Transaction[] arr, String category) {
        // stub: sum matching category recursively
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String generateBudgetReportRecursive(Transaction[] arr) {
        // stub: build report string recursively
        throw new UnsupportedOperationException("Not implemented");
    }
}
