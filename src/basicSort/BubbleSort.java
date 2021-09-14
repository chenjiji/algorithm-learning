package basicSort;

import java.util.Arrays;

public class BubbleSort {
    private BubbleSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++)
            for (int j = i + 1; j < len; j++)
                if (arr[i].compareTo(arr[j]) > 0)
                    swap(arr, i, j);
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        int len = arr.length;
        // 用于在内循环遍历，满足换位条件时，记录换位后的下标（初始标记在末项）
        int lastSwapIndex = len - 1;

        /**
         * 优化冒泡排序
         * 每次内循环开始时，设置lastSwapIndex为0，内循环结束时，记录最后一次换位后的下标
         * 如果内循环结束时，lastSwapIndex仍为0，则说明该数组已有序
         * 否则以[0, lastSwapIndex)作为下次内循环的范围
         * （因为每次内循环，最后一次换位后，往后的数据，已然有序，无需再次比较）
         */
        while (lastSwapIndex > 0) {
            int n = lastSwapIndex;

            lastSwapIndex = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    // 记录换位后的下标
                    lastSwapIndex = i + 1;
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] arr) {
        int len = arr.length;
        // 用于在内循环遍历，满足换位条件时，记录换位后的下标（初始标记在首项）
        int lastSwapIndex = 0;
        int lastIndex = len - 1;

        /**
         * 优化冒泡排序
         * 每次内循环开始时，设置lastSwapIndex为末项(lastIndex)，内循环结束时，记录最后一次换位后的下标
         * 如果内循环结束时，lastSwapIndex仍为末项，则说明该数组已有序
         * 否则以(lastSwapIndex, lastIndex]作为下次内循环的范围，从后往前遍历
         * （因为每次内循环，最后一次换位后，往前的数据，已然有序，无需再次比较）
         */
        while (lastSwapIndex < lastIndex) {
            int n = lastSwapIndex;
            lastSwapIndex = lastIndex;

            for (int i = lastIndex; i > n; i--) {
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                    // 记录换位后的下标
                    lastSwapIndex = i - 1;
                }
            }
        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 10000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
        System.out.println();


        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Ordered Array");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
    }
}
