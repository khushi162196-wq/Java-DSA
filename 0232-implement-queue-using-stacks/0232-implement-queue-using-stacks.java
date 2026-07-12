import java.util.*;

class MyQueue {
    private Stack<Integer> s1; // for push
    private Stack<Integer> s2; // for pop/peek

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // O(1)
    public void push(int x) {
        s1.push(x);
    }

    // O(N) worst, O(1) amortized
    public int pop() {
        peek(); // ensure s2 has elements
        return s2.pop();
    }

    // O(N) worst, O(1) amortized
    public int peek() {
        if(s2.isEmpty()) {
            // s1 ke saare elements ko reverse karke s2 me daal do
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */