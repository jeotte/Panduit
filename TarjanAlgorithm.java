package graphAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This class implements Tarjan's Algorithm and is used to find strongly connected components in the graph.
 * 
 * @author Jessica Otte
 *
 */
public class TarjanAlgorithm {

	int index = 0;
	Stack<Vertex> nodeStack = new Stack<Vertex>();
	Map<Vertex, Integer> depthMap = new HashMap<Vertex, Integer>();
	Map<Vertex, Integer> lowLinkMap = new HashMap<Vertex, Integer>();
	Map<Vertex, Boolean> onStackMap = new HashMap<Vertex, Boolean>();
	
	//This is a list of strongly connected components (subgraphs of the main digraph)
	List<List<Vertex>> lSetOfSCC = new ArrayList<List<Vertex>>();
	
	/**
	 * This method will apply Tarjan's Algorithm to the graph and return the strongly connected components.  If the 
	 * graph is null or does not have any vertices, an error is printed out and null is returned.
	 * 
	 * @param pGraph The graph for which the algorithm should be applied to
	 * @return The strongly connected components
	 */
	public List<List<Vertex>> findStronglyConnectedComponents(Digraph pGraph) {
		//Validate that the graph is non-null and has vertices
		if (pGraph == null || pGraph.getVertices() == null || pGraph.getVertices().isEmpty()) {
			System.out.println("The graph must be non-null and have at least 1 vertex.");
			return null;
		}
		
		//Initialize the map
		for (Vertex lVertex : pGraph.getVertices()) {
			depthMap.put(lVertex, null);
		}
		
		//Now loop over all vertices and apply the algorithm, if the vertex hasn't been visited
		for (Vertex lVertex : pGraph.getVertices()) {
			if (depthMap.get(lVertex) == null) {
				strongConnect(lVertex);
			}
		}
		
		return lSetOfSCC;
	}
	
	private void strongConnect(Vertex pVertex) {
		List<Vertex> lStronglyConnectedComponent = null;
		depthMap.put(pVertex, index);
		lowLinkMap.put(pVertex, index);
		index++;
		nodeStack.push(pVertex);
		onStackMap.put(pVertex, true);
		
		List<Edge> lOutgoingEdges = pVertex.getOutgoingEdges();
		if (lOutgoingEdges != null && !lOutgoingEdges.isEmpty()) {
			for (Edge lEdge : lOutgoingEdges) {
				Vertex lNextVertex = lEdge.getToVertex();
				
				if (lNextVertex != null && depthMap.containsKey(lNextVertex)
					&& depthMap.get(lNextVertex) == null) {
					strongConnect(lNextVertex);
					if (lowLinkMap.get(pVertex) > lowLinkMap.get(lNextVertex)) {
						lowLinkMap.put(pVertex, lowLinkMap.get(lNextVertex));
					}
				} else if (onStackMap.containsKey(lNextVertex)
					&& onStackMap.get(lNextVertex) == true) {
					//This next vertex is in the stack and hence in the current SCC
					if (lowLinkMap.get(pVertex) > depthMap.get(lNextVertex)) {
						lowLinkMap.put(pVertex, depthMap.get(lNextVertex));
					}
				}
			}
		}
		
		//If this vertex is a root node, pop the stack and generate an SCC
		if (lowLinkMap.get(pVertex).equals(depthMap.get(pVertex))) {
			lStronglyConnectedComponent = new ArrayList<Vertex>();
			
			Vertex lStackVertex = nodeStack.pop();
			onStackMap.put(lStackVertex, false);
			lStronglyConnectedComponent.add(lStackVertex);
			
			while (!lStackVertex.equals(pVertex)) {
				lStackVertex = nodeStack.pop();
				onStackMap.put(lStackVertex, false);
				lStronglyConnectedComponent.add(lStackVertex);
			}
			lSetOfSCC.add(lStronglyConnectedComponent);
		}
	}
}
