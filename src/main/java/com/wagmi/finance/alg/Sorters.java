package main.java.com.wagmi.finance.alg;

/*
 TODO[Student]: Sorting algorithms
 - Implement Quick Sort by amount (ascending).
 - Implement Merge Sort by date (ascending), stable.
 - Implement Insertion Sort by category using provided comparator.
 - See `SortersTest` for exact expectations and edge cases.
*/

import java.util.Comparator;

import main.java.com.wagmi.finance.model.Transaction;

public final class Sorters {
    private Sorters() {
    }

    public static void quickSortByAmount(Transaction[] arr) {
        // stub for quicksort
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void mergeSortByDate(Transaction[] arr) {
        // stub for mergesort
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void insertionSortByCategory(Transaction[] arr, Comparator<String> categoryComparator) {
        // stub for insertion sort by category string
        throw new UnsupportedOperationException("Not implemented");
    }
}
