package graphAssignment;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class TarjanAlgorithmTest {
	
	/*
	 * To help with visualizing, the graphs the I used for these tests look something like the following.
	 * The @ will represent the arrow head
	 * 
	 * 
	 * Graph1:
	 * 
	 * 		A -----------@ B -----------@ C -----------@ D
	 *      @ \            |             @|
	 *      |   \          |             ||
	 *      |    \         |             ||
	 *      |      \       |             ||
	 *      |         \    |             ||
	 *      |            @ @             |@
	 *      E @---------- F -----------@ G @------------ H
	 *      
	 * 
	 * Graph2:
	 *                                    @-------------  
	 * 	 	A @----------- C @---------- F ------------@ G
	 *      |           @  @             @               @
	 *      |         /    |             |               |
	 *      |       /      |             |               |
	 *      |     /        |             |               |
	 *      |   /          |             |               |
	 *      @ /            |             |               |
	 *      B @----------- D -----------@ E @----------- H @__
	 *                      @------------                |____|
	 * 
	 *                  
	 */
	
	@Test
	public void test_Graph1() {
		Digraph lGraph = new Digraph();
		
		//Create the vertices
		Vertex lVertexA = new Vertex("A");
		Vertex lVertexB = new Vertex("B");
		Vertex lVertexC = new Vertex("C");
		Vertex lVertexD = new Vertex("D");
		Vertex lVertexE = new Vertex("E");
		Vertex lVertexF = new Vertex("F");
		Vertex lVertexG = new Vertex("G");
		Vertex lVertexH = new Vertex("H");
		
		//Add the edges between the vertices
		lGraph.addEdge(lVertexA, lVertexB);
		lGraph.addEdge(lVertexA, lVertexF);
		lGraph.addEdge(lVertexB, lVertexC);
		lGraph.addEdge(lVertexB, lVertexF);
		lGraph.addEdge(lVertexC, lVertexD);
		lGraph.addEdge(lVertexC, lVertexG);
		lGraph.addEdge(lVertexE, lVertexA);
		lGraph.addEdge(lVertexF, lVertexE);
		lGraph.addEdge(lVertexF, lVertexG);
		lGraph.addEdge(lVertexG, lVertexC);
		lGraph.addEdge(lVertexH, lVertexG);
	
		//Find the strongly connected components in this graph
		TarjanAlgorithm lAlgorithm = new TarjanAlgorithm();
		List<List<Vertex>> lResults = lAlgorithm.findStronglyConnectedComponents(lGraph);
		
		//It should result in the grouping:
		//{A, B, E, F}, {C, G}, {D}, {H}
		
		assertEquals(4, lResults.size());
		assertEquals(1, lResults.get(0).size()); 
		assertTrue(lResults.get(0).contains(lVertexD));
		
		assertEquals(2, lResults.get(1).size()); 
		assertTrue(lResults.get(1).contains(lVertexC));
		assertTrue(lResults.get(1).contains(lVertexG));
		
		assertEquals(4, lResults.get(2).size()); 
		assertTrue(lResults.get(2).contains(lVertexA));
		assertTrue(lResults.get(2).contains(lVertexB));
		assertTrue(lResults.get(2).contains(lVertexE));
		assertTrue(lResults.get(2).contains(lVertexF));
		
		assertEquals(1, lResults.get(3).size()); 
		assertTrue(lResults.get(3).contains(lVertexH));
		
	}

	@Test
	public void test_Graph2() {
		Digraph lGraph = new Digraph();
		
		//Create the vertices
		Vertex lVertexA = new Vertex("A");
		Vertex lVertexB = new Vertex("B");
		Vertex lVertexC = new Vertex("C");
		Vertex lVertexD = new Vertex("D");
		Vertex lVertexE = new Vertex("E");
		Vertex lVertexF = new Vertex("F");
		Vertex lVertexG = new Vertex("G");
		Vertex lVertexH = new Vertex("H");
		
		//Add the edges between the vertices
		lGraph.addEdge(lVertexA, lVertexB);
		lGraph.addEdge(lVertexB, lVertexC);
		lGraph.addEdge(lVertexC, lVertexA);
		lGraph.addEdge(lVertexD, lVertexB);
		lGraph.addEdge(lVertexD, lVertexC);
		lGraph.addEdge(lVertexD, lVertexE);
		lGraph.addEdge(lVertexE, lVertexD);
		lGraph.addEdge(lVertexE, lVertexF);
		lGraph.addEdge(lVertexF, lVertexC);
		lGraph.addEdge(lVertexF, lVertexG);
		lGraph.addEdge(lVertexG, lVertexF);
		lGraph.addEdge(lVertexH, lVertexE);
		lGraph.addEdge(lVertexH, lVertexG);
		lGraph.addEdge(lVertexH, lVertexH);
	
		//Find the strongly connected components in this graph
		TarjanAlgorithm lAlgorithm = new TarjanAlgorithm();
		List<List<Vertex>> lResults = lAlgorithm.findStronglyConnectedComponents(lGraph);
		
		//It should result in the grouping:
		//{A, B, C}, {D, E}, {F, G}, {H}
		
		assertEquals(4, lResults.size());
		
		assertEquals(3, lResults.get(0).size());
		assertTrue(lResults.get(0).contains(lVertexA));
		assertTrue(lResults.get(0).contains(lVertexB));
		assertTrue(lResults.get(0).contains(lVertexC));
		
		assertEquals(2, lResults.get(1).size());
		assertTrue(lResults.get(1).contains(lVertexG));
		assertTrue(lResults.get(1).contains(lVertexF));
		
		assertEquals(2, lResults.get(2).size());
		assertTrue(lResults.get(2).contains(lVertexD));
		assertTrue(lResults.get(2).contains(lVertexE));
		
		assertEquals(1, lResults.get(3).size());
		assertTrue(lResults.get(3).contains(lVertexH));
	}
}
