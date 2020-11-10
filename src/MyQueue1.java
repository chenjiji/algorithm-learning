import java.util.Stack;

/**
 * 0 为top，n 为bottom
 */
class MyQueue1 {
    private Stack<Integer> data;
    private int front;

    /**
     * Initialize your data structure here.
     */
    public MyQueue1() {
        data = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (data.isEmpty()) {
            front = x;
        }

        data.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        Stack<Integer> data2 = new Stack<>();

        while (data.size() > 1) {
            front = data.peek();
            data2.push(data.pop());
        }

        int res = data.pop();

        while (!data2.isEmpty()) {
            data.push(data2.pop());
        }

        return res;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return front;
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
        MyQueue1 obj = new MyQueue1();

        obj.push(1);
        obj.push(2);

        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();

        System.out.println(String.format("param_2: %d, param_3: %d, param_4: %b", param_2, param_3, param_4));
    }
}

