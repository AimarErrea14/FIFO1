package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link IntPriorityQueue} class.
 * This test suite verifies the proper functioning of the integer-based priority queue,
 * specifically ensuring that the max-heap properties and dynamic resizing logic
 * are handled correctly.
 */
public class TestIntPriorityQueue {

    /**
     * Tests the initialization of the priority queue.
     * Ensures that a newly instantiated queue is empty and reports a size of zero.
     */
    @Test
    public void test_emptyCreation() {
        IntPriorityQueue queue = new IntPriorityQueue(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests the priority ordering of the queue.
     * Verifies that elements are dequeued in strictly descending order (largest first),
     * regardless of the order in which they were inserted.
     */
    @Test
    public void test_priorityBehavior() {
        IntPriorityQueue queue = new IntPriorityQueue(5);
        queue.insertElement(15);
        queue.insertElement(50);
        queue.insertElement(10);

        assertEquals(3, queue.size());
        // 50 must come out first because it is the largest value (Max-Heap)
        assertEquals(50, queue.popElement());
        assertEquals(15, queue.popElement());
        assertEquals(10, queue.popElement());
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests the dynamic resizing mechanism of the underlying array.
     * By initializing the queue with a very small capacity (2) and inserting more
     * elements than it can hold, this test ensures the internal array grows without data loss.
     */
    @Test
    public void test_resize() {
        IntPriorityQueue queue = new IntPriorityQueue(2);
        queue.insertElement(5);
        queue.insertElement(10);
        queue.insertElement(20);

        assertEquals(3, queue.size());
        assertEquals(20, queue.popElement());
    }

    @Test
    public void test_exceptionsAndIterator() {
        IntPriorityQueue queue = new IntPriorityQueue(5);

        // Exception coverage
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.element());
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.popElement());

        // Iterator coverage
        queue.insertElement(10);
        queue.insertElement(20);
        java.util.Iterator<Integer> it = queue.iterator();

        org.junit.jupiter.api.Assertions.assertTrue(it.hasNext());
        it.next();
        it.next();
        org.junit.jupiter.api.Assertions.assertFalse(it.hasNext());
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> it.next());
    }
}