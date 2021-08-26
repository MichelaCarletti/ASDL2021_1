package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class BinaryHeapMinPriorityQueueTest {

    PriorityQueueElement element = new PriorityQueueElement() {
        @Override
        public double getPriority() {
            return 0;
        }

        @Override
        public void setPriority(double newPriority) {

        }

        @Override
        public int getHandle() {
            return 0;
        }

        @Override
        public void setHandle(int newHandle) {

        }
    };

    @Test
    final void testBinaryHeapMinPriorityQueue() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        Assertions.assertTrue(heap.isEmpty());
    }

    @Test
    final void testInsert() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        heap.insert(element);
        Assertions.assertFalse(heap.isEmpty());
    }

    @Test
    final void testMinimum() {
        fail();
    }

    @Test
    final void testExtractMinimum() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testDecreasePriority() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testIsEmpty() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testSize() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testClear() {
        fail("Not yet implemented"); // TODO
    }

}
