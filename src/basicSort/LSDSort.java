package basicSort;

import java.util.Arrays;

public class LSDSort {

    private LSDSort() {
    }

    public static void sort(String[] arr, int W) {
        for (String s : arr)
            if (s.length() != W)
                throw new IllegalArgumentException("All Strings' length must be the same.");

        int len = arr.length;
        int R = 256;
        // 存储256个 ASCII(0-255) 码，统计在arr中出现的次数
        int[] cnt = new int[R];
        // 存储每个字符对应的开始坐标
        int[] index = new int[R + 1];
        String[] temp = new String[len];

        for (int i = W - 1; i >= 0; i--) {
            Arrays.fill(cnt, 0);
            // 统计每个字符出现的次数
            for (String s : arr)
                cnt[s.charAt(i)]++;

            /**
             * 通过每个字符的出现次数，计算出对应的开始下标与范围
             * 比如：a(1次), b(2次), c(1次)
             *
             *      => index = [0, 1, 3, 4]
             *      a 起始下标为0，b = a下标 + a的次数 = 1
             *      c = b下标 + b的次数 = 3
             *
             *      => a(范围0-0), b(范围1-2), c(范围3-3)
             *      取值范围 = [起始下标, 下一个字符的起始下标 - 1]
             */
            for (int j = 0; j < R; j++)
                index[j + 1] = index[j] + cnt[j];

            // 进行计数排序，保存至temp
            for (String s : arr) {
                temp[index[s.charAt(i)]] = s;
                index[s.charAt(i)]++;
            }

            // 讲排好序的结果替换至原数据
            for (int j = 0; j < len; j++)
                arr[j] = temp[j];
        }
    }

    public static void main(String[] args) {
        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for (String s : arr)
            System.out.println(s);
    }
}
