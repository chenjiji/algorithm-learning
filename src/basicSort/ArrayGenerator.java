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
