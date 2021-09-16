package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

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
    HashMap<GraphNode<L>,GraphNode<L>> parentsMap;

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
            if(!edge.hasWeight()){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
        }
        this.graph = graph;
        parentsMap = new HashMap<>();
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        GraphNode<L> localSourceNode = getNodeFrom(sourceNode);
        Set<GraphNode<L>> nodes = new HashSet<>();
        for(GraphNode<L> node : this.getGraph().getNodes()){
            node.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            nodes.add(node);
        }
        localSourceNode.setFloatingPointDistance(0);
        for(int i = 0; i < graph.nodeCount(); i ++) {
            for (GraphEdge<L> graphEdge : this.getGraph().getEdges()) {
                GraphNode<L> node1 = graphEdge.getNode1();
                GraphNode<L> node2 = graphEdge.getNode2();
                if (node2.getFloatingPointDistance() > (node1.getFloatingPointDistance() + graphEdge.getWeight())) {
                    node2.setFloatingPointDistance(node1.getFloatingPointDistance() + graphEdge.getWeight());
                    parentsMap.put(node2, node1);
                }
            }
        }
        for(GraphEdge<L> graphEdge : this.getGraph().getEdges()){
            GraphNode<L> node1 = graphEdge.getNode1();
            GraphNode<L> node2 = graphEdge.getNode2();
            if(node2.getFloatingPointDistance() > (node1.getFloatingPointDistance() + graphEdge.getWeight())){
               throw new IllegalArgumentException("Il grafo contiene un ciclo di peso negativo");
           }
        }
        this.lastSource = localSourceNode;
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
        GraphNode<L> localTargetNode = getNodeFrom(targetNode);
        if(localTargetNode == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!this.getGraph().containsNode(localTargetNode)){
            throw new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        if(!isComputed()){
            throw new IllegalStateException("Non è mai stato eseguito il calcolo del cammino minimo");
        }
        List<GraphEdge<L>> edges = new ArrayList<>();
        GraphNode<L> child = localTargetNode;
        while(!child.equals(getLastSource())){
            //Percorso all'indietro sulla lista che associa nodi padre e nodi figli del percorso minimo
            GraphNode<L> parent = parentsMap.get(child);
            if(parent != null){
                edges.add(graph.getEdge(parent,child));
                child = parent;
            }
            else{
                //Se parent è null vuol dire che non c'è l'arco, quindi il percorso non esiste
                return null;
            }
        }
        Collections.reverse(edges);
        return edges;
    }

    private GraphNode<L> getNodeFrom(GraphNode<L> node){
        int index = graph.getNodeIndexOf(node.getLabel());
        return graph.getNodeAtIndex(index);
    }
}
