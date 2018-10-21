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
    public void test1() {
        Graph listgraph = new AdjacencyListGraph();
        listgraph.addVertex(new Vertex("APPLE"));
        listgraph.addVertex(new Vertex("ABBLE"));
        listgraph.addVertex(new Vertex("AB"));
        listgraph.addVertex(new Vertex("Zope"));
        listgraph.addVertex(new Vertex("apple"));
        listgraph.addVertex(new Vertex("apPle"));
        listgraph.addVertex(new Vertex("Lemon"));





        System.out.println(listgraph.getVertices());

    }


}
