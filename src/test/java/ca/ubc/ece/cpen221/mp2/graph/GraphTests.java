package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.boggle.BogglePlayer;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class GraphTests {
    private static Graph g1 = new AdjacencyMatrixGraph();
    private static Graph g2 = new AdjacencyListGraph();
    private static Graph g3 = new AdjacencyMatrixGraph();
    private static Graph g4 = new AdjacencyListGraph();
    private static Graph edgeless = new AdjacencyMatrixGraph();
    private static Graph w1edge = new AdjacencyListGraph();

    @BeforeClass
    public static void startup() {
        //Graph g1:
        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        g1.addVertex(v0);
        g1.addVertex(v1);
        g1.addVertex(v2);
        g1.addVertex(v3);
        g1.addVertex(v4);
        g1.addVertex(v5);
        g1.addVertex(v6);
        g1.addVertex(v7);

        g1.addEdge(v1, v5);
        g1.addEdge(v1, v0);
        g1.addEdge(v1, v2);
        g1.addEdge(v1, v6);
        g1.addEdge(v1, v2);
        g1.addEdge(v1, v4);
        g1.addEdge(v4, v3);
        g1.addEdge(v4, v7);
        g1.addEdge(v2, v6);

        //Graph g2
        List<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 1; i <= 8; i++) {
            Vertex vertex = new Vertex(Integer.toString(i));
            vertices.add(vertex);
            g2.addVertex(vertex);
        }
        g2.addEdge(vertices.get(0),vertices.get(7));
        g2.addEdge(vertices.get(7),vertices.get(1));
        g2.addEdge(vertices.get(1),vertices.get(4));
        g2.addEdge(vertices.get(1),vertices.get(3));
        g2.addEdge(vertices.get(1),vertices.get(2));
        g2.addEdge(vertices.get(2),vertices.get(3));
        g2.addEdge(vertices.get(3),vertices.get(5));
        g2.addEdge(vertices.get(5),vertices.get(4));
        g2.addEdge(vertices.get(6),vertices.get(4));

        //Graph g3
        vertices.clear();
        for (int i = 0; i < 9; i++) {
            Vertex vertex = new Vertex(Integer.toString(i));
            vertices.add(vertex);
            g3.addVertex(vertex);
        }
        g3.addEdge(vertices.get(0),vertices.get(1));
        g3.addEdge(vertices.get(0),vertices.get(7));
        g3.addEdge(vertices.get(1),vertices.get(2));
        g3.addEdge(vertices.get(1),vertices.get(7));
        g3.addEdge(vertices.get(2),vertices.get(3));
        g3.addEdge(vertices.get(2),vertices.get(5));
        g3.addEdge(vertices.get(2),vertices.get(8));
        g3.addEdge(vertices.get(8),vertices.get(6));
        g3.addEdge(vertices.get(8),vertices.get(7));
        g3.addEdge(vertices.get(7),vertices.get(6));
        g3.addEdge(vertices.get(3),vertices.get(4));
        g3.addEdge(vertices.get(3),vertices.get(5));
        g3.addEdge(vertices.get(6),vertices.get(5));
        g3.addEdge(vertices.get(5),vertices.get(4));


        //Graph g3
        vertices.clear();
        vertices.add(new Vertex("a"));
        vertices.add(new Vertex("b"));
        vertices.add(new Vertex("c"));
        vertices.add(new Vertex("d"));
        vertices.add(new Vertex("e"));
        vertices.add(new Vertex("f"));
        vertices.add(new Vertex("g"));
        vertices.add(new Vertex("h"));
        vertices.add(new Vertex("i"));
        vertices.add(new Vertex("j"));
        vertices.add(new Vertex("k"));

        for (Vertex vertex : vertices) {
            g4.addVertex(vertex);
        }

        g4.addEdge(new Vertex("a"), new Vertex("b"));
        g4.addEdge(new Vertex("a"), new Vertex("c"));
        g4.addEdge(new Vertex("b"), new Vertex("c"));
        g4.addEdge(new Vertex("b"), new Vertex("e"));
        g4.addEdge(new Vertex("c"), new Vertex("d"));
        g4.addEdge(new Vertex("d"), new Vertex("f"));
        g4.addEdge(new Vertex("d"), new Vertex("g"));
        g4.addEdge(new Vertex("a"), new Vertex("b"));
        g4.addEdge(new Vertex("e"), new Vertex("f"));
        g4.addEdge(new Vertex("h"), new Vertex("i"));
        g4.addEdge(new Vertex("i"), new Vertex("j"));

        //Edgeless graph:
        edgeless.addVertex(new Vertex("1"));
        edgeless.addVertex(new Vertex("2"));
        edgeless.addVertex(new Vertex("3"));
        edgeless.addVertex(new Vertex("4"));

        //w1edge
        w1edge.addVertex(new Vertex("1"));
        w1edge.addVertex(new Vertex("2"));
        w1edge.addVertex(new Vertex("3"));
        w1edge.addVertex(new Vertex("4"));
        w1edge.addEdge(new Vertex("4"), new Vertex("3"));
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

    } // can be removed

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

    } // can be removed

    @Test
    public void graphfromBoggleBoard_txt() {
        Graph testgraph = new AdjacencyMatrixGraph();
        BoggleBoard board = new BoggleBoard("datasets/board-q.txt");
        Permeate.boggleGraph(board, testgraph);
        /*System.out.println(testgraph.getVertices());
        for (Vertex vertex : testgraph.getVertices()) {
            System.out.println(vertex + ":  " + testgraph.getNeighbors(vertex));
        }*/
    } // can likely be removed

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
        System.out.println("Marvel graph created");
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
        assertEquals(3, Algorithms.shortestDistance(g1, new Vertex("0"), new Vertex("7")));
        assertEquals(2, Algorithms.shortestDistance(g2, new Vertex("7"), new Vertex("6")));
        assertEquals(4, Algorithms.shortestDistance(g2, new Vertex("7"), new Vertex("1")));
        assertEquals(2, Algorithms.shortestDistance(g2, new Vertex("7"), new Vertex("6")));
        assertEquals(2, Algorithms.shortestDistance(g2, new Vertex("5"), new Vertex("3")));
        assertEquals(0, Algorithms.shortestDistance(g2, new Vertex("8"), new Vertex("8")));
        assertEquals(4, Algorithms.shortestDistance(g3, new Vertex("0"), new Vertex("4")));
        assertEquals(2, Algorithms.shortestDistance(g3, new Vertex("0"), new Vertex("8")));
        assertEquals(3, Algorithms.shortestDistance(g3, new Vertex("7"), new Vertex("4")));
        assertEquals(3, Algorithms.shortestDistance(g4, new Vertex("a"), new Vertex("f")));
        assertEquals(2, Algorithms.shortestDistance(g4, new Vertex("h"), new Vertex("j")));
        assertEquals(-1, Algorithms.shortestDistance(g4, new Vertex("a"), new Vertex("i")));
        assertEquals(-1, Algorithms.shortestDistance(g4, new Vertex("h"), new Vertex("k")));

    }
    /*public void findDistances(){
        Map<Vertex, Integer> distances1 = Algorithms.findDistances(g1, new Vertex("0"), new Vertex("6"));
        for(Vertex v : distances1.keySet()){
            System.out.println(v.getLabel() + ": " + distances1.get(v));
            System.out.println("\n");
        }
    }
    */


    @Test
    public void BFS_1() {
        Set<List<Vertex>> list1 = Algorithms.breadthFirstSearch(g1);
        Set<List<Vertex>> list2 = Algorithms.breadthFirstSearch(g2);
        Set<List<Vertex>> list3 = Algorithms.breadthFirstSearch(g3);
        Set<List<Vertex>> list4 = Algorithms.breadthFirstSearch(g4);


        System.out.println("Graph g1: ");
        for(List<Vertex> list : list1){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g2: ");
        for(List<Vertex> list : list2){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g3: ");
        for(List<Vertex> list : list3){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g4: ");
        for(List<Vertex> list : list4){
            System.out.println(list.toString());
            System.out.println("\n");
        }

    }


    @Test
    public void DFS() {
        Set<List<Vertex>> list1 = Algorithms.depthFirstSearch(g1);
        Set<List<Vertex>> list2 = Algorithms.depthFirstSearch(g2);
        Set<List<Vertex>> list3 = Algorithms.depthFirstSearch(g3);
        Set<List<Vertex>> list4 = Algorithms.depthFirstSearch(g4);


        System.out.println("Graph g1: ");
        for(List<Vertex> list : list1){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g2: ");
        for(List<Vertex> list : list2){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g3: ");
        for(List<Vertex> list : list3){
            System.out.println(list.toString());
            System.out.println("\n");
        }

        System.out.println("Graph g4: ");
        for(List<Vertex> list : list4){
            System.out.println(list.toString());
            System.out.println("\n");
        }
    }


    @Test
    public void center(){
        System.out.println(Algorithms.center(g1).getLabel());
        System.out.println(Algorithms.center(g2).getLabel());
        System.out.println(Algorithms.center(g3).getLabel());
        System.out.println(Algorithms.center(g4).getLabel());

    }

    @Test
    public void diameter(){
        System.out.println(Algorithms.diameter(g1));
        System.out.println(Algorithms.diameter(g2));
        System.out.println(Algorithms.diameter(g3));
        System.out.println(Algorithms.diameter(g4));
        System.out.println(Algorithms.diameter(edgeless));
        System.out.println(Algorithms.diameter(w1edge));
    }

    @Test
    public void center_marvel() {
        Graph marvelGraph = new AdjacencyListGraph();
        Permeate.marvelList("datasets/marvel.txt", marvelGraph);
        assertEquals("\"3-D MAN/CHARLES CHAN\"", Algorithms.center(marvelGraph).toString());
    }

    @Test
    public void diameter_marvel() {
        Graph marvelGraph = new AdjacencyListGraph();
        Permeate.marvelList("datasets/marvel.txt", marvelGraph);
        assertEquals(5, Algorithms.diameter(marvelGraph));
    }


    //5x5 Board, 25x25 board, and 5x25 was made by Gabriel Robinson Leith and is used here with
    // permission to compare system outputs.
    @Test
    public void boggle(){
        BoggleBoard board = new BoggleBoard("datasets/board5x5.txt");

        BogglePlayer player = new BogglePlayer(Permeate.textToStringAr("datasets/dictionary-yawl.txt"));

        for (String word : player.getAllValidWords(board)) {
            System.out.println(word);
        }
    }



}
