package basicSort;

import java.util.Random;

public class ArrayGenerator {

    public ArrayGenerator() {

    }

    public static Integer[] generateOrderedArray(int len) {
        Integer[] list = new Integer[len];

        for (int i = 0; i < len; i++) {
            list[i] = i;
        }

        return list;
    }

    public static Integer[] generateRandomArray(int len, int bound) {
        Integer[] list = new Integer[len];

        for (int i = 0; i < len; i++) {
            list[i] = new Random().nextInt(bound);
        }

        return list;
    }

    public static String[] generateRandomSameLengthStringArray(int n, int w) {

        // https://www.ascii-code.com/
        // 33-126 可打印字符
        String[] arr = new String[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < w; j++)
                sb.append((char) (rnd.nextInt(94) + 33));
            arr[i] = sb.toString();
        }
        return arr;
    }

    public static String[] generateRandomStringArray(int n, int bound) {

        // https://www.ascii-code.com/
        // 33-126 可打印字符
        String[] arr = new String[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int w = rnd.nextInt(bound);
            for (int j = 0; j < w; j++)
                sb.append((char) (rnd.nextInt(94) + 33));
            arr[i] = sb.toString();
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] orderedList = ArrayGenerator.generateOrderedArray(10);
        Integer[] randomList = ArrayGenerator.generateRandomArray(10, 10);

        for (int i = 0; i < orderedList.length; i++) {
            System.out.print(orderedList[i] + " ");
        }

        System.out.println("");

        for (int i = 0; i < orderedList.length; i++) {
            System.out.print(randomList[i] + " ");
        }
    }
}
