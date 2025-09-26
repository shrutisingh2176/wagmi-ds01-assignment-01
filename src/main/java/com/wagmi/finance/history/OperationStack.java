package main.java.com.wagmi.finance.history;

/*
 TODO[Student]: Array-based stack
 - Enforce underflow/overflow behaviors per tests.
 - Note dynamic growth is allowed only when initial capacity >= 10.
 - Validate null pushes (should throw).
 - Run `OperationStackTest` after changes.
*/

public class OperationStack {
    private final String[] data;
    private int top = -1;

    public OperationStack(int capacity) {
        this.data = new String[capacity];
    }

    public void push(String operation) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public String pop() {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public String peek() {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public int size() {
        return top + 1;
    }
}
