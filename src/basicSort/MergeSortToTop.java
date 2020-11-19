package basicSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * 归并排序
 */
public class MergeSortToTop {
    private MergeSortToTop() {

    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int len = arr.length;
        E[] temp = Arrays.copyOf(arr, len);
        int o = 16;

        for (int j = 0; j < len; j += o) {
            InsertionSort.sort(arr, j, Math.min(j + o - 1, len - 1));
        }

        for (int size = o; size < len; size += size) {
            for (int i = 0; i + size < len; i += size + size) {

//                System.out.println("Before " + format(arr) + "  l: " + i + ", mid: " + (i + size - 1) + ", r: " + Math.min(i + size + size - 1, len - 1) + "\n");

                if (arr[i + size - 1].compareTo(arr[i + size]) > 0) {
                    merge(arr, i, i + size - 1, Math.min(i + size + size - 1, len - 1), temp);
                }
            }
        }
    }

    public static String getRandomString(int length) {
        @SuppressWarnings("SpellCheckingInspection")
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
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

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }


    public static void main(String[] args) {
//        int[] dataSize = {9, 10, 11, 13};
        int[] dataSize = {100000};

        for (int n : dataSize) {
            Integer[] randomList = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("MergeSortToTop", randomList);
            SortingHelper.sortTest("MergeSort", randomList);
//            MergeSortToTop.sort(randomList);
//            System.out.println(format(randomList));
//            System.out.println("----------------------------------------");
        }


    }
}
