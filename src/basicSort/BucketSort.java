package basicSort;

public class BucketSort {
    private BucketSort() {
    }

    public static void sort(Integer[] arr, int B) {
        if (B <= 1)
            throw new IllegalArgumentException("B must > 1");

        int len = arr.length;
        Integer[] temp = new Integer[len];
        sort(arr, 0, len - 1, B, temp);
    }

    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if (left >= right)
            return;

        int max = arr[left], min = arr[left];

        for (int i = left + 1; i <= right; i++) {
            int item = arr[i];

            if (item >= max)
                max = item;
            else if (item < min)
                min = item;
        }

        if (max == min)
            return;

        int W = (max - min + 1) / B + ((max - min + 1) % B == 0 ? 0 : 1);
        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for (int i = left; i <= right; i++)
            cnt[(arr[i] - min) / W]++;

        for (int i = 0; i < B; i++)
            index[i + 1] = index[i] + cnt[i];

        for (int i = left; i <= right; i++) {
            int bucketIndex = (arr[i] - min) / W;
            temp[index[bucketIndex] + left] = arr[i];
            index[bucketIndex]++;
        }

        for (int i = left; i <= right; i++)
            arr[i] = temp[i];

        sort(arr, left, index[0] - 1 + left, B, temp);
        for (int i = 0; i < B - 1; i++)
            sort(arr, index[i] + left, index[i + 1] - 1 + left, B, temp);
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("BucketSort", arr);
    }
}
