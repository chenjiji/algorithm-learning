import java.util.LinkedList;
import java.util.Queue;

/**
 * 0 为栈底， n为栈顶
 */
class MyStack1 {
    private Queue<Integer> data;
    private int top;

    /**
     * Initialize your data structure here.
     */
    public MyStack1() {
        data = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        data.offer(x);
        top = x;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        Queue<Integer> newData = new LinkedList<>();

        while (data.size() > 1) {
            int e = data.poll();

            top = e;

            newData.add(e);
        }

        int ret = data.poll();

        data = newData;

        return ret;
    }

    /**
     * Get the top element.
     */
    public int top() {
        return top;
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
        MyStack1 obj = new MyStack1();

        obj.push(1);
        obj.push(2);

        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();

        System.out.println(String.format("param_2: %d, param_3: %d, param_4: %b", param_2, param_3, param_4));
    }
}


