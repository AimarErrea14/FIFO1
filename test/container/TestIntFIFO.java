package container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestIntFIFO {

    @Test
    public void test_emptyCreation() {
        IntFIFO queue = new IntFIFO(10);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

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

    @Test
    public void test_circularResize() {
        IntFIFO queue = new IntFIFO(2);
        queue.insertElement(1);
        queue.insertElement(2);

        queue.popElement(); // Saca el 1, libera el índice 0
        queue.insertElement(3); // Se inserta en el índice 0 (comportamiento circular)
        queue.insertElement(4); // El arreglo está lleno, esto fuerza el resize()

        assertEquals(3, queue.size());
        assertEquals(2, queue.popElement());
        assertEquals(3, queue.popElement());
        assertEquals(4, queue.popElement());
    }
}