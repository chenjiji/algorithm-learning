package basicData;

public class LoopQueue<E extends Comparable<E>> implements Queue {
    private int front;
    private int tail;
    private int size;
    private Comparable[] data;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = new Comparable[capacity + 1];
        size = 0;
        tail = 0;
        front = 0;
    }

    @Override
    public void enqueue(Comparable e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public Comparable dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("出队失败，循环队列为空.");
        }

        Comparable res = data[front];

        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (getCapacity() / 4 == size && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return res;
    }

    @Override
    public Comparable getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    public void resize(int newCapacity) {
        Comparable[] newData = new Comparable[newCapacity + 1];

        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        front = 0;
        tail = size;
        data = newData;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue Capacity: " + getCapacity() + ", Size: " + size + ", front [");

        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);

            if (tail != (i + 1) % data.length) {
                res.append(", ");
            }
        }

        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue arr = new LoopQueue(5);

        for (int i = 0; i < 10; i++) {
            arr.enqueue(i);
            System.out.println(arr);

            if (i % 3 == 2) {
                arr.dequeue();
                System.out.println(arr);
            }
        }
    }
}
