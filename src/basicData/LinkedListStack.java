package basicData;

public class LinkedListStack<E extends Comparable<E>> implements Stack {
    private LinkedList list;

    LinkedListStack() {
        list = new LinkedList();
    }

    @Override
    public void push(Comparable e) {
        list.addFirst(e);
    }

    @Override
    public Comparable pop() {
        return list.removeFirst();
    }

    @Override
    public Comparable peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("Stack: top ");
        res.append(list);

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> data = new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            data.push(i);
            System.out.println(data);
        }

        data.pop();

        System.out.println(data);
    }
}
