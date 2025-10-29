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

        if (date == null || date.length() != 10) return false;


        if (date.isEmpty()) return true;

        char c = date.charAt(0);
        int index = 10 - date.length();


        if (index == 4 || index == 7) {
            if (c != '-') return false;
        } else {
            if (!Character.isDigit(c)) return false;
        }


        return isValidDateRecursive(date.substring(1));

    }

    public static double categoryTotalRecursive(Transaction[] arr, String category) {
        // stub: sum matching category recursively
        if (arr == null || arr.length == 0 || category == null) return 0;

        // base case: when only one element
        if (arr.length == 1) {
            Transaction t = arr[0];
            if (t != null && category.equalsIgnoreCase(t.getCategory()) && t.getAmount() < 0) {
                return t.getAmount();
            } else {
                return 0;
            }
        }
        Transaction first = arr[0];
        double current = 0;
        if (first != null && category.equalsIgnoreCase(first.getCategory()) && first.getAmount() < 0) {
            current = first.getAmount();
        }
        Transaction[] rest = new Transaction[arr.length - 1];
        System.arraycopy(arr, 1, rest, 0, arr.length - 1);

        return current + categoryTotalRecursive(rest, category);
    }

    public static String generateBudgetReportRecursive(Transaction[] arr) {
        // stub: build report string recursively
        throw new UnsupportedOperationException("Not implemented");
    }
}
