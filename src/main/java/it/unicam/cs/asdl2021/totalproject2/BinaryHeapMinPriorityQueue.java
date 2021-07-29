/**
 * 
 */
package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Implementazione di una coda con priorità tramite heap binario. Gli oggetti
 * inseriti in coda implementano l'interface PriorityQueueElement che permette
 * di gestire la priorità e una handle dell'elemento. La handle è fondamentale
 * per realizzare in tempo logaritmico l'operazione di decreasePriority che,
 * senza la handle, dovrebbe cercare l'elemento all'interno dello heap e poi
 * aggiornare la sua posizione. Nel caso di heap binario rappresentato con una
 * ArrayList la handle è semplicemente l'indice dove si trova l'elemento
 * nell'ArrayList. Tale campo naturalmente va tenuto aggiornato se l'elemento
 * viene spostato in un'altra posizione.
 * 
 * @author Template: Luca Tesei
 * 
 * @param <E>
 *                il tipo degli elementi che vengono inseriti in coda.
 *
 */
public class BinaryHeapMinPriorityQueue {

    /*
     * ArrayList per la rappresentazione dello heap. Vengono usate tutte le
     * posizioni (la radice dello heap è quindi in posizione 0).
     */
    private ArrayList<PriorityQueueElement> heap;

    // TODO implementare: inserire eventuali altre variabili istanza

    /**
     * Crea una coda con priorità vuota.
     * 
     */
    public BinaryHeapMinPriorityQueue() {
       this.heap = new ArrayList<>();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the heap. The handle of the element will also be set
     * accordingly.
     * 
     * @param element
     *                    the new element to add
     * @throws NullPointerException
     *                                  if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        if(element == null){
            throw new NullPointerException("L'elemento passato è nullo");
        }
        appendElement(element);
        shiftUp(element);
    }

    private void appendElement(PriorityQueueElement element){
        element.setHandle(heap.size());             //Aggiorno l'handle dell'elemento
        this.heap.add(element);
    }

    private void shiftUp(PriorityQueueElement element){
        while (element.getHandle() > 0 &&
                parent(element).getPriority() > element.getPriority()){
            swap(parent(element), element);
        }
    }

    private void swap(PriorityQueueElement element1, PriorityQueueElement element2){
        Collections.swap(this.heap,element1.getHandle(),element2.getHandle());      //Scambia la posizione nell'albero di element1 ed element2
        int temp = element1.getHandle();
        element1.setHandle(element2.getHandle());
        element2.setHandle(temp);
    }

    private int parent(int handle){
        return (handle - 1) / 2;
    }

    private int leftChild(int handle){
        return (handle * 2) + 1;
    }

    private int rightChild(int handle){
        return (handle * 2) + 2;
    }

    private PriorityQueueElement parent(PriorityQueueElement element){
        return (this.heap.get(parent(element.getHandle())));
    }

    private PriorityQueueElement leftChild(PriorityQueueElement element){
        return (this.heap.get(leftChild(element.getHandle())));
    }

    private PriorityQueueElement rightChild(PriorityQueueElement element){
        return (this.heap.get(rightChild(element.getHandle())));
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the heap.
     * 
     * @return the current minimum element of this min-priority queue
     * 
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        if(heap.isEmpty()){
            throw new NoSuchElementException("La coda di priorità è vuota");
        }
        return this.heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     * 
     * @return the current minimum element
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        if(heap.isEmpty()){
            throw new NoSuchElementException("La coda di priorità è vuota");
        }
        return this.heap.get(0);

    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     * 
     * @param element
     *                        the element whose priority will be decreased, it
     *                        must currently be inside this min-priority queue
     * @param newPriority
     *                        the new priority to assign to the element
     * 
     * @throws NoSuchElementException
     *                                      if the element is not currently
     *                                      present in this min-priority queue
     * @throws IllegalArgumentException
     *                                      if the specified newPriority is not
     *                                      strictly less than the current
     *                                      priority of the element
     */
    public void decreasePriority(PriorityQueueElement element,
            double newPriority) {
        // TODO implementare

    }

    /**
     * Determines if this priority queue is empty.
     * 
     * @return true if this priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if(heap.size() == 0) {
            return true;
        }
        return false;

        //TODO implementare
    }

    /**
     * Return the current size of this queue.
     * 
     * @return the number of elements currently in this queue.
     */
    public int size() {
        int cont = 0;
        for(PriorityQueueElement element : heap){
            cont++;
        }
        return cont;
        //TODO implementare
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    // TODO inserire eventuali altri metodi privati per scopi di implementazione

}
