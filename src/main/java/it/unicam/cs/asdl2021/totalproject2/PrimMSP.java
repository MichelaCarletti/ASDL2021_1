package it.unicam.cs.asdl2021.totalproject2;

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
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException(("Il grafo passato ha almeno un peso negativo"));
            }
        }
        for(GraphNode<L> node : g.getNodes()){
            node.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            parentsMap.put(null, node);
        }
        Set<GraphNode<L>> nodes = g.getNodes();
        while(!nodes.isEmpty()){
            GraphNode<L> minNode = extractMin(nodes);
            for(GraphNode<L> node : g.getNodes()){
                GraphEdge<L> edge = g.getEdge(minNode, node);
                if((nodes.contains(node)&&(edge != null)&&(edge.getWeight() < node.getFloatingPointDistance()))){
                    node.setFloatingPointDistance(edge.getWeight());
                    parentsMap.put(minNode, node);
                }
            }
        }
    }

    private GraphNode<L> extractMin(Set<GraphNode<L>> nodes){
        GraphNode<L> minNode = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for(GraphNode<L> node : nodes){
            if(node.getFloatingPointDistance() < minDistance){
                minDistance = node.getFloatingPointDistance();
                minNode = node;
            }
        }
        nodes.remove(minNode);
        return minNode;
    }

}
