/*
 * Copyright (c) 2018. Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import java.util.function.Function;

public class Benchmark<T> {

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
        double time = 0.0;
        for (int i = 0; i < n; i++) {
            double start = System.nanoTime();
            f.apply(t);
            double diff = System.nanoTime() - start;
            time += diff;
        }
        return time / n;
    }

    private final Function<T, Void> f;

    public static void main(String[] args) {
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing

        Integer[] array = new Integer[1000];

        // create instance of Random class
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            int randInt = rand.nextInt(1000);
            array[i] = randInt;
        }

        int n = 200;

        benchmarkSort(array, n, "SelectionSort", new SelectionSort<>(), m);
        benchmarkSort(array, n, "InsertionSort", new InsertionSort<>(), m);

    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
            Integer[] copiedArray = new Integer[n];
            System.arraycopy(xs, 0, copiedArray, 0, n);
            sorter.sort(copiedArray, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n=" + n);
    }
}
