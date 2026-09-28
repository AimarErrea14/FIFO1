package container;

/**
 * A demonstration class used to test and showcase the basic functionality
 * of the {@link IntFIFO} circular buffer queue.
 * This class contains a main method that performs a series of insertions
 * and removals, printing the results to the standard output for manual verification.
 */
public class MainIntFIFO {

    /**
     * The entry point of the application.
     * Executes a sequence of operations on an IntFIFO instance and prints
     * the actual outcomes alongside the expected values.
     *
     * @param arg the command-line arguments (not used)
     */
    public static void main(String[] arg) {

        IntFIFO queue = new IntFIFO(10);

        System.out.println(queue.isEmpty());

        queue.insertElement(12);
        queue.insertElement(42);
        queue.insertElement(1);

        System.out.println(queue.size() + " expect " +  3);

        System.out.println(queue.element() + " expect " +  12);
        System.out.println(queue.popElement() + " expect " +  12);
        System.out.println(queue.size() + " expect " +  2);

        System.out.println(queue.element() + " expect " +  42);
        System.out.println(queue.popElement() + " expect " +  42);
        System.out.println(queue.size()  + " expect " +  1);

        System.out.println(!queue.isEmpty());

        System.out.println(queue.element() + " expect " +  1);
        System.out.println(queue.popElement() + " expect " +  1);
        System.out.println(queue.size() + " expect " +  0);

        System.out.println(queue.isEmpty());
    }
}