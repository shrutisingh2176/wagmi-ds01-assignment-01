package test.java.com.wagmi.finance.processing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.model.Transaction;
import main.java.com.wagmi.finance.processing.PendingQueue;

public class PendingQueueTest {

    private Transaction t(String id) {
        return new Transaction(id, "d", 1.0, "Cat", LocalDate.parse("2024-01-01"), false);
    }

    @Test
    void enqueue_dequeue_circular_behavior_and_overflow_underflow() {
        PendingQueue q = new PendingQueue(2);
        assertTrue(q.isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> q.dequeue());
        q.enqueue(t("1"));
        q.enqueue(t("2"));
        assertTrue(q.isFull());
        assertThrows(UnsupportedOperationException.class, () -> q.enqueue(t("3")));
        assertEquals("1", q.dequeue().getId());
        q.enqueue(t("3"));
        assertEquals(2, q.size());
        assertEquals("2", q.dequeue().getId());
        assertEquals("3", q.dequeue().getId());
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_with_capacity_one() {
        PendingQueue q = new PendingQueue(1);
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());

        q.enqueue(t("1"));
        assertFalse(q.isEmpty());
        assertTrue(q.isFull());

        assertEquals("1", q.dequeue().getId());
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());
    }

    @Test
    void queue_fifo_order() {
        PendingQueue q = new PendingQueue(5);
        q.enqueue(t("1"));
        q.enqueue(t("2"));
        q.enqueue(t("3"));

        assertEquals("1", q.dequeue().getId());
        assertEquals("2", q.dequeue().getId());
        assertEquals("3", q.dequeue().getId());
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_circular_wrap_around() {
        PendingQueue q = new PendingQueue(3);
        q.enqueue(t("1"));
        q.enqueue(t("2"));
        q.enqueue(t("3"));

        assertEquals("1", q.dequeue().getId());
        q.enqueue(t("4"));

        assertEquals("2", q.dequeue().getId());
        assertEquals("3", q.dequeue().getId());
        assertEquals("4", q.dequeue().getId());
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_multiple_wrap_arounds() {
        PendingQueue q = new PendingQueue(2);
        q.enqueue(t("1"));
        q.enqueue(t("2"));
        assertEquals("1", q.dequeue().getId());
        q.enqueue(t("3"));
        assertEquals("2", q.dequeue().getId());
        q.enqueue(t("4"));
        assertEquals("3", q.dequeue().getId());
        assertEquals("4", q.dequeue().getId());
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_size_tracking() {
        PendingQueue q = new PendingQueue(4);
        assertEquals(0, q.size());

        q.enqueue(t("1"));
        assertEquals(1, q.size());

        q.enqueue(t("2"));
        assertEquals(2, q.size());

        q.dequeue();
        assertEquals(1, q.size());

        q.dequeue();
        assertEquals(0, q.size());
    }

    @Test
    void queue_with_zero_capacity() {
        PendingQueue q = new PendingQueue(0);
        assertTrue(q.isEmpty());
        assertTrue(q.isFull());
        assertThrows(UnsupportedOperationException.class, () -> q.enqueue(t("1")));
        assertThrows(UnsupportedOperationException.class, () -> q.dequeue());
    }

    @Test
    void queue_with_large_capacity() {
        PendingQueue q = new PendingQueue(1000);
        for (int i = 0; i < 1000; i++) {
            q.enqueue(t(String.valueOf(i)));
        }
        assertTrue(q.isFull());
        assertEquals(1000, q.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(String.valueOf(i), q.dequeue().getId());
        }
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_enqueue_dequeue_alternating() {
        PendingQueue q = new PendingQueue(3);
        q.enqueue(t("1"));
        assertEquals("1", q.dequeue().getId());

        q.enqueue(t("2"));
        q.enqueue(t("3"));
        assertEquals("2", q.dequeue().getId());

        q.enqueue(t("4"));
        assertEquals("3", q.dequeue().getId());
        assertEquals("4", q.dequeue().getId());
        assertTrue(q.isEmpty());
    }

    @Test
    void queue_with_null_transaction() {
        PendingQueue q = new PendingQueue(2);
        assertThrows(Exception.class, () -> q.enqueue(null));
    }
}
