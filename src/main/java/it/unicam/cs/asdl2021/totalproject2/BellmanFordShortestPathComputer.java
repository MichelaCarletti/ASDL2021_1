package it.unicam.cs.asdl2021.totalproject2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementazione dell'algoritmo di Bellman-Ford per il calcolo di cammini
 * minimi a sorgente singola in un grafo pesato che può contenere anche pesi
 * negativi, ma non cicli di peso negativo.
 * 
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                etichette dei nodi del grafo
 */
public class BellmanFordShortestPathComputer<L>
        implements SingleSourceShortestPathComputer<L> {

    Graph<L> graph;
    GraphNode<L> lastSource;
    Set<GraphNode<L>> shortestPathTree;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * orientato e pesato.
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
     *                                      se il grafo passato non è diretto
     * 
     * @throws IllegalArgumentException
     *                                      se il grafo passato non è pesato,
     *                                      cioè esiste almeno un arco il cui
     *                                      peso è {@code Double.NaN}.
     */
    public BellmanFordShortestPathComputer(Graph<L> graph) {
        if(graph == null){
            throw new NullPointerException("Il grafo passato è nullo");
        }
        if(graph.isEmpty()){
            throw new IllegalArgumentException("Il grafo passato è vuoto");
        }
        if(!graph.isDirected()){
            throw new IllegalArgumentException("Il grafo passato non è orientato");
        }
        for(GraphEdge<L> edge : graph.getEdges()){
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
        }
        this.graph = graph;
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        Set<GraphNode<L>> shortestPathTree = new HashSet<>();
        Set<GraphNode<L>> nodes = new HashSet<>();
        for(GraphNode<L> node : this.getGraph().getNodes()){
            node.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            nodes.add(node);
        }
        sourceNode.setFloatingPointDistance(0);
        for(GraphNode<L> graphNode : this.getGraph().getNodes()){
            for(GraphEdge<L> graphEdge : this.getGraph().getEdges()){
                GraphNode<L> node1 = graphEdge.getNode1();
                GraphNode<L> node2 = graphEdge.getNode2();
                if(node2.getFloatingPointDistance() > (node1.getFloatingPointDistance() + graphEdge.getWeight())){
                    node2.setFloatingPointDistance(node1.getFloatingPointDistance() + graphEdge.getWeight());
                }
            }
            for(GraphEdge<L> graphEdge : this.getGraph().getEdges()){
                GraphNode<L> node1 = graphEdge.getNode1();
                GraphNode<L> node2 = graphEdge.getNode2();
                if(node2.getFloatingPointDistance() > (node1.getFloatingPointDistance() + graphEdge.getWeight())){
                    throw new IllegalArgumentException("Il grafo contiene un ciclo di peso negativo");
                }
            }
        }
        this.shortestPathTree = shortestPathTree;
        this.lastSource = sourceNode;
    }

    @Override
    public boolean isComputed() {
        return this.lastSource != null;
    }

    @Override
    public GraphNode<L> getLastSource() {
        return this.lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        // TODO implementare
        return null;
    }
}
