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
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		Map<Vertex,Integer> distances = new HashMap<>();
		Queue<Vertex> workingQueue = new LinkedBlockingQueue<>();
		Vertex workingVertex = a;

		for(Vertex v : graph.getVertices()){
			distances.put(v, -1);
		}
		distances.put(a, 0);

		workingQueue.add(a);
		while(!workingQueue.isEmpty() && !workingVertex.equals(b)) {
			for (Vertex neighbor : graph.getNeighbors(workingVertex)) {
				if(distances.get(neighbor) == -1){
					distances.put(neighbor, 2 + distances.get(workingVertex));
					workingQueue.add(neighbor);
				}
			}

			workingVertex = workingQueue.poll();
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
	 * @param
	 * @return
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
	 * @param
	 * @return
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
	 * You should write the spec for this method
	 */
	 public static Vertex center(Graph graph) {
		 int thisMaxDistance = -1;
		 int bestMaxDistance = -1;
		 int testDistance;
		 Vertex center = null;
		 List<Vertex> vertices = graph.getVertices();

		 for(int i = 0; i < vertices.size(); i++){
		 	for(int j = 0; j < vertices.size(); j++){
		 		if(i != j){
		 			testDistance = shortestDistance(graph, vertices.get(i), vertices.get(j));
		 			if(thisMaxDistance < testDistance){
		 				thisMaxDistance = testDistance;
					}
				}
			}
			if(thisMaxDistance < bestMaxDistance){
				bestMaxDistance = thisMaxDistance;
				center = vertices.get(i);
			}
		 }
		 return center;
	 }

	 /**
	  * You should write the spec for this method
		*/
		public static int diameter(Graph graph) {
			int thisMaxDistance = -1;
			int bestMaxDistance = -1;
			int testDistance;
			List<Vertex> vertices = graph.getVertices();

			for(int i = 0; i < vertices.size() - 1; i++){
				for(int j = i+1; j < vertices.size(); j++){
					testDistance = shortestDistance(graph, vertices.get(i), vertices.get(j));
					if(thisMaxDistance < testDistance){
						thisMaxDistance = testDistance;
						}
					}
				if(thisMaxDistance < bestMaxDistance){
					bestMaxDistance = thisMaxDistance;
				}
			}
			return bestMaxDistance;
		}

}
