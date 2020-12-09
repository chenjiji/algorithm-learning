package basicSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * 归并排序
 */
public class MergeSort {
    private MergeSort() {

    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        HashMap<String, String> logs = new HashMap<>();

        sort(arr, 0, arr.length - 1, logs, 0);

        for (String key : logs.keySet()) {
            System.out.println(logs.get(key));
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r, HashMap<String, String> logs, int depth) {
        if (l >= r) {
            return;
        }

        String key = depth + "";
        int mid = l + (r - l) / 2;

//        logs.put(key, (logs.get(key) == null ? "" : logs.get(key)) + "Before " + format(arr) + "  l: " + l + ", mid: " + mid + ", r: " + r + "\n");

        sort(arr, l, mid, logs, depth + 1);
        sort(arr, mid + 1, r, logs, depth + 1);
        merge(arr, l, mid, r);

//        logs.put(key, (logs.get(key) == null ? "" : logs.get(key)) + "After  " + format(arr) + "\n");
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        HashMap<String, String> logs = new HashMap<>();
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort2(arr, 0, arr.length - 1, logs, 0, temp);

        for (String key : logs.keySet()) {
            System.out.println(logs.get(key));
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr, int l, int r, HashMap<String, String> logs, int depth, E[] temp) {
        /**
         *  此处可以用以下方式优化，此优化方式仅适用于非脚本语言，借助插入排序趋于有序的数组排序性能更高的方式来优化
         *  if (r - l <= 16) {
         *      // 使用InsertionSort来进行小范围排序
         *      return;
         *  }
         */
        if (l >= r) {
            return;
        }

        String key = depth + "";
        int mid = l + (r - l) / 2;

        sort2(arr, l, mid, logs, depth + 1, temp);
        sort2(arr, mid + 1, r, logs, depth + 1, temp);

        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge2(arr, l, mid, r, temp);
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

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
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
        int[] dataSize = {5000000};

        for (int n : dataSize) {
            Integer[] randomList = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("MergeSort", randomList);
            SortingHelper.sortTest("MergeSort2", randomList);
        }
    }
}
