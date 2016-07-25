package graphAssignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a vertex in a directed graph.  It keeps track of both the incoming and outgoing edges.  An 
 * incoming edge is an edge where this vertex is the the "to vertex"; an outgoing edge is an edge where this vertex is 
 * the "from vertex".  A vertex at a minimum should have a label to identify it.
 * 
 * @author Jessica Otte
 *
 */
public class Vertex implements Serializable {
	
	/**
	 * Generated id
	 */
	private static final long serialVersionUID = 8569834335406007436L;
	
	private String label;
	private List<Edge> outgoingEdges;
	private List<Edge> incomingEdges;
	
	/**
	 * Class constructor
	 * 
	 * @param pLabel The label which uniquely identifies this vertex
	 */
	Vertex(String pLabel) {
		label = pLabel;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
	}
	
	/**
	 * 
	 * @return The list of all edges that are directed toward this vertex
	 */
	public List<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	
	/**
	 * 
	 * @return The list of all edges that are directed away from this vertex
	 */
	public List<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	/**
	 * 
	 * @return The label, which uniquely identifies this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Adds an incoming edge to the list of incoming edges.  If the edge is null, an error will be printed out and 
	 * the method will return immediately.  If the list already contains this edge, it will not be added.
	 * 
	 * @param pEdge An edge directed toward this vertex
	 */
	public void addIncomingEdge(Edge pEdge) {
		
		//Validate that the edge is non-null
		if (pEdge == null) {
			System.out.println("Cannot add a null edge");
			return;
		}
		
		//Initialize the list if it hasn't yet been initialized
		if (incomingEdges == null) {
			incomingEdges = new ArrayList<Edge>();
		}
		
		//Add the edge if it doesn't already exist
		if (!incomingEdges.contains(pEdge)) {
			incomingEdges.add(pEdge);
		}
	}
	
	/**
	 * Adds an outgoing edge to the list of outgoing edges.  If the edge is null, an error will be printed out and the
	 * method will return immediately.  If the list already contains this edge, it will not be added.
	 * 
	 * @param pEdge An edge directed away from this vertex
	 */
	public void addOutgoingEdge(Edge pEdge) {
		
		//Validate that the edge is non-null
		if (pEdge == null) {
			System.out.println("Cannot add a null edge");
			return;
		}
		
		//Initialize the list if it hasn't yet been initialized
		if (outgoingEdges == null) {
			outgoingEdges = new ArrayList<Edge>();
		}
		
		//Add the edge if it doesn't already exist
		if (!outgoingEdges.contains(pEdge)) {
			outgoingEdges.add(pEdge);
		}
	}
	
	/**
	 * This method will check if the edge exists in incoming edges list or the outgoing edges list and remove the edge
	 * from the appropriate list.  If the edge is null, an error will be printed out an the method will return 
	 * immediately.  Additionally, if there are no edges associated with this vertex, an error will be printed and the 
	 * method will return.
	 * 
	 * @param pEdge The edge to remove
	 */
	public void removeEdge(Edge pEdge) {
		
		//Validate that the edge is non-null
		if (pEdge == null) {
			System.out.println("Cannot remove a null edge.");
			return;
		}
		
		//Validate that there are edges associated with this vertex
		if ((incomingEdges == null || incomingEdges.isEmpty())
			&& (outgoingEdges == null || outgoingEdges.isEmpty())) {
			System.out.println("There are currently no edges to remove from.");
			return;
		}
		
		//Look for the edge in the incoming and outgoing lists, and remove it accordingly
		if (incomingEdges != null && incomingEdges.contains(pEdge)) {
			incomingEdges.remove(pEdge);
		} else if (outgoingEdges != null && outgoingEdges.contains(pEdge)) {
			outgoingEdges.remove(pEdge);
		}
	}
	
	/**
	 * 
	 * @return A new list comprised of all incoming and outgoing edges.
	 */
	public List<Edge> getAllEdges() {
		List<Edge> lAllEdges = new ArrayList<Edge>();
		lAllEdges.addAll(outgoingEdges);
		lAllEdges.addAll(incomingEdges);
		return lAllEdges;
	}
	
	/**
	 * Two vertices are considered equal if their labels are the same
	 */
	@Override
	public boolean equals(Object pObj) {
		if (pObj == null) {
			return false;
		}
		
		if (pObj == this) {
	    	return true;
	    }
	    
	    if (!(pObj instanceof Vertex)) {
	    	return false;
	    }
	    
	    Vertex lVertex = (Vertex)pObj;
	    
	    return ((getLabel() == null && lVertex.getLabel() == null) || getLabel() != null && getLabel().equals(lVertex.getLabel()));
	}
	
	/**
	 * Returns a string representation of the vertex, displaying the label
	 */
	@Override
	public String toString() {
		 return "Vertex: " + label;
	}
	
}
