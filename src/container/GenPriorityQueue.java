package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic priority queue implementation based on a max-heap data structure.
 * Elements stored in this queue must implement the Comparable interface to define their natural ordering.
 *
 * @param <E> the type of elements held in this collection, must be Comparable
 */
public class GenPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] heap;
    private int size;

    /**
     * Constructs a new GenPriorityQueue with the specified initial capacity.
     * If the provided capacity is less than or equal to zero, a default capacity of 10 is used.
     *
     * @param capacity the initial capacity of the priority queue
     */
    @SuppressWarnings("unchecked")
    public GenPriorityQueue(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        this.heap = (E[]) new Comparable[capacity];
        this.size = 0;
    }

    /**
     * Inserts the specified element into the priority queue.
     * The capacity of the queue is automatically increased if necessary.
     *
     * @param e the element to add
     * @return true if the element was successfully added
     */
    @Override
    public boolean insertElement(E e) {
        if (size == heap.length) {
            resize();
        }

        heap[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    /**
     * Retrieves, but does not remove, the highest priority element (the root of the max-heap).
     *
     * @return the highest priority element
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        return heap[0];
    }

    /**
     * Retrieves and removes the highest priority element (the root of the max-heap).
     *
     * @return the highest priority element
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E popElement() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");

        E rootValue = heap[0];
        size--;

        heap[0] = heap[size];
        heap[size] = null;

        if (size > 0) {
            siftDown(0);
        }

        return rootValue;
    }

    /**
     * Checks if the priority queue contains no elements.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current number of elements in the priority queue.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this priority queue.
     * Note: The iterator does not guarantee any specific traversal order.
     *
     * @return an Iterator over the elements in the queue
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the queue");
                }
                return heap[currentIndex++];
            }
        };
    }

    /* --- Private Helper Methods --- */

    /**
     * Doubles the capacity of the underlying array when the queue is full.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newHeap = (E[]) new Comparable[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        this.heap = newHeap;
    }

    /**
     * Restores the max-heap property by moving a newly inserted element up the tree.
     *
     * @param index the index of the element to move up
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap[index].compareTo(heap[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Restores the max-heap property by moving an element down the tree
     * (used after extracting the root).
     *
     * @param index the index of the element to move down
     */
    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap[leftChild].compareTo(heap[largest]) > 0) {
                largest = leftChild;
            }

            if (rightChild < size && heap[rightChild].compareTo(heap[largest]) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    /**
     * Swaps two elements in the heap array.
     */
    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}