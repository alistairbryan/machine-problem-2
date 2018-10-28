package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 *
	 * Please see the README for this machine problem for a more detailed
	 * specification of the behavior of each method that one should
	 * implement.
	 */

	/**
	 *
	 *
	 * @param graph, an undirected and unweighted object that implements graph. Must contain
	 * @param a, a Vertex that must be in graph.
	 * @param b, a Vertex that must be in graph.
	 * @return the shortest distance between a and b, taking each edge to be length 1,
	 *         -1 if there is no path between a and b.
	 * @throws IllegalArgumentException if a or b are not in graph
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) { return Algorithms.findDistances(graph,a,b).get(b); }

	/**
	 * Perform a complete depth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a DFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 * @param graph, the graph through which we wish to search.
     *               Requires that the graph contains at least one Vertex
	 * @return a Set of Lists, with each List indicating the chronological order at which vertices are reached.
	 *         Each List corresponds to a different starting Vertex.
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		Set<List<Vertex>> allResults = new HashSet<>();

		for(Vertex vStart : graph.getVertices()){ //Starts at every vertex on the graph.
			List<Vertex> visited = new ArrayList<>();
			Stack<Vertex> workingStack = new Stack<>();

			visited.add(vStart);
			workingStack.push(vStart);
			Vertex position = vStart;

			while(!workingStack.isEmpty()){

				boolean noUnvisited = true;
				for (Vertex neighbour : graph.getNeighbors(position)) {
					if (!visited.contains(neighbour)) {
						noUnvisited = false;
					}
				}

				if (noUnvisited) {
					workingStack.pop();
				} else {
					for(Vertex neighbour : graph.getNeighbors(position)){
						if(!visited.contains(neighbour)){
							visited.add(neighbour);
							workingStack.push(neighbour);
							break;
						}
					}
				}

				if (!workingStack.empty())
					position = workingStack.peek();
			}

			allResults.add(visited);
		}

		return allResults;

	}

	/**
	 * Perform a complete breadth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a BFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 * @param graph, the graph through which we wish to search.
     *               Requires that the graph contain at least one Vertex.
	 * @return a Set of Lists, with each List indicating the chronological order at which the vertices are reached.
	 *         Each List corresponds to a different starting Vertex.
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		Set<List<Vertex>> allResults = new HashSet<List<Vertex>>();
		Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
		Vertex workingVertex;

		for(Vertex vStart : graph.getVertices()){
			List<Vertex> result = new ArrayList<>();

			workingQueue.add(vStart);
			result.add(vStart);
			workingVertex = vStart;
			workingQueue.add(workingVertex);

			while(!workingQueue.isEmpty() || workingVertex != null){

				for(Vertex neighbor : graph.getNeighbors(workingVertex)){
					if(!result.contains(neighbor)){
						result.add(neighbor);
						workingQueue.add(neighbor);
					}
				}
				workingVertex = workingQueue.poll();
			}
			allResults.add(result);
		}

		return allResults;
	}

	/**
	 * Finds the center vertex of a graph, with the center defined as the vertex with the smallest eccentricity.
	 * A vertex's eccentricity is the defined as the greatest distance between that vertex and any other in the graph
	 * and to be infinite if the vertex is not connected to any other.
	 *
	 * @param graph, a graph for which you wish to find the center.
     *               Requires the graph contain at least one Vertex and that there is at least one connection between vertices.
	 * @returns Vertex that is part of the largest connected component of the graph, with the lowest
	 * 			eccentricity. If multiple vertices have the eccentricity,
	 * 			returns the vertex with the smallest ID lexicographically.
	 */
	public static Vertex center(Graph graph) {
		Vertex center = graph.getVertices().get(0);
		boolean isInitialized = false;

		int minEccentricity = Integer.MAX_VALUE;
		int minConnectedSize = Integer.MAX_VALUE;
		//Map<Vertex, Integer> connectedSize = new HashMap<>();

		for(Vertex vStart : graph.getVertices()){ //remember vertices are already sorted low to high

			if(!graph.getNeighbors(vStart).isEmpty()){
				Map<Vertex, Integer> distances = Algorithms.findDistances(graph, vStart, null);
				int thisEccentricity = Collections.max(distances.values());

				if(!isInitialized){
					minEccentricity = thisEccentricity;
					center = vStart;
					isInitialized = true;
					minConnectedSize = getConnectedSize(distances);
				} else if (thisEccentricity < minEccentricity && getConnectedSize(distances) >= minConnectedSize){
					minEccentricity = thisEccentricity;
					center = vStart;
				}
			}
		}
		return center;
	}

	/**
	 *
	 * @param distances a Map that contains every other vertex and its position. If the two vertices are disconnected,
	 *                  the integer value is -1
	 * @return the size of the connected portion of the graph that the vertex belongs to.
	 */
	private static int getConnectedSize(Map<Vertex,Integer> distances) {
		int count = 0;
		for (Integer value : distances.values()) {
			if (value != -1) {
				count++;
			}
		}
		return count;
	}

	 /**
	  * Determines the diameter of a graph, with the diameter defined as the maximum finite distance between
	  * two vertices on the graph. If there is no such finite distance, the diameter is infinite and the
	  * function returns Integer.MAX_VALUE.
	  *
	  * @param graph, graph with at least one vertex
	  * @return diameter, the greatest finite distance between any 2 vertices in graph. Returns Integer.MAX_VAlUE if
	  * 		 no such finite distance exists.
	  */
		public static int diameter(Graph graph) {
			Map<Vertex, Integer> distances = new HashMap<>();
			List<Vertex> vertices = graph.getVertices();
			int diameter = Integer.MAX_VALUE;
			int localMax;
			boolean finiteDiameter = false;

			for (Vertex vStart : vertices){
				if (!graph.getNeighbors(vStart).isEmpty()){
					localMax = Collections.max(Algorithms.findDistances(graph, vStart, null).values());

					if(!finiteDiameter){
						diameter = localMax;
						finiteDiameter = true;
					}else if(localMax > diameter){
						diameter = localMax;
					}
				}
			}
			return diameter;
		}

    /**
     *
     * @param graph, an undirected and unweighted object that implements graph. Must contain
     * @param startVertex, the start Vertex. All distances are given relative to it.
     *                         Requires that startVertex is in the graph.
     * @param breakVertex, once the search reaches this Vertex, the distances map is returned.
     *                     To carry out a full search, pass null.
     * @return A Map<Vertex, Integer> that maps vertices within the graph to their start Vertex.
     * @throws IllegalArgumentException if startVertex is not in graph.
     */
		private static Map<Vertex, Integer> findDistances(Graph graph, Vertex startVertex, Vertex breakVertex){
			Map<Vertex, Integer> distances = new HashMap<>();
			Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
			Vertex workingVertex = startVertex;
			List<Vertex> vertices = graph.getVertices();
			boolean bFound = false;

			if (!vertices.contains(startVertex)) {
				throw new IllegalArgumentException("One or more vertices not in graph.");
			}

			if (startVertex.equals(breakVertex)) {
				distances.put(breakVertex, 0);
				return distances;
			}

			for (Vertex v : vertices) {
				distances.put(v, -1);
			}

			distances.put(startVertex, 0);
			workingQueue.add(startVertex);

			while (workingVertex != null && !bFound) {
				for (Vertex neighbor : graph.getNeighbors(workingVertex)) {
					if (distances.get(neighbor) == -1) {
						distances.put(neighbor, 1 + distances.get(workingVertex));
						if (neighbor.equals(breakVertex)) {
							bFound = true;
							break;
						}
						workingQueue.add(neighbor);
					}
				}

				workingVertex = workingQueue.poll();
				if (workingVertex != null && workingVertex.equals(breakVertex)) {
					bFound = true;
				}
			}
			return distances;
		}
}
