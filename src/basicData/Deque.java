package basicData;

/**
 * 双端队列
 *
 * @param <E>
 */
public class Deque<E extends Comparable<E>> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public Deque() {
        this(10);
    }

    public Deque(int capacity) {
        data = (E[]) new Comparable[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        return data[front];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        int tailIndex = tail == 0 ? data.length - 1 : tail - 1;
        return data[tailIndex];
    }

    public void addFront(E e) {
        if (getCapacity() == size) {
            resize(getCapacity() * 2);
        }

        front = front == 0 ? data.length - 1 : front - 1;
        data[front] = e;
        size++;
    }

    public void addLast(E e) {
        if (getCapacity() == size) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return res;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        tail = tail == 0 ? data.length - 1 : tail - 1;
        E res = data[tail];
        data[tail] = null;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Deque Capacity: " + getCapacity() + ", Size: " + size + ", front [");

        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);

            if (i != size - 1) {
                res.append(", ");
            }
        }

        res.append("] tail");

        return res.toString();
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    public static void main(String[] args) {
        // 在下面的双端队列的测试中，偶数从队尾加入；奇数从队首加入
        Deque<Integer> dq = new Deque<>();

        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) dq.addLast(i);
            else dq.addFront(i);
            System.out.println(dq);
        }

        // 之后，我们依次从队首和队尾轮流删除元素
        System.out.println();

        for (int i = 0; !dq.isEmpty(); i++) {
            if (i % 2 == 0) dq.removeFront();
            else dq.removeLast();
            System.out.println(dq);
        }
    }
}
