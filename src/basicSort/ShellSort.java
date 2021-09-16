package basicSort;

import java.util.Arrays;

public class ShellSort {
    // 优化版希尔排序
    public static <E extends Comparable<E>> void sort(E[] arr) {
        int len = arr.length;
        int interval = len / 2;

        while (interval >= 1) {
            for (int i = interval; i < len; i++) {
                E item = arr[i];
                int j = i;

                for (; j - interval >= 0 && item.compareTo(arr[j - interval]) < 0; j -= interval)
                    arr[j] = arr[j - interval];

                arr[j] = item;
            }

            interval /= 2;
        }
    }

    // 希尔排序，步长序列
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        int len = arr.length;
        int interval = 1;

        while (interval < len) interval = interval * 3 + 1;

        while (interval >= 1) {
            for (int i = interval; i < len; i++) {
                E item = arr[i];
                int j = i;

                for (; j - interval >= 0 && item.compareTo(arr[j - interval]) < 0; j -= interval)
                    arr[j] = arr[j - interval];

                arr[j] = item;
            }

            interval /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = arr.clone();

        System.out.println("Random Array");
        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("ShellSort2", arr2);
        System.out.println();

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = arr.clone();

        System.out.println("Ordered Array");
        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("ShellSort2", arr2);
    }
}
