package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

// La clase acepta un tipo E que obligatoriamente implementa Comparable
public class GenPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] heap;
    private int size;

    // Constructor solicitado
    @SuppressWarnings("unchecked")
    public GenPriorityQueue(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        // Solución al Type Erasure usando casting, como pide el enunciado
        this.heap = (E[]) new Comparable[capacity];
        this.size = 0;
    }

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

    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");
        return heap[0];
    }

    @Override
    public E popElement() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");

        E rootValue = heap[0];
        size--;

        heap[0] = heap[size];
        heap[size] = null; // Evitar fugas de memoria

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
    public Iterator<E> iterator() {
        return null;
    }

    // --- MÉTODOS PRIVADOS AUXILIARES ---

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newHeap = (E[]) new Comparable[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        this.heap = newHeap;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // Reemplazamos el operador ">" por "compareTo"
            if (heap[index].compareTo(heap[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
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

            // Reemplazamos el operador ">" por "compareTo"
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

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}