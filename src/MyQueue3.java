import java.util.Stack;

/**
 * data1: n 为top，0 为bottom
 * data2: 0 为top，n 为bottom
 */
class MyQueue3 {

    private Stack<Integer> data1;
    private Stack<Integer> data2;
    int front;

    /**
     * Initialize your data structure here.
     */
    public MyQueue3() {
        data1 = new Stack<>();
        data2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (data1.isEmpty()) {
            front = x;
        }

        data1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!data2.isEmpty()) {
            return data2.pop();
        }

        while (data1.size() > 1) {
            data2.push(data1.pop());
        }

        return data1.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!data2.isEmpty()) {
            return data2.peek();
        }

        return front;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return data1.isEmpty() && data2.isEmpty();
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    public static void main(String[] args) {
        MyQueue3 obj = new MyQueue3();

        obj.push(1);
        obj.push(2);

        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();

        System.out.println(String.format("param_2: %d, param_3: %d, param_4: %b", param_2, param_3, param_4));
    }
}

