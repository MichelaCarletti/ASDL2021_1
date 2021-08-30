package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
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
        GraphNode<Integer> node1 = new GraphNode<>(0);
        heap.insert(node1);
        Assertions.assertFalse(heap.isEmpty());
        boolean nullException = false;
        try{
            heap.insert(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testMinimum() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        GraphNode<Integer> node1 = new GraphNode<>(0);
        node1.setPriority(6);
        GraphNode<Integer> node2 = new GraphNode<>(1);
        GraphNode<Integer> node3 = new GraphNode<>(6);
        heap.insert(node2);
        heap.insert(node3);
        heap.insert(node1);
        Assertions.assertEquals(node3,heap.minimum());
        /*heap.clear();
        boolean noSuchException = false;
        try{
            heap.minimum();
        }
        catch(NoSuchElementException e){
            noSuchException = true;
        }
        Assertions.assertTrue(noSuchException);*/
    }

    @Test
    final void testExtractMinimum() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        GraphNode<Integer> node1 = new GraphNode<>(4);
        GraphNode<Integer> node2 = new GraphNode<>(1);
        GraphNode<Integer> node3 = new GraphNode<>(6);
        heap.insert(node1);
        heap.insert(node2);
        heap.insert(node3);
        Assertions.assertEquals(node3,heap.extractMinimum());
        /*heap.clear();
        boolean noSuchException = false;
        try{
            heap.extractMinimum();
        }
        catch(NoSuchElementException e){
            noSuchException = true;
        }
        Assertions.assertTrue(noSuchException);*/
    }

    @Test
    final void testDecreasePriority() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        GraphNode<Integer> node1 = new GraphNode<>(0);
        GraphNode<Integer> node2 = new GraphNode<>(5);
        GraphNode<Integer> node3 = new GraphNode<>(6);
        GraphNode<Integer> node4 = new GraphNode<>(1);
        heap.insert(node1);
        heap.insert(node2);
        heap.insert(node3);
        heap.decreasePriority(node3, 2);
        Assertions.assertEquals(node2, heap.minimum());
        /*boolean noSuchException = false;
        boolean illegalException = false;
        try{
            heap.decreasePriority(node4, 0);
        }
        catch(NoSuchElementException e){
            noSuchException = true;
        }
        try{
            heap.decreasePriority(node1, 10);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(noSuchException);
        Assertions.assertTrue(illegalException);*/
    }

    @Test
    final void testIsEmpty() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        Assertions.assertTrue(heap.isEmpty());
    }

    @Test
    final void testSize() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        GraphNode<Integer> node1 = new GraphNode<>(0);
        GraphNode<Integer> node2 = new GraphNode<>(5);
        GraphNode<Integer> node3 = new GraphNode<>(6);
        GraphNode<Integer> node4 = new GraphNode<>(1);
        heap.insert(node1);
        heap.insert(node2);
        heap.insert(node3);
        heap.insert(node4);
        Assertions.assertEquals(4, heap.size());
    }

    @Test
    final void testClear() {
        BinaryHeapMinPriorityQueue heap = new BinaryHeapMinPriorityQueue();
        GraphNode<Integer> node1 = new GraphNode<>(0);
        GraphNode<Integer> node2 = new GraphNode<>(5);
        GraphNode<Integer> node3 = new GraphNode<>(6);
        GraphNode<Integer> node4 = new GraphNode<>(1);
        heap.insert(node1);
        heap.insert(node2);
        heap.insert(node3);
        heap.insert(node4);
        Assertions.assertFalse(heap.isEmpty());
        heap.clear();
        Assertions.assertTrue(heap.isEmpty());
    }

}
