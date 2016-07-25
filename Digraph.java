package graphAssignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This is a directed graph data structure.
 * 
 * @author Jessica Otte
 *
 */
public class Digraph implements Serializable {
  
	/**
	 * Generated id
	 */
	private static final long serialVersionUID = -542393287108382128L;
	
	
	private Map<String, Vertex> vertices;
	private Map<Integer, Edge> edges;
	
	/**
	 * Class Constructor. Initializes the vertices and edges maps
	 */
	Digraph() {
		vertices = new HashMap<String, Vertex>();
		edges = new HashMap<Integer, Edge>();
	}
	
	/**
	 * Class Constructor, with list of vertices as a parameter
	 * 
	 * @param pVertices The initial set of vertices for this graph
	 */
	Digraph(List<Vertex> pVertices) {
		vertices = new HashMap<String, Vertex>();
		edges = new HashMap<Integer, Edge>();
		
		//Initialize the Map with the vertices, using the label as the key
		if (pVertices != null) {
			for (Vertex lVertex : pVertices) {
				vertices.put(lVertex.getLabel(), lVertex);
			}
		}
	}
	
	/**
	 * Creates an edge based on the from and to vertices, and adds the edge to the map of edges
	 * 
	 * @param pFromVertex The starting vertex for the edge
	 * @param pToVertex The ending vertex of the edge
	 */
	public void addEdge(Vertex pFromVertex, Vertex pToVertex) {
		addEdge(new Edge(pFromVertex, pToVertex));
	}
	
	/**
	 * Creates an edge based on the from and to vertices and weight, and adds the edge to the map of edges
	 * 
	 * @param pFromVertex The starting vertex for the edge
	 * @param pToVertex The ending vertex of the edge
	 * @param pWeight The weight or cost of this edge
	 */
	public void addEdge(Vertex pFromVertex, Vertex pToVertex, int pWeight) {
		addEdge(new Edge(pFromVertex, pToVertex, pWeight));
	}
	
	/**
	 * Creates an edge based on the passed in parameters, and adds the edge to the map of edges
	 * 
	 * @param pFromVertex The starting vertex for the edge
	 * @param pToVertex The ending vertex of the edge
	 * @param pWeight The weight or cost of this edge
	 * @param pLabel The label or name of this edge
	 */
	public void addEdge(Vertex pFromVertex, Vertex pToVertex, int pWeight, String pLabel) {
		Edge lEdge = new Edge(pFromVertex, pToVertex, pWeight, pLabel);
		addEdge(lEdge);
	}
	
	/**
	 * Adds an edge to the graph.  If the from and to vertices do not yet exist in this graph, they are added.  This 
	 * method is synchronized so that multiple threads won't add edges at the same time. 
	 * 
	 * <p>
	 * If the edge is null or does not have both a from and to vertex, an error is printed
	 * out and the method immediately returns.  Additionally, if the edge already exists in the graph, based on the 
	 * from and to vertices and the weight of the edge, the edge will not be added a second time.
	 * </p>
	 * 
	 * @param pEdge The edge to add to the graph
	 */
	public synchronized void addEdge(Edge pEdge) {
		
		//Validate that the edge is non-null
		if (pEdge == null) {
			System.out.println("Cannot add a null edge.");
			return;
		}
		
		//Validate that the edge has both a from and to vertex
		if (pEdge.getFromVertex() == null || pEdge.getToVertex() == null) {
			System.out.println("An edge must have both a from and to vertex.");
			return;
		}
		
		//If the edge already exists, do not add it
		if (edges.containsValue(pEdge)) {
			System.out.println("This edge already exists");
			return;
		}
				
		//Get the from and to vertices from the edge
		Vertex lFromVertex = pEdge.getFromVertex();
		Vertex lToVertex = pEdge.getToVertex();
		
		//If the from vertex does not yet exist, add it
		if (!vertices.containsKey(lFromVertex.getLabel())) {
			addVertex(lFromVertex);
		}
				
		//If the to vertex does not yet exist, add it
		if (!vertices.containsKey(lToVertex.getLabel())) {
			addVertex(lToVertex);
		}
				
		//Add the edge to the appropriate list for each of the vertices
		lFromVertex.addOutgoingEdge(pEdge);
		lToVertex.addIncomingEdge(pEdge);
		
		//Add the edge to the map of edges
		edges.put(pEdge.hashCode(), pEdge);		
	}
	
	/**
	 * Removes the edge from the graph, and removes the edge from its from and to vertices.  If the edge is null, an 
	 * error is printed out and the method returns immediately.  This method is synchronized so that multiple threads 
	 * won't remove edges at the same time. 
	 * 
	 * @param pEdge The edge to remove from the graph
	 */
	public synchronized void removeEdge(Edge pEdge) {
		
		//Validate that the edge is non-null
		if (pEdge == null) {
			System.out.println("Cannot add a null edge.");
			return;
		}
		
		//Remove the edge from the from and to vertices
		pEdge.getFromVertex().removeEdge(pEdge);
		pEdge.getToVertex().removeEdge(pEdge);
		
		//Remove the edge from the graph
		edges.remove(pEdge.hashCode());
	}
	
	/**
	 * 
	 * @param pLabel The label that uniquely identifies the vertex
	 * @return The Vertex with the label matching pLabel
	 */
	public Vertex getVertex(String pLabel) {
		return vertices.get(pLabel);
	}
	
	/**
	 * Adds a vertex to the graph.  If the vertex is null, an error will be printed out and the method will return 
	 * immediately.  If the vertex already exists in the graph, a message will be printed out and the vertex will not 
	 * be added again.  This method is synchronized so that multiple threads will not be adding vertices at the 
	 * same time.
	 * 
	 * @param pVertex The vertex to add to the graph
	 */
	public synchronized void addVertex(Vertex pVertex) {
		//Validate that the vertex is non-null
		if (pVertex == null) {
			System.out.println("Cannot add a null vertex.");
			return;
		}
				
		//If the vertex already exists, do not add it
		if (vertices.containsValue(pVertex)) {
			System.out.println("This vertex already exists.");
			return;
		}
				
		//Add the vertex to the graph
		vertices.put(pVertex.getLabel(), pVertex);
	}
	
	/**
	 * Removes a vertex from the graph, and removes all connecting edges.  If the vertex is null, or is not contained
	 * in the graph, an error message is printed out and the method returns immediately.  This method is synchronized
	 * so that multiple threads cannot remove vertices at the same time.
	 * 
	 * @param pVertex The vertex to remove from the graph
	 */
	public synchronized void removeVertex(Vertex pVertex) {
		//Validate that the vertex is non-null
		if (pVertex == null) {
			System.out.println("Cannot remove a null vertex.");
			return;
		}
						
		//If the vertex doesn't exist in the graph, nothing to do, return
		if (!vertices.containsValue(pVertex)) {
			System.out.println("This vertex does not exist in the graph.");
			return;
		}
		
		//Remove the vertex from the graph
		Vertex lVertex = vertices.remove(pVertex.getLabel());
		
		//Remove all edges associated with this vertex
		List<Edge> lAllEdges = lVertex.getAllEdges();
		if (lAllEdges != null && !lAllEdges.isEmpty()) {
			while (lAllEdges.size() > 0) {
				removeEdge(lAllEdges.remove(0));
			}
		}
	}
	
	/**
	 * Returns a string representation of the graph.
	 * 
	 * @return A string representing the edges and vertices of the graph
	 */
	@Override
	public String toString() {
		StringBuilder lStrBuilder = new StringBuilder();
		
		lStrBuilder.append("\nEdges:");
		
		if (edges != null && !edges.isEmpty()) {
			Iterator<Entry<Integer, Edge>> lIter = edges.entrySet().iterator();
			while (lIter.hasNext()) {
				Edge lEdge = lIter.next().getValue();
				lStrBuilder.append("\n" + lEdge.toString());
			}
		} else {
			lStrBuilder.append("\nNone.");
		}
		
		lStrBuilder.append("\n\nVertices:\n");
		
		if (vertices != null && !vertices.isEmpty()) {
			lStrBuilder.append(getVertexKeys());
		} else {
			lStrBuilder.append("None.");
		}
		lStrBuilder.append("\n");
		
		return lStrBuilder.toString();
	}
	
	/**
	 * 
	 * @return The label of each vertex in the graph
	 */
	public Set<String> getVertexKeys() {
		return vertices.keySet();
	}
	
	/**
	 * 
	 * @return Each vertex in the graph
	 */
	public List<Vertex> getVertices() {
		return new ArrayList<Vertex>(vertices.values());
	}
	
	/**
	 * 
	 * @return Each edge in the graph
	 */
	public List<Edge> getEdges() {
		return new ArrayList<Edge>(edges.values());
	}
	
	/**
	 * Gets all of the edges associated with a given vertex.  If the vertex is null or if the vertex does not exist in 
	 * the graph, an error is printed out, and the method returns null.
	 * 
	 * @param pVertex The vertex whose edges are returned 
	 * @return All edges, incoming and outgoing, for pVertex
	 */
	public List<Edge> getAllEdges(Vertex pVertex) {
		
		//Validate that the vertex is non-null
		if (pVertex == null) {
			System.out.println("Vertex is null.");
			return null;
		}
		
		if (!vertices.containsKey(pVertex.getLabel())) {
			System.out.println("The graph does not contain the given vertex.");
			return null;
		}
		
		return vertices.get(pVertex.getLabel()).getAllEdges();
	}
	
	/**
	 * This method will return all vertices adjacent to the given vertex where the edge is directed towards pVertex.  
	 * If pVertex is null or is not contained in the graph, an error is printed out and the method returns null.  If 
	 * the vertex does not have any previous vertices, null is returned.
	 * 
	 * @param pVertex The vertex whose previous vertices are desired
	 * @return All adjacent vertices to pVertex where the edge connecting them is incoming for pVertex (directed towards
	 * pVertex)
	 */
	public List<Vertex> getPreviousVertices(Vertex pVertex) {
		//Validate that the vertex is non-null
		if (pVertex == null) {
			System.out.println("Cannot find previous vertices for null vertex.");
			return null;
		}
		
		//Validate that the graph contains this vertex
		if (!vertices.containsKey(pVertex.getLabel())) {
			System.out.println("The vertex does not exist in the graph.");
			return null;
		}
		
		//If the vertex has no incoming edges, return null
		if (pVertex.getIncomingEdges() == null || pVertex.getIncomingEdges().isEmpty()) {
			System.out.println("This vertex does not have any previous vertices.");
			return null;
		}
		
		//Find all adjacent vertices using the incoming edges for the vertex
		List<Vertex> lAdjacentVertices = new ArrayList<Vertex>();
		for (Edge lEdge : pVertex.getIncomingEdges()) {
			lAdjacentVertices.add(lEdge.getFromVertex());
		}			
		
		return lAdjacentVertices;
	}
	
	/**
	 * This method will return all vertices adjacent to the given vertex where the edge is directed away from pVertex.  
	 * If pVertex is null or is not contained in the graph, an error is printed out and the method returns null.  If 
	 * the vertex does not have any next vertices, null is returned.
	 * 
	 * @param pVertex The vertex whose next vertices are desired
	 * @return All adjacent vertices to pVertex where the edge connecting them is outgoing for pVertex (directed away
	 * from pVertex)
	 */
	public List<Vertex> getNextVertices(Vertex pVertex) {
		//Validate that the vertex is non-null
		if (pVertex == null) {
			System.out.println("Cannot find next vertices for null vertex.");
			return null;
		}

		//Validate that the graph contains this vertex
		if (!vertices.containsKey(pVertex.getLabel())) {
			System.out.println("The vertex does not exist in the graph.");
			return null;
		}

		//If the vertex has no outgoing edges, return null
		if (pVertex.getOutgoingEdges() == null || pVertex.getOutgoingEdges().isEmpty()) {
			return null;
		}

		//Find all adjacent vertices using the outgoing edges for the vertex
		List<Vertex> lAdjacentVertices = new ArrayList<Vertex>();
		for (Edge lEdge : pVertex.getOutgoingEdges()) {
			lAdjacentVertices.add(lEdge.getToVertex());
		}			

		return lAdjacentVertices;
	}	
	
	/**
	 * A graph is equal (in this case) if the vertices and edges are the same.
	 */
	@Override
	public boolean equals(Object pObj) {
	    if (pObj == null) {
	    	return false;
	    }
	    
	    if (pObj == this) {
	    	return true;
	    }
	    
	    if (!(pObj instanceof Digraph)) {
	    	return false;
	    }
	    
	    boolean lIsEqual = true;
	    Digraph lGraph = (Digraph)pObj;
	    
	    //A graph is equal if it's vertices and edges are equal
	    //First just check to make sure the amount of vertices and edges are equal
	    lIsEqual = ((getVertices() == null && lGraph.getVertices() == null) || (getVertices().isEmpty() && lGraph.getVertices().isEmpty()) || (getVertices() != null && lGraph.getVertices() != null));
	    lIsEqual = lIsEqual && ((getEdges() == null && lGraph.getEdges() == null) || (getEdges().isEmpty() && lGraph.getEdges().isEmpty()) || (getEdges() != null && lGraph.getEdges() != null));
	    lIsEqual = lIsEqual && getVertices().size() == lGraph.getVertices().size() && getEdges().size() == lGraph.getEdges().size(); 
	    
	    if (lIsEqual) {
	    	
	    	//Make sure all of the vertices in the other graph exist in the list of vertices in this graph
	    	if (lGraph.getVertices() != null && !lGraph.getVertices().isEmpty()) {
		    	for (Vertex lOtherVertex : lGraph.getVertices()) {
		    		if (!getVertices().contains(lOtherVertex)) {
		    			lIsEqual = false;
		    			break;
		    		}
		    	}
	    	}
	    	
	    	if (lIsEqual) {
	    		//Make sure all of the edges in the other graph are contained in this graph
	    		if (lGraph.getEdges() != null && !lGraph.getEdges().isEmpty()) {
	    			for (Edge lOtherEdge : lGraph.getEdges()) {
	    				if (!getEdges().contains(lOtherEdge)) {
	    					lIsEqual = false;
	    					break;
	    				}
	    			}
	    		}
	    	}
	    }
	    	    
	    return lIsEqual;
	}
	
	/**
	 * This method returns true if the graph is considered connected, ie: there is an edge connecting each vertex.  It
	 * performs a depth first search and then compares the number of vertices that were marked as visited with the 
	 * number of vertices in the graph.  If all were visited, then the graph is connected.
	 * 
	 * @return True if the graph is connected, False otherwise
	 * 
	 */
	public Boolean isConnected() {
		//Perform DFS using any vertex in the graph, and get back the vertices that were visited
		List<Vertex> lVerticesVisited = depthFirstSeach(getVertices().get(0));
		
		//Compare the number of vertices visited with the number of vertices in the graph
		return lVerticesVisited.size() == getVertices().size();
	}
	
	/**
	 * Performs a depth first search of the graph, given a starting vertex.  
	 * 
	 * @param pVertex The starting vertex for the search
	 * @return The vertices that were visited
	 */
	public List<Vertex> depthFirstSeach(Vertex pVertex) {
		List<Vertex> lVistedList = new ArrayList<Vertex>();
		
		depthFirstSearch(pVertex, lVistedList);
		
		return lVistedList;
	}
	
	/**
	 * Performs a depth first search of the graph, given a starting vertex and a visited list of vertices.
	 * 
	 * @param pVertex The starting vertex for the search
	 * @param pVisitedList The vertices that were visited
	 */
	private void depthFirstSearch(Vertex pVertex, List<Vertex> pVisitedList) {
		
		//Add the vertex to the list of visited vertices
		pVisitedList.add(pVertex);
		
		//Traverse the graph by getting the next vertex that this vertex can get to, and if it hasn't been visited
		//add it to the list and perform the search on that next vertex
		List<Vertex> lNextVertices = getNextVertices(pVertex);
		if (lNextVertices != null && !lNextVertices.isEmpty()) {
			for (Vertex lNextVertex : getNextVertices(pVertex)) {
				if (!pVisitedList.contains(lNextVertex)) {
					depthFirstSearch(lNextVertex, pVisitedList);
				}
			}
		}
	}
	
	
	/**
	 * This class method saves a specified graph to a specified file, in non-human readable form. If the graph is 
	 * null or has no vertices, then an error is printed out and the graph is not saved.  If a file name is not 
	 * provided, an error is printed and the graph is not saved.
	 * 
	 * @param pGraph The graph to save to the file
	 * @param pFileName The name of the file
	 * @return True if the graph was saved to the file successfully, False otherwise
	 */
	public static Boolean saveGraphToFile(Digraph pGraph, String pFileName) throws IOException {
		//Validate that the graph is not null and not empty
		if (pGraph == null || pGraph.getVertices() == null || pGraph.getVertices().isEmpty()) {
			System.out.println("The graph is null or empty.");
			return false;
		}
		
		//Validate the file name
		if (pFileName == null || pFileName.isEmpty()) {
			System.out.println("A file name must be provided.");
			return false;
		}
		
		FileOutputStream lFileOutputStream = null;
		ObjectOutputStream lObjOut = null;
		
		//Write the graph to the specified file
		try {
			lFileOutputStream = new FileOutputStream(pFileName);
			lObjOut = new ObjectOutputStream(lFileOutputStream);
			lObjOut.writeObject(pGraph);
			lObjOut.flush();
		} catch (FileNotFoundException pException) {
			System.out.println("Exception: " + pException.getMessage());
			return false;
		} catch (IOException pException) {
			System.out.println("Exception: " + pException.getMessage());
			return false;
		} finally {
			if (lFileOutputStream != null) {
				lFileOutputStream.close();
			}
			if (lObjOut != null) {
				lObjOut.close();
			}
		}
		
		return true;
	}
	
	/**
	 * Reads the specified file and returns the graph representation.  If any errors occur, they will be printed out 
	 * and null will be returned.
	 * 
	 * @param pFileName The file to read
	 * @return The graph that was stored in the file
	 */
	public static Digraph readGraphFromFile(String pFileName) throws IOException {
		//Validate the file name is not null
		if (pFileName == null || pFileName.isEmpty()) {
			System.out.println("A file name must be provided.");
			return null;
		}
		
		ObjectInputStream lObjectInputStream = null;
		Digraph lGraph = null;
		
		//Read the graph from the file
		try {
			lObjectInputStream = new ObjectInputStream(new FileInputStream(pFileName));
			Object lObj = lObjectInputStream.readObject();
			if (lObj == null) {
				System.out.println("The object in the file is null");
			} else if (!(lObj instanceof Digraph)) {
				System.out.println("The object in the file is null");
			} else {
				lGraph = (Digraph)lObj;
			}
		} catch (IOException pException) {
			System.out.println("Exception: " + pException.getMessage());
		} catch (ClassNotFoundException pException) {
			System.out.println("Exception: " + pException.getMessage());
		} finally {
			if (lObjectInputStream != null) {
				lObjectInputStream.close();
			}
		}
		return lGraph;
	}
}
