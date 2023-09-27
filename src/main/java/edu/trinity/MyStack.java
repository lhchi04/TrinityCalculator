package edu.trinity;

import java.util.EmptyStackException;
import java.util.ArrayList;

public class MyStack<T> {
    private ArrayList<T> stack = new ArrayList<>();
    public void push(T hello) {
        stack.add(hello);
    }

    public T peek() {
        return stack.get(stack.size()-1);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size()-1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
