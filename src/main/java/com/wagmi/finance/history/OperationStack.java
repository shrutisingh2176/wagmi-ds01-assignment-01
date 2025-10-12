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
        if ( capacity ==-1) { throw new UnsupportedOperationException("capacity should be positive");}

        this.data = new String[capacity];
    }

    public void push(String operation) {
        // stub
        if ( operation ==null) {
            throw new UnsupportedOperationException("cannot push null value");
        }
        if ( top>= data.length-1) {
            throw new UnsupportedOperationException("Stack Overflow");
        }  else
        { data[++top] = operation;}
    }

    public String pop() {
        // stub
        if (top==-1){
        throw new UnsupportedOperationException("Stack Underflow");}
        String value = data[top];
        data [top--] = null;
        return value;
    }

    public String peek() {
        // stub
        if (top==-1){
        throw new UnsupportedOperationException("Stack is empty");
    }
        return data[top];
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public int size() {
        return top + 1;
    }
}
