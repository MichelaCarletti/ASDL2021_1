package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class AdjacencyMatrixDirectedGraphTest {

    // TODO implementare tutti i test non ancora implementati

    @Test
    final void testNodeCount() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node2);
        Assertions.assertEquals(2,graph.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node4,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge1);
        Assertions.assertEquals(4,graph.edgeCount());
    }

    @Test
    final void testClear(){
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node4,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge1);
        graph.clear();
        Assertions.assertEquals(0,graph.nodeCount());
        Assertions.assertEquals(0,graph.edgeCount());
    }

    @Test
    final void testIsDirected() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        Assertions.assertTrue(graph.isDirected());
    }

    @Test
    final void testGetNodes() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        Set<GraphNode<Integer>> nodes = new HashSet<>();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        nodes.add(node1);
        graph.addNode(node2);
        nodes.add(node2);
        graph.addNode(node3);
        nodes.add(node3);
        graph.addNode(node4);
        nodes.add(node4);
        Assertions.assertEquals(nodes,graph.getNodes());
    }

    @Test
    final void testAddNode() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        Assertions.assertTrue(graph.addNode(new GraphNode<Integer>(0)));
        Assertions.assertTrue(graph.addNode(new GraphNode<Integer>(1)));
        GraphNode<Integer> node3 = new GraphNode<>(5);
        Assertions.assertTrue(graph.addNode(node3));
        Assertions.assertFalse(graph.addNode(node3));
        boolean nullException = false;
        try{
            graph.addNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testRemoveNode() {
        fail();
        //TODO "Remove di nodi non supportata". Quindi cosa testo?
    }

    @Test
    final void testContainsNode() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        Assertions.assertTrue(graph.containsNode(node1));
        Assertions.assertFalse(graph.containsNode(node4));
        boolean nullException = false;
        try{
            graph.containsNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testGetNodeOf() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        Assertions.assertEquals(node1,graph.getNodeOf(0));
        boolean nullException = false;
        try{
            graph.getNodeOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testGetNodeIndexOf() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        Assertions.assertEquals(graph.nodesIndex.get(node1),graph.getNodeIndexOf(0));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.getNodeIndexOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.getNodeIndexOf(node4);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testGetNodeAtIndex() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        Assertions.assertEquals(node1,graph.getNodeAtIndex(0));
        boolean outException = false;
        try{
            graph.getNodeAtIndex(5);
        }
        catch(IndexOutOfBoundsException e){
            outException = true;
        }
        Assertions.assertTrue(outException);
    }

    @Test
    final void testGetEdge() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        Assertions.assertEquals(edge1,graph.getEdge(node1,node3));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.getEdge(null,null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.getEdge(node4,node1);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);

        //TODO non funziona
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        //int nodeIndex1 = graph.nodesIndex.get(node1);
        //int nodeIndex3 = graph.nodesIndex.get(node3);
        //Assertions.assertEquals(edge1, graph.getEdgeAtNodeIndexes(nodeIndex1,nodeIndex3));
        fail();
        //TODO non funziona e gestione IndexOutOFBoundException. graph.nodeIndex.get di solito funziona
    }

    @Test
    final void testGetAdjacentNodesOf() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Set<GraphNode<Integer>> adjacentNodes = new HashSet<>();
        adjacentNodes.add(node2);
        Assertions.assertEquals(adjacentNodes,graph.getAdjacentNodesOf(node3));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.getAdjacentNodesOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.getAdjacentNodesOf(node4);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testGetPredecessorNodesOf() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Set<GraphEdge<Integer>> predecessorNodes = new HashSet<>();
        predecessorNodes.add(edge3);
        predecessorNodes.add(edge4);
        Assertions.assertEquals(predecessorNodes,graph.getPredecessorNodesOf(node2));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.getAdjacentNodesOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.getAdjacentNodesOf(node4);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);

        //TODO non funziona
    }

    @Test
    final void testGetEdges() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Set<GraphEdge<Integer>> edges = new HashSet<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        Assertions.assertEquals(edges,graph.getEdges());

        //TODO non funziona
    }

    @Test
    final void testAddEdge() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node4,node3, graph.isDirected());
        Assertions.assertTrue(graph.addEdge(new GraphEdge(node1,node2, graph.isDirected())));
        Assertions.assertTrue(graph.addEdge(edge1));
        Assertions.assertFalse(graph.addEdge(edge1));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.addEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.addEdge(edge2);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);

        //TODO non funziona
    }

    @Test
    final void testRemoveEdge() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Assertions.assertTrue(graph.removeEdge(edge1));
        Assertions.assertFalse(graph.removeEdge(edge1));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.removeEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.removeEdge(edge2);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);

        //TODO non funziona
    }

    @Test
    final void testContainsEdge() {
        AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph();
        GraphNode<Integer> node1 = new GraphNode<Integer>(0);
        GraphNode<Integer> node2 = new GraphNode<Integer>(1);
        GraphNode<Integer> node3 = new GraphNode<Integer>(2);
        GraphNode<Integer> node4 = new GraphNode<Integer>(3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node3, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node2,node1, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        GraphEdge<Integer> edge4 = new GraphEdge<Integer>(node3,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Assertions.assertTrue(graph.containsEdge(edge1));
        Assertions.assertFalse(graph.containsEdge(edge1));
        boolean nullException = false;
        boolean illegalException = false;
        try{
            graph.containsEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.containsEdge(edge4);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);

        //TODO non funziona
    }

    @Test
    final void testGetEdgesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetIngoingEdgesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testAdjacencyMatrixDirectedGraph() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testSize() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testIsEmpty() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetDegreeOf() {
        fail("Not yet implemented"); // TODO
    }

}
