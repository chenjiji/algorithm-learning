package basicData;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);

        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){
        E ret = data.getFirst();
        data.set(0, e);
        siftDown(0);

        return ret;
    }

    public int size() {
        return data.getSize();
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");

        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);

        siftUp(data.getSize() - 1);
    }

    // 上浮
    private void siftUp(int index) {
        if (index > 0) {
            int parentIndex = parent(index);
            E parent = data.get(parentIndex);
            E current = data.get(index);

            if (current.compareTo(parent) > 0) {
                data.swap(parentIndex, index);
                siftUp(parentIndex);
            }
        }
    }

    // 下沉
    private void siftDown(int index) {
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int downIndex;
        int size = size();

        E leftChild = leftIndex > 0 && leftIndex < size ? data.get(leftIndex) : null;
        E rightChild = rightIndex > 0 && rightIndex < size ? data.get(rightIndex) : null;

        if (leftChild == null && rightChild != null)
            downIndex = rightIndex;
        else if (leftChild != null && rightChild == null)
            downIndex = leftIndex;
        else if (leftChild != null && rightChild != null)
            downIndex = leftChild.compareTo(rightChild) > 0 ? leftIndex : rightIndex;
        else
            return;

        if (data.get(downIndex).compareTo(data.get(index)) > 0) {
            data.swap(downIndex, index);
            siftDown(downIndex);
        }
    }

    public E extractMax() {
        E max = data.getFirst();

        data.swap(0, size() - 1);
        data.deleteLast();
        siftDown(0);

        return max;
    }

    public static void main(String[] args) {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();

        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }
}
