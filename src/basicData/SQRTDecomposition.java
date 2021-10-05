package basicData;

public class SQRTDecomposition<E> {
    private E[] sums;
    private E[] nums;
    private int sqrtNum;
    private Merger<E> merger; // 合并函数 merger

    public SQRTDecomposition(E[] arr, Merger<E> merger) {
        int len = arr.length;
        this.merger = merger;

        sqrtNum = (int) Math.sqrt(len);
        this.nums = (E[]) new Object[len];
        sums = (E[]) new Object[len + 1];

        for (int i = 0, j = 0, k = 0; i < len; i++, k++) {
            if (k >= sqrtNum) {
                k = 0;
                j++;
            }

            this.nums[i] = arr[i];

            if (i % sqrtNum == 0)
                sums[j] = arr[i];
            else
                sums[j] = merger.merge(sums[j], arr[i]);
        }
    }

    public void update(int index, E val) {
        nums[index] = val;
        int bIndex = index / sqrtNum;

        for (int i = bIndex * sqrtNum, j = i + sqrtNum; i < j; i++)
            sums[bIndex] = merger.merge(sums[bIndex], nums[i]);
    }

    public E sumRange(int left, int right) {
        E res = nums[left];
        int bStart = left / sqrtNum;
        int bEnd = right / sqrtNum;

        if (bStart == bEnd) {
            for (int i = left + 1; i <= right; i++)
                res = merger.merge(res, nums[i]);

            return res;
        }

        for (int i = left + 1, j = (bStart + 1) * sqrtNum; i < j; i++)
            res = merger.merge(res, nums[i]);

        for (int i = bStart + 1; i < bEnd; i++)
            res = merger.merge(res, sums[i]);

        for (int i = bEnd * sqrtNum; i <= right; i++)
            res = merger.merge(res, nums[i]);

        return res;
    }

    public static void main(String[] args) {
        SQRTDecomposition<Integer> numArray = new SQRTDecomposition<>(new Integer[]{-2, 0, 3, -5, 2, -1}, (a, b) -> a + b);

        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
    }
}
