package basicSort;

import basicData.MaxHeap;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    private HeapSort() {
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>(testData.length);
            for (int num : testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < testData.length; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data)
            maxHeap.add(e);

        for (int i = data.length - 1; i >= 0; i--)
            data[i] = maxHeap.extractMax();
    }

    public static <E extends Comparable<E>> void sort2(E[] data) {
        int len = data.length;

        if (len <= 1) return;

        // heapify
        for (int i = (len - 1 - 1) / 2; i >= 0; i--)
            siftDown(data, i, len - 1);

        // sort
        for (int i = len - 1; i > 0; i--) {
            swap(data, 0, i);
            siftDown(data, 0, i - 1);
        }
    }

    // 对 data[0, n] 所形成的最大堆中，索引 k 的元素，执行 siftDown
    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int n) {
        // 2 * k + 1 = leftChildIndex
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            // j = leftChildIndex，j + 1 = rightChildIndex，如果right比left大，j++，选right与当前节点比较，反正选left
            if (j + 1 <= n && data[j + 1].compareTo(data[j]) > 0)
                j++;

            // 如果当前值大于等于子节点中的最大值，就说明已整理好顺序
            if (data[k].compareTo(data[j]) >= 0)
                break;

            // 否则调整两个节点的位置，将K值更新，接着往其子集做siftDown
            swap(data, k, j);
            k = j;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort2ways", arr2);
        SortingHelper.sortTest("QuickSort3ways", arr3);
        SortingHelper.sortTest("HeapSort", arr4);
        SortingHelper.sortTest("HeapSort2", arr5);

//        int n = 1000000;
//
//        Random random = new Random();
//        Integer[] testData1 = new Integer[n];
//        for (int i = 0; i < n; i++)
//            testData1[i] = random.nextInt(Integer.MAX_VALUE);
//
//        Integer[] testData2 = Arrays.copyOf(testData1, n);
//
//        double time1 = testHeap(testData1, false);
//        System.out.println("Without heapify: " + time1 + " s");
//
//        double time2 = testHeap(testData2, true);
//        System.out.println("With heapify: " + time2 + " s");
    }
}
