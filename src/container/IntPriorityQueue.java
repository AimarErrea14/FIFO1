package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] heap;
    private int size;

    // Constructor solicitado
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
            resize(); // Redimensiona si está lleno
        }

        // 1. Insertamos el elemento al final del árbol (final del arreglo)
        heap[size] = e;

        // 2. Lo hacemos "flotar" hacia arriba si es más grande que su padre
        siftUp(size);

        size++;
        return true;
    }

    @Override
    public Integer element() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");
        return heap[0]; // El más grande siempre está en la raíz
    }

    @Override
    public Integer popElement() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");

        Integer rootValue = heap[0];
        size--;

        // 1. Movemos el último elemento del árbol a la raíz
        heap[0] = heap[size];
        heap[size] = null; // Ayuda al recolector de basura

        // 2. Lo hundimos hacia abajo si es más pequeño que sus hijos
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

    // --- MÉTODOS PRIVADOS AUXILIARES PARA EL TAS ---

    private void resize() {
        Integer[] newHeap = new Integer[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        this.heap = newHeap;
    }

    // Hace "flotar" un elemento hacia arriba si es mayor que su padre
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap[index] > heap[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex; // Subimos de nivel
            } else {
                break; // Si ya no es mayor que el padre, nos detenemos
            }
        }
    }

    // "Hunde" un elemento hacia abajo si es menor que alguno de sus hijos
    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            // Comprobamos si el hijo izquierdo es mayor que el padre actual
            if (leftChild < size && heap[leftChild] > heap[largest]) {
                largest = leftChild;
            }

            // Comprobamos si el hijo derecho es mayor que el más grande hasta ahora
            if (rightChild < size && heap[rightChild] > heap[largest]) {
                largest = rightChild;
            }

            // Si el padre no es el más grande, lo intercambiamos con el hijo mayor
            if (largest != index) {
                swap(index, largest);
                index = largest; // Bajamos de nivel
            } else {
                break; // Si ya es mayor que sus hijos, está en su lugar correcto
            }
        }
    }

    private void swap(int i, int j) {
        Integer temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}