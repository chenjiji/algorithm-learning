package basicData;

public class LinkedListQueue<E extends Comparable<E>> implements Queue {
    private Node front;
    private Node tail;
    private int size;

    private class Node {
        public Comparable e;
        public Node next;

        public Node(Comparable e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    @Override
    public void enqueue(Comparable e) {
        Node node = new Node(e, null);

        if (isEmpty()) {
            front = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }

        size++;
    }

    @Override
    public Comparable dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        Node oldFront = front;
        front = oldFront.next;
        oldFront.next = null;

        if (front == null) {
            tail = null;
        }

        size--;

        return oldFront.e;
    }

    @Override
    public Comparable getFront() {
        return front.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("Queue: front ");

        for (Node i = front; i != null; i = i.next) {
            res.append(i.e + "->");
        }

        res.append("NULL tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue list = new LinkedListQueue();

        for (int i = 0; i < 10; i++) {
            list.enqueue(i);
            System.out.println(list);

            if (i % 3 == 2) {
                list.dequeue();
                System.out.println(list);
            }
        }
    }
}
