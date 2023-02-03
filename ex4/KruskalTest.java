import graph.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;

//
public class KruskalTest {
  private Graph<String> simpleGraph;
  private Graph<String> datasetGraph;

  @Before
  public void setUp() {
    simpleGraph = new Graph<>(false);

    Vertex<String> a = new Vertex<String>("A");
    Vertex<String> b = new Vertex<String>("B");
    Vertex<String> c = new Vertex<String>("C");
    Vertex<String> d = new Vertex<String>("D");
    Vertex<String> e = new Vertex<String>("E");
    Vertex<String> f = new Vertex<String>("F");
    Vertex<String> g = new Vertex<String>("G");
    Vertex<String> h = new Vertex<String>("H");
    Vertex<String> i = new Vertex<String>("I");




    Edge<String> ab4 = new Edge<String>(a, b, 4);
    Edge<String> bc8 = new Edge<String>(b, c, 8);
    Edge<String> cd7 = new Edge<String>(c, d, 7);
    Edge<String> de9 = new Edge<String>(d, e, 9);
    Edge<String> ef10 = new Edge<String>(e, f, 10);
    Edge<String> fg2 = new Edge<String>(f, g, 2);
    Edge<String> gh1 = new Edge<String>(g, h, 1);
    Edge<String> ha8 = new Edge<String>(h, a, 8);
    Edge<String> hb11 = new Edge<String>(h, b, 11);
    Edge<String> hi7 = new Edge<String>(h, i, 7);
    Edge<String> ic2 = new Edge<String>(i, c, 2);
    Edge<String> ig6 = new Edge<String>(i, g, 6);
    Edge<String> cf4 = new Edge<String>(c, f, 5);
    Edge<String> df14 = new Edge<String>(d, f, 14);
    Edge<String> fd14 = new Edge<String>(f, d, 14);



    simpleGraph.addEdge(ab4);
    simpleGraph.addEdge(bc8);
    simpleGraph.addEdge(cd7);
    simpleGraph.addEdge(de9);
    simpleGraph.addEdge(ef10);
    simpleGraph.addEdge(fg2);
    simpleGraph.addEdge(gh1);
    simpleGraph.addEdge(ha8);
    simpleGraph.addEdge(hb11);
    simpleGraph.addEdge(hi7);
    simpleGraph.addEdge(ic2);
    simpleGraph.addEdge(ig6);
    simpleGraph.addEdge(cf4);
    simpleGraph.addEdge(df14);
    simpleGraph.addEdge(fd14);

    datasetGraph = new Graph<>(false);
    String path = "italian_dist_graph.csv";
    FileUtils loader = new FileUtils();
    loader.GraphCSV(datasetGraph, path);
    }

  @Test
  public void testSimpleGraph() {
    Kruskal<String> algorithm = new Kruskal<String>();
    Graph<String> forest =  algorithm.mst(simpleGraph);
    assertEquals(38.0, forest.getGraphWeight(),0);
  }

 @Test
  public void testDatasetGraph() {
    Kruskal<String> algorithm = new Kruskal<String>();
    Graph<String> forest = algorithm.mst(datasetGraph);

    System.out.format("Nodi: %d", forest.getVertices().size()).println();
    System.out.format("Archi: %d",forest.getEdges().size()).println();
    System.out.format("Peso: %.3f Km", forest.getGraphWeight()/1000).println();

    assertEquals(18640, forest.getVertices().size());
    assertEquals(18637, forest.getEdges().size());
    assertEquals(89939.913f,forest.getGraphWeight()/1000, 0.02f);
  }

  // For automaticly run all the tests
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(KruskalTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println("\nKruskalTest result: " + result.wasSuccessful());
  }
}