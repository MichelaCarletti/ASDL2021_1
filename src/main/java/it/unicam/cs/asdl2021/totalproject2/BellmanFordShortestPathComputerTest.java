package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class BellmanFordShortestPathComputerTest {

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
        GraphEdge<String> edge = new GraphEdge<String>(node1,node2,true);
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
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> node1 = new GraphNode<>("Node1");
        GraphNode<String> node2 = new GraphNode<>("Node2");
        GraphNode<String> node3 = new GraphNode<>("Node3");
        GraphNode<String> node4 = new GraphNode<>("Node4");
        GraphNode<String> node5 = new GraphNode<>("Node5");
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        GraphEdge<String> edge1 = new GraphEdge<String>(node1,node2,true,6);
        graph.addEdge(edge1);
        GraphEdge<String> edge2 = new GraphEdge<String>(node1,node5,true,7);
        graph.addEdge(edge2);
        GraphEdge<String> edge3 = new GraphEdge<String>(node2,node3,true,5);
        graph.addEdge(edge3);
        GraphEdge<String> edge4 = new GraphEdge<String>(node2,node4,true,-4);
        graph.addEdge(edge4);
        GraphEdge<String> edge5 = new GraphEdge<String>(node2,node5,true,8);
        graph.addEdge(edge5);
        GraphEdge<String> edge6 = new GraphEdge<String>(node3,node2,true,-2);
        graph.addEdge(edge6);
        GraphEdge<String> edge7 = new GraphEdge<String>(node4,node3,true,3);
        graph.addEdge(edge7);
        GraphEdge<String> edge8 = new GraphEdge<String>(node4,node1,true,2);
        graph.addEdge(edge8);
        GraphEdge<String> edge9 = new GraphEdge<String>(node5,node4,true,9);
        graph.addEdge(edge9);
        GraphEdge<String> edge10 = new GraphEdge<String>(node5,node3,true,-3);
        graph.addEdge(edge10);
        BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph);
        boolean illegalExcpetion = false;
        try {
            path.computeShortestPathsFrom(node1);
        }
        catch(IllegalArgumentException e){
            illegalExcpetion = true;
        }
        Assertions.assertTrue(illegalExcpetion);
    }

    @Test
    final void testIsComputed() {
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
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true, -2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true, -2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true, 7.03);
        BellmanFordShortestPathComputer<String> c = new BellmanFordShortestPathComputer<String>(
                g);
        Assertions.assertFalse(c.isComputed());
        c.computeShortestPathsFrom(ns);
        Assertions.assertTrue(c.isComputed());
    }

    @Test
    final void testGetLastSource() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> node1 = new GraphNode<>("Node1");
        GraphNode<String> node2 = new GraphNode<>("Node2");
        GraphNode<String> node3 = new GraphNode<>("Node3");
        GraphNode<String> node4 = new GraphNode<>("Node4");
        GraphNode<String> node5 = new GraphNode<>("Node5");
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        GraphEdge<String> edge1 = new GraphEdge<String>(node1,node2,true,6);
        graph.addEdge(edge1);
        GraphEdge<String> edge2 = new GraphEdge<String>(node1,node5,true,7);
        graph.addEdge(edge2);
        GraphEdge<String> edge3 = new GraphEdge<String>(node2,node3,true,5);
        graph.addEdge(edge3);
        GraphEdge<String> edge4 = new GraphEdge<String>(node2,node4,true,-4);
        graph.addEdge(edge4);
        GraphEdge<String> edge5 = new GraphEdge<String>(node2,node5,true,8);
        graph.addEdge(edge5);
        GraphEdge<String> edge6 = new GraphEdge<String>(node3,node2,true,-2);
        graph.addEdge(edge6);
        GraphEdge<String> edge7 = new GraphEdge<String>(node4,node3,true,7);
        graph.addEdge(edge7);
        GraphEdge<String> edge8 = new GraphEdge<String>(node4,node1,true,2);
        graph.addEdge(edge8);
        GraphEdge<String> edge9 = new GraphEdge<String>(node5,node4,true,9);
        graph.addEdge(edge9);
        GraphEdge<String> edge10 = new GraphEdge<String>(node5,node3,true,-3);
        graph.addEdge(edge10);
        BellmanFordShortestPathComputer<String> path = new BellmanFordShortestPathComputer<>(graph);
        path.computeShortestPathsFrom(node1);
        GraphNode<String> node1Test = new GraphNode<>("Node1");
        Assertions.assertEquals(node1Test, path.getLastSource());
    }

    @Test
    final void testGetGraph() {
        Graph<String> graph1 = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> node1 = new GraphNode<>("Node1");
        GraphNode<String> node2 = new GraphNode<>("Node2");
        GraphNode<String> node3 = new GraphNode<>("Node3");
        GraphNode<String> node4 = new GraphNode<>("Node4");
        GraphNode<String> node5 = new GraphNode<>("Node5");
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addNode(node4);
        graph1.addNode(node5);
        GraphEdge<String> edge1 = new GraphEdge<String>(node1,node2,true,6);
        graph1.addEdge(edge1);
        GraphEdge<String> edge2 = new GraphEdge<String>(node1,node5,true,7);
        graph1.addEdge(edge2);
        GraphEdge<String> edge3 = new GraphEdge<String>(node2,node3,true,5);
        graph1.addEdge(edge3);
        BellmanFordShortestPathComputer<String> c = new BellmanFordShortestPathComputer<String>(
                graph1);
        Assertions.assertEquals(graph1, c.getGraph());
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
        BellmanFordShortestPathComputer<String> c = new BellmanFordShortestPathComputer<String>(
                g);
        boolean illegalException = false;
        try{
            illegalException = c.isComputed();
        }
        catch(IllegalStateException e){
        }
        Assertions.assertFalse(illegalException);
        illegalException = false;
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
        boolean nullException = false;
        try {
            c.computeShortestPathsFrom(null);
        }
        catch(NullPointerException e){
            nullException = true;
        }
        Assertions.assertTrue(nullException);
        try {
            GraphNode<String> notExistNode = new GraphNode<>("Not exist");
            c.computeShortestPathsFrom(notExistNode);
        }
        catch(IllegalArgumentException e){
            illegalException = true;
        }
        Assertions.assertTrue(illegalException);
    }

}
