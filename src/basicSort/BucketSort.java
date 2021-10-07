package basicSort;

import basicData.MaxHeap;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    private BucketSort() {
    }

    public static void sort(Integer[] arr, int B) {
        if (B <= 1)
            throw new IllegalArgumentException("B must > 1");

        int len = arr.length;
        Integer[] temp = new Integer[len];
        sort(arr, 0, len - 1, B, temp);
    }

    // 简单桶排序
    public static void sort2(Integer[] arr, int c) {
        if (c <= 0)
            throw new IllegalArgumentException("c must be > 0");

        int max = arr[0], min = arr[0];

        for (int item : arr) {
            max = Math.max(max, item);
            min = Math.min(min, item);
        }

        int range = max - min + 1;
        int B = range / c + (range % c == 0 ? 0 : 1);
        LinkedList<Integer>[] buckets = new LinkedList[B];

        for (int i = 0; i < B; i++)
            buckets[i] = new LinkedList<>();

        for (int item : arr) {
            buckets[(item - min) / c].add(item);
        }

        for (LinkedList<Integer> bucket : buckets)
            Collections.sort(bucket);

        int index = 0;
        for (LinkedList<Integer> bucket : buckets)
            for (Integer item : bucket)
                arr[index++] = item;
    }

    // 基于MSD的桶排序
    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if (left >= right)
            return;

        int max = arr[left], min = arr[left];

        for (int i = left + 1; i <= right; i++) {
            int item = arr[i];

            if (item >= max)
                max = item;
            else if (item < min)
                min = item;
        }

        if (max == min)
            return;

        int W = (max - min + 1) / B + ((max - min + 1) % B == 0 ? 0 : 1);
        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for (int i = left; i <= right; i++)
            cnt[(arr[i] - min) / W]++;

        for (int i = 0; i < B; i++)
            index[i + 1] = index[i] + cnt[i];

        for (int i = left; i <= right; i++) {
            int bucketIndex = (arr[i] - min) / W;
            temp[index[bucketIndex] + left] = arr[i];
            index[bucketIndex]++;
        }

        for (int i = left; i <= right; i++)
            arr[i] = temp[i];

        sort(arr, left, index[0] - 1 + left, B, temp);
        for (int i = 0; i < B - 1; i++)
            sort(arr, index[i] + left, index[i + 1] - 1 + left, B, temp);
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("BucketSort", arr);
        SortingHelper.sortTest("BucketSort2", arr2);
    }
}
