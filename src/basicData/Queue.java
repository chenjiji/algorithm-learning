package basicData;

public interface Queue<E extends Comparable<E>>  {
    public void enqueue(E e);
    public E dequeue();
    public E getFront();
    public int getSize();
    public boolean isEmpty();
}
