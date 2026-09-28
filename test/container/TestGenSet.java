package container;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the {@link GenSet} class.
 * This test suite verifies the core properties of the Binary Search Tree implementation,
 * including proper insertion, binary search retrieval, duplicate prevention,
 * and strictly increasing in-order iteration.
 */
public class TestGenSet {

    /**
     * Tests the initialization of the set.
     * Verifies that the set starts empty and that the contains method
     * safely handles queries on an empty tree.
     */
    @Test
    public void test_emptyCreation() {
        GenSet<Integer> set = new GenSet<>();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains(10));
    }

    /**
     * Tests the insertion of elements and the binary search retrieval.
     * Verifies that the size updates correctly and that inserted elements can be found.
     */
    @Test
    public void test_insertAndContains() {
        GenSet<Integer> set = new GenSet<>();
        assertTrue(set.insertElement(10));
        assertTrue(set.insertElement(5));
        assertTrue(set.insertElement(15));

        assertEquals(3, set.size());
        assertFalse(set.isEmpty());

        assertTrue(set.contains(10));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));

        // Search for a number that does not exist in the set
        assertFalse(set.contains(99));
    }

    /**
     * Tests the fundamental mathematical property of a Set: no duplicate elements.
     * Verifies that attempting to insert an existing element returns false
     * and does not artificially inflate the size of the set.
     */
    @Test
    public void test_noDuplicatesAllowed() {
        GenSet<Integer> set = new GenSet<>();
        assertTrue(set.insertElement(10));
        assertTrue(set.insertElement(20));

        // Attempt to insert 10 again. It should return false.
        assertFalse(set.insertElement(10));

        // The size must remain 2, ignoring the duplicate insertion attempt
        assertEquals(2, set.size());
    }

    /**
     * Tests the in-order traversal of the iterator.
     * This is a critical test for Exercise 9: it verifies that the iterator
     * uses the Stack correctly to return elements in strictly ascending order,
     * regardless of the order in which they were inserted.
     */
    @Test
    public void test_iteratorIncreasingOrder() {
        GenSet<Integer> set = new GenSet<>();

        // Insert numbers in a completely unsorted order
        set.insertElement(10);
        set.insertElement(5);
        set.insertElement(15);
        set.insertElement(2);
        set.insertElement(7);

        Iterator<Integer> it = set.iterator();

        // Verify that the Stack-based iterator returns them from smallest to largest
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertEquals(5, it.next());
        assertEquals(7, it.next());
        assertEquals(10, it.next());
        assertEquals(15, it.next());

        // Verify that the iterator correctly detects the end of the collection
        assertFalse(it.hasNext());
    }
}