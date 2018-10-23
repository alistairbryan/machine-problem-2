package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;


import static org.junit.Assert.*;

public class GraphTests {
    @BeforeClass
    public static void setup() {

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
    public void scannertest() {
        Graph testgraph = new AdjacencyListGraph();
        GraphCreator.boggleGraph("datasets/board-quinquevalencies.txt", testgraph);
        System.out.println(testgraph.getVertices());
        for (Vertex vertex : testgraph.getVertices()) {
            System.out.println(vertex + ":  " + testgraph.getNeighbors(vertex));
        }
    }


}
