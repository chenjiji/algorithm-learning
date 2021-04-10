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

    // > target 的最小值索引
    public static <E extends Comparable<E>> int upper(E[] data, E target) {

        int l = 0, r = data.length;

        // 在 data[l, r] 中寻找解
        while (l < r) {

            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    // > target ，返回最小值索引
    // == target，返回最大索引
    public static <E extends Comparable<E>> int ceil(E[] data, E target) {

        int u = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0)
            return u - 1;
        return u;
    }

    // >= target 的最小值索引
    public static <E extends Comparable<E>> int lower_ceil(E[] data, E target) {

        int l = 0, r = data.length;

        // 在 data[l, r] 中寻找解
        while (l < r) {

            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) < 0)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    // < target 的最大值索引
    public static <E extends Comparable<E>> int lower(E[] data, E target) {

        int l = -1, r = data.length - 1;

        // 在 data[l, r] 中寻找解
        while (l < r) {
//            System.out.println(l + " " + r);
            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) < 0)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    // < target ，返回最大值索引
    // == target，返回最小索引
    public static <E extends Comparable<E>> int lower_floor(E[] data, E target) {

        int l = lower(data, target);
        if (l + 1 < data.length && data[l + 1].compareTo(target) == 0)
            return l + 1;
        return l;
    }

    // <= target 最大索引
    public static <E extends Comparable<E>> int upper_floor(E[] data, E target) {

        int l = -1, r = data.length - 1;

        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) <= 0)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    // >= target 最小索引
    public static <E extends Comparable<E>> boolean isContains(E[] arr, E target) {
        int l = 0;
        int r = arr.length;
        int m;

        while (l < r) {
            m = l + (r - l) / 2;

            if (arr[m].compareTo(target) >= 0)
                r = m;
            else
                l = m + 1;
        }

        return l > -1 && l < arr.length && arr[l].compareTo(target) == 0;
    }

    public static void main(String[] args) {
//        Integer[] arr1 = ArrayGenerator.generateOrderedArray(100000);
//        int targetIndex = BinarySearch.search(arr1, 12345);
//        System.out.println(targetIndex + ": " + arr1[targetIndex]);


//        Integer[] arr2 = ArrayGenerator.generateOrderedArray(100);
//        System.out.println(BinarySearch.upper(arr2, 60));


//        Integer[] arr3 = new Integer[]{1, 1, 2, 2, 2, 3, 3, 3, 3};
//        System.out.println(BinarySearch.upper(arr3, 3));


//        Integer[] arr4 = new Integer[]{1, 1, 3, 3, 5, 5};
//        for (int i = 0; i <= 6; i++)
//            System.out.print(BinarySearch.lower(arr4, i) + " ");
//        System.out.println("");


//        Integer[] arr5 = new Integer[]{1, 1, 3, 3, 5, 5};
//        for (int i = 0; i <= 6; i++)
//            System.out.print(BinarySearch.lower_floor(arr5, i) + " ");
//        System.out.println("");


//        Integer[] arr6 = new Integer[]{1, 1, 3, 3, 5, 5};
//        for (int i = 0; i <= 6; i++)
//            System.out.print(BinarySearch.upper_floor(arr6, i) + " ");
//        System.out.println("");

        Integer[] arr7 = new Integer[]{1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++)
            System.out.print(BinarySearch.isContains(arr7, i) + " ");
        System.out.println("");

    }
}
