package basicData;

public class LinkedList<E> {
    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public boolean contains(E e) {
        Node current = dummyHead.next;

        for (int i = 0; i < size; i++) {
            if (current.e.equals(e)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node current = dummyHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public int getSize() {
        return size;
    }

    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node current = dummyHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.e = e;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node current = dummyHead;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node next = current.next;
        current.next = next.next;
        E res = next.e;
        next = null;
        size--;

        return res;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node current = dummyHead.next; current != null; current = current.next) {
            res.append(current.e + "->");
        }

        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> data = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            data.addFirst(i);
            System.out.println(data);
        }

        data.add(2, 666);
        data.remove(2);
        System.out.println(data);
    }
}
