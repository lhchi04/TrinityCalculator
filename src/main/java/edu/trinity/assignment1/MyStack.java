package edu.trinity.assignment1;

import java.util.EmptyStackException;
import java.util.ArrayList;

// This implementation uses an ArrayList<T> to store the items of type T.
// That means adding and removing items at the end is quick and easy.
public class MyStack<T> {
    private ArrayList<T> stack = new ArrayList<>();
    int top = 0;
    public void push(T hello) {
        stack.add(hello);
        top++;
    }

    public T peek() {
        return stack.get(top-1);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(--top);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
