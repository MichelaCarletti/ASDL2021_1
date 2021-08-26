package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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


    // TODO implementare: inserire eventuali altre variabili istanza

    /**
     * Costruisce un calcolatore di un albero di copertura minimo che usa
     * l'algoritmo di Kruskal su un grafo non orientato e pesato.
     */
    public KruskalMSP() {
        this.disjointSets = new ArrayList<HashSet<GraphNode<L>>>();
        // TODO implementare: completare con eventuali altre inizializzazioni
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
        if(!g.isDirected()){
            throw new IllegalArgumentException(("Il grafo passato non è orientato"));
        }
        for(GraphEdge<L> edge : g.getEdges()){
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException(("Il grafo passato ha almeno un peso negativo"));
            }
        }
        Set<GraphEdge<L>> tidyEdges = g.getEdges();
        for(GraphNode<L> node : g.getNodes()){
            makeSet(node);
        }
        edgesInsertionSort(tidyEdges);
        return null;
    }

    private void makeSet(GraphNode<L> noe){
        // TODO Cosa fa makeSet?
    }

    private void edgesInsertionSort(Set<GraphEdge> tidyEdges){
        int length = tidyEdges.size();
        Double edgesWeight[] = new Double[length];
        int i = 0;
        for(GraphEdge<L> edge : tidyEdges){
            edgesWeight[i] = edge.getWeight();
            i ++;
        }
        i = 0;
        Double key = 0.0;
        for(int j = 0; j < length; j ++){
            key = edgesWeight[j];
            i = j - 1;
            while((i > 0) && (edgesWeight[i] > key)){
                edgesWeight[i + 1] = edgesWeight[i];
                i = i -1;
            }
            edgesWeight[i + 1] = key;
        }
    }
    // TODO implementare: inserire eventuali metodi privati per fini di
    // implementazione
}
