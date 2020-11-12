package basicData;

import java.util.Random;

public class TestCase {

    private static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1);

        LoopQueue1<Integer> loopQueue1 = new LoopQueue1<>();
        double time2 = testQueue(loopQueue1, opCount);
        System.out.println("LoopQueue, time: " + time2);

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3);

//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
//        double time1 = testStack(arrayStack, opCount);
//        System.out.println("ArrayStack, time: " + time1);
//
//        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
//        double time2 = testStack(linkedListStack, opCount);
//        System.out.println("LinkedListStack, time: " + time2);
    }
}
