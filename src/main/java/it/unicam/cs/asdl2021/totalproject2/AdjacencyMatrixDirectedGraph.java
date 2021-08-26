/**
 * 
 */
package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Classe che implementa un grafo orientato tramite matrice di adiacenza. Non
 * sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * 
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * 
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco orientato e contiene un oggetto della classe
 * GraphEdge<L> se lo sono. Tale oggetto rappresenta l'arco.
 * 
 * Questa classe non supporta la cancellazione di nodi, ma supporta la
 * cancellazione di archi e tutti i metodi che usano indici, utilizzando
 * l'indice assegnato a ogni nodo in fase di inserimento.
 * 
 * @author Template: Luca Tesei
 *
 */
public class AdjacencyMatrixDirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    // Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
    // matrice di adiacenza
    protected Map<GraphNode<L>, Integer> nodesIndex;

    // Matrice di adiacenza, gli elementi sono null o oggetti della classe
    // GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
    // dimensione gradualmente ad ogni inserimento di un nuovo nodo.
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() {
        return this.nodesIndex.size();
    }

    @Override
    public int edgeCount() {
        int edges = 0;
        for(ArrayList<GraphEdge<L>> list : matrix){
            for(GraphEdge<L> edge : list) {
                if(edge != null) {
                    edges++;
                }
            }
        }
        return edges;
    }

    @Override
    public void clear() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa un grafo orientato
        return true;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.nodesIndex.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Non si può aggiungere un nodo nullo");
            // Throw interrompe l'esecuzione del programma
        }
        if(this.containsNode(node)){
            return false;
        }
        this.nodesIndex.put(node,nodesIndex.size());
        for(ArrayList<GraphEdge<L>> edgesList : matrix){
            edgesList.add(null);
        }
        ArrayList<GraphEdge<L>> edges = new ArrayList<>();
        for(int i = 0; i< nodesIndex.size(); i++){
            edges.add(null);
        }
        matrix.add(edges);
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Remove di nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Non si può cercare un nodo nullo");
            // Throw interrompe l'esecuzione del programma
        }
        for(GraphNode<L> nodes : this.nodesIndex.keySet()){
            if(nodes.equals(node)){
                return true;
            }
        }
        return false;
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if(label == null){
            throw new NullPointerException("Non si può cercare un nodo con etichetta nulla");
            // Throw interrompe l'esecuzione del programma
        }
        for(GraphNode<L> node : this.nodesIndex.keySet()){
            if(node.getLabel().equals(label)){
                return node;
            }
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if(label == null){
            throw new NullPointerException("Non si può cercare un nodo con etichetta nulla");
            // Throw interrompe l'esecuzione del programma
        }
        int i = 0;
        for(GraphNode<L> node : this.nodesIndex.keySet()){
            if(node.getLabel().equals(label)){
                return nodesIndex.get(node);
            }
        }
        throw new IllegalArgumentException("Il nodo cercato non esiste all'interno di questa lista");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        if(i >= nodesIndex.size()){
            throw new IndexOutOfBoundsException("Indice fuori dai valori");
        }
        for(GraphNode<L> node : nodesIndex.keySet()){
            if(nodesIndex.get(node).equals(i)){
                return node;
            }
        }
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!this.containsNode(node)){
            throw new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        Set<GraphNode<L>> nodes = new HashSet<>();
        int indexNode = this.nodesIndex.get(node);
        ArrayList<GraphEdge<L>> nodeEdges = this.matrix.get(indexNode);
        for(GraphEdge<L> edge : nodeEdges){
            if(edge != null) {
                nodes.add(edge.getNode2());
            }
        }
        return nodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!this.containsNode(node)){
            throw new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        Set<GraphNode<L>> nodes = new HashSet<>();
        int indexNode = this.nodesIndex.get(node);
        for(ArrayList<GraphEdge<L>> listEdges : matrix){
            GraphEdge<L> edgeInNode = listEdges.get(indexNode);
            if(edgeInNode != null) {
                nodes.add(edgeInNode.getNode1());
            }
        }
        return nodes;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edgesList = new HashSet<>();
        for(ArrayList<GraphEdge<L>> edges : this.matrix){
            for(GraphEdge<L> edge : edges) {
                if(edge != null) {
                    edgesList.add(edge);
                }
            }
        }
        return edgesList;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if(edge == null){
            throw new NullPointerException("L'arco passato è nullo");
        }
        if((!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2())))){
            throw new IllegalArgumentException("Almeno uno dei due nodi nell'arco non esiste nel grafo");
        }
        int nodeIndex1 = this.nodesIndex.get(edge.getNode1());
        int nodeIndex2 = this.nodesIndex.get(edge.getNode2());
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        if(!edge.equals(edgesList.get(nodeIndex2))) {
            edgesList.set(nodeIndex2, edge);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        if(edge == null){
            throw new NullPointerException("L'arco passato è nullo");
        }
        if((!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2())))){
            throw new IllegalArgumentException("Almeno uno dei due nodi nell'arco non esiste nel grafo");
        }
        int nodeIndex1 = this.nodesIndex.get(edge.getNode1());
        int nodeIndex2 = this.nodesIndex.get(edge.getNode2());
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        if(edge.equals(edgesList.get(nodeIndex2))) {
            edgesList.set(nodeIndex2, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if(edge == null){
            throw new NullPointerException("L'arco passato è nullo");
        }
        if((!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2())))){
            throw new IllegalArgumentException("Almeno uno dei due nodi nell'arco non esiste nel grafo");
        }
        int nodeIndex1 = this.nodesIndex.get(edge.getNode1());
        int nodeIndex2 = this.nodesIndex.get(edge.getNode2());
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        GraphEdge<L> edgeFound = edgesList.get(nodeIndex2);
        return edge.equals(edgeFound);
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!this.nodesIndex.containsKey(node)){
            throw  new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        Set<GraphEdge<L>> edges = new HashSet<>();
        int nodeIndex1 = this.nodesIndex.get(node);
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        for(GraphEdge<L> singleEdge : edgesList){
            if(singleEdge != null){
                edges.add(singleEdge);
            }
        }
        return edges;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if(!nodesIndex.containsKey(node)){
            throw  new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        Set<GraphEdge<L>> edges = new HashSet<>();
        int nodeIndex1 = this.nodesIndex.get(node);
        for(ArrayList<GraphEdge<L>> edgesList : this.matrix){
            GraphEdge<L> singleEdge = edgesList.get(nodeIndex1);
            //Prendo la colonna nodeIndex1 della mtrice di adiacenza
            if(singleEdge != null){
                edges.add(singleEdge);
            }
        }
        return edges;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        if((node1 == null)||(node2 == null)){
            throw new NullPointerException("Il nodo passato è nullo");
        }
        if((!this.nodesIndex.containsKey(node1)||((!this.nodesIndex.containsKey(node2))))){
            throw  new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        int nodeIndex1 = this.nodesIndex.get(node1);
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        for(GraphEdge<L> singleEdge : edgesList){
            if((singleEdge != null)&&(singleEdge.getNode2().equals(node2))){
                return singleEdge;
            }
        }
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        if((i >= this.nodesIndex.size())||(j >= this.nodesIndex.size())){
            throw new IndexOutOfBoundsException("Indice fuori dai valori");
        }
        GraphNode<L> node1 = getNodeAtIndex(i);
        GraphNode<L> node2 = getNodeAtIndex(j);
        int nodeIndex1 = this.nodesIndex.get(node1);
        ArrayList<GraphEdge<L>> edgesList = this.matrix.get(nodeIndex1);
        for(GraphEdge<L> singleEdge : edgesList){
            if((singleEdge != null)&&(singleEdge.getNode2().equals(node2))){
                return singleEdge;
            }
        }
        return null;
    }

    // TODO inserire eventuali metodi privati per fini di implementazione
}