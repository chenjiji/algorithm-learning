package basicSort;

public class SelectionSort {

    private SelectionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (list[j].compareTo(list[minIndex]) == -1) {
                    minIndex = j;
                }
            }

            SelectionSort.swap(list, i, minIndex);
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
            SortingHelper.sortTest("SelectionSort", randomList);
        }
    }
}
