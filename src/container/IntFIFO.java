package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntFIFO implements Queue<Integer> {

    private Integer[] buffer;
    private int head;  // Índice del próximo elemento a sacar
    private int tail;  // Índice donde se insertará el próximo elemento
    private int size;  // Cantidad de elementos actuales en la cola

    public IntFIFO(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        this.buffer = new Integer[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

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

    @Override
    public Integer element() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return buffer[head];
    }

    @Override
    public Integer popElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }

        Integer value = buffer[head];
        buffer[head] = null;

        head = (head + 1) % buffer.length;
        size--;

        return value;
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
}
