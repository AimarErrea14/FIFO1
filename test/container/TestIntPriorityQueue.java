package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIntPriorityQueue {

    @Test
    public void test_emptyCreation() {
        IntPriorityQueue queue = new IntPriorityQueue(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void test_priorityBehavior() {
        IntPriorityQueue queue = new IntPriorityQueue(5);
        queue.insertElement(15);
        queue.insertElement(50);
        queue.insertElement(10);

        assertEquals(3, queue.size());
        // El 50 debe salir primero por ser el mayor
        assertEquals(50, queue.popElement());
        assertEquals(15, queue.popElement());
        assertEquals(10, queue.popElement());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test_resize() {
        IntPriorityQueue queue = new IntPriorityQueue(2);
        queue.insertElement(5);
        queue.insertElement(10);
        queue.insertElement(20);

        assertEquals(3, queue.size());
        assertEquals(20, queue.popElement());
    }
}