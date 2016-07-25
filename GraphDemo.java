package graphAssignment;

import java.io.IOException;
import java.util.List;

public class GraphDemo {

	public static void main(String[] args) {
		
		Digraph lDigraph = new Digraph();
		System.out.println("Starting with a blank Graph");
		System.out.println(lDigraph);
		
		System.out.println("Adding vertex V1 and V2 to the graph.");
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		lDigraph.addVertex(lVertex1);
		lDigraph.addVertex(lVertex2);
		
		System.out.println(lDigraph);
		
		System.out.println("Adding an edge between V1 and V2.");
		Edge lEdge1to2 = new Edge(lVertex1, lVertex2);
		lDigraph.addEdge(lEdge1to2);
		
		System.out.println(lDigraph);
		
		System.out.println("Adding an edge between V3 and V4, which will also add those vertices.");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Edge lEdge3to4 = new Edge(lVertex3, lVertex4, 5, "edge v3 to v4");
		lDigraph.addEdge(lEdge3to4);
		
		System.out.println(lDigraph);
		
		System.out.println("Test if the graph is connected:");
		System.out.println("Is connected: " + lDigraph.isConnected());
		
		System.out.println("Add an edge from V2 to V3");
		Edge lEdge2to3 = new Edge(lVertex2, lVertex3, 4);
		lDigraph.addEdge(lEdge2to3);
		
		System.out.println(lDigraph);
		System.out.println("Is connected: " + lDigraph.isConnected());
		
		System.out.println("Adding a few more edges.");
		
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		Vertex lVertex8 = new Vertex("V8");
		Vertex lVertex9 = new Vertex("V9");
		
		Edge lEdge4to1 = new Edge(lVertex4, lVertex1, 3, "edge v4 to v1");
		Edge lEdge1to3 = new Edge(lVertex1, lVertex3, 2, "edge v1 to v3");
		Edge lEdge2to5 = new Edge(lVertex2, lVertex5, 3, "edge v2 to v5");
		Edge lEdge5to6 = new Edge(lVertex5, lVertex6, 3, "edge v5 to v6");
		Edge lEdge5to7 = new Edge(lVertex5, lVertex7, 6, "edge v5 to v7");
		Edge lEdge7to5 = new Edge(lVertex7, lVertex5, 1, "edge v7 to v5");
		Edge lEdge8to7 = new Edge(lVertex8, lVertex7, 5, "edge v8 to v7");
		Edge lEdge3to7 = new Edge(lVertex3, lVertex7, 1, "edge v3 to v7");
		Edge lEdge2to7 = new Edge(lVertex2, lVertex7, 5, "edge v2 to v7");
		Edge lEdge7to9 = new Edge(lVertex7, lVertex9, 4, "edge v7 to v9");
		
		lDigraph.addEdge(lEdge4to1);
		lDigraph.addEdge(lEdge1to3);
		lDigraph.addEdge(lEdge2to5);
		lDigraph.addEdge(lEdge5to6);
		lDigraph.addEdge(lEdge5to7);
		lDigraph.addEdge(lEdge7to5);
		lDigraph.addEdge(lEdge8to7);
		lDigraph.addEdge(lEdge3to7);
		lDigraph.addEdge(lEdge2to7);
		lDigraph.addEdge(lEdge7to9);
		
		System.out.println(lDigraph);
		System.out.println("Total number of edges: " + lDigraph.getEdges().size());
		
		System.out.println("Removing the edge from v2 to v7.");
		lDigraph.removeEdge(lEdge2to7);
		
		System.out.println(lDigraph);
		System.out.println("Total number of edges: " + lDigraph.getEdges().size());
		
		System.out.println("Total number of vertices: " + lDigraph.getVertices().size());
		System.out.println("Removing the vertex v9.");
		lDigraph.removeVertex(lVertex9);
		
		System.out.println(lDigraph);
		System.out.println("Total number of vertices: " + lDigraph.getVertices().size());
		
		System.out.println("\nThe edges for vertex v3");
		List<Edge> lV3Edges = lDigraph.getAllEdges(lVertex3);
		for (Edge lEdge : lV3Edges) {
			System.out.println(lEdge);
		}
		
		System.out.println("\nPartition the graph based on strongly connected components");
		TarjanAlgorithm lTarjanAlgorithm = new TarjanAlgorithm();
		List<List<Vertex>> lListOfSCC = lTarjanAlgorithm.findStronglyConnectedComponents(lDigraph);
		for (List<Vertex> lSCC : lListOfSCC) {
			System.out.println(lSCC);
		}
		
		System.out.println("\nFind the shortest path between v1 and v7");
		DijkstrasAlgorithm lDijkstrasAlgorithm = new DijkstrasAlgorithm();
		Integer lDistance = lDijkstrasAlgorithm.getShortestDistance(lDigraph, lVertex1, lVertex7);
		System.out.println("Distance: " + lDistance);
		List<Vertex> lPath = lDijkstrasAlgorithm.getShortestPath(lDigraph, lVertex1, lVertex7);
		System.out.println("Path: " + lPath);
		
		System.out.println("\nSave the graph to file: Graph2File");
		try {
			Digraph.saveGraphToFile(lDigraph, "Graph2File");
		} catch (IOException pException) {
			System.out.println("An exception occured saving the graph");
		}
		
		System.out.println("\nRead the graph from the file just created, Graph2File");
		try {
			Digraph lReadGraph = Digraph.readGraphFromFile("Graph2File");
			System.out.println("The graph from the file: ");
			System.out.println(lReadGraph);
		} catch (IOException pException) {
			System.out.println("An exception occured reading the graph from the file");
		}
	}
}
