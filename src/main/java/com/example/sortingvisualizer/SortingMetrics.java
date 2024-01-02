package com.example.sortingvisualizer;

public class SortingMetrics {

    private long timeTaken;
    private int interchanges;
    private int comparisons;

    public SortingMetrics() {
        this.timeTaken = 0;
        this.interchanges = 0;
        this.comparisons = 0;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getInterchanges() {
        return interchanges;
    }

    public void incrementInterchanges() {
        this.interchanges++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void incrementComparisons() {
        this.comparisons++;
    }
}
