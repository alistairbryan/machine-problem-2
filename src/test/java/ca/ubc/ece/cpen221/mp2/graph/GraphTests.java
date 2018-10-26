package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class GraphTests {
    private Graph g1 = new AdjacencyMatrixGraph();
    private Vertex v0 = new Vertex("0");
    private Vertex v7 = new Vertex("7");


    public static void startup(Graph g) {

        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        g.addVertex(v0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        g.addEdge(v1, v5);
        g.addEdge(v1, v0);
        g.addEdge(v1, v2);
        g.addEdge(v1, v6);
        g.addEdge(v1, v2);
        g.addEdge(v1, v4);
        g.addEdge(v4, v3);
        g.addEdge(v4, v7);
        g.addEdge(v2, v6);
    }

    @Test
    public void test1_AdjL() {
        List<Vertex> vertices = new ArrayList<Vertex>(Arrays.asList(new Vertex("APPLE"),
                new Vertex("apple"), new Vertex("Zoinks"), new Vertex("aPple"),
                new Vertex("people")));

        Graph listgraph = new AdjacencyListGraph(vertices);
        listgraph.addEdge(vertices.get(0), vertices.get(1));
        listgraph.addEdge(vertices.get(1), vertices.get(2));
        listgraph.addEdge(vertices.get(1), vertices.get(3));

        System.out.println(listgraph.getVertices());

        System.out.println(listgraph.getNeighbors(vertices.get(0)));
        System.out.println(listgraph.getNeighbors(vertices.get(1)));

    }

    @Test
    public void test1_AdjM() {

        List<Vertex> vertices = new ArrayList<Vertex>(Arrays.asList(new Vertex("APPLE"),
                new Vertex("apple"), new Vertex("Zoinks"), new Vertex("aPple"),
                new Vertex("people")));

        Graph listgraph = new AdjacencyMatrixGraph(vertices);
        listgraph.addEdge(vertices.get(0), vertices.get(1));
        listgraph.addEdge(vertices.get(1), vertices.get(2));
        listgraph.addEdge(vertices.get(1), vertices.get(3));

        System.out.println("Adjacency Matrix: ");
        System.out.println(listgraph.getVertices());

        System.out.println(listgraph.getNeighbors(vertices.get(0)));
        System.out.println(listgraph.getNeighbors(vertices.get(1)));

    }

    @Test
    public void graphfromBoggleBoard_txt() {
        Graph testgraph = new AdjacencyMatrixGraph();
        BoggleBoard board = new BoggleBoard("datasets/board-q.txt");
        Permeate.boggleGraph(board, testgraph);
        /*System.out.println(testgraph.getVertices());
        for (Vertex vertex : testgraph.getVertices()) {
            System.out.println(vertex + ":  " + testgraph.getNeighbors(vertex));
        }*/
    }

    @Test
    public void graphfromBoggleBoard_mn() {
        Graph testgraph = new AdjacencyMatrixGraph();
        BoggleBoard board = new BoggleBoard(20,20);
        Permeate.boggleGraph(board, testgraph);
        /*System.out.println(testgraph.getVertices());
        for (Vertex vertex : testgraph.getVertices()) {
            System.out.println(vertex + ":  " + testgraph.getNeighbors(vertex));
        }*/
    }

    @Test
    public void createMavelGraph() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        System.out.println(testgraph.getVertices());
        for (Vertex vertex : testgraph.getVertices()) {
            System.out.println(vertex + ": " + testgraph.getNeighbors(vertex));
        }
    }

    @Test
    public void marvel_shortestDistance1() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        assertEquals(1, Algorithms.shortestDistance(testgraph,
                new Vertex("\"24-HOUR MAN/EMMANUEL\""), new Vertex("\"FROST, CARMILLA\"")));
    }

    @Test
    public void shortestDistance(){
        Graph testGraph = new AdjacencyMatrixGraph();
        startup(testGraph);
        System.out.println(Algorithms.shortestDistance(testGraph, v0, v7));
    }

    @Test
    public void marvel_BFS() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        Set<List<Vertex>> lists = Algorithms.breadthFirstSearch(testgraph);
        for (List<Vertex> list : lists) {
            System.out.println(list.toString());
        }
    }

    @Test
    public void marvel_BFS2() {
        Graph testGraph = new AdjacencyMatrixGraph();
        startup(testGraph);
        Set<List<Vertex>> lists = Algorithms.breadthFirstSearch(testGraph);

        for(List<Vertex> list : lists){
            for(Vertex v : list){
                System.out.print(v.getLabel() + ", ");
            }
            System.out.println("\n");
        }


    }

    @Test
    public void marvel_DFS() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        Set<List<Vertex>> lists = Algorithms.depthFirstSearch(testgraph);
        for (List<Vertex> list : lists) {
            System.out.println(list.toString());
        }
    }

    @Test
    public void marvel_DFS2() {
        Graph testGraph = new AdjacencyMatrixGraph();
        startup(testGraph);
        Set<List<Vertex>> lists = Algorithms.depthFirstSearch(testGraph);
    }
    @Test
    public void marvelcenter() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        System.out.println(Algorithms.center(testgraph).toString());
    }

    @Test
    public void marveldiameter() {
        Graph testgraph = new AdjacencyMatrixGraph();
        Permeate.marvelList("datasets/marvel.txt", testgraph);
        System.out.println(Algorithms.diameter(testgraph));
    }

    @Test
    public void center(){
        Graph testGraph = new AdjacencyMatrixGraph();
        startup(testGraph);
        System.out.println(Algorithms.center(testGraph).getLabel());
    }

    @Test
    public void diameter(){
        Graph testGraph = new AdjacencyMatrixGraph();
        startup(testGraph);
        System.out.println(Algorithms.diameter(testGraph));
    }
}
