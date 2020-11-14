package basicData;

public class LinkedListR<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        if (index == 0) {
            Node node = new Node(e, head);
            head = node;
        } else {
            Node prev = get(head, index - 1);
            prev.next = new Node(e, prev.next);
        }

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public boolean contains(E e) {
        return get(head, e) != null;
    }

    public Comparable get(int index) {
        Node node = get(head, index);
        return node == null ? null : node.e;
    }

    private Node get(Node head, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        if (head == null) {
            return null;
        }

        if (index == 0) {
            return head;
        }

        return get(head.next, index - 1);
    }

    private Node get(Node head, E e) {
        if (head == null) {
            return null;
        }

        if (head.e == e) {
            return head;
        }

        return get(head.next, e);
    }

    public Comparable getFirst() {
        return get(0);
    }

    public Comparable getLast() {
        return get(size - 1);
    }

    public int getSize() {
        return size;
    }

    public void set(int index, E e) {
        Node res = get(head, index);

        if (res != null) {
            res.e = e;
        }
    }

    public Comparable remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Comparable res = null;

        if (index == 0) {
            res = head.e;
            head = head.next;
        } else {
            Node prev = get(head, index - 1);
            Node current = prev.next;
            res = current.e;
            prev.next = current.next;
            current.next = null;
        }

        size--;

        return res;
    }

    public Comparable removeFirst() {
        return remove(0);
    }

    public Comparable removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node current = head; current != null; current = current.next) {
            res.append(current.e + "->");
        }

        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListR<Integer> data = new LinkedListR<>();

        for (int i = 0; i < 5; i++) {
            data.addFirst(i);
            System.out.println(data);
        }

        data.add(2, 666);
        data.remove(2);
        System.out.println(data);
    }
}
