package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * 
 * L'algoritmo usa una coda di min priorità tra i nodi implementata dalla classe
 * TernaryHeapMinPriorityQueue. I nodi vengono visti come PriorityQueueElement
 * poiché la classe GraphNode<L> implementa questa interfaccia. Si noti che
 * nell'esecuzione dell'algoritmo è necessario utilizzare l'operazione di
 * decreasePriority.
 * 
 * @author Template: Luca Tesei
 * 
 * @param <L>
 *                etichette dei nodi del grafo
 *
 */
public class PrimMSP<L> {

    /*
     * Coda di priorità che va usata dall'algoritmo. La variabile istanza è
     * protected solo per scopi di testing JUnit.
     */
    protected BinaryHeapMinPriorityQueue queue;
    HashMap<GraphNode<L>,GraphNode<L>> parentsMap;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        this.queue = new BinaryHeapMinPriorityQueue();
        parentsMap = new HashMap<>();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non negativi.
     * Dopo l'esecuzione del metodo nei nodi del grafo il campo previous deve
     * contenere un puntatore a un nodo in accordo all'albero di copertura
     * minimo calcolato, la cui radice è il nodo sorgente passato.
     * 
     * @param g
     *              un grafo non orientato, pesato, con pesi non negativi
     * @param s
     *              il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *              dell'albero di copertura minimo. Tale nodo sarà la radice
     *              dell'albero di copertura trovato
     * 
     * @throw NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throw IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     *        con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        if((g == null)||(s == null)){
            throw new NullPointerException("Il grafo o il nodo sorgente passati sono nulli");
        }
        if(!g.containsNode(s)){
            throw new IllegalArgumentException("Il nodo passato non è contenuto all'interno del grafo");
        }
        if(g.isDirected()){
            throw new IllegalArgumentException("Il grafo passato è orientato");
        }
        for(GraphEdge<L> edge : g.getEdges()){
            if(!edge.hasWeight()){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException(("Il grafo passato ha almeno un peso negativo"));
            }
        }
        ArrayList<GraphNode<L>> visitedNodes = new ArrayList<>();
        for(GraphNode<L> node : g.getNodes()){
            if(node.equals(s)) {
                node.setPriority(0);
            }
            else{
                node.setPriority(Double.POSITIVE_INFINITY);
            }
            this.queue.insert(node);
            parentsMap.put(null, node);
            node.setPrevious(null);
        }
        while(!this.queue.isEmpty()){
            GraphNode<L> minNode = (GraphNode<L>)this.queue.extractMinimum();
            visitedNodes.add(minNode);
            for(GraphNode<L> node : g.getNodes()){
                GraphEdge<L> edge = g.getEdge(minNode, node);
                if(((!visitedNodes.contains(node))&&(edge != null)&&(edge.getWeight() < node.getPriority()))){
                    queue.decreasePriority(node,edge.getWeight());
                    parentsMap.put(minNode, node);
                    node.setPrevious(minNode);
                }
            }
        }
    }
}
