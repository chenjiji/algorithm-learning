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

    public static <E extends Comparable<E>> void sort(E[] list, int l, int r) {
        for (int i = l; i <= r; i++) {
            E t = list[i];
            int j;

            for (j = i; j - 1 >= l && t.compareTo(list[j - 1]) == -1; j--) {
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

        int[] dataSize = {100};

        for (int n : dataSize) {
            Integer[] randomList = ArrayGenerator.generateRandomArray(n, n);
            Integer[] orderedList = ArrayGenerator.generateOrderedArray(n);
            InsertionSort.sort(randomList, 0, randomList.length - 1);

            for (int i : randomList) {
                System.out.print(i + " ");
            }
//            Integer[] copyList = Arrays.copyOf(randomList, randomList.length);
//            System.out.println("random");
//            SortingHelper.sortTest("InsertionSort", randomList);
//            SortingHelper.sortTest("SelectionSort", Arrays.copyOf(randomList, randomList.length));
//            System.out.println("ordered");
//            SortingHelper.sortTest("InsertionSort", orderedList);
//            SortingHelper.sortTest("SelectionSort", Arrays.copyOf(orderedList, orderedList.length));
//            basicSort.SortingHelper.sortTest("InsertionSort2", copyList);
        }
    }
}
