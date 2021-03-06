package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Floyd-Warshall per il calcolo di cammini
 * minimi tra tutte le coppie di nodi in un grafo pesato che può contenere anche
 * pesi negativi, ma non cicli di peso negativo.
 * 
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                etichette dei nodi del grafo
 */
public class FloydWarshallAllPairsShortestPathComputer<L> {

    /*
     * Il grafo su cui opera questo calcolatore.
     */
    private Graph<L> graph;

    /*
     * Matrice dei costi dei cammini minimi. L'elemento in posizione i,j
     * corrisponde al costo di un cammino minimo tra il nodo i e il nodo j, dove
     * i e j sono gli interi associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private double[][] costMatrix;

    /*
     * Matrice dei predecessori. L'elemento in posizione i,j è -1 se non esiste
     * nessun cammino tra i e j oppure corrisponde all'indice di un nodo che
     * precede il nodo j in un qualche cammino minimo da i a j. Si intende che i
     * e j sono gli indici associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private int[][] predecessorMatrix;

    boolean isComputed = false;

    /**
     * Crea un calcolatore di cammini minimi fra tutte le coppie di nodi per un
     * grafo orientato e pesato. Non esegue il calcolo, che viene eseguito
     * invocando successivamente il metodo computeShortestPaths().
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
     */
    public FloydWarshallAllPairsShortestPathComputer(Graph<L> g) {
        if(g == null){
            throw new NullPointerException("Il grafo passato è nullo");
        }
        if(g.isEmpty()){
            throw new IllegalArgumentException("Il grafo passato è vuoto");
        }
        if(!g.isDirected()){
            throw new IllegalArgumentException("Il grafo passato non è orientato");
        }
        for(GraphEdge<L> edge : g.getEdges()){
            if(edge.getWeight() == Double.NaN){
                throw new IllegalArgumentException(("Il grafo passato non è pesato"));
            }
        }
        this.graph = g;
        this.predecessorMatrix = new int[g.nodeCount()][g.nodeCount()];
        this.costMatrix = new double[g.nodeCount()][g.nodeCount()];
    }

    /**
     * Esegue il calcolo per la matrice dei costi dei cammini minimi e per la
     * matrice dei predecessori così come specificato dall'algoritmo di
     * Floyd-Warshall.
     * 
     * @throws IllegalStateException
     *                                   se il calcolo non può essere effettuato
     *                                   per via dei valori dei pesi del grafo,
     *                                   ad esempio se il grafo contiene cicli
     *                                   di peso negativo.
     */
    public void computeShortestPaths() {
        this.isComputed = true;
        int nodeCount = this.getGraph().nodeCount();
        for(int i = 0; i < nodeCount; i ++){
            for(int j = 0; j < nodeCount; j ++){
                GraphEdge<L> edge = this.getGraph().getEdgeAtNodeIndexes(i, j);
                if(edge != null){
                    this.predecessorMatrix[i][j] = i;
                    this.costMatrix[i][j] = edge.getWeight();
                }
                else{
                    this.predecessorMatrix[i][j] = -1;
                    this.costMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for(int k = 0; k < nodeCount; k ++){
            for(int i = 0; i < nodeCount; i ++) {
                for (int j = 0; j < nodeCount; j++) {
                    double newDistance = this.costMatrix[i][k] + this.costMatrix[k][j];
                    if(newDistance < this.costMatrix[i][j]){
                        this.costMatrix[i][j] = newDistance;
                        this.predecessorMatrix[i][j] = k;
                    }
                }
            }
        }
    }

    /**
     * Determina se è stata invocatala procedura di calcolo dei cammini minimi.
     * 
     * @return true se i cammini minimi sono stati calcolati, false altrimenti
     */
    public boolean isComputed() {
        return this.isComputed;
    }

    /**
     * Restituisce il grafo su cui opera questo calcolatore.
     * 
     * @return il grafo su cui opera questo calcolatore
     */
    public Graph<L> getGraph() {
        return this.graph;
    }

    /**
     * Restituisce una lista di archi da un nodo sorgente a un nodo target. Tale
     * lista corrisponde a un cammino minimo tra i due nodi nel grafo gestito da
     * questo calcolatore.
     * 
     * @param sourceNode
     *                       il nodo di partenza del cammino minimo da
     *                       restituire
     * @param targetNode
     *                       il nodo di arrivo del cammino minimo da restituire
     * @return la lista di archi corrispondente al cammino minimo; la lista è
     *         vuota se il nodo sorgente è il nodo target. Viene restituito
     *         {@code null} se il nodo target non è raggiungibile dal nodo
     *         sorgente
     * 
     * @throws NullPointerException
     *                                      se almeno uno dei nodi passati è
     *                                      nullo
     * 
     * @throws IllegalArgumentException
     *                                      se almeno uno dei nodi passati non
     *                                      esiste
     * 
     * @throws IllegalStateException
     *                                      se non è stato eseguito il calcolo
     *                                      dei cammini minimi
     * 
     * 
     */
    public List<GraphEdge<L>> getShortestPath(GraphNode<L> sourceNode,
            GraphNode<L> targetNode) {
        int indexSource = getGraph().getNodeIndexOf(sourceNode.getLabel());
        int indexTarget = getGraph().getNodeIndexOf(targetNode.getLabel());
        int predecessor = this.predecessorMatrix[indexSource][indexTarget];
        List<GraphEdge<L>> edges = new ArrayList<>();
        while(indexSource != predecessor) {
            if (predecessor != -1) {
                edges.add(getGraph().getEdgeAtNodeIndexes(predecessor, indexTarget));
                indexTarget = predecessor;
                predecessor = this.predecessorMatrix[indexSource][indexTarget];
            } else {
                //Non esite il cammino tra sourceNode e targetNode
                return null;
            }
        }
        Collections.reverse(edges);
        return edges;
    }

    /**
     * Restituisce il costo di un cammino minimo da un nodo sorgente a un nodo
     * target.
     * 
     * @param sourceNode
     *                       il nodo di partenza del cammino minimo
     * @param targetNode
     *                       il nodo di arrivo del cammino minimo
     * @return il coso di un cammino minimo tra il nodo sorgente e il nodo
     *         target. Viene restituito {@code Double.POSITIVE_INFINITY} se il
     *         nodo target non è raggiungibile dal nodo sorgente, mentre viene
     *         restituito zero se il nodo sorgente è il nodo target.
     * 
     * @throws NullPointerException
     *                                      se almeno uno dei nodi passati è
     *                                      nullo
     * 
     * @throws IllegalArgumentException
     *                                      se almeno uno dei nodi passati non
     *                                      esiste
     * 
     * @throws IllegalStateException
     *                                      se non è stato eseguito il calcolo
     *                                      dei cammini minimi
     * 
     * 
     */
    public double getShortestPathCost(GraphNode<L> sourceNode,
            GraphNode<L> targetNode) {
        // TODO implementare
        return Double.NaN;
    }

    /**
     * Genera una stringa di descrizione di un path riportando i nodi
     * attraversati e i pesi degli archi. Nel caso di cammino vuoto genera solo
     * la stringa {@code "[ ]"}.
     * 
     * @param path
     *                 un cammino minimo
     * @return una stringa di descrizione del cammino minimo
     * @throws NullPointerException
     *                                  se il cammino passato è nullo
     */
    public String printPath(List<GraphEdge<L>> path) {
        if (path == null)
            throw new NullPointerException(
                    "Richiesta di stampare un path nullo");
        if (path.isEmpty())
            return "[ ]";
        // Costruisco la stringa
        StringBuffer s = new StringBuffer();
        s.append("[ " + path.get(0).getNode1().toString());
        for (int i = 0; i < path.size(); i++)
            s.append(" -- " + path.get(i).getWeight() + " --> "
                    + path.get(i).getNode2().toString());
        s.append(" ]");
        return s.toString();
    }

    /**
     * @return the costMatrix
     */
    public double[][] getCostMatrix() {
        return costMatrix;
    }

    /**
     * @return the predecessorMatrix
     */
    public int[][] getPredecessorMatrix() {
        return predecessorMatrix;
    }

    // TODO inserire eventuali metodi privati per fini di implementazione
}
