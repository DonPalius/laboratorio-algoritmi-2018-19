package graph;
import java.util.LinkedList;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import static org.junit.Assert.*;

// 
public class GraphTests {
    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>(false);

        Vertex<String> a = new Vertex<String>("A");
        Vertex<String> b = new Vertex<String>("B");
        Vertex<String> c = new Vertex<String>("C");
        Vertex<String> d = new Vertex<String>("D");
        Vertex<String> e = new Vertex<String>("E");

        Edge<String> ab5 = new Edge<String>(a, b, 5);
        Edge<String> bc10 = new Edge<String>(b, c, 10);
        Edge<String> ac6 = new Edge<String>(a, c, 6);
        Edge<String> de6 = new Edge<String>(d, e, 6);

        graph.addEdge(ab5);
        graph.addEdge(bc10);
        graph.addEdge(ac6);
        graph.addEdge(de6);
    }
    @Test
    public void testIsOriented(){
        assertFalse(graph.isOriented());
    }
	
	@Test
    public void testSizeEdges(){
        assertEquals(4,graph.sizeEdges());
    }
	
	@Test
    public void testSizeVertexes(){
        assertEquals(5,graph.sizeVertexes());
    }
	
    @Test
    public void testContainsVertexes(){
        assertTrue(graph.containsV(new Vertex<String>("A")));
		assertTrue(graph.containsV(new Vertex<String>("B")));
		assertTrue(graph.containsV(new Vertex<String>("C")));
		assertTrue(graph.containsV(new Vertex<String>("D")));
		assertTrue(graph.containsV(new Vertex<String>("E")));
    }
	@Test
    public void testContainsEdges(){
        assertTrue(graph.containsE(new Edge<String>(new Vertex<String>("A"), new Vertex<String>("B"), 5)));
        assertTrue(graph.containsE(new Edge<String>(new Vertex<String>("B"), new Vertex<String>("C"), 10)));
        assertTrue(graph.containsE(new Edge<String>(new Vertex<String>("C"), new Vertex<String>("B"), 10)));
        assertTrue(graph.containsE(new Edge<String>(new Vertex<String>("E"), new Vertex<String>("D"), 6)));
    }
	
    @Test
    public void testAddVertex() {
        Vertex<String> toAdd = new Vertex<>("F");
        graph.addVertex(toAdd);
        assertTrue(graph.containsV(toAdd));
    }

    @Test
    public void testRemoveVertex() {
        Vertex<String> toRemove = new Vertex<>("E");
        graph.removeVertex(toRemove);
        assertFalse(graph.containsV(toRemove));
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(new Edge<String>(new Vertex<String>("A"), new Vertex<String>("D"), 1));
        assertEquals(1.0, graph.getEdgeWeight(new Vertex<String>("A"), new Vertex<String>("D")),0);
    }

    @Test
    public void testRemoveEdge() {
        boolean test = graph.removeEdge(new Edge<String>(new Vertex<String>("D"), new Vertex<String>("E"), 6));
        assertTrue(test);
    }

    @Test
    public void testGetVertices() {
        LinkedList<Vertex<String>> expected = new LinkedList<>();
        expected.add(new Vertex<>("A"));
        expected.add(new Vertex<>("B"));
        expected.add(new Vertex<>("C"));
        expected.add(new Vertex<>("D"));
        expected.add(new Vertex<>("E"));

        LinkedList<Vertex<String>> result = graph.getVertices();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAdjVertices() {
        Vertex result = null;
        LinkedList<Vertex<String>> adjVertices = graph.getAdjVertices(new Vertex<>("E"));
        for (Vertex v : adjVertices)
            result = v;
        assertEquals(new Vertex<String>("D").getLabel(), result.getLabel());
    }

    @Test
    public void testGetEdges() {
        Graph<String> graphClone = graph;
        LinkedList<Edge<String>> expected = graphClone.getEdges();
        LinkedList<Edge<String>> result = graph.getEdges();
        assertEquals(expected, result);
    }

    @Test
    public void testGetEdgeWeight() {
        assertEquals(5.0, graph.getEdgeWeight(new Vertex<String>("A"), new Vertex<String>("B")),0);
    }

    @Test
    public void testGetGraphWeight() {
        assertEquals(27.0, graph.getGraphWeight(),0);
    }

    @Test
    public void testToString() {
        assertEquals("", new Graph(false).toString());
    }

    // For automaticly run all the tests
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GraphTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("\nGraphTests result: " + result.wasSuccessful());
    }
}
