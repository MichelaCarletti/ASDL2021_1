/**
 * 
 */
package it.unicam.cs.asdl2021.totalproject2;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * non orientato. Non sono accettate etichette dei nodi null e non sono
 * accettate etichette duplicate nei nodi (che in quel caso sono lo stesso
 * nodo).
 * 
 * Per la rappresentazione viene usata una variante della rappresentazione con
 * liste di adiacenza. A differenza della rappresentazione standard si usano
 * strutture dati più efficienti per quanto riguarda la complessità in tempo
 * della ricerca se un nodo è presente (pseudocostante, con tabella hash) e se
 * un arco è presente (pseudocostante, con tabella hash). Lo spazio occupato per
 * la rappresentazione risulta tuttavia più grande di quello che servirebbe con
 * la rappresentazione standard.
 * 
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è l'insieme dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi collegati al nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presente basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 * 
 * Questa classe non supporta le operazioni indicizzate di ricerca di nodi e
 * archi.
 * 
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                etichette dei nodi del grafo
 */
public class MapAdjacentListUndirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi collegati. Nel caso in cui un nodo
     * non abbia archi collegati è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListUndirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    @Override
    public int nodeCount() {
        return this.adjacentLists.size();
    }

    @Override
    public int edgeCount() {
        HashSet<GraphEdge<L>> edges = new HashSet<GraphEdge<L>>();
        //Cicla ogni nodo e inserisce tutti gli archi ad esso collegati all'interno di edges. Il metodo addAll controlla già che non ce ne siano due o più uguali
        for(Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : this.adjacentLists.entrySet()){
            edges.addAll(entry.getValue());
        }
        return edges.size();
    }

    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi non orientati
        return false;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        //keySet ritorna l'insieme delle chiavi (che in questo caso sono i nodi)
        return this.adjacentLists.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {

        //Scorrere la lista dei nodi per controllare se node è già presente nella lista.
        //Eccezione da gestire se il nodo passato è null
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");  // Throw interrompe l'esecuzione del programma
        }
        if(this.containsNode(node)){
            return false;
        }
        HashSet<GraphEdge<L>> edges = new HashSet<GraphEdge<L>>();
        this.adjacentLists.put(node,edges);
        return true;
        //TODO metodo shift up o shift down
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Non si può eliminare un nodo nullo");  // Throw interrompe l'esecuzione del programma
        }
        return (this.adjacentLists.remove(node) != null);     // Ritorna il valore associato alla chiave o null se non c'è la chiave. L'eccezione UnsupportedOperationException viene già lanciata dal metodo remove
        //TODO metodo shift up o shift down
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");  // Throw interrompe l'esecuzione del programma
        }
        return this.adjacentLists.containsKey(node);        // Il metodo containsKey ritorna true se trova quella chiave in adjacentLists. False altrimenti
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if(label == null){
            throw new NullPointerException("L'etichetta passata è nulla");  // Throw interrompe l'esecuzione del programma
        }
        for(GraphNode<L> currentNode : this.adjacentLists.keySet()){
            if(label.equals(currentNode.getLabel())) {
                return currentNode;
            }
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException(
                    "Tentativo di ricercare un nodo con etichetta null");
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if(node == null){
            throw new NullPointerException("Non si può eliminare un nodo nullo");  // Throw interrompe l'esecuzione del programma
        }
        if(!this.containsNode(node)){
            throw new IllegalArgumentException("Il nodo non esiste");
        }
        Set<GraphEdge<L>> edges = this.adjacentLists.get(node);   // Ritorna la lista di archi associata a quel nodo
        Set<GraphNode<L>> nodeList = new HashSet<GraphNode<L>>();
        for(GraphEdge<L> currentEdge: edges){
            if((currentEdge.getNode1().equals(node))) {
                nodeList.add(currentEdge.getNode2());
            }
            else{
                nodeList.add(currentEdge.getNode1());           // Se c'è un loop il nodo è adiacente a sé stesso
            }
        }
        return nodeList;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi predecessori non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edges = new HashSet<GraphEdge<L>>();
        for(Set<GraphEdge<L>> nodeEdges : this.adjacentLists.values()){
            edges.addAll(nodeEdges);                // addAll aggiunge edge se e solo se edge non è già presente nella lista di archi. Prende tutti gli elementi di nodeEdges e li aggiunge ad edges
        }
        return edges;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if(edge == null){
            throw new NullPointerException("Non si può aggiungere un arco nullo");  // Throw interrompe l'esecuzione del programma
        }
        if(!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2()))){
            throw new IllegalArgumentException("Almeno uno dei due nodi collegati all'arco non esiste");
        }
        if(edge.isDirected()) {
            throw new IllegalArgumentException("Non si può aggiungere un arco orientato in un grafo non orientato");
        }
        else {
            boolean node1Added = this.getEdgesOf(edge.getNode1()).add(edge);
            boolean node2Added = this.getEdgesOf(edge.getNode2()).add(edge);
            return node1Added && node2Added;                // Ritorna true solo se entrambe sono vere
        }
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        if(!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2()))){
            throw new IllegalArgumentException("Almeno uno dei due nodi collegati all'arco non esiste");
        }
        if(edge == null){
            throw new NullPointerException("Non si può aggiungere un arco nullo");  // Throw interrompe l'esecuzione del programma
        }
        boolean node1Removed = this.getEdgesOf(edge.getNode1()).remove(edge);
        boolean node2Removed = this.getEdgesOf(edge.getNode2()).remove(edge);
        return node1Removed && node2Removed;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if(edge == null){
            throw new NullPointerException("Non si può aggiungere un arco nullo");  // Throw interrompe l'esecuzione del programma
        }
        if(!this.containsNode(edge.getNode1())||(!this.containsNode(edge.getNode2()))){
            throw new IllegalArgumentException("Almeno uno dei due nodi collegati all'arco non esiste");
        }
        for(Set<GraphEdge<L>> nodeEdges : this.adjacentLists.values()){
            if(nodeEdges.contains(edge)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if(!this.containsNode(node)){
            throw new IllegalArgumentException("Il nodo passato non esiste nel grafo");
        }
        if(node == null){
            throw new NullPointerException("Il nodo passato è nullo");  // Throw interrompe l'esecuzione del programma
        }
        return this.adjacentLists.get(node);
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca degli archi entranti non supportata in un grafo non orientato");
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        if((node1 == null)||(node2 == null)){
            throw new IllegalArgumentException("Almeno uno dei due nodi collegati all'arco è null");
        }
        if(!this.containsNode(node1)||(!this.containsNode(node2))){
            throw new IllegalArgumentException("Almeno uno dei due nodi collegati all'arco non esiste");
        }
        Set<GraphEdge<L>> edgesNode1 = this.getEdgesOf(node1);
        for(GraphEdge<L> edge : edgesNode1){
            if ((node1.equals(edge.getNode1()) && node2.equals(edge.getNode2()))
                    ||(node2.equals(edge.getNode1()) && node1.equals(edge.getNode2()))) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        throw new UnsupportedOperationException(
                "Operazioni con indici non supportate");
    }

}
