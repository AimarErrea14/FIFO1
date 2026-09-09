package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] heap;
    private int size;

    public IntPriorityQueue(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        this.heap = new Integer[capacity];
        this.size = 0;
    }

    @Override
    public boolean insertElement(Integer e) {
        if (size == heap.length) {
            resize();
        }

        heap[size] = e;

        siftUp(size);

        size++;
        return true;
    }

    @Override
    public Integer element() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");
        return heap[0];
    }

    @Override
    public Integer popElement() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");

        Integer rootValue = heap[0];
        size--;

        heap[0] = heap[size];
        heap[size] = null;

        if (size > 0) {
            siftDown(0);
        }

        return rootValue;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }


    private void resize() {
        Integer[] newHeap = new Integer[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        this.heap = newHeap;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap[index] > heap[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex; // Subimos de nivel
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap[leftChild] > heap[largest]) {
                largest = leftChild;
            }

            if (rightChild < size && heap[rightChild] > heap[largest]) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest; // Bajamos de nivel
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Integer temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}