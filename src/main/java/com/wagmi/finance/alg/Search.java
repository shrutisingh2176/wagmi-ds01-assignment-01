package main.java.com.wagmi.finance.alg;

/*
 TODO[Student]: Binary search by Transaction ID
 - Array must be sorted by `Transaction.getId()` ascending.
 - Handle null array (throw NPE) and null id (return -1) per tests.
 - See `SearchTest` for target indices and edge cases.
*/

import main.java.com.wagmi.finance.model.Transaction;

public final class Search {
    private Search() {
    }

    public static int binarySearchById(Transaction[] sortedById, String id) {
        // stub for binary search
        if (sortedById == null) {
            throw new NullPointerException("Array is null");
        }
        if (id == null) {
            return -1;
        }
        int start = 0;
        int end = sortedById.length - 1;
         while (start <= end) {
            int mid = start + (end - start) / 2;

            String midId = sortedById[mid].getId();
            if (midId.equals(id)) {
                return mid;
            } else if (midId.compareTo(id) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return -1;
    }}
