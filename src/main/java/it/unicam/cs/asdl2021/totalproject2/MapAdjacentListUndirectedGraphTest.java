package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class MapAdjacentListUndirectedGraphTest {

    static Random rnd = new Random();

    private MapAdjacentListUndirectedGraph<Integer> createGraph(int nodes){
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<Integer>();
        for(int i = 0; i < nodes; i ++){
            graph.addNode(new GraphNode<>(rnd.nextInt()));
        }
        return graph;
    }

    @Test
    final void testNodeCount() {
        MapAdjacentListUndirectedGraph<Integer> graph = createGraph(3);
        Assertions.assertEquals(3, graph.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        MapAdjacentListUndirectedGraph<Integer> graph = createGraph(3);
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(new GraphEdge<Integer>(node1 , node2 , graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node1 , node4 , graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node2 , node3 , graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node2 , node1 , graph.isDirected()));
        Assertions.assertEquals(3,graph.edgeCount());
    }

    @Test
    final void testClear() {
        MapAdjacentListUndirectedGraph<Integer> graph = createGraph(3);
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(new GraphEdge<Integer>(node1 , node2 , graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node1 , node4 , graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node2 , node3 , graph.isDirected()));
        Assertions.assertEquals(7,graph.nodeCount());
        Assertions.assertEquals(3,graph.edgeCount());
        graph.clear();
        Assertions.assertEquals(0,graph.nodeCount());
        Assertions.assertEquals(0,graph.edgeCount());
    }

    @Test
    final void testIsDirected() {
        MapAdjacentListUndirectedGraph<Integer> graph = createGraph(3);
        Assertions.assertFalse(graph.isDirected());
    }

    @Test
    final void testGetNodes() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        Set<GraphNode<Integer>> nodeList = graph.getNodes();
        Assertions.assertEquals(4,nodeList.size());
        Assertions.assertTrue(nodeList.contains(node1));
        Assertions.assertTrue(nodeList.contains(node2));
        Assertions.assertTrue(nodeList.contains(node3));
        Assertions.assertTrue(nodeList.contains(node4));
    }

    @Test
    final void testAddNode() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        Assertions.assertTrue(graph.addNode(new GraphNode<>(2)));
        GraphNode<Integer> node2 = new GraphNode<>(4);
        Assertions.assertTrue(graph.addNode(node2));
        Assertions.assertFalse(graph.addNode(node2));
        boolean nullException = false;
        try {
            graph.addNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);

    }

    @Test
    final void testRemoveNode() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(new GraphEdge<Integer>(node1 , node2 , graph.isDirected()));
        Assertions.assertTrue(graph.removeNode(node1));
        Assertions.assertFalse(graph.removeNode(new GraphNode<>(6)));
        boolean nullException = false;
        boolean unsupportedException = false;
        try {
            graph.removeNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        catch(UnsupportedOperationException e){
            unsupportedException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertFalse(unsupportedException);

    }

    @Test
    final void testContainsNode() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        Assertions.assertTrue(graph.containsNode(node1));
        Assertions.assertFalse(graph.containsNode(node2));
        boolean nullException = false;
        try {
            graph.containsNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testGetNodeOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        graph.addNode(node2);
        Assertions.assertEquals(node1,graph.getNodeOf(2));
        Assertions.assertEquals(node2,graph.getNodeOf(4));
        boolean nullException = false;
        try {
            graph.containsNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testGetNodeIndexOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        graph.addNode(node2);
        boolean nullException = false;
        boolean unsupportedException = false;
        try {
            graph.getNodeIndexOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try{
            graph.getNodeIndexOf(2);
        }
        catch(UnsupportedOperationException e){
            unsupportedException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(unsupportedException);
    }

    @Test
    final void testGetNodeAtIndex() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        graph.addNode(node2);
        boolean unsupportedException = false;
        try{
            graph.getNodeIndexOf(2);
        }
        catch(UnsupportedOperationException e) {
            unsupportedException = true;
        }
        Assertions.assertTrue(unsupportedException);
    }

    @Test
    final void testGetEdge() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<>(node1,node2, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge3 = new GraphEdge<>(node2,node3, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        Assertions.assertEquals(edge1,graph.getEdge(node1,node2));
        boolean nullException = false;
        boolean illegalException = false;
        try {
            graph.containsNode(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertFalse(illegalException);
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        boolean unsupportedException = false;
        try{
            graph.getEdgeAtNodeIndexes(2,3);
        }
        catch(UnsupportedOperationException e) {
            unsupportedException = true;
        }
        Assertions.assertTrue(unsupportedException);
    }

    @Test
    final void testGetAdjacentNodesOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(new GraphEdge<Integer>(node1,node4, graph.isDirected()));
        graph.addEdge(new GraphEdge<Integer>(node1,node2, graph.isDirected()));
        Set<GraphNode<Integer>> nodes = graph.getAdjacentNodesOf(node1);
        Assertions.assertTrue(nodes.contains(node2));
        Assertions.assertTrue(nodes.contains(node4));
    }

    @Test
    final void testGetPredecessorNodesOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        boolean unsupportedException = false;
        try{
            graph.getPredecessorNodesOf(node1);
        }
        catch(UnsupportedOperationException e) {
            unsupportedException = true;
        }
        Assertions.assertTrue(unsupportedException);
    }

    @Test
    final void testGetEdges() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node1,node2, graph.isDirected());
        Assertions.assertEquals(0,graph.getEdges().size());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        Set<GraphEdge<Integer>> edges = graph.getEdges();
        Assertions.assertTrue(edges.contains(edge1));
        Assertions.assertTrue(edges.contains(edge2));
    }

    @Test
    final void testAddEdge() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node1,node2, graph.isDirected());
        Assertions.assertTrue(graph.addEdge(edge1));
        Assertions.assertTrue(graph.addEdge(edge2));
        Assertions.assertFalse(graph.addEdge(edge1));
        Assertions.assertFalse(graph.addEdge(new GraphEdge<Integer>(node1,node4, graph.isDirected())));
        Assertions.assertFalse(graph.addEdge(new GraphEdge<Integer>(node4,node1, graph.isDirected())));
        boolean illegalException = false;
        boolean nullException = false;
        try {
            graph.addEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        try {
            GraphNode<Integer> node7 = new GraphNode<>(8);
            graph.addEdge(new GraphEdge<Integer>(node7,node4, graph.isDirected()));
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try {
            graph.addEdge(new GraphEdge<Integer>(node1,node4, !graph.isDirected()));
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testRemoveEdge() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node1,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        Assertions.assertTrue(graph.containsEdge(edge1));
        Assertions.assertTrue(graph.removeEdge(edge1));
        Assertions.assertFalse(graph.containsEdge(edge1));
        Assertions.assertFalse(graph.containsEdge(edge1));
        boolean illegalException = false;
        boolean nullException = false;
        try {
            GraphNode<Integer> node7 = new GraphNode<>(8);
            graph.removeEdge(new GraphEdge<Integer>(node7,node4, graph.isDirected()));
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        try {
            graph.removeEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(illegalException);
        Assertions.assertTrue(nullException);
    }

    @Test
    final void testContainsEdge() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node1,node2, graph.isDirected());
        graph.addEdge(edge1);
        Assertions.assertTrue(graph.containsEdge(edge1));
        Assertions.assertFalse(graph.containsEdge(edge2));
        boolean nullException = false;
        boolean illegalException = false;
        try {
            GraphNode<Integer> node7 = new GraphNode<>(8);
            graph.containsEdge(new GraphEdge<Integer>(node7,node4, graph.isDirected()));
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        try {
            graph.containsEdge(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testGetEdgesOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(6);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        GraphEdge<Integer> edge1 = new GraphEdge<Integer>(node1,node4, graph.isDirected());
        GraphEdge<Integer> edge2 = new GraphEdge<Integer>(node1,node2, graph.isDirected());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        Set<GraphEdge<Integer>> edges = graph.getEdgesOf(node1);
        Assertions.assertTrue(edges.contains(edge1));
        Assertions.assertTrue(edges.contains(edge2));
        boolean illegalException = false;
        boolean nullException = false;
        try {
            GraphNode<Integer> node7 = new GraphNode<>(8);
            graph.getEdgesOf(node7);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        try {
            graph.getEdgesOf(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testGetIngoingEdgesOf() {
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        GraphNode<Integer> node1 = new GraphNode<>(2);
        GraphNode<Integer> node2 = new GraphNode<>(4);
        graph.addNode(node1);
        graph.addNode(node2);
        boolean unsupportedException = false;
        try{
            graph.getIngoingEdgesOf(node1);
        }
        catch(UnsupportedOperationException e) {
            unsupportedException = true;
        }
        Assertions.assertTrue(unsupportedException);
    }

    @Test
    final void testMapAdjacentListUndirectedGraph() {           // Test costruttore
        MapAdjacentListUndirectedGraph<Integer> graph = new MapAdjacentListUndirectedGraph<>();
        Assertions.assertEquals(0,graph.getNodes().size());
        Assertions.assertEquals(0, graph.getEdges().size());
    }

}
