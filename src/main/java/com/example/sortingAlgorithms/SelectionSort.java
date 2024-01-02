package com.example.sortingAlgorithms;

public class SelectionSort {
    private int comparisons;
    private int interchanges;
    private long time;

    public void sort(int[] array) {
        comparisons = 0;
        interchanges = 0;
        long start = System.currentTimeMillis();
        int n = array.length;
        int temp;
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = i + 1; j < n; j++) {
                comparisons++;
                if(array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i){
                interchanges++;
                temp = array[min];
                array[min] = array[i];
                array[i] = temp;
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
