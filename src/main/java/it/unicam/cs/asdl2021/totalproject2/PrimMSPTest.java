package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class PrimMSPTest {

    @Test
    final void testFindMSP1() {
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
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == c);
        assertTrue(e.getPrevious() == d);
        assertTrue(f.getPrevious() == c);
        assertTrue(g.getPrevious() == f);
        assertTrue(h.getPrevious() == g);
        assertTrue(i.getPrevious() == c);
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null, null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            GraphNode<String> ne = new GraphNode<String>("Not exist");
            alg.computeMSP(gr, ne);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
            alg.computeMSP(undirectedGraph, f);
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
            alg.computeMSP(notWeightGraph, b);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> negativeGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            negativeGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            negativeGraph.addNode(node2);
            negativeGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(negativeGraph, d);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testFindMSP2() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null, null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            GraphNode<String> ne = new GraphNode<String>("Not exist");
            alg.computeMSP(gr, ne);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
            alg.computeMSP(undirectedGraph, a);
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
            alg.computeMSP(notWeightGraph, b);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> negativeGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            negativeGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            negativeGraph.addNode(node2);
            negativeGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(negativeGraph, d);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testFindMSP3() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, b);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == null);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null, null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            GraphNode<String> ne = new GraphNode<String>("Not exist");
            alg.computeMSP(gr, ne);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
            alg.computeMSP(undirectedGraph, c);
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
            alg.computeMSP(notWeightGraph, b);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> negativeGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            negativeGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            negativeGraph.addNode(node2);
            negativeGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(negativeGraph, d);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testFindMSP4() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, c);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == c);
        assertTrue(c.getPrevious() == null);
        assertTrue(d.getPrevious() == b);
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null, null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            GraphNode<String> ne = new GraphNode<String>("Not exist");
            alg.computeMSP(gr, ne);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
            alg.computeMSP(undirectedGraph, b);
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
            alg.computeMSP(notWeightGraph, b);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> negativeGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            negativeGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            negativeGraph.addNode(node2);
            negativeGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(negativeGraph, d);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testFindMSP5() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, d);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == d);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == null);
        boolean nullException = false;
        boolean illegalException = false;
        try{
            alg.computeMSP(null, null);
        }
        catch(NullPointerException exc){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            GraphNode<String> ne = new GraphNode<String>("Not exist");
            alg.computeMSP(gr, ne);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
            alg.computeMSP(undirectedGraph, d);
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
            alg.computeMSP(notWeightGraph, b);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            Graph<String> negativeGraph = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node1 = new GraphNode<String>("a");
            negativeGraph.addNode(node1);
            GraphNode<String> node2 = new GraphNode<String>("b");
            negativeGraph.addNode(node2);
            negativeGraph.addEdge(new GraphEdge<String>(node1, node2, false, -8));
            alg.computeMSP(negativeGraph, d);
        }
        catch(IllegalArgumentException exc){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }
}
