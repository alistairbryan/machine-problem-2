package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Permeate {
    // Fills graphs with what they need to be filled with.
    /**
     * @param board, a board object.
     *               Requires that the board is not null.
     * @param graph, a graph that you wish to populate with vertices from the board.
     *               Requires that the graph object contains no vertices and is not null.
     */
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
    /**
     * @param filename, the name of the file that you wish to read convert to a graph.
     *                  Requires that filename points to a file that is of type .txt and is in the dataset directory
     *                  Requires that filename points to a file which contains text of the format: ""Label"    "Content"
     *                  Requires that filename points to a file that has each Vertex description on its own line, and that there are no empty lines.
     * @param graph, the graph that you wish to populate with the above described vertices.
     *               Requires that graph contains no vertices and that it is not null.
     */
    public static void marvelList(String filename, Graph graph) {
        try {
            Scanner dataset = new Scanner(new File(filename));

            Map<String, Set<Vertex>> bookChars = new HashMap<String, Set<Vertex>>();
            Set<Vertex> vertices = new HashSet<Vertex>();

            while (dataset.hasNextLine()) {
                String line = dataset.nextLine();
                String[] lineChBook = line.split("\t"); // where 0 is character, 1 is book

                Vertex vertex = new Vertex(lineChBook[0]);

                if (!vertices.contains(vertex)) {
                    vertices.add(vertex);
                    graph.addVertex(vertex);
                }

                if (!bookChars.containsKey(lineChBook[1])) {
                    bookChars.put(lineChBook[1], new HashSet<Vertex>(Arrays.asList(vertex)));
                } else {
                    Set<Vertex> characters = bookChars.get(lineChBook[1]);
                    for (Vertex character : characters) {
                        graph.addEdge(vertex, character);
                    }
                    characters.add(vertex);
                    bookChars.put(lineChBook[1], characters);
                }
            }
            dataset.close();

        } catch(IOException ex) {
            System.out.println("Error reading file.");
        }
    }

/**
 * @param filename, the name of the file that you wish to read convert to a graph.
 *                  Requires that filename points to a file that is of type .txt and is in the dataset directory
 *                  Requires that filename points to a file that has no empty lines.
 * @return A String array with the contents of each line in filename populating a unique index in the order at which they were found in the file at filename.
 */
    public static String[] textToStringAr(String filename) {
        List<String> words = new ArrayList<String>();

        try {
            Scanner file = new Scanner(new File(filename));
            while(file.hasNextLine()) {
                words.add(file.nextLine());
            }
            file.close();
        } catch(IOException ex) {
            System.out.println("Error finding dictionary");
        }

        return words.toArray(new String[words.size()]);
    }
}

