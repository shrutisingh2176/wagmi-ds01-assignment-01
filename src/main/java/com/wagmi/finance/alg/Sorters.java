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
        if (arr == null || arr.length <= 1) return;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != null && arr[j] != null) {
                    if (arr[i].getAmount() > arr[j].getAmount()) {
                        Transaction temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    public static void mergeSortByDate(Transaction[] arr) {
        // stub for mergesort
        if (arr == null || arr.length <= 1) return;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] != null && arr[j + 1] != null) {
                    if (arr[j].getDate().compareTo(arr[j + 1].getDate()) > 0) {
                        Transaction temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void insertionSortByCategory(Transaction[] arr, Comparator<String> categoryComparator) {
        // stub for insertion sort by category string
        if (arr == null || arr.length <= 1) return;

        for (int i = 1; i < arr.length; i++) {
            Transaction key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] != null && key != null &&
                    categoryComparator.compare(arr[j].getCategory(), key.getCategory()) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
    }
}
