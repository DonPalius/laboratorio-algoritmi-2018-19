import graph.*;
import java.util.*; 
// 
 
/**
 * Class for Kruskal's algorithm implementation
 */
public class Kruskal<T> {

  /**
  * Kruskal's algorithm implementation
  *
  * @param graph weighted and undirected  
  * @return Graph oriented: minimum spanning tree of the graph
  */

  public Graph<T> mst(Graph<T> graph) {
        
    Graph<T> magicforest = new Graph<>(false);
    UnionFind UF = new UnionFind<T>();
        
    for (Vertex<T> v : graph.getVertices())
      UF.MakeSet(v);
        
      List<Edge<T>> edges = graph.getEdges();

        /* Sort the edges in ascending order of weight(size). */
      Collections.sort(edges);
      for(Edge<T> e : edges){
        if(UF.Find(e.getVertex1()) != UF.Find(e.getVertex2())){
            magicforest.addEdge(e);
            UF.Union(UF.Find(e.getVertex1()),UF.Find(e.getVertex2()));
        }
      }
      return magicforest;
    }
}






