package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * 
 * Classe singoletto che implementa l'algoritmo di Kruskal per trovare un
 * Minimum Spanning Tree di un grafo non orientato, pesato e con pesi non
 * negativi.
 * 
 * @author Template: Luca Tesei
 * 
 * @param <L>
 *                etichette dei nodi del grafo
 *
 */
public class KruskalMSP<L> {

    /*
     * Struttura dati per rappresentare gli insiemi disgiunti utilizzata
     * dall'algoritmo di Kruskal.
     */
    private ArrayList<HashSet<GraphNode<L>>> disjointSets;



    /**
     * Costruisce un calcolatore di un albero di copertura minimo che usa
     * l'algoritmo di Kruskal su un grafo non orientato e pesato.
     */
    public KruskalMSP() {
        this.disjointSets = new ArrayList<HashSet<GraphNode<L>>>();
    }

    /**
     * Utilizza l'algoritmo goloso di Kruskal per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non
     * negativi. L'albero restituito non è radicato, quindi è rappresentato
     * semplicemente con un sottoinsieme degli archi del grafo.
     * 
     * @param g
     *              un grafo non orientato, pesato, con pesi non negativi
     * @return l'insieme degli archi del grafo g che costituiscono l'albero di
     *         copertura minimo trovato
     * @throw NullPointerException se il grafo g è null
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     *        con pesi negativi
     */
    public Set<GraphEdge<L>> computeMSP(Graph<L> g) {
        if(g == null){
            throw new NullPointerException("Il grafo passato è nullo");
        }
        if(g.isDirected()){
            throw new IllegalArgumentException(("Il grafo passato è orientato"));
        }
        for(GraphEdge<L> edge : g.getEdges()){
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException(("Il grafo passato ha almeno un peso negativo"));
            }
        }
        Set<GraphEdge<L>> result = new HashSet<>();
        for(GraphNode<L> node : g.getNodes()){
            HashSet<GraphNode<L>> set = new HashSet<>();
            set.add(node);
            disjointSets.add(set);
        }
        ArrayList<GraphEdge<L>> orderedEdges = new ArrayList<>();
        //A differenza di Set, con LinkedHashSet l'ordine degli elementi è garantito
        orderedEdges.addAll(g.getEdges());
        Collections.sort(orderedEdges);
        for(GraphEdge<L> edge : orderedEdges){
            int set1 = findSet(edge.getNode1());
            int set2 = findSet(edge.getNode2());
            if(set1 != set2){
                result.add(edge);
                union(set1 , set2);
            }
        }
        return result;
    }

    private int findSet(GraphNode<L> node){
        for(int i = 0; i < disjointSets.size(); i ++){
            Set<GraphNode<L>> nodes = disjointSets.get(i);
            if(nodes.contains(node)){
                return i;
            }
        }
        return -1;
    }

    private void union(int indexSet1, int indexSet2){
        Set<GraphNode<L>> set1 = disjointSets.get(indexSet1);
        Set<GraphNode<L>> set2 = disjointSets.get(indexSet2);
        set1.addAll(set2);
        disjointSets.remove(set2);
    }
}
