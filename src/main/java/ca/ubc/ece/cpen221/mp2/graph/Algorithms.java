package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;

import java.lang.reflect.Array;
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
	 * This is provided as an example to indicate that this method and
	 * other methods should be implemented here.
	 *
	 * You should write the specs for this and all other methods.
	 *
	 * @param graph, an undirected and unweighted object that implementats graph. Must contain
	 * @param a, a Vertex that must be in graph.
	 * @param b, a Vertex that must be in graph.
	 * @return the shortest distance between a and b, taking each edge to be length 1.
	 * @throws IllegalArgumentException if there is no path from a to b.
	 * @throws IllegalArgumentException if a or b are not in graph
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		Map<Vertex, Integer> distances = new HashMap<>();
		Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
		Vertex workingVertex = a;
		List<Vertex> vertices = new ArrayList<>();
		boolean bFound = false;

		if (!vertices.contains(a) || !vertices.contains(b)) {
			throw new IllegalArgumentException("One or more vertices not in graph.");
		}

		if (a.equals(b)) {
			return 0;
		}

		for (Vertex v : graph.getVertices()) {
			distances.put(v, -1);
		}

		distances.put(a, 0);
		workingQueue.add(a);

		while (!workingQueue.isEmpty() && !bFound) {
			for (Vertex neighbor : graph.getNeighbors(workingVertex)) {
				if (distances.get(neighbor) == -1) {
					distances.put(neighbor, 2 + distances.get(workingVertex));
					if (neighbor.equals(b)) {
						bFound = true;
						break;
					}
					workingQueue.add(neighbor);
				}
			}

			workingVertex = workingQueue.poll();
			if (workingVertex.equals(b)) {
				bFound = true;
			}
		}

		if (!bFound) {
			throw new IllegalArgumentException("No path between a and b");
		}
	return distances.get(b);
	}


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
	 * @param graph, the graph through which we wish to search. Must contain at least one vertex
	 * @return a Set of Lists, with each List indicating the chronological order at which vertices are reached.
	 *         Each List corresponds to a different starting Vertex.
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		Set<List<Vertex>> allResults = new HashSet<>();
		List<Vertex> result = new ArrayList<>();
		Stack<Vertex> workingStack = new Stack<>();
		List<Vertex> neighbors = new ArrayList<>();
		Vertex workingVertex;

		for(Vertex vStart : graph.getVertices()){ //Starts at every vertex on the graph.
			result.clear();

			result.add(vStart);
			workingStack.push(vStart);
			workingVertex = vStart;

			while(!workingStack.isEmpty()){
				for(Vertex neighbor : graph.getNeighbors(workingVertex)){
					if(!result.contains(neighbor)){
						result.add(neighbor);
						workingStack.push(neighbor);
						break;
					}
				}

				workingVertex = workingStack.pop();
			}
			allResults.add(result);
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
	 * @param graph, the graph through which we wish to search. Must contain at least one vertex
	 * @return a Set of Lists, with each List indicating the chronological order at which the vertices are reached.
	 *         Each List corresponds to a different starting Vertex.
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		Set<List<Vertex>> allResults = new HashSet<>();
		List<Vertex> result = new ArrayList<>();
		Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
		Vertex workingVertex;

		for(Vertex vStart : graph.getVertices()){
			result.clear();

			workingQueue.add(vStart);
			result.add(vStart);
			workingVertex = vStart;
			workingQueue.add(workingVertex);

			while(!workingQueue.isEmpty()){

				for(Vertex neighbor : graph.getNeighbors(workingVertex)){
					if(!result.contains(neighbor)){
						result.add(neighbor);
						workingQueue.add(neighbor);
					}
				}
				workingVertex = workingQueue.poll();
			}
		}

		return allResults;
	}

	/**
	 * Finds the center vertex of a graph, with the center defined as the vertex with the smallest eccentricity.
	 * A vertex's eccentricity is the defined as the greatest distance between that vertex and any other in the graph
	 * and to be infinite if the vertex is not connected to any other.
	 *
	 * @param graph, the graph. Must have at least one connection between vertices.
	 * @returns Vertex, the vertex with the lowest eccentricity. If multiple vertices have the eccentricity,
	 * 			 returns the vertex with the smallest ID lexicographically.
	 */
	 public static Vertex center(Graph graph) {
		 Map<Vertex,Integer> distances = new HashMap<>();
		 Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
		 Vertex workingVertex;
		 List<Vertex> vertices = graph.getVertices();
		 Vertex center = vertices.get(0);
		 int minMax = 0;
		 boolean isInitialized = false;
		 int localMax;

		 for(Vertex v : vertices){
			 distances.put(v, -1);
		 }
		 for(Vertex vStart : vertices) {
		 	 if(!graph.getNeighbors(vStart).isEmpty()) {
				 distances.put(vStart, 0);

				 workingQueue.add(vStart);
				 workingVertex = vStart;
				 while (!workingQueue.isEmpty()) {

					 for (Vertex neighbor : graph.getNeighbors(workingVertex)) {
						 if (distances.get(neighbor) == -1) {
							 distances.put(neighbor, 2 + distances.get(workingVertex));
							 workingQueue.add(neighbor);
						 }
					 }

					 workingVertex = workingQueue.poll();
				 }

				 localMax = Collections.max(distances.values());

				 if (!isInitialized) {
					 minMax = localMax;
					 isInitialized = true;
				 } else if (localMax < minMax) {
					 minMax = localMax;
					 center = vStart;
				 }
			 }
		 }
		 return center;
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
			Map<Vertex,Integer> distances = new HashMap<>();
			Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
			Vertex workingVertex;
			List<Vertex> vertices = graph.getVertices();
			int diameter = Integer.MAX_VALUE;
			int localMax;
			boolean finiteDiameter = false;

			for(Vertex v : vertices){
				distances.put(v, -1);
			}
			for(Vertex vStart : vertices) {
				if (!graph.getNeighbors(vStart).isEmpty()) {
					distances.put(vStart, 0);

					workingQueue.add(vStart);
					workingVertex = vStart;
					while (!workingQueue.isEmpty()) {

						for (Vertex neighbor : graph.getNeighbors(workingVertex)) {
							if (distances.get(neighbor) == -1) {
								distances.put(neighbor, 2 + distances.get(workingVertex));
								workingQueue.add(neighbor);
							}
						}

						workingVertex = workingQueue.poll();
					}

					localMax = Collections.max(distances.values());

					if(!finiteDiameter){
						diameter = localMax;
						finiteDiameter = true;
					}
					else if (localMax > diameter) {
						diameter = localMax;
					}
				}
			}
			return diameter;
		}
}
