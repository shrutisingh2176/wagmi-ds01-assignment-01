package main.java.com.wagmi.finance.processing;

/*
 TODO[Student]: Circular queue for pending transactions
 - Implement circular `enqueue` and `dequeue` with head/tail updates and size tracking.
 - Enforce overflow/underflow rules as per tests.
 - Validate null enqueues (should throw).
 - Run `PendingQueueTest` after changes.
*/

import main.java.com.wagmi.finance.model.Transaction;

public class PendingQueue {
    private final Transaction[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public PendingQueue(int capacity) {
        this.data = new Transaction[capacity];
    }

    public void enqueue(Transaction tx) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public Transaction dequeue() {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }
}
