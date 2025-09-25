package main.java.com.wagmi.finance.ds;

import main.java.com.wagmi.finance.model.Transaction;

public class DoublyLinkedTransactions {

    private static class Node {
        Transaction value;
        Node prev;
        Node next;

        Node(Transaction value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void add(Transaction tx) {
        // stub: implement insertion at tail
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean deleteById(String id) {
        // stub: implement search and delete
        throw new UnsupportedOperationException("Not implemented");
    }

    public Transaction findById(String id) {
        // stub: linear search
        throw new UnsupportedOperationException("Not implemented");
    }

    public void reverse() {
        // stub: reverse pointers
        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return size;
    }

    public Transaction[] toArray() {
        // stub: iterate from head
        throw new UnsupportedOperationException("Not implemented");
    }
}
