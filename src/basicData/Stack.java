package basicData;

public interface Stack<E extends Comparable<E>> {
    public void push(E e);

    public E pop();

    public E peek();

    public int getSize();

    public boolean isEmpty();
}
