package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphCreator {
    public static void boggleGraph(String filename, Graph graph) {
        int rows;
        int cols;
        List<Vertex> vertices = new ArrayList<Vertex>();

        try {
            Scanner dataset = new Scanner(new File(filename));

            rows = Integer.valueOf(dataset.next());
            cols = Integer.valueOf(dataset.next());

            for(int i = 0; i < rows*cols ; i++) {
                vertices.add(new Vertex(dataset.next(),Integer.toString(i)));
                graph.addVertex(vertices.get(i));
                if (i != 0 && i % cols != 0) {
                    graph.addEdge(vertices.get(i),vertices.get(i-1));
                }
                if (i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i-cols));
                }
                if (i % cols != cols - 1 && i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i - cols + 1));
                }
                if (i % cols != 0 && i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i-cols -1));
                }
            }
        } catch (IOException ex) {
            System.out.println("File does not exist");
        }

    }
}
