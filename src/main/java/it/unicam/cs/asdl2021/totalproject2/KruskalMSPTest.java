package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class KruskalMSPTest {

    @Test
    final void testComputeMSP() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode(e);
        GraphNode<String> f = new GraphNode<String>("f");
        gr.addNode(f);
        GraphNode<String> g = new GraphNode<String>("g");
        gr.addNode(g);
        GraphNode<String> h = new GraphNode<String>("h");
        gr.addNode(h);
        GraphNode<String> i = new GraphNode<String>("i");
        gr.addNode(i);
        gr.addEdge(new GraphEdge<String>(a, b, false, 4));
        gr.addEdge(new GraphEdge<String>(a, h, false, 8.5));
        gr.addEdge(new GraphEdge<String>(b, h, false, 11));
        gr.addEdge(new GraphEdge<String>(b, c, false, 8));
        gr.addEdge(new GraphEdge<String>(c, i, false, 2));
        gr.addEdge(new GraphEdge<String>(c, d, false, 7));
        gr.addEdge(new GraphEdge<String>(c, f, false, 4));
        gr.addEdge(new GraphEdge<String>(d, f, false, 14));
        gr.addEdge(new GraphEdge<String>(d, e, false, 9));
        gr.addEdge(new GraphEdge<String>(e, f, false, 10));
        gr.addEdge(new GraphEdge<String>(f, g, false, 2));
        gr.addEdge(new GraphEdge<String>(g, i, false, 6));
        gr.addEdge(new GraphEdge<String>(g, h, false, 1));
        gr.addEdge(new GraphEdge<String>(h, i, false, 7));
        KruskalMSP<String> alg = new KruskalMSP<String>();
        Set<GraphEdge<String>> result = new HashSet<GraphEdge<String>>();
        result.add(new GraphEdge<String>(a, b, false, 4));
        result.add(new GraphEdge<String>(b, c, false, 8));
        result.add(new GraphEdge<String>(c, i, false, 2));
        result.add(new GraphEdge<String>(c, d, false, 7));
        result.add(new GraphEdge<String>(c, f, false, 4));
        result.add(new GraphEdge<String>(d, e, false, 9));
        result.add(new GraphEdge<String>(f, g, false, 2));
        result.add(new GraphEdge<String>(g, h, false, 1));
        assertTrue(alg.computeMSP(gr).equals(result));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            Graph<String> orientedGraph = new AdjacencyMatrixDirectedGraph<>();
            alg.computeMSP(orientedGraph);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> notWeightGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            notWeightGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            notWeightGraph.addNode(node2);
            notWeightGraph.addEdge(new GraphEdge<String>(node1, node2, false));
            alg.computeMSP(notWeightGraph);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> notWeightGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            notWeightGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            notWeightGraph.addNode(node2);
            notWeightGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(notWeightGraph);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

}
