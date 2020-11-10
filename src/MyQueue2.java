import java.util.Stack;

/**
 * n 为top，0 为bottom
 */
class MyQueue2 {
    private Stack<Integer> data;
    private int front;

    /**
     * Initialize your data structure here.
     */
    public MyQueue2() {
        data = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        Stack<Integer> data2 = new Stack<>();

        while (!data.isEmpty()) {
            data2.push(data.pop());
        }

        data.push(x);

        while (!data2.isEmpty()) {
            data.push(data2.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return data.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return data.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return data.isEmpty();
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
        MyQueue2 obj = new MyQueue2();

        obj.push(1);
        obj.push(2);

        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();

        System.out.println(String.format("param_2: %d, param_3: %d, param_4: %b", param_2, param_3, param_4));
    }
}

