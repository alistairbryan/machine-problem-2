package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.core.Graph;

import java.util.*;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Lists.
 *
 ******************************************************************************/


public class AdjacencyListGraph implements Graph, Comparator {

    Map<Vertex, Set<Vertex>> adjacencyList = new HashMap<Vertex, Set<Vertex>>();



    public AdjacencyListGraph() { }

    public AdjacencyListGraph(List<Vertex> inputList) {

    }

    /**
     * Adds a vertex to the graph.
     * <p>
     * Precondition: v is not already a vertex in the graph
     * </p>
     */
    public void addVertex(Vertex v) {
        adjacencyList.put(v,new HashSet<Vertex>());
    }

    /**
     * Adds an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are vertices in the graph
     * </p>
     */
    public void addEdge(Vertex v1, Vertex v2) {
        Set<Vertex> v1set = adjacencyList.get(v1);
        v1set.add(v2);
        adjacencyList.put(v1, v1set);

        Set<Vertex> v2set = adjacencyList.get(v2);
        v2set.add(v1);
        adjacencyList.put(v2, v2set);

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
    public boolean edgeExists(Vertex v1, Vertex v2) {
        if (adjacencyList.get(v1).contains(v2) && adjacencyList.get(v2).contains((v1))) {
            return true;
        }

        return false;
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
    public List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> returnlist = new ArrayList<Vertex>();
        returnlist.addAll(adjacencyList.get(v));
        return returnlist;

    }

    /**
     * Get all vertices in the graph.
     * <p>
     * Postcondition: returns a list containing all vertices in the graph,
     * sorted by label in non-descending order.
     * This method should return a list of size 0 iff the graph has no vertices.
     * </p>
     */
    public List<Vertex> getVertices() {
        List<Vertex> returnlist = new ArrayList<Vertex>();
        for(Map.Entry<Vertex,Set<Vertex>> vertex : adjacencyList.entrySet()) {
            returnlist.add(vertex.getKey());
        }

        Comparator compare = new AdjacencyListGraph();
        returnlist.sort(compare);

        return returnlist;
    }

    /**
     * Compares vertices alphabetically
     * @param v1: Type Vertex
     * @param v2: Type vertex
     * @return normal comparator function
     */
    @Override
    public int compare(Object v1, Object v2) {
        return ((Vertex) v1).getLabel().compareTo(((Vertex) v2).getLabel());
    }
}
