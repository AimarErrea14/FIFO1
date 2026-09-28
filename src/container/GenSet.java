package container;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A generic set implementation based on a Binary Search Tree (BST).
 * Elements stored in this set must implement the Comparable interface
 * to maintain a sorted order and prevent duplicates.
 *
 * @param <E> the type of elements maintained by this set, must be Comparable
 */
public class GenSet<E extends Comparable<E>> implements SetContainer<E> {

    /**
     * Internal class to represent nodes in the Binary Search Tree.
     */
    private class Node {
        E value;
        Node left;
        Node right;

        Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    /**
     * Constructs an empty GenSet.
     */
    public GenSet() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Inserts the specified element into the set if it is not already present.
     * Navigates the Binary Search Tree to place the element in the correct sorted position.
     *
     * @param e the element to add
     * @return true if the element was successfully added, false if it was already present
     */
    @Override
    public boolean insertElement(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return true;
        }
        return insertRecursive(root, e);
    }

    /**
     * Recursive helper method for inserting an element.
     *
     * @param current the current node being evaluated
     * @param value the value to insert
     * @return true if inserted, false if it's a duplicate
     */
    private boolean insertRecursive(Node current, E value) {
        int compareResult = value.compareTo(current.value);

        if (compareResult < 0) { // Smaller: go left
            if (current.left == null) {
                current.left = new Node(value);
                size++;
                return true;
            } else {
                return insertRecursive(current.left, value);
            }
        } else if (compareResult > 0) { // Greater: go right
            if (current.right == null) {
                current.right = new Node(value);
                size++;
                return true;
            } else {
                return insertRecursive(current.right, value);
            }
        } else {
            // Equal (compareResult == 0): duplicate found, do not insert
            return false;
        }
    }

    /**
     * Returns true if this set contains the specified element.
     * Uses an iterative binary search for maximum efficiency.
     *
     * @param e the element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    @Override
    public boolean contains(E e) {
        Node current = root;
        while (current != null) {
            int compareResult = e.compareTo(current.value);

            if (compareResult == 0) {
                return true; // Element found
            } else if (compareResult < 0) {
                current = current.left; // Search in the left subtree
            } else {
                current = current.right; // Search in the right subtree
            }
        }
        return false; // Reached a null leaf, element does not exist
    }

    /**
     * Checks if the set contains no elements.
     *
     * @return true if the set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current number of elements in the set.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this set.
     * The iterator traverses the Binary Search Tree in ascending order (In-Order Traversal)
     * using an iterative approach with a Stack.
     *
     * @return an Iterator over the elements in the set
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            // A Stack is used to keep track of the parent nodes during traversal
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the set");
                }

                // Traverse down the left subtree to find the smallest available element
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                // Pop the smallest node saved in the stack
                Node node = stack.pop();
                E result = node.value;

                // Move the cursor to the right subtree for the next iteration
                current = node.right;

                return result;
            }
        };
    }
}