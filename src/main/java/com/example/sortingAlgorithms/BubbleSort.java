package com.example.sortingAlgorithms;

public class BubbleSort {
    private int comparisons;
    private int interchanges;
    private long time;

    public void sort(int[] array) {
        comparisons = 0;
        interchanges = 0;
        long start = System.currentTimeMillis();
        int n = array.length;
        int temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;

                if (array[j] > array[j + 1]) {
                    interchanges++;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps were made in this pass, the array is already sorted
            if (!swapped) {
                break;
            }
        }

        long end = System.currentTimeMillis();
        time = end - start;
    }


    public int getComparisons() {
        return comparisons;
    }

    public int getInterchanges() {
        return interchanges;
    }

    public long getTime() {
        return time;
    }
}
