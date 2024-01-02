package com.example.sortingAlgorithms;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = generateRandomNumbers(10000);
        int[] array2 = generateAscendingNumbers(10000);
        int[] array3 = generateDescendingNumbers(10000);
        // comapre 3 sorting algorithms
        QuickSort quickSort = new QuickSort();
        BubbleSort bubbleSort = new BubbleSort();
        SelectionSort selectionSort = new SelectionSort();
        // random numbers
        quickSort.sort(Arrays.copyOf(array, array.length));
        bubbleSort.sort(Arrays.copyOf(array, array.length));
        selectionSort.sort(Arrays.copyOf(array, array.length));
        System.out.println("Random numbers");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "","Quick Sort", "Bubble Sort", "Selection Sort");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Time(ms)", quickSort.getTime(), bubbleSort.getTime(), selectionSort.getTime());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Comparisons", quickSort.getComparisons(), bubbleSort.getComparisons(), selectionSort.getComparisons());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Interchanges", quickSort.getInterchanges(), bubbleSort.getInterchanges(), selectionSort.getInterchanges());
        System.out.println("---------------------------------------------");
        // ascending numbers
        quickSort.sort(Arrays.copyOf(array2, array2.length));
        bubbleSort.sort(Arrays.copyOf(array2, array2.length));
        selectionSort.sort(Arrays.copyOf(array2, array2.length));
        System.out.println("Sorted numbers");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "","Quick Sort", "Bubble Sort", "Selection Sort");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Time(ms)", quickSort.getTime(), bubbleSort.getTime(), selectionSort.getTime());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Comparisons", quickSort.getComparisons(), bubbleSort.getComparisons(), selectionSort.getComparisons());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Interchanges", quickSort.getInterchanges(), bubbleSort.getInterchanges(), selectionSort.getInterchanges());
        System.out.println("---------------------------------------------");
        // descending numbers
        quickSort.sort(Arrays.copyOf(array3, array3.length));
        bubbleSort.sort(Arrays.copyOf(array3, array3.length));
        selectionSort.sort(Arrays.copyOf(array3, array3.length));
        System.out.println("Reversed numbers");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "","Quick Sort", "Bubble Sort", "Selection Sort");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Time(ms)", quickSort.getTime(), bubbleSort.getTime(), selectionSort.getTime());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Comparisons", quickSort.getComparisons(), bubbleSort.getComparisons(), selectionSort.getComparisons());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Interchanges", quickSort.getInterchanges(), bubbleSort.getInterchanges(), selectionSort.getInterchanges());
        System.out.println("---------------------------------------------");
    }
    // generate 10k random numbers
    public static int[] generateRandomNumbers(int size) {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = (int)(Math.random() * 10000);
        }
        return array;
    }
    // generate 10k numbers in ascending order
    public static int[] generateAscendingNumbers(int size) {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
    // generate 10k numbers in descending order
    public static int[] generateDescendingNumbers(int size) {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
