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
        for(GraphNode<L> node : g.getNodes()){
            //Creare V alberi, uno per ogni vertice
        }
        Set<GraphEdge<L>> orderedEdges = orderEdges(g.getEdges());
        for(GraphEdge<L> edge : g.getEdges()){
            //Verifica se i due nodi dell'arco appartengono allo stesso albero
        }
        return null;
    }

    private Set<GraphEdge<L>> orderEdges(Set<GraphEdge<L>> edges){
        GraphEdge<L> key;
        int j;
        for(int i = 0; i < edges.size(); i ++){
            key = edges[i];
            j = i -1;
            while((j > 0)&&(edges[j] > key)){
                edges[j + 1] = edges[j];
                j = j -1;
            }
            edges[i + 1] = key;
        }
        return edges;
    }
}
