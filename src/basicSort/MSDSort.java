package basicSort;

import java.util.Arrays;

public class MSDSort {

    private MSDSort() {
    }

    public static void sort(String[] arr) {
        int len = arr.length;
        String[] temp = new String[len];

        sort(arr, 0, len - 1, 0, temp);
    }

    // 根据 r 位置的字符，处理 arr[left, right]
    private static void sort(String[] arr, int left, int right, int r, String[] temp) {
        if (left >= right)
            return;

        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];

        for (int i = left; i <= right; i++)
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)]++;

        for (int i = 0; i < R; i++)
            index[i + 1] = index[i] + cnt[i];

        for (int i = left; i <= right; i++) {
            int idx = r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1);
            temp[index[idx] + left] = arr[i];
            index[idx]++;
        }

        // 循环赋值
        for (int i = left; i <= right; i++)
            arr[i] = temp[i];

        // 循环递归
        for (int i = 1, j = R + 1; i < j; i++)
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
    }

    public static void main(String[] args) {
//        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
//        MSDSort.sort(arr);
//        for (String s : arr)
//            System.out.println(s);

        int n = 10000000, bound = 20;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, bound);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("QuickSort2Ways", arr);
        SortingHelper.sortTest("MSDSort", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);
    }
}
