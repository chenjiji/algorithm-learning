package basicData;

import basicSort.Student;

public class Array<E extends Comparable<E>> {

    private int size;
    private E[] data;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        this.size = 0;
        this.data = (E[]) new Comparable[capacity];
    }

    public Array(E[] arr) {
        size = arr.length;
        data = arr;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void push(E e) {
        insert(size, e);
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void insert(int index, E e) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Insert Failed. Index must be >= 0 && < Array.length.");
        }

        if (size >= data.length) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        insert(0, e);
    }

    public E get(int index) {
        if (index > size - 1 || index < 0) {
            throw new RuntimeException("Get failed. Index is illegal.");
        }

        return data[index];
    }

    public void set(int index, E e) {
        if (index > size - 1 || index < 0) {
            throw new RuntimeException("Set failed. Index is illegal.");
        }

        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].compareTo(e) == 0) {
                return true;
            }
        }

        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].compareTo(e) == 0) {
                return i;
            }
        }

        return -1;
    }

    public E delete(int index) {
        if (index > size - 1 || index < 0) {
            throw new RuntimeException("Set failed. Index is illegal.");
        }

        E delElem = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        data[size] = null;

        if (size <= data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return delElem;
    }

    public E getFirst() {
        if (size == 0)
            throw new IllegalArgumentException("data is null");

        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E deleteFirst() {
        return delete(0);
    }

    public void addLast(E e) {
        insert(size, e);
    }

    public E deleteLast() {
        return delete(size - 1);
    }

    public boolean deleteElement(E e) {
        int elemIndex = find(e);

        if (elemIndex == -1) {
            return false;
        }

        delete(elemIndex);

        return true;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(String.format("size: %d, capacity: %d, [", size, data.length));

        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                str.append(data[i] + ",");
            } else {
                str.append(data[i] + "]");
            }
        }

        return str.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public int getCapacity() {
        return data.length;
    }

    public static void main(String[] args) {

        Array<Student> arr = new Array();

        arr.push(new Student("无名", 1));
        arr.push(new Student("无名", 2));
        arr.push(new Student("无名", 3));
        arr.push(new Student("无名", 5));
        arr.insert(3, new Student("无名", 4));
        arr.addFirst(new Student("无名", 0));
        arr.push(new Student("无名", 7));
//        System.out.println("isEmpty: " + arr.isEmpty());
//        System.out.println("getSize: " + arr.getSize());
//        System.out.println("get: " + arr.get(6));
        arr.set(6, new Student("无名", 6));

//        System.out.println(arr);
//
//        System.out.println("find: " + arr.find(new Student("无名", 5)));
//        System.out.println("contains: " + arr.contains(new Student("无名", 5)));
//
//        System.out.println("delete: " + arr.delete(3));
//        System.out.println(arr);
//        System.out.println("deleteElement: " + arr.deleteElement(new Student("无名", 5)));

        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));

        System.out.println(arr);

        arr.deleteElement(new Student("无名", 5));
        arr.deleteElement(new Student("无名", 5));
        arr.deleteElement(new Student("无名", 5));

        System.out.println(arr);

        arr.push(new Student("无名", 5));
        arr.push(new Student("无名", 5));

        System.out.println(arr);
    }
}
