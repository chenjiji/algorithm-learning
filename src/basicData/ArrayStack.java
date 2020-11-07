package basicData;

public class
ArrayStack<E extends Comparable<E>> implements Stack {
    private Array array;

    public ArrayStack(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayStack() {
        array = new Array<E>();
    }

    @Override
    public void push(Comparable e) {
        array.addLast(e);
    }

    @Override
    public Comparable pop() {
        return array.deleteLast();
    }

    @Override
    public Comparable peek() {
        return array.getLast();
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
        res.append("Stack Capacity: " + array.getCapacity() + ", [");

        for (int i = 0; i < array.getSize(); i++) {
            if (i < array.getSize() - 1) {
                res.append(array.get(i) + ", ");
            } else {
                res.append(array.get(i) + "] top");
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack arr = new ArrayStack(20);

        arr.push(1);
        arr.push(2);
        arr.push(3);
        arr.push(4);

        System.out.println(arr);

        System.out.println("pop: " + arr.pop());

        System.out.println(arr);

        System.out.println("peek: " + arr.peek());

        System.out.println(arr);
    }
}
