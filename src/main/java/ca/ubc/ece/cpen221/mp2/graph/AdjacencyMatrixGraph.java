package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.util.*;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 ******************************************************************************/

public class AdjacencyMatrixGraph implements Graph, Comparator{

    private List<List<Boolean>> adjMatrix = new ArrayList<>(); //List of columns
    private Map<Vertex, Integer> matrixMap = new HashMap<>();
    private int vertexCount = 0;

    public AdjacencyMatrixGraph() { }

    public AdjacencyMatrixGraph(List<Vertex> inputList) {
        for (Vertex v : inputList) {
            addVertex(v);
        }
    }

    /**
     * Adds a vertex to the graph.
     * <p>
     * Precondition: v is not already a vertex in the graph
     * </p>
     */
    public void addVertex(Vertex v){
        matrixMap.put(v, vertexCount);
        List<Boolean> vList = new ArrayList<>();
        vertexCount++;

        for(List<Boolean> row : adjMatrix){
            row.add(false);
        }

        for(int i = 0; i < vertexCount; i++){
            vList.add(false);
        }
        adjMatrix.add(vList);
    }

    /**
     * Adds an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are vertices in the graph
     * </p>
     */
    public void addEdge(Vertex v1, Vertex v2){
        int index1 = matrixMap.get(v1);
        int index2 = matrixMap.get(v2);

        adjMatrix.get(index1).set(index2, true); //Change the v2 boolean for the row corresponding to v1
        adjMatrix.get(index2).set(index1, true); //Change the v1 boolean for the row corresponding to v2
    }

    /**
     * Check if there is an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are vertices in the graph
     * </p>
     * <p>
     * Postcondition: return true iff an edge from v1 connects to v2
     * </p>
     */
    public boolean edgeExists(Vertex v1, Vertex v2){
        int index1 = matrixMap.get(v1);
        int index2 = matrixMap.get(v2);

        return adjMatrix.get(index1).get(index2);
    }

    /**
     * Get an array containing all vertices adjacent to v.
     * <p>
     * Precondition: v is a vertex in the graph
     * </p>
     * <p>
     * Postcondition: returns a list containing each vertex w such that there is
     * an edge from v to w. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no downstream neighbors.
     * </p>
     */
    public List<Vertex> getNeighbors(Vertex v){
        List<Vertex> neighbours = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        int rowIndex = matrixMap.get(v);

        for(int i = 0; i < adjMatrix.get(rowIndex).size(); i++){
            if(adjMatrix.get(rowIndex).get(i) == true){
                indices.add(i);
            }
        }
        for(Vertex vCheck : matrixMap.keySet()){
            if(indices.contains(matrixMap.get(vCheck))){
                neighbours.add(vCheck);
            }
        }

        return neighbours;
    }

    /**
     * Get all vertices in the graph.
     * <p>
     * Postcondition: returns a list containing all vertices in the graph,
     * sorted by label in non-descending order.
     * This method should return a list of size 0 iff the graph has no vertices.
     * </p>
     */
    public List<Vertex> getVertices(){
        List<Vertex> vertices = new ArrayList<>();

        for(Vertex v : matrixMap.keySet()){
            vertices.add(v);
        }

        Comparator compare = new AdjacencyMatrixGraph();

        vertices.sort(compare);

        return vertices;
    }


    @Override
    public int compare(Object v1, Object v2){
        return ((Vertex) v1).getLabel().compareTo(((Vertex) v2).getLabel());
    }

}
