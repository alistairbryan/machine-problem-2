package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Permeate {

    public static void boggleGraph(BoggleBoard board, Graph graph) {
        int rows = board.rows();
        int cols = board.cols();

        List<Vertex> vertices = new ArrayList<Vertex>();

        for (int m = 0; m < rows * cols; m++) {
            int i = m / cols; //row
            int j = m % cols; //column

            vertices.add(new Vertex(Character.toString(board.getLetter(i,j)), Integer.toString(m)));
            graph.addVertex(vertices.get(m));
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int m = j + i*cols;
                graph.addEdge(vertices.get(m),vertices.get((m + 1) % cols + i*cols)); // adding horizontal edges.
                graph.addEdge(vertices.get(m),vertices.get((m + cols) % (cols*rows))); //adding vertical edges.
                graph.addEdge(vertices.get(m),vertices.get((i+1) % rows * cols + (j+1) % cols)); //down-right edges
                graph.addEdge(vertices.get(m),vertices.get((i+1) % rows * cols + (j+cols-1) % cols)); //down-right edges
            }
        }


    }


    public static void marvelList(String filename, Graph graph) {
        try {
            Scanner dataset = new Scanner(new File(filename)); /* should add each vertex, individually,
                    but also create edges based on the comic book addy. Use hash map with arraylist value for this */

            Map<String, List<String>> book_chars = new HashMap<String, List<String>>();

            int i = 0;
            while (dataset.hasNextLine() && i < 10) {
                String line = dataset.nextLine();
                String[] ch_book = line.split("\t");

                if(!book_chars.containsKey(ch_book[1])) {
                    book_chars.put(ch_book[1], new ArrayList<String>(Arrays.asList(ch_book[0])));
                } else {
                    //List<String> characters = book_chars.get(ch_book[1]);

                }

                i++;
            }

            for (String book : book_chars.keySet()) {
                System.out.println(book + ": " + book_chars.get(book).toString());
            }


        } catch(IOException ex) {
            System.out.println("Error reading file.");
        }
    }
}

