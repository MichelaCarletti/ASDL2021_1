package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Gli oggetti di questa classe sono calcolatori di cammini minimi con sorgente
 * singola su un certo grafo orientato e pesato dato. Il grafo su cui lavorare
 * deve essere passato quando l'oggetto calcolatore viene costruito e non può
 * contenere archi con pesi negativi. Il calcolatore implementa il classico
 * algoritmo di Dijkstra per i cammini minimi con sorgente singola utilizzando
 * una coda con priorità che estrae l'elemento con priorità minima e aggiorna le
 * priorità con l'operazione decreasePriority in tempo logaritmico (coda
 * realizzata con uno heap binario). In questo caso il tempo di esecuzione
 * dell'algoritmo di Dijkstra è {@code O(n log m)} dove {@code n} è il numero di
 * nodi del grafo e {@code m} è il numero di archi.
 * 
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                il tipo delle etichette dei nodi del grafo
 */
public class DijkstraShortestPathComputer<L>
        implements SingleSourceShortestPathComputer<L> {

    Graph<L> graph;
    GraphNode<L> lastSource;
    HashMap<GraphNode<L>,GraphNode<L>> parentsMap;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * diretto e pesato privo di pesi negativi.
     * 
     * @param graph
     *                  il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException
     *                                      se il grafo passato è nullo
     * 
     * @throws IllegalArgumentException
     *                                      se il grafo passato è vuoto
     * 
     * @throws IllegalArgumentException
     *                                      se il grafo passato non è orientato
     * 
     * @throws IllegalArgumentException
     *                                      se il grafo passato non è pesato,
     *                                      cioè esiste almeno un arco il cui
     *                                      peso è {@code Double.NaN}
     * @throws IllegalArgumentException
     *                                      se il grafo passato contiene almeno
     *                                      un peso negativo
     */
    public DijkstraShortestPathComputer(Graph<L> graph) {
        if(graph == null){
            throw new NullPointerException("Il grafo passato è nullo");
        }
        if(graph.isEmpty()){
            throw new IllegalArgumentException(("Il grafo passato è vuoto"));
        }
        if(!graph.isDirected()){
            throw new IllegalArgumentException(("Il grafo passato non è orientato"));
        }
        for(GraphEdge<L> edge : graph.getEdges()){
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException(("Il grafo passato ha almeno un peso negativo"));
            }
        }
        this.graph = graph;
        parentsMap = new HashMap<>();
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

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        Set<GraphNode<L>> nodes = new HashSet<>();
        for(GraphNode<L> node : this.getGraph().getNodes()){
            node.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            nodes.add(node);
        }
        sourceNode.setFloatingPointDistance(0);
        while(!nodes.isEmpty()){
            GraphNode<L> minNode = extractMin(nodes);               //Estraggo il nodo con distanza minima
            for(GraphNode<L> adjacentNode : getGraph().getAdjacentNodesOf(minNode)){
                GraphEdge<L> edge = getGraph().getEdge(minNode,adjacentNode);
                if(edge != null) {
                    double distance = minNode.getFloatingPointDistance() + edge.getWeight();
                    if (adjacentNode.getFloatingPointDistance() > distance) {
                        adjacentNode.setFloatingPointDistance(distance);
                        parentsMap.put(adjacentNode,minNode);
                    }
                }
            }
        }
        this.lastSource = sourceNode;
    }

    @Override
    public boolean isComputed() {
        return this.lastSource != null;
    }

    @Override
    public GraphNode<L> getLastSource() {
        if(!isComputed()){
            throw new IllegalStateException("Non è mai stato eseguito il calcolo del cammino minimo");
        }
        return this.lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        if(targetNode == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!this.getGraph().containsNode(targetNode)){
            throw new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        if(!isComputed()){
            throw new IllegalStateException("Non è mai stato eseguito il calcolo del cammino minimo");
        }
        List<GraphEdge<L>> edges = new ArrayList<>();
        GraphNode<L> child = targetNode;
        while(child != getLastSource()){
            //Percorso all'indietro sulla lista che associa nodi padre e nodi figli del percorso minimo
            GraphNode<L> parent = parentsMap.get(child);
            if(parent != null){
                edges.add(graph.getEdge(parent,child));
            }
            else{
                //Se parent è null vuol dire che non c'è l'arco, quindi il percorso non esiste
                return null;
            }
        }
        Collections.reverse(edges);
        return edges;
    }
}
