package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class BellmanFordShortestPathComputerTest {

    // TODO implementare tutti i test non ancora implementati

    @Test
    final void testBellmanFordShortestPathComputer() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<String>();
        Graph<String> graph2 = new MapAdjacentListUndirectedGraph<>();
        boolean nullException = false;
        boolean illegalException = false;
        try{
            BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try{
            BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        try{
            BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph2);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
        illegalException = false;
        GraphNode<String> node1 = new GraphNode<>("Node1");
        GraphNode<String> node2 = new GraphNode<>("Node2");
        graph.addNode(node1);
        graph.addNode(node2);
        GraphEdge<String> edge = new GraphEdge<String>(node1,node2,true,0);
        graph.addEdge(edge);
        try{
            BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

    @Test
    final void testComputeShortestPathsFrom() {
        fail("Not yet implemented");
    }

    @Test
    final void testIsComputed() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<String>();
        BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph);
        GraphNode<String> node1 = new GraphNode<>("Node1");
        GraphNode<String> node2 = new GraphNode<>("Node2");
        graph.addNode(node1);
        graph.addNode(node2);
        GraphEdge<String> edge = new GraphEdge<String>(node1,node2,true,5);
        graph.addEdge(edge);
        path.computeShortestPathsFrom(node1);
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetLastSource() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetGraph() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetShortestPathTo() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true, 7.03);

        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<String>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<String>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<String>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<String>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<String>(nv, ny, true, 4.07);
        g.addEdge(evy);
        BellmanFordShortestPathComputer<String> c = new BellmanFordShortestPathComputer<>(g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);
        List<GraphEdge<String>> pathTest = new ArrayList<GraphEdge<String>>();
        assertTrue(c.getShortestPathTo(nsTest).equals(pathTest));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        GraphEdge<String> esxTest = new GraphEdge<String>(nsTest, nxTest, true,
                5.12);
        pathTest.add(esxTest);
        assertTrue(c.getShortestPathTo(nxTest).equals(pathTest));
        GraphEdge<String> exuTest = new GraphEdge<String>(nxTest, nuTest, true,
                3.04);
        pathTest.add(exuTest);
        assertTrue(c.getShortestPathTo(nuTest).equals(pathTest));
        GraphNode<String> nvTest = new GraphNode<String>("v");
        GraphEdge<String> euvTest = new GraphEdge<String>(nuTest, nvTest, true,
                1.0);
        pathTest.add(euvTest);
        assertTrue(c.getShortestPathTo(nvTest).equals(pathTest));
        pathTest.clear();
        pathTest.add(esxTest);
        GraphNode<String> nyTest = new GraphNode<String>("y");
        GraphEdge<String> exyTest = new GraphEdge<String>(nxTest, nyTest, true,
                2.0);
        pathTest.add(exyTest);
        assertTrue(c.getShortestPathTo(nyTest).equals(pathTest));
    }

}
