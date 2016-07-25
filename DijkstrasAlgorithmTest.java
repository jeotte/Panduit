package graphAssignment;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import org.junit.Assert;

public class DijkstrasAlgorithmTest {
	
	
	/*
	 * To help with visualizing, the graphs the I used for the tests look something like the following.
	 * Excuse the lack of artistic ability.  Also, the @ will represent the arrow head
	 * 
	 * 
	 * Graph1:
	 * 
	 * 					6             6
	 * 		    B -------------@ D ----------@ F
	 * 		   @  \             @              @
	 *      5 /     \			|			   |
	 *   	 /        \			|			   |
	 *  	A       3   \       | 2    		   | 2
	 *		 \            \     |              |
	 *	  10  \             \	|	 		   |
	 *		   @              @	|     		   |
	 * 			C @------------ E -----------@ G
	 *                  2              2
	 * 
	 * Graph2:
	 * 
	 *                  7
	 * 			  B -------------@ D
	 *          @ |@ \         /  @
	 *         /  ||  \       /   |
	 *      5 /   ||   \     / 2  |
	 *       /   2||     \ /      |
	 *      A     ||     / \      | 7
	 *       \    ||    /   \     |
	 *     3  \   ||1  /     \ 1  |
	 *         \  ||  /       \   |
	 *          @ @  @         @  |
	 *            C -------------@ E
	 *                  4
	 * 
	 * 
	 * Graph3:
	 * 
	 *                   A
	 *                 / @  @
	 *            4 /    |    \  2
	 *            /      |       \ 
	 *          /        |         \
	 *        @          |           \
	 *       F           |            B
	 *       @ \@        | 1        / @
	 *       |   \\ 1    |        /   |
	 *       |   1 \\    |      / 2   |
	 *       |       @\  |   @        |
	 *     3 |           G            |3
	 *       |        @  | \          |
	 *       |     /     |    \  4    |
	 *       |  /  2     |        @   |
	 *       E           |            C
	 *        @          |        @  
	 *          \        |3    / 2
	 *          1   \    @  /
	 *                   D
	 * 
	 * 
	 */
	

	@Test
	public void testFindMinimumDistance_Graph1_SourceA() {
		Digraph lGraph = getGraph1();
		
		//Test finding the minimum distance from all vertices to the source vertex
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Map<Vertex, Integer> lDistanceMap = lAlgorithm.findMinimumDistance(lGraph, lGraph.getVertex("A"));
		
		Assert.assertEquals(5, lDistanceMap.get(lGraph.getVertex("B")).intValue());
		Assert.assertEquals(10, lDistanceMap.get(lGraph.getVertex("C")).intValue());
		Assert.assertEquals(10, lDistanceMap.get(lGraph.getVertex("D")).intValue());
		Assert.assertEquals(8, lDistanceMap.get(lGraph.getVertex("E")).intValue());
		Assert.assertEquals(12, lDistanceMap.get(lGraph.getVertex("F")).intValue());
		Assert.assertEquals(10, lDistanceMap.get(lGraph.getVertex("G")).intValue());
	}
	
	
	@Test
	public void testFindMinimumDistance_Graph2_SourceA() {
		Digraph lGraph = getGraph2();
		
		//Test finding the minimum distance from all vertices to the source vertex
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Map<Vertex, Integer> lDistanceMap = lAlgorithm.findMinimumDistance(lGraph, lGraph.getVertex("A"));
		
		Assert.assertEquals(4, lDistanceMap.get(lGraph.getVertex("B")).intValue());
		Assert.assertEquals(3, lDistanceMap.get(lGraph.getVertex("C")).intValue());
		Assert.assertEquals(12, lDistanceMap.get(lGraph.getVertex("D")).intValue());
		Assert.assertEquals(6, lDistanceMap.get(lGraph.getVertex("E")).intValue());
	}
	
	@Test
	public void testFindMinimumDistance_Graph3_SourceA() {
		Digraph lGraph = getGraph3();
		
		//Test finding the minimum distance from all vertices to the source vertex
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Map<Vertex, Integer> lDistanceMap = lAlgorithm.findMinimumDistance(lGraph, lGraph.getVertex("A"));
		
		Assert.assertEquals(12, lDistanceMap.get(lGraph.getVertex("B")).intValue());
		Assert.assertEquals(9, lDistanceMap.get(lGraph.getVertex("C")).intValue());
		Assert.assertEquals(8, lDistanceMap.get(lGraph.getVertex("D")).intValue());
		Assert.assertEquals(9, lDistanceMap.get(lGraph.getVertex("E")).intValue());
		Assert.assertEquals(4, lDistanceMap.get(lGraph.getVertex("F")).intValue());
		Assert.assertEquals(5, lDistanceMap.get(lGraph.getVertex("G")).intValue());
	}
	
	@Test
	public void testFindMinimumDistance_Graph3_SourceD() {
		Digraph lGraph = getGraph3();
		
		//Test finding the minimum distance from all vertices to the source vertex
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Map<Vertex, Integer> lDistanceMap = lAlgorithm.findMinimumDistance(lGraph, lGraph.getVertex("D"));
		
		Assert.assertEquals(4, lDistanceMap.get(lGraph.getVertex("A")).intValue());
		Assert.assertEquals(5, lDistanceMap.get(lGraph.getVertex("B")).intValue());
		Assert.assertEquals(2, lDistanceMap.get(lGraph.getVertex("C")).intValue());
		Assert.assertEquals(1, lDistanceMap.get(lGraph.getVertex("E")).intValue());
		Assert.assertEquals(4, lDistanceMap.get(lGraph.getVertex("F")).intValue());
		Assert.assertEquals(3, lDistanceMap.get(lGraph.getVertex("G")).intValue());
	}
	
	@Test
	public void testGetShortestPath_Graph1_SourceA() {
		Digraph lGraph = getGraph1();
		
		//Test finding the shortest path from vertex A to vertex F
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		List<Vertex> lPath = lAlgorithm.getShortestPath(lGraph, lGraph.getVertex("A"), lGraph.getVertex("F"));
		
		Assert.assertEquals("A", lPath.get(0).getLabel());
		Assert.assertEquals("B", lPath.get(1).getLabel());
		Assert.assertEquals("E", lPath.get(2).getLabel());
		Assert.assertEquals("G", lPath.get(3).getLabel());
		Assert.assertEquals("F", lPath.get(4).getLabel());
	}
	
	@Test
	public void testGetShortestPath_Graph2_SourceA() {
		Digraph lGraph = getGraph2();
		
		//Test finding the shortest path from vertex A to vertex B
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		List<Vertex> lPath = lAlgorithm.getShortestPath(lGraph, lGraph.getVertex("A"), lGraph.getVertex("B"));
		
		Assert.assertEquals("A", lPath.get(0).getLabel());
		Assert.assertEquals("C", lPath.get(1).getLabel());
		Assert.assertEquals("B", lPath.get(2).getLabel());
	}
	
	@Test
	public void testGetShortestPath_Graph3_SourceD() {
		Digraph lGraph = getGraph3();
		
		//Test finding the shortest path from vertex D to vertex A
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		List<Vertex> lPath = lAlgorithm.getShortestPath(lGraph, lGraph.getVertex("D"), lGraph.getVertex("A"));
		
		Assert.assertEquals("D", lPath.get(0).getLabel());
		Assert.assertEquals("E", lPath.get(1).getLabel());
		Assert.assertEquals("G", lPath.get(2).getLabel());
		Assert.assertEquals("A", lPath.get(3).getLabel());
	}
	
	@Test
	public void testGetShortestDistance_Graph1_SourceA() {
		Digraph lGraph = getGraph1();
		
		//Test finding the shortest distance from vertex A to vertex F
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Integer lValue = lAlgorithm.getShortestDistance(lGraph, lGraph.getVertex("A"), lGraph.getVertex("F"));
		
		Assert.assertEquals(12, lValue.intValue());
	}
	
	@Test
	public void testGetShortestDistance_Graph2_SourceA() {
		Digraph lGraph = getGraph2();
		
		//Test finding the shortest distance from vertex A to vertex B
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Integer lValue = lAlgorithm.getShortestDistance(lGraph, lGraph.getVertex("A"), lGraph.getVertex("B"));
		
		Assert.assertEquals(4, lValue.intValue());
	}
	
	@Test
	public void testGetShortestDistance_Graph3_SourceD() {
		Digraph lGraph = getGraph3();
		
		//Test finding the shortest distance from vertex D to vertex A
		DijkstrasAlgorithm lAlgorithm = new DijkstrasAlgorithm();
		Integer lValue = lAlgorithm.getShortestDistance(lGraph, lGraph.getVertex("D"), lGraph.getVertex("A"));
		
		Assert.assertEquals(4, lValue.intValue());
	}
	
	
	
	private Digraph getGraph1() {
		Digraph lGraph = new Digraph();
		
		Vertex lVertexA = new Vertex("A");
		Vertex lVertexB = new Vertex("B");
		Vertex lVertexC = new Vertex("C");
		Vertex lVertexD = new Vertex("D");
		Vertex lVertexE = new Vertex("E");
		Vertex lVertexF = new Vertex("F");
		Vertex lVertexG = new Vertex("G");
		
		lGraph.addEdge(lVertexA, lVertexB, 5, "a->b");
		lGraph.addEdge(lVertexA, lVertexC, 10, "a->c");
		lGraph.addEdge(lVertexB, lVertexD, 6, "b->d");
		lGraph.addEdge(lVertexB, lVertexE, 3, "b->e");
		lGraph.addEdge(lVertexD, lVertexF, 6, "d->f");
		lGraph.addEdge(lVertexE, lVertexC, 2, "e->c");
		lGraph.addEdge(lVertexE, lVertexD, 2, "e->d");
		lGraph.addEdge(lVertexE, lVertexG, 2, "e->g");
		lGraph.addEdge(lVertexG, lVertexF, 2, "g->f");
		
		return lGraph;
	}
	
	private Digraph getGraph2() {
		Digraph lGraph = new Digraph();
		
		Vertex lVertexA = new Vertex("A");
		Vertex lVertexB = new Vertex("B");
		Vertex lVertexC = new Vertex("C");
		Vertex lVertexD = new Vertex("D");
		Vertex lVertexE = new Vertex("E");
		
		lGraph.addEdge(lVertexA, lVertexB, 5, "a->b");
		lGraph.addEdge(lVertexA, lVertexC, 3, "a->c");
		lGraph.addEdge(lVertexB, lVertexC, 2, "b->c");
		lGraph.addEdge(lVertexB, lVertexD, 7, "b->d");
		lGraph.addEdge(lVertexB, lVertexE, 1, "b->e");
		lGraph.addEdge(lVertexC, lVertexB, 1, "c->b");
		lGraph.addEdge(lVertexC, lVertexE, 4, "c->e");
		lGraph.addEdge(lVertexD, lVertexC, 2, "d->c");
		lGraph.addEdge(lVertexE, lVertexD, 7, "e->d");
		
		return lGraph;
	}
	
	private Digraph getGraph3() {
		Digraph lGraph = new Digraph();
		
		Vertex lVertexA = new Vertex("A");
		Vertex lVertexB = new Vertex("B");
		Vertex lVertexC = new Vertex("C");
		Vertex lVertexD = new Vertex("D");
		Vertex lVertexE = new Vertex("E");
		Vertex lVertexF = new Vertex("F");
		Vertex lVertexG = new Vertex("G");
		
		lGraph.addEdge(lVertexA, lVertexF, 4, "a->f");
		lGraph.addEdge(lVertexB, lVertexA, 2, "b->a");
		lGraph.addEdge(lVertexB, lVertexG, 2, "b->g");
		lGraph.addEdge(lVertexC, lVertexB, 3, "c->b");
		lGraph.addEdge(lVertexD, lVertexC, 2, "d->c");
		lGraph.addEdge(lVertexD, lVertexE, 1, "d->e");
		lGraph.addEdge(lVertexE, lVertexF, 3, "e->f");
		lGraph.addEdge(lVertexE, lVertexG, 2, "e->g");
		lGraph.addEdge(lVertexF, lVertexG, 1, "f->g");
		lGraph.addEdge(lVertexG, lVertexF, 1, "g->f");
		lGraph.addEdge(lVertexG, lVertexA, 1, "g->a");
		lGraph.addEdge(lVertexG, lVertexC, 4, "g->c");
		lGraph.addEdge(lVertexG, lVertexD, 3, "g->d");
		
		return lGraph;
	}

}
