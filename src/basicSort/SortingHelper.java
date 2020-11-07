package basicSort;

public class SortingHelper {

    private SortingHelper() {

    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {

        long startTime = System.nanoTime();

        if (sortName.equals("basicSearch.SelectionSort")) {
            SelectionSort.sort(arr);
        } else if (sortName.equals("InsertionSort2")) {
            InsertionSort.sort2(arr);
        } else {
            InsertionSort.sort(arr);
        }

        long endTime = System.nanoTime();

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(sortName + " failed");
        }

        System.out.println(String.format("%s, n=%d : %fs", sortName, arr.length, (endTime - startTime) / 1000000000.0));
    }
}
