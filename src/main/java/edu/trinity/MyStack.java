package edu.trinity;

import java.util.EmptyStackException;
import java.util.Vector;

public class MyStack<T> {
    private Vector<T> stack = new Vector<>();
    int top = 0;
    public void push(T hello) {
        stack.add(hello);
        top++;
    }

    public T peek() {
        return stack.lastElement();
    }

    public T pop() {
        if (top == 0) {
            throw new EmptyStackException();
        }
        return stack.remove(--top);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
