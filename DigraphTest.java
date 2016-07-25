package graphAssignment;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DigraphTest {

	@Test
	public void test() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge2");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Print out the graph to view it
		System.out.println(lDigraph.toString());
		
		//Verify the size of the graph
		assertEquals(11, lDigraph.getEdges().size());
		assertEquals(7, lDigraph.getVertices().size());
	}
	
	@Test
	public void testGetVertexKeys_byAddingEdges() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge2");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Print out the graph
		System.out.println(lDigraph.getVertexKeys().toString());
		
		//Assert the vertex keys got added to the graph
		assertNotNull(lDigraph.getVertexKeys());
		assertEquals(7, lDigraph.getVertexKeys().size());
	}
	
	@Test
	public void testGetVertexKeys_byAddingVertices() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addVertex(lVertex1);
		lDigraph.addVertex(lVertex2);
		lDigraph.addVertex(lVertex3);
		lDigraph.addVertex(lVertex4);
		lDigraph.addVertex(lVertex5);
		lDigraph.addVertex(lVertex6);
		lDigraph.addVertex(lVertex7);
		
		//Print out the vertex keys
		System.out.println(lDigraph.getVertexKeys().toString());
		
		//Assert the vertex keys got added to the graph
		assertNotNull(lDigraph.getVertexKeys());
		assertEquals(7, lDigraph.getVertexKeys().size());
	}
	
	@Test
	public void testRemoveVertex_NoEdges() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addVertex(lVertex1);
		lDigraph.addVertex(lVertex2);
		lDigraph.addVertex(lVertex3);
		lDigraph.addVertex(lVertex4);
		lDigraph.addVertex(lVertex5);
		lDigraph.addVertex(lVertex6);
		lDigraph.addVertex(lVertex7);
		
		//Precondition: the number of vertex keys should be 7, and edges should be 0
		assertEquals(7, lDigraph.getVertexKeys().size());
		assertEquals(0, lDigraph.getEdges().size());
		
		//Remove the vertex "V4"
		lDigraph.removeVertex(lVertex4);
		
		//Print out the graph
		System.out.println(lDigraph.toString());
		
		//Verify that the size of vertices is expected: 6 (one less because we removed one) 
		assertNotNull(lDigraph.getVertexKeys());
		assertEquals(6, lDigraph.getVertexKeys().size());
		
		//No edges were added, so the number of edges should still be 0.  This is just to be sure.
		assertEquals(0, lDigraph.getEdges().size());
	}
	
	@Test
	public void testRemoveVertex_WithEdgesAffected() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge2");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Precondition: verify the number of vertices is 7 and the edges is 11
		assertEquals(7, lDigraph.getVertexKeys().size());
		assertEquals(11, lDigraph.getEdges().size());
		
		//Remove the vertex "V4" from the graph
		lDigraph.removeVertex(lVertex4);
		
		//Print out the graph
		System.out.println(lDigraph.toString());
		
		//Verify that vertices are one less (6), and the edges are fewer: 8 (because there were 3 edges linked to V4)
		assertNotNull(lDigraph.getVertexKeys());
		assertEquals(6, lDigraph.getVertexKeys().size());
		assertEquals(8, lDigraph.getEdges().size());
	}
	
	@Test
	public void testRemoveVertex_WithEdgesAffected2() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge2");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Precondition: verify the number of vertices (7) and edges (11)
		assertEquals(7, lDigraph.getVertexKeys().size());
		assertEquals(11, lDigraph.getEdges().size());
		
		//Remove vertex "V3" from the graph
		lDigraph.removeVertex(lVertex3);
		
		//Print the graph
		System.out.println(lDigraph.toString());
		
		//Verify that vertices are one less (6), and the edges are fewer: 6 (because there were 5 edges linked to V3)
		assertNotNull(lDigraph.getVertexKeys());
		assertEquals(6, lDigraph.getVertexKeys().size());
		assertEquals(6, lDigraph.getEdges().size());
	}
	
	@Test
	public void testRemoveEdge() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		Edge lEdge = new Edge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge 2->3");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lEdge);
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Precondition: verify the number of vertices (7) and edges (11) 
		assertEquals(11, lDigraph.getEdges().size());
		assertEquals(7, lDigraph.getVertexKeys().size());
		
		//Remove the edge linking V3 to V4
		lDigraph.removeEdge(lEdge);
		
		//Print the graph
		System.out.println(lDigraph.toString());
		
		//Verify that the edges decreased by one and the vertices were unaffected
		assertNotNull(lDigraph.getEdges());
		assertEquals(10, lDigraph.getEdges().size());
		assertEquals(7, lDigraph.getVertexKeys().size());
	}
	
	@Test
	public void testGetPreviousVertices() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge 2->3");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Get the vertices where there is an edge to vertex 6
		List<Vertex> lVertices = lDigraph.getPreviousVertices(lVertex6);
		
		//Assert that the correct vertices were returned: V3, V4, V5
		assertNotNull(lVertices);
		assertEquals(3, lVertices.size());
		assertTrue(lVertices.contains(lVertex3));
		assertTrue(lVertices.contains(lVertex4));
		assertTrue(lVertices.contains(lVertex5));
	}
	
	@Test
	public void testGetNextVertices() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		Vertex lVertex6 = new Vertex("V6");
		Vertex lVertex7 = new Vertex("V7");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3, 3, "edge 2->3");
		lDigraph.addEdge(lVertex3, lVertex6, 4);
		lDigraph.addEdge(lVertex3, lVertex5, 15, "edge 3->5");
		lDigraph.addEdge(lVertex3, lVertex4, 10, "edge 3->4");
		lDigraph.addEdge(lVertex4, lVertex6);
		lDigraph.addEdge(lVertex5, lVertex4, 2);
		lDigraph.addEdge(lVertex5, lVertex6, 5);
		lDigraph.addEdge(lVertex6, lVertex7, 10, "edge 6->7");
		lDigraph.addEdge(lVertex7, lVertex1);
		
		//Get the next vertices following the edges after V3
		List<Vertex> lVertices = lDigraph.getNextVertices(lVertex3);
		
		//Assert that the vertices are expected: V4, V5, V6
		assertNotNull(lVertices);
		assertEquals(3, lVertices.size());
		assertTrue(lVertices.contains(lVertex4));
		assertTrue(lVertices.contains(lVertex5));
		assertTrue(lVertices.contains(lVertex6));
	}
	
	@Test
	public void testDepthFirstSearch() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3);
		lDigraph.addEdge(lVertex3, lVertex1);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addEdge(lVertex3, lVertex4);
		
		//Run the depth first search with vertex V3 as the root
		List<Vertex> lListOfVertices = lDigraph.depthFirstSeach(lVertex3);
		
		//Assert the expected order of the results: 3, 1, 2, 4
		assertEquals(4, lListOfVertices.size());
		assertEquals(lVertex3, lListOfVertices.get(0));
		assertEquals(lVertex1, lListOfVertices.get(1));
		assertEquals(lVertex2, lListOfVertices.get(2));
		assertEquals(lVertex4, lListOfVertices.get(3));
	}
	
	@Test
	public void testIsConnected_True() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3);
		lDigraph.addEdge(lVertex3, lVertex1);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addEdge(lVertex3, lVertex4);
		
		//Find out if the graph is connected
		Boolean lIsConnected = lDigraph.isConnected();
		
		//This graph should be connected
		assertTrue(lIsConnected);
	}
	
	@Test
	public void testIsConnected_False() {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3);
		lDigraph.addEdge(lVertex3, lVertex1);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addVertex(lVertex5);
		
		//Find out if the graph is connected
		Boolean lIsConnected = lDigraph.isConnected();
		
		//This graph is not connected; specifically V5 is not connected by any edges to the rest of the graph
		assertFalse(lIsConnected);
	}
	
	@Test
	public void testSaveAndReadFromFile() throws Exception {
		Digraph lDigraph = new Digraph();
		
		//Create the vertices
		Vertex lVertex1 = new Vertex("V1");
		Vertex lVertex2 = new Vertex("V2");
		Vertex lVertex3 = new Vertex("V3");
		Vertex lVertex4 = new Vertex("V4");
		Vertex lVertex5 = new Vertex("V5");
		
		//Add edges between the vertices
		lDigraph.addEdge(lVertex1, lVertex2);
		lDigraph.addEdge(lVertex1, lVertex3);
		lDigraph.addEdge(lVertex2, lVertex3);
		lDigraph.addEdge(lVertex3, lVertex1);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addEdge(lVertex3, lVertex4);
		lDigraph.addVertex(lVertex5);
		
		Boolean lSaveSuccessful = Digraph.saveGraphToFile(lDigraph, "Graph2File");
		assertTrue(lSaveSuccessful);
		
		Digraph lReadGraph = Digraph.readGraphFromFile("Graph2File");
		assertNotNull(lReadGraph);
		assertEquals(lDigraph, lReadGraph);
	}
}
