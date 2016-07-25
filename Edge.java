package graphAssignment;

import java.io.Serializable;

/**
 * This class represents an edge in a directed graph.  It must have a from and to vertex, and can optionally have a
 * label and weight. 
 * 
 * @author Jessica Otte
 */
public class Edge implements Serializable  {
	
	/**
	 * Generated id
	 */
	private static final long serialVersionUID = -2234935466538726673L;
	
	private Vertex fromVertex;
	private Vertex toVertex;
	private String label;
	private int weight;
	private final static int DEFAULT_EDGE_WEIGHT = 1;
	
	
	/**
	 * Class constructor that takes in the from and to vertices, initializes the label to empty string and defaults the 
	 * weight to 1.
	 * 
	 * @param pFromVertex The vertex that this edge starts at
	 * @param pToVertex The vertex that this edge connects to
	 */
	Edge(Vertex pFromVertex, Vertex pToVertex) {
		this(pFromVertex, pToVertex, DEFAULT_EDGE_WEIGHT, "");
	}
	
	/**
	 * Class constructor that takes in the from and to vertices, as well as the weight, and initializes the label 
	 * to empty string.
	 * 
	 * @param pFromVertex The vertex that this edge starts at
	 * @param pToVertex The vertex that this edge connects to
	 * @param pWeight The weight or cost of this edge
	 */
	Edge(Vertex pFromVertex, Vertex pToVertex, int pWeight) {
		this(pFromVertex, pToVertex, pWeight, "");
	}
	
	/**
	 * Class constructor that takes in the from and to vertices, the weight, and the label.
	 * @param pFromVertex The vertex that this edge starts at
	 * @param pToVertex The vertex that this edge connects to
	 * @param pWeight The weight or cost of this edge
	 * @param pLabel The label or name
	 */
	Edge(Vertex pFromVertex, Vertex pToVertex, int pWeight, String pLabel) {
		fromVertex = pFromVertex;
		toVertex = pToVertex;
		label = pLabel;
		weight = pWeight;
	}
	
	/**
	 * 
	 * @return The from vertex, which is the starting point for this edge
	 */
	public Vertex getFromVertex() {
		return fromVertex;
	}
	
	/**
	 * 
	 * @return The to vertex, which is the end of this edge
	 */
	public Vertex getToVertex() {
		return toVertex;
	}
	
	/**
	 * 
	 * @return The label or name of this edge
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @param pLabel The label or name for this edge
	 */
	public void setLabel(String pLabel) {
		label = pLabel;
	}
	
	/**
	 * 
	 * @return The weight or cost of this edge
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * 
	 * @param pWeight The weight or cost of this edge
	 */
	public void setWeight(int pWeight) {
		weight = pWeight;
	}
	
	/*
	//This will take in a vertex and return the other vertex that this edge connects to
	//TODO: is this needed?
	public Vertex getNeighbor(Vertex pVertex) {
		if (pVertex == null) {
			return null;
		}
			
		if (pVertex.equals(fromVertex)) {
			return toVertex;
		} else if (pVertex.equals(toVertex)) {
			return fromVertex;
		} else {
			return null;
		}
	}*/
	
	/**
	 * Two edges are considered equal if their from and to vertices, and the weight is equal.
	 */
	@Override
	public boolean equals(Object pObj) {
	    if (pObj == null) {
	    	return false;
	    }
	    
	    if (pObj == this) {
	    	return true;
	    }
	    
	    if (!(pObj instanceof Edge)) {
	    	return false;
	    }
	    
	    Edge lEdge = (Edge)pObj;
	    
	    boolean lIsEqual = true;
	    
	    lIsEqual = (getFromVertex() == null && lEdge.getFromVertex() == null) || (getFromVertex() != null && getFromVertex().equals(lEdge.getFromVertex()));
	    lIsEqual = lIsEqual && ((getToVertex() == null && lEdge.getToVertex() == null) || (getToVertex() != null && getToVertex().equals(lEdge.getToVertex())));
	    lIsEqual = lIsEqual && (getWeight() == lEdge.getWeight());
	    
	    return lIsEqual;
	}
	
	/**
	 * Returns a string representation of the edge, displaying the from and to vertices, along with the weight and label
	 */
	@Override
	public String toString() {
		 return "{" + fromVertex + ", " + toVertex + "}\t\tWeight: " + weight + "\tLabel: " + label;
	}

}
