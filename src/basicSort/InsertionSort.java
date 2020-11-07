package basicSort;

import java.util.Arrays;

public class InsertionSort {

    private InsertionSort() {

    }

    public static <E extends Comparable<E>> void sort2(E[] list) {
        for (int i = 1; i < list.length; i++) {
            for (int j = i; j > 0 && list[j].compareTo(list[j - 1]) == -1; j--) {
                InsertionSort.swap(list, j, j - 1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] list) {
        for (int i = 1; i < list.length; i++) {
            E t = list[i];
            int j;

            for (j = i; j > 0 && t.compareTo(list[j - 1]) == -1; j--) {
                list[j] = list[j - 1];
            }

            list[j] = t;
        }
    }

    private static <E> void swap(E[] arr, int source, int target) {
        E temp = arr[source];
        arr[source] = arr[target];
        arr[target] = temp;
    }

    public static void main(String[] args) {

        int[] dataSize = {1000, 10000};

        for (int n : dataSize) {
            Integer[] randomList = ArrayGenerator.generateRandomArray(n, n);
            Integer[] orderedList = ArrayGenerator.generateOrderedArray(n);
//            Integer[] copyList = Arrays.copyOf(randomList, randomList.length);
            System.out.println("random");
            SortingHelper.sortTest("basicSearch.InsertionSort", randomList);
            SortingHelper.sortTest("basicSearch.SelectionSort", Arrays.copyOf(randomList, randomList.length));
            System.out.println("ordered");
            SortingHelper.sortTest("basicSearch.InsertionSort", orderedList);
            SortingHelper.sortTest("basicSearch.SelectionSort", Arrays.copyOf(orderedList, orderedList.length));
//            basicSearch.SortingHelper.sortTest("InsertionSort2", copyList);
        }
    }
}
