package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGenPriorityQueue {

    @Test
    public void test_emptyCreation() {
        GenPriorityQueue<String> queue = new GenPriorityQueue<>(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

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
}