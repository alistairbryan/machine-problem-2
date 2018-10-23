package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {

    /**
     *
     * @param filename: name of file to read boggle graph setups from
     * @param graph:
     */
    public static void boggleGraph_text(String filename, Graph graph) {
        List<Vertex> vertices = new ArrayList<Vertex>();

        try {
            Scanner dataset = new Scanner(new File(filename));

            int rows = Integer.valueOf(dataset.next());
            int cols = Integer.valueOf(dataset.next());

            for(int i = 0; i < rows*cols; i++) {
                String character = dataset.next();
                if (character.length() == 2) {
                    character = Character.toString(character.charAt(0));
                }
                vertices.add(new Vertex(character,Integer.toString(i)));
                graph.addVertex(vertices.get(i));
                if (i != 0 && i % cols != 0) {
                    graph.addEdge(vertices.get(i),vertices.get(i-1)); //adding horizontal lines
                }
                if (i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i-cols)); //adding vertical lines
                }
                if (i % cols != cols - 1 && i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i - cols + 1)); //adding diagonal 1
                }
                if (i % cols != 0 && i >= cols) {
                    graph.addEdge(vertices.get(i),vertices.get(i-cols -1)); //adding diagonal 2
                }
            }
        } catch (IOException ex) {
            System.out.println("Wrong format or file does not exist");
        }

    }

    public static void vertex_location(String filename, Graph graph) {

        try {
            Scanner dataset = new Scanner(new File(filename)); /* should add each vertex, individually,
                    but also create edges based on the comic book addy. Use hash map with arraylist value for this */

            Map<String, List<String>> locations = new HashMap<String, List<String>>();
            int i = 0;
            while (dataset.hasNextLine() && i < 100) {
                String line = dataset.nextLine();
                System.out.println(line);
                i++;
            }


        } catch(IOException ex) {
            System.out.println("Error reading file.");
        }
    }
}

