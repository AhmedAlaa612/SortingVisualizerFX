package com.example.sortingAlgorithms;

public class QuickSort {
    private int comparisons;
    private int interchanges;
    private long time;

    public void sort(int[] array) {
        comparisons = 0;
        interchanges = 0;
        long start = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        time = end - start;
    }

    private void quickSort(int[] array, int low, int high) {
        if(low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int left = low;
        int right = high;

        while (left <= right) {
            while (array[left] < pivot) {
                left++;
                comparisons++;
            }

            while (array[right] > pivot) {
                right--;
                comparisons++;
            }

            if (left <= right) {
                if (left != right) {
                    interchanges++;
                    int temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }
                left++;
                right--;
            }
        }

        return left;
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
