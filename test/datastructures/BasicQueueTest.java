package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Generic type arguments must be reference types.
 * Since primitives do not extend Object they cannot be used as generic type arguments for a parametrized type.
 */
public class BasicQueueTest {
    private BasicQueue<Integer> myQueue;

    @Before
    public void setUp() {
        myQueue = new BasicQueue<>(5);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(10);
    }

    @Test
    public void testEmptySize() {
        while (myQueue.size() > 0) {
            myQueue.deQueue();
        }
        assertEquals(0, myQueue.size());
    }

    @Test
    public void testEnqueueAndSize() {

        assertEquals(3, myQueue.size());
    }

    @Test
    public void testDequeue() {
        int x = myQueue.deQueue();
        assertEquals(5, x);
    }

    @Test
    public void testContains() {
        assertTrue(myQueue.contains(10));
    }

    @Test
    public void testAccess() {
        int x = myQueue.access(1);
        assertEquals(6, x);
    }

    @Test
    public void testToString() {
        assertEquals("[5, 6, 10]", myQueue.toString());
    }
}