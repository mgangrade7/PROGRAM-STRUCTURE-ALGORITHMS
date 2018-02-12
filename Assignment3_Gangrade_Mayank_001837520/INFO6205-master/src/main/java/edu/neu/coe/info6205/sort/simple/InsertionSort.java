package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.*;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement insertionSort

        int n = xs.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && Helper.less(xs[j], xs[j - 1]); j--) {
                Helper.swap(xs, j, j - 1);
            }

        }
    }
}
