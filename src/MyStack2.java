import java.util.LinkedList;
import java.util.Queue;

/**
 * n 为栈底， 0为栈顶
 */
class MyStack2 {
    private Queue<Integer> data;
    private int top;

    /**
     * Initialize your data structure here.
     */
    public MyStack2() {
        data = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        data.offer(x);

        for (int i = 1; i < data.size(); i++) {
            data.offer(data.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return data.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return data.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return data.isEmpty();
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
    public static void main(String[] args) {
        MyStack2 obj = new MyStack2();

        obj.push(1);
        obj.push(2);

        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();

        System.out.println(String.format("param_2: %d, param_3: %d, param_4: %b", param_2, param_3, param_4));
    }
}


