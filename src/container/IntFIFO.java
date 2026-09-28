package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A First-In-First-Out (FIFO) queue implementation for Integer elements.
 * This class uses a circular buffer array to manage its elements efficiently
 * without the need to shift them during insertions or removals.
 */
public class IntFIFO implements Queue<Integer> {

    private Integer[] buffer;
    private int head;  // Index of the next element to be removed
    private int tail;  // Index where the next element will be inserted
    private int size;  // Current number of elements in the queue

    /**
     * Constructs an empty IntFIFO queue with the specified initial capacity.
     * If the provided capacity is less than or equal to zero, a default capacity of 10 is used.
     *
     * @param capacity the initial capacity of the circular buffer
     */
    public IntFIFO(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        this.buffer = new Integer[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * Inserts the specified element at the tail of the queue.
     * The underlying circular buffer is resized if it is full.
     *
     * @param e the element to insert
     * @return true if the element was successfully added
     */
    @Override
    public boolean insertElement(Integer e) {
        if (size == buffer.length) {
            resize();
        }

        buffer[tail] = e;
        tail = (tail + 1) % buffer.length;
        size++;

        return true;
    }

    /**
     * Doubles the capacity of the circular buffer and realigns the elements.
     * The head is reset to index 0 to ensure contiguous alignment in the new array.
     */
    private void resize() {
        int newCapacity = buffer.length * 2;
        Integer[] newBuffer = new Integer[newCapacity];

        for (int i = 0; i < size; i++) {
            newBuffer[i] = buffer[(head + i) % buffer.length];
        }

        this.buffer = newBuffer;
        this.head = 0;
        this.tail = size;
    }

    /**
     * Retrieves, but does not remove, the element at the head of the queue.
     *
     * @return the element at the head of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public Integer element() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        return buffer[head];
    }

    /**
     * Retrieves and removes the element at the head of the queue.
     *
     * @return the removed element
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public Integer popElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }

        Integer value = buffer[head];
        buffer[head] = null;

        head = (head + 1) % buffer.length;
        size--;

        return value;
    }

    /**
     * Checks if the queue contains no elements.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current number of elements in the queue.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * The iterator respects the circular nature of the buffer.
     *
     * @return an Iterator over the elements in the queue
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the FIFO queue");
                }
                Integer value = buffer[(head + count) % buffer.length];
                count++;
                return value;
            }
        };
    }
}