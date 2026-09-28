package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the {@link IntFIFO} class.
 * This test suite verifies the proper functioning of a First-In-First-Out queue,
 * with a special focus on validating the underlying circular buffer mechanics
 * and its dynamic resizing capabilities.
 */
public class TestIntFIFO {

    /**
     * Tests the initialization of the FIFO queue.
     * Ensures that a newly instantiated queue is empty and reports a size of zero.
     */
    @Test
    public void test_emptyCreation() {
        IntFIFO queue = new IntFIFO(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests the standard First-In-First-Out behavior.
     * Verifies that elements are dequeued in the exact same order they were enqueued.
     */
    @Test
    public void test_fifoBehavior() {
        IntFIFO queue = new IntFIFO(5);
        queue.insertElement(10);
        queue.insertElement(20);
        queue.insertElement(30);

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());

        assertEquals(10, queue.popElement());
        assertEquals(20, queue.popElement());
        assertEquals(30, queue.popElement());
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests the circular nature of the buffer and its resizing logic.
     * By performing insertions and removals that wrap around the array's boundary,
     * this test ensures that the internal array resizes correctly without losing
     * or misaligning any data.
     */
    @Test
    public void test_circularResize() {
        IntFIFO queue = new IntFIFO(2);
        queue.insertElement(1);
        queue.insertElement(2);

        // Remove an element to advance the head index, testing the circular offset
        queue.popElement();

        // These insertions will force the queue to resize while the head is not at index 0
        queue.insertElement(3);
        queue.insertElement(4);

        assertEquals(3, queue.size());
        assertEquals(2, queue.popElement());
        assertEquals(3, queue.popElement());
        assertEquals(4, queue.popElement());
    }

    @Test
    public void test_exceptionsAndIterator() {
        IntFIFO queue = new IntFIFO(5);

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