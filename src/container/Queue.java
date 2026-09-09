package container;

import java.util.NoSuchElementException;

public interface Queue<Integer> extends Iterable<Integer> {
    /** Add specified element into this queue, increase capacity of the queue if not enough space
     *
     * @return true if the element was successfully added
     * es generica la interfaz
     */
    boolean insertElement(Integer e);

    /**
     * Retrieves (without removing) the highest element of this queue
     *
     * @return the highest element of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    Integer element();

    /**
     * Retrieves (and remove) the highest element of this queue
     *
     * @return the highest element of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    Integer popElement();

    /** Returns true if this queue contains no elements. */
    boolean isEmpty();

    /** Returns the number of elements contained in this queue */
    int size();
}
