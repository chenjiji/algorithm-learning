package basicData;

public class ArrayQueue<E extends Comparable<E>> implements Queue {
    private Array array;

    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayQueue() {
        array = new Array<E>();
    }

    @Override
    public void enqueue(Comparable e) {
        array.addLast(e);
    }

    @Override
    public Comparable dequeue() {
        return array.deleteFirst();
    }

    @Override
    public Comparable getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue Capacity: " + array.getCapacity() + ", top [");

        for (int i = 0; i < array.getSize(); i++) {
            if (i < array.getSize() - 1) {
                res.append(array.get(i) + ", ");
            } else {
                res.append(array.get(i) + "] tail");
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue arr = new ArrayQueue(20);

        arr.enqueue(1);
        arr.enqueue(2);
        arr.enqueue(3);
        arr.enqueue(4);

        System.out.println(arr);

        System.out.println("dequeue: " + arr.dequeue());

        System.out.println(arr);

        System.out.println("getFront: " + arr.getFront());

        System.out.println(arr);
    }
}
