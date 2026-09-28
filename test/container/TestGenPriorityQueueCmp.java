package container;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link GenPriorityQueueCmp} class.
 * This test suite verifies that the priority queue correctly delegates its ordering
 * logic to an external Comparator, allowing the same class to function as either
 * a Max-Heap or a Min-Heap.
 */
public class TestGenPriorityQueueCmp {

    /**
     * Tests the queue using a standard natural-order comparator.
     * Verifies that the queue behaves as a traditional Max-Heap where the
     * largest elements are given the highest priority.
     */
    @Test
    public void test_maxHeapBehavior() {
        // Create a standard comparator (classic Max-Heap behavior)
        Comparator<Integer> maxComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1.compareTo(n2);
            }
        };

        GenPriorityQueueCmp<Integer> queue = new GenPriorityQueueCmp<>(5, maxComparator);
        queue.insertElement(15);
        queue.insertElement(50);
        queue.insertElement(10);

        assertEquals(3, queue.size());
        // 50 comes out first because it is the largest
        assertEquals(50, queue.popElement());
        assertEquals(15, queue.popElement());
        assertEquals(10, queue.popElement());
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests the queue using an inverted comparator.
     * Verifies that the exact same queue implementation can behave as a Min-Heap
     * simply by injecting a different Comparator rule.
     */
    @Test
    public void test_minHeapBehavior() {
        // Create an INVERTED comparator to force a Min-Heap behavior
        Comparator<Integer> minComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                // By swapping n2 and n1, we invert the mathematical result
                return n2.compareTo(n1);
            }
        };

        // We use the SAME class, but inject the new comparator
        GenPriorityQueueCmp<Integer> queue = new GenPriorityQueueCmp<>(5, minComparator);
        queue.insertElement(15);
        queue.insertElement(50);
        queue.insertElement(10);

        assertEquals(3, queue.size());
        // Magic! Now 10 comes out first because the comparator gives priority to smaller numbers
        assertEquals(10, queue.popElement());
        assertEquals(15, queue.popElement());
        assertEquals(50, queue.popElement());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test_exceptionsAndIterator() {
        java.util.Comparator<Integer> comp = (n1, n2) -> n1.compareTo(n2);
        GenPriorityQueueCmp<Integer> queue = new GenPriorityQueueCmp<>(5, comp);

        // Coverage for exceptions on an empty queue
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.element());
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> queue.popElement());

        // Coverage for the iterator
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