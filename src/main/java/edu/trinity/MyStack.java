package edu.trinity;

import java.util.Stack;

public class MyStack<T> {
    private Stack<T> stack = new Stack<>();
    public void push(T hello) {
        stack.push(hello);
    }

    public T peek() {
        return stack.peek();
    }

    public T pop() { return stack.pop(); }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
