package test.java.com.wagmi.finance.history;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.history.OperationStack;

public class OperationStackTest {

    @Test
    void push_pop_and_peek_behaviors_and_underflow_overflow() {
        OperationStack stack = new OperationStack(2);
        assertTrue(stack.isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> stack.pop());
        stack.push("ADD:1");
        stack.push("DELETE:1");
        assertEquals("DELETE:1", stack.peek());
        assertThrows(UnsupportedOperationException.class, () -> stack.push("OVER"));
        assertEquals("DELETE:1", stack.pop());
        assertEquals("ADD:1", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void stack_with_capacity_one() {
        OperationStack stack = new OperationStack(1);
        assertTrue(stack.isEmpty());
        stack.push("ADD:1");
        assertFalse(stack.isEmpty());
        assertEquals("ADD:1", stack.peek());
        assertThrows(UnsupportedOperationException.class, () -> stack.push("OVER"));
        assertEquals("ADD:1", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void stack_with_large_capacity() {
        OperationStack stack = new OperationStack(100);
        for (int i = 0; i < 100; i++) {
            stack.push("OP:" + i);
        }
        assertFalse(stack.isEmpty());
        assertEquals("OP:99", stack.peek());
        assertEquals("OP:99", stack.pop());
        assertEquals("OP:98", stack.peek());
    }

    @Test
    void stack_lifo_order() {
        OperationStack stack = new OperationStack(5);
        stack.push("FIRST");
        stack.push("SECOND");
        stack.push("THIRD");

        assertEquals("THIRD", stack.pop());
        assertEquals("SECOND", stack.pop());
        assertEquals("FIRST", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void peek_does_not_modify_stack() {
        OperationStack stack = new OperationStack(3);
        stack.push("ADD:1");
        stack.push("DELETE:2");

        assertEquals("DELETE:2", stack.peek());
        assertEquals("DELETE:2", stack.peek());
        assertEquals("DELETE:2", stack.peek());

        assertEquals("DELETE:2", stack.pop());
        assertEquals("ADD:1", stack.pop());
    }

    @Test
    void stack_with_empty_strings() {
        OperationStack stack = new OperationStack(3);
        stack.push("");
        stack.push("VALID");
        stack.push("");

        assertEquals("", stack.pop());
        assertEquals("VALID", stack.pop());
        assertEquals("", stack.pop());
    }

    @Test
    void stack_with_null_operations() {
        OperationStack stack = new OperationStack(2);
        assertThrows(Exception.class, () -> stack.push(null));
    }

    @Test
    void stack_multiple_peeks_before_pop() {
        OperationStack stack = new OperationStack(2);
        stack.push("OPERATION");

        assertEquals("OPERATION", stack.peek());
        assertEquals("OPERATION", stack.peek());
        assertEquals("OPERATION", stack.peek());

        assertEquals("OPERATION", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void stack_zero_capacity() {
        OperationStack stack = new OperationStack(0);
        assertTrue(stack.isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> stack.push("ANY"));
        assertThrows(UnsupportedOperationException.class, () -> stack.pop());
        assertThrows(UnsupportedOperationException.class, () -> stack.peek());
    }
}
