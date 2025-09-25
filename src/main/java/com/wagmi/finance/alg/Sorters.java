package main.java.com.wagmi.finance.alg;

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
