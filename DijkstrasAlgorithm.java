package graphAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements Dijkstra's Algorithm and uses it to find the shortest path between two vertices as well
 * as the shortest distance between two vertices, given a directed graph.  
 * 
 * @author Jessica 
 *
 */
public class DijkstrasAlgorithm {

	private Map<Vertex, Integer> distanceFromSource = new HashMap<Vertex, Integer>();
	private Map<Vertex, Vertex> previousVertex = new HashMap<Vertex, Vertex>();
	private Vertex source;
	private Digraph graph;
	
	/**
	 * This is the implementation of Dijkstra's Algorithm.  It takes in the directed graph and the source vertex, 
	 * and returns a map that contains each vertex (other than the source) and the minimum distance from the source 
	 * vertex to that vertex.  If the graph or source vertex is null, an error is printed out and null is returned.
	 * 
	 * @param pGraph The directed graph on which to execute this algorithm
	 * @param pSource The vertex that is the source / starting point of the search
	 * @return A map containing each vertex (other than the source), and its distance from the source vertex.
	 */
	public Map<Vertex, Integer> findMinimumDistance(Digraph pGraph, Vertex pSource) {
		//Validate that the source and graph are non-null
		if (pGraph == null || pSource == null || pSource.getLabel() == null) {
			System.out.println("The graph and source vertex must be provided.");
			return null;
		}
		
		//Verify that the graph has vertices
		if (pGraph.getVertices() == null || pGraph.getVertices().isEmpty()) {
			System.out.println("The graph does not have any vertices.");
			return null;
		}
		
		//Get the source vertex in the graph
		Vertex lSourceVertex = pGraph.getVertex(pSource.getLabel());
		
		//If the source is null it means we have not yet run this algorithm, and we need to.
		//If the source is not null, check if the source vertex or the graph is different from the last time this 
		//algorithm was run.  If they're different, we need to run it.  Initialize the variables
		if (source == null || !lSourceVertex.equals(source) || !pGraph.equals(graph)) {
			graph = pGraph;
			source = lSourceVertex;
			distanceFromSource = new HashMap<Vertex, Integer>();
			previousVertex = new HashMap<Vertex, Vertex>();
		} else if (lSourceVertex.equals(source) && graph.equals(pGraph)) {
			//If the source vertex and graph parameters are the same as the variables, we know we have already
			//run this algorithm and can directly return the results
			return distanceFromSource;
		}
		
		//Initialize the distance for each vertex (except source) to the max value
		for (Vertex lVertex : pGraph.getVertices()) {
			if (!lVertex.equals(pSource)) {
				distanceFromSource.put(lVertex, Integer.MAX_VALUE);
			}
		}

		List<Vertex> lFringe = new ArrayList<Vertex>();
		List<Edge> lEdgesToTraverse = lSourceVertex.getOutgoingEdges();
		
		//Get each of the next vertices 
		//Use the edge to determine the distance from the source (which is currently only one edge length away)
		//Set the previous vertex to the source vertex 
		//Add the vertex to the "fringe" 
		if (lEdgesToTraverse != null && !lEdgesToTraverse.isEmpty()) {
			for (Edge lEdge : lEdgesToTraverse) {
				Vertex lNextVertex = lEdge.getToVertex();
				distanceFromSource.put(lNextVertex, lEdge.getWeight());
				previousVertex.put(lNextVertex, lEdge.getFromVertex());
				lFringe.add(lNextVertex);
			}
		}
		
		//Loop over the fringe vertices and do what we just did above for each of them
		while (!lFringe.isEmpty()) {
			
			//Get one of the fringe vertices
			Vertex lFringeVertex = lFringe.remove(0);
			lEdgesToTraverse = lFringeVertex.getOutgoingEdges();
			if (lEdgesToTraverse != null && !lEdgesToTraverse.isEmpty()) {
				for (Edge lEdge : lEdgesToTraverse) {
					Vertex lNextVertex = lEdge.getToVertex();
					
					// We don't want to include the source in our calculations
					if (lNextVertex.equals(pSource) || distanceFromSource.get(lNextVertex) == null) {
						continue;
					}
					
					//The distance is the distance from the previous vertex + the edge to this vertex
					Integer lCurrentDistance = distanceFromSource.get(lFringeVertex) + lEdge.getWeight();
					
					//If the distance from the source to this vertex is the max, then update the distance
					if (distanceFromSource.get(lNextVertex).equals(Integer.MAX_VALUE)) {
						
						distanceFromSource.put(lNextVertex, lCurrentDistance);
						previousVertex.put(lNextVertex, lFringeVertex);
						lFringe.add(lNextVertex);
					} else {
						//Check if the current distance is less than the distance that was previously calculated
						if (distanceFromSource.get(lNextVertex) > lCurrentDistance) {
							distanceFromSource.put(lNextVertex, lCurrentDistance);
							previousVertex.put(lNextVertex, lFringeVertex);
						}
					}
				}
			}
		}
		return distanceFromSource;
	}
	
	/**
	 * This method can be called to clear / reset the variables
	 */
	public void clear() {
		graph = null;
		source = null;
		distanceFromSource = new HashMap<Vertex, Integer>();
		previousVertex = new HashMap<Vertex, Vertex>();
	}
	
	/**
	 * Given a directed graph, gets the shortest path between two vertices.  If either the graph, source, or 
	 * destination parameters are null, null will be returned.
	 * 
	 * @param pGraph The directed graph to run the algorithm on
	 * @param pSource The vertex to start at
	 * @param pDestination The vertex to end at
	 * @return A list of vertices, such that position 0 will contain the source vertex, and the last vertex in the 
	 * list will be the destination vertex 
	 */
	public List<Vertex> getShortestPath(Digraph pGraph, Vertex pSource, Vertex pDestination) {
		//Validate the parameters are non-null
		if (pGraph == null || pSource == null || pDestination == null) {
			System.out.println("The graph, source, and destination must all be non-null.");
			return null;
		}
		
		//If we haven't yet done the calculation, do it now
		if ((source == null || previousVertex.isEmpty())
			|| (source != null && !pSource.equals(source)) || (graph != null && !pGraph.equals(graph))) {
			findMinimumDistance(pGraph, pSource);
		} 
		
		List<Vertex> lPathOfVertices = new ArrayList<Vertex>();
		
		//First, let's add the destination
		lPathOfVertices.add(0, pDestination);
		
		//Get the previous vertex for the destination
		Vertex lPreviousVertex = previousVertex.get(pDestination);
		
		//Work backward looking at each previous vertex from destination to source
		while (!lPreviousVertex.equals(pSource)) {
			
			//Push the previous vertex onto the path 
			//(This will insert the value at the front of the list, and shift the other values to the right)
			lPathOfVertices.add(0, lPreviousVertex);
			
			//Get the next previous vertex
			lPreviousVertex = previousVertex.get(lPreviousVertex);
		}
		
		//Make sure to push the source onto the path
		lPathOfVertices.add(0, pSource);
		
		return lPathOfVertices;
	}
	
	/**
	 * Given a directed graph, gets the shortest distance between two vertices.  If either the graph, source, or 
	 * destination parameters are null, null will be returned.
	 * 
	 * @param pGraph The directed graph to run the algorithm on
	 * @param pSource The vertex to start at
	 * @param pDestination The vertex to end at
	 * @return The distance from source to destination
	 */
	public Integer getShortestDistance(Digraph pGraph, Vertex pSource, Vertex pDestination) {
		//Validate the parameters are non-null
		if (pGraph == null || pSource == null || pDestination == null) {
			System.out.println("The graph, source, and destination must all be non-null.");
			return null;
		}
		
		//If we haven't yet done the calculation, do it now
		if ((source == null || distanceFromSource.isEmpty())
			|| (source != null && !pSource.equals(source)) || (graph != null && !pGraph.equals(graph))) {
			findMinimumDistance(pGraph, pSource);
		} 
		
		return distanceFromSource.get(pDestination);
	}
}
