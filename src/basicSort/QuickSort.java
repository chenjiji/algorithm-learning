package basicSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private QuickSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    // 插入排序 + 快速排序组合，依照插入排序在趋于有序的数组中性能更好的特点来优化
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
//        if (l >= r) return;

        if (r - l + 1 <= 8) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int p = l;
        E num = arr[p];

        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(num) < 0) {
                p++;
                swap(arr, p, i);
            }
        }

        swap(arr, l, p);

//        System.out.println(format(arr) + " -> " + p);

        return p;
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        Random random = new Random();
        sort2(arr, 0, arr.length - 1, random);
    }

    // 随机标定点优化，解决数组完全有序的情况下，时间复杂度为O(N^2)
    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r, Random random) {
        if (l >= r) return;

        int p = partition2(arr, l, r, random);
        sort2(arr, l, p - 1, random);
        sort2(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);
        p = l;
        int pe = r;
        E num = arr[p];

        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(num) < 0) {
                arr[p++] = arr[i];
            } else {
                arr[pe--] = arr[i];
            }
        }

        arr[p] = num;

//        System.out.println(format(arr) + " -> " + p);

        return p;
    }


    public static <E extends Comparable<E>> void sort2ways(E[] arr) {
        Random random = new Random();
        sort2ways(arr, 0, arr.length - 1, random);
    }

    // 双路快速排序
    private static <E extends Comparable<E>> void sort2ways(E[] arr, int l, int r, Random random) {
        if (l >= r) return;

        int p = partition3(arr, l, r, random);
        sort2ways(arr, l, p - 1, random);
        sort2ways(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition3(E[] arr, int l, int r, Random random) {
        int p = l + random.nextInt(r - l + 1);

        swap(arr, l, p);

        int i = l + 1;
        int j = r;

        while (true) {
            while (i <= j && arr[i].compareTo(arr[l]) < 0)
                i++;

            while (j >= i && arr[j].compareTo(arr[l]) > 0)
                j--;

            if (i >= j) break;

            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);

        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int l, int r) {
        E temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static <E extends Comparable<E>> String format(E[] arr) {
        StringBuilder res = new StringBuilder();

        res.append("[");

        for (int i = 0; i < arr.length; i++) {
            res.append(arr[i]);
            if (i != arr.length - 1) {
                res.append(", ");
            }
        }

        res.append("]");

        return res.toString();
    }

    public static void main(String[] args) {
        int[] dataSize = {50000};

        for (int n : dataSize) {
            Integer[] randomArray = ArrayGenerator.generateRandomArray(n, n);
            System.out.println("randomArray");
            SortingHelper.sortTest("MergeSort", Arrays.copyOf(randomArray, randomArray.length));
            SortingHelper.sortTest("QuickSort2", Arrays.copyOf(randomArray, randomArray.length));
            SortingHelper.sortTest("QuickSort2ways", Arrays.copyOf(randomArray, randomArray.length));
            System.out.println("");
            Integer[] orderedArray = ArrayGenerator.generateOrderedArray(n);
            System.out.println("orderedArray");
            SortingHelper.sortTest("MergeSort", Arrays.copyOf(orderedArray, orderedArray.length));
            SortingHelper.sortTest("QuickSort2", Arrays.copyOf(orderedArray, orderedArray.length));
            SortingHelper.sortTest("QuickSort2ways", Arrays.copyOf(orderedArray, orderedArray.length));

            Integer[] randomArray2 = ArrayGenerator.generateRandomArray(n, 1);
            System.out.println("");
            System.out.println("randomArray2");
            SortingHelper.sortTest("QuickSort2", Arrays.copyOf(randomArray2, randomArray2.length));
            SortingHelper.sortTest("QuickSort2ways", Arrays.copyOf(randomArray2, randomArray2.length));
        }
    }
}
