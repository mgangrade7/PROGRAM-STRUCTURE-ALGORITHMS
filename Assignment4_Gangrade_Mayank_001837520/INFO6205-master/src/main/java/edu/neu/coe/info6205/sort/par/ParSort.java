package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) {
            Arrays.sort(array, from, to);
        } else {
            int mid = from + (to - from) / 2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid);

            CompletableFuture<int[]> parsort2 = parsort(array, mid, to);

            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TODO implement me
                        int i = 0, j = 0, k = 0;

                        while (i < xs1.length && j < xs2.length) {
                            result[k++] = xs1[i] < xs2[j] ? xs1[i++] : xs2[j++];
                        }

                        while (i < xs1.length) {
                            result[k++] = xs1[i++];
                        }

                        while (j < xs2.length) {
                            result[k++] = xs2[j++];
                        }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
                for (int i = from, j = 0; i < to; i++) {
                    array[i] = result[j];
                    j++;
                }
                //System.out.println("When Complete : " + Arrays.toString(result));
                //System.out.println("Error : " + throwable);
            });
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];

                    for (int i = from, j = 0; i < to; i++) {
                        result[j] = array[i];
                        j++;
                    }
                    sort(result, 0, result.length);
                    return result;
                }
        );
    }

}
