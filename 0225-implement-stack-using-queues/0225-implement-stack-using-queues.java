import java.util.*;

class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // O(N) - naya element sabse aage chala jaye
    public void push(int x) {
        q2.add(x); // naya element q2 me

        // q1 ke saare elements q2 ke peeche daal do
        while(!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // q1 aur q2 swap kar do
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // O(1)
    public int pop() {
        return q1.remove(); // sabse pehle wala hi top hai
    }

    // O(1)
    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */