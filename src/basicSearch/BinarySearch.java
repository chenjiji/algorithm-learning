package basicSearch;

import basicSort.ArrayGenerator;

public class BinarySearch {
    private BinarySearch() {
    }

    // 非递归方式实现二分查找
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int res = -1;
        int l = 0;
        int r = arr.length - 1;
        int mid;

        while (l <= r) {
            mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) == 0) {
                res = mid;
                break;
            } else if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    public static <E extends Comparable<E>> int upper(E[] arr, E target) {
        int r = arr.length;
        int l = 0;
        int mid;

        while (l < r) {
            mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) > 0)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    // 递归方式实现二分查找
    public static <E extends Comparable<E>> int searchR(E[] arr, E target) {
        return searchR(arr, target, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> int searchR(E[] arr, E target, int l, int r) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;

        if (arr[mid].compareTo(target) == 0)
            return mid;
        else if (arr[mid].compareTo(target) < 0)
            return searchR(arr, target, mid + 1, r);
        else
            return searchR(arr, target, l, mid - 1);
    }

    public static void main(String[] args) {
//        Integer[] arr1 = ArrayGenerator.generateOrderedArray(100000);
//
//        int targetIndex = BinarySearch.search(arr1, 12345);
//
//        System.out.println(targetIndex + ": " + arr1[targetIndex]);

        Integer[] arr2 = ArrayGenerator.generateOrderedArray(100);

        int res = BinarySearch.upper(arr2, 60);

        System.out.println(res);
    }
}
