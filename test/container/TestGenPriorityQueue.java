package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link GenPriorityQueue} class.
 * This test suite verifies the correct behavior of the generic max-heap implementation
 * using different data types that implement the Comparable interface.
 */
public class TestGenPriorityQueue {

    /**
     * Tests the initialization of the priority queue.
     * Ensures that a newly created queue starts empty and with a size of zero.
     */
    @Test
    public void test_emptyCreation() {
        GenPriorityQueue<String> queue = new GenPriorityQueue<>(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests the priority ordering using String objects.
     * Ensures that elements are dequeued according to their natural lexicographical order
     * (e.g., "Zebra" comes out before "Apple" in a max-heap).
     */
    @Test
    public void test_stringPriorityBehavior() {
        GenPriorityQueue<String> queue = new GenPriorityQueue<>(5);
        queue.insertElement("Apple");
        queue.insertElement("Zebra");
        queue.insertElement("Mango");

        assertEquals(3, queue.size());
        assertEquals("Zebra", queue.popElement());
        assertEquals("Mango", queue.popElement());
        assertEquals("Apple", queue.popElement());
    }

    /**
     * Tests the priority ordering using Integer objects and verifies the resizing mechanism.
     * The queue is initialized with a very small capacity to force an internal array resize
     * when multiple elements are inserted.
     */
    @Test
    public void test_integerPriorityBehaviorAndResize() {
        GenPriorityQueue<Integer> queue = new GenPriorityQueue<>(2);
        queue.insertElement(42);
        queue.insertElement(7);
        queue.insertElement(100);

        assertEquals(3, queue.size());
        assertEquals(100, queue.popElement());
        assertEquals(42, queue.popElement());
        assertEquals(7, queue.popElement());
    }

    @Test
    public void test_exceptionsAndIterator() {
        GenPriorityQueue<Integer> queue = new GenPriorityQueue<>(5);

        // Coverage for exceptions on an empty queue
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.element());
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.popElement());

        // Coverage for the iterator and its anonymous class
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