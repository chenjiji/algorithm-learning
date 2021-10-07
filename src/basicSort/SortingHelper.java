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

        if (sortName.equals("SelectionSort"))
            SelectionSort.sort(arr);
        else if (sortName.equals("InsertionSort2"))
            InsertionSort.sort2(arr);
        else if (sortName.equals("MergeSort"))
            MergeSort.sort(arr);
        else if (sortName.equals("MergeSort2"))
            MergeSort.sort2(arr);
        else if (sortName.equals("MergeSortToTop"))
            MergeSortToTop.sort(arr);
        else if (sortName.equals("QuickSort"))
            QuickSort.sort(arr);
        else if (sortName.equals("QuickSort2"))
            QuickSort.sort2(arr);
        else if (sortName.equals("QuickSort2Ways"))
            QuickSort.sort2ways(arr);
        else if (sortName.equals("QuickSort3Ways"))
            QuickSort.sort3ways(arr);
        else if (sortName.equals("HeapSort"))
            HeapSort.sort(arr);
        else if (sortName.equals("HeapSort2"))
            HeapSort.sort2(arr);
        else if (sortName.equals("BubbleSort"))
            BubbleSort.sort(arr);
        else if (sortName.equals("BubbleSort2"))
            BubbleSort.sort2(arr);
        else if (sortName.equals("BubbleSort3"))
            BubbleSort.sort3(arr);
        else if (sortName.equals("ShellSort"))
            ShellSort.sort(arr);
        else if (sortName.equals("ShellSort2"))
            ShellSort.sort2(arr);
        else if (sortName.equals("LSDSort")) {
            String[] strArr = (String[]) arr;
            if (strArr.length == 0) throw new IllegalArgumentException("arr can not be empty.");
            LSDSort.sort(strArr, strArr[0].length());
        } else if (sortName.equals("MSDSort")) {
            String[] strArr = (String[]) arr;
            MSDSort.sort(strArr);
        } else if (sortName.equals("BucketSort")) {
            Integer[] intArr = (Integer[]) arr;
            BucketSort.sort(intArr, 200);
        } else if (sortName.equals("BucketSort2")) {
            Integer[] intArr = (Integer[]) arr;
            BucketSort.sort2(intArr, 100);
        } else
            InsertionSort.sort(arr);

        long endTime = System.nanoTime();

        if (!SortingHelper.isSorted(arr))
            throw new RuntimeException(sortName + " failed");


        System.out.println(String.format("%s, n=%d : %fs", sortName, arr.length, (endTime - startTime) / 1000000000.0));
    }
}
