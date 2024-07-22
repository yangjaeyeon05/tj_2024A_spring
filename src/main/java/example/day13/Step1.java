package example.day13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Step1 {
    public static void main(String[] args) {

        // 1. 스택
        Stack<Integer> coinBox = new Stack<>();

        // 2. 스택 push
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(10);
        System.out.println("coinBox = " + coinBox);

        // 4. 스택 peek
        int lastDataPeek = coinBox.peek();
        System.out.println("lastDataPeek = " + lastDataPeek);

        // 3. 스택 pop
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);

        // 5. 큐
        Queue<Integer> pointBox = new LinkedList<>();

        // 6. 큐 Enqueue
        pointBox.offer(100);
        pointBox.offer(50);
        pointBox.offer(500);
        pointBox.offer(10);
        System.out.println("pointBox = " + pointBox);

        // 7. 큐
        int frontDataPeek = pointBox.peek();
        System.out.println("frontDataPeek = " + frontDataPeek);

        // 8. 큐 Dequeue
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);

    }   // main end
}   // class end
