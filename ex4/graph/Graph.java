package graph;

import java.util.*;

// 
public class Graph<T> {
  private final Map<Vertex<T>, LinkedList<Edge<T>>> adjList //(of all vertices);
  private final boolean isOriented;

  /**
   * Graph constructor
   * @param isOriented Graph type
   */
 
  public Graph(boolean isOriented) {
    this.adjList = new HashMap<>();
    this.isOriented = isOriented;
  }

  /**
   * Checks the graph's oriented property
   *
   * @return true if the graph is oriented, false otherwise
   */
  public boolean isOriented() {
    return isOriented;
  }

public int sizeVertexes(){
  return adjList.size();
}
public int sizeEdges(){
  int size = 0;
    for (Vertex<T> v : adjList.keySet()) 
      size += adjList.get(v).size();
    if(this.isOriented)
      return size;
    else
      return size/2;
}
  /**
   * Checks if the Vertex v is contained in the Graph
   *
   * @param v Vertex
   * @return true if it is contained, false otherwise
   */
  public boolean containsV(Vertex<T> v) {
    return adjList.containsKey(v);
  }

/**
* Checks if the Edge is contained in the Graph
* @param e Edge
* @return true if it is contained, false otherwise
*/
 public boolean containsE(Edge<T> e) {
    return adjList.get(e.getVertex1()).contains(e);
  }

  /**
   * Adds the Vertex v in the Graph.
   *
   * @param v Vertex
   */
  public void addVertex(Vertex<T> v) {
    if (!adjList.containsKey(v))
      adjList.put(v, new LinkedList<>());
  }

  /**
   * Removes the Vertex v and all the edges connected to it from the adjList.
   *
   * @param v Vertex
   */
  public void removeVertex(Vertex<T> v) {
    try{
      while(adjList.get(v).removeFirst() != null)
        adjList.get(v).removeFirst();
    } catch(NoSuchElementException e) {
        /* this exception is thrown by removeFirst() method in LinkedList,
         * when it occurs, we know that the LinkedList is empty, because the
         * first element is null. If the LinkedList is null, then we remove
         * the vertex from the adjList.
         */
        adjList.remove(v);
    }
  }

  /**
   * Adds Edge<T> e in the Graph.
   *
   * @param e the Edge to be added in the Graph.
   */
  public void addEdge(Edge<T> e) {
    if (isOriented) {
      addEdgeOriented(e);
    }
    else {
      Edge<T> eReverse = new Edge<>(e.getVertex2(), e.getVertex1(), e.getWeight());
      addEdgeOriented(e);
      addEdgeOriented(eReverse);
    }
  }

  /**
   * Support method used in addEdge()
   *
   * @param e the Edge<T> to be added to the Graph.
   * @see #addEdge(Edge)
   */
  private void addEdgeOriented(Edge<T> e) {
    // first we check if both the vertices of the edge
    // are already inside the adjList, if don't, then we add them.
    adjList.putIfAbsent(e.getVertex1(), new LinkedList<>());
    adjList.putIfAbsent(e.getVertex2(), new LinkedList<>());

    // finally we put the edge in the adjList
    adjList.get(e.getVertex1()).add(e);
  }

  /**
   * Removes an edge from the Graph.
   *
   * @param e the Edge<T> to be removed
   * @return true if it's removed, false otherwise
   */
  public boolean removeEdge(Edge<T> e) {
    if (this.isOriented) {
      LinkedList<Edge<T>> edges = adjList.get(e.getVertex1());
      return edges.remove(e);
    } else {
      LinkedList<Edge<T>> edgesV1 = adjList.get(e.getVertex1()); 
      LinkedList<Edge<T>> edgesV2 = adjList.get(e.getVertex2());
      Edge<T> eReverse = new Edge<>(e.getVertex2(), e.getVertex1(), e.getWeight());
      return edgesV1.remove(e) && edgesV2.remove(eReverse);
    }
  }

  /**
   * It returns a LinkedList<T> which contains all the vertex of the Graph.
   *
   * @return a LinkedList<T> which contains all the vertex of the Graph.
   * @see GraphTests
   * 
   */
  public LinkedList<Vertex<T>> getVertices() {
    return new LinkedList<>(adjList.keySet());
  }

  /**
   * Gets adjacent vertices from the Vertex<T> v.
   *
   * @param v Vertex from which start the computation.
   * @return a LinkedList<Vertex<T> containing all the adjacent vertices of the Vertex<T> v.
   */
  public LinkedList<Vertex<T>> getAdjVertices(Vertex<T> v) {
    LinkedList<Vertex<T>> res = new LinkedList<>();
    for (Edge<T> e: adjList.get(v)) {
      res.add(e.getVertex2());
    }
    return res;
  }

  /**
   * It returns a LinkedList<T> which contains all the edges of the Graph.
   *
   * @return a LinkedList<T> which contains all the edges of the Graph.
   */
  public LinkedList<Edge<T>> getEdges() {
    LinkedList<Edge<T>> res = new LinkedList<>();
    for (Vertex<T> v : adjList.keySet()) {
      res.addAll(adjList.get(v));
    }
    return res;
  }

  /**
   * Gets edge's weight
   *
   * @param v1 start vertex of the Edge to compute the weight
   * @param v2 final vertex of the Edge to compute the weight
   * @return the weight of edge from the first vertex v1 to the final vertex v2
   */
  public double getEdgeWeight(Vertex<T> v1, Vertex<T> v2) {
    LinkedList<Edge<T>> res = adjList.get(v1);
    for (Edge<T> e : res)
      if (e.getVertex1().equals(v1) && e.getVertex2().equals(v2))
        return e.getWeight();
    return 0;
  }

  /**
   * Gets graph's weight.
   *
   * @return the weight of the graph.
   */
  public double getGraphWeight() {
    double weight = 0;
    for (Vertex<T> v : adjList.keySet()) {
      for (Edge<T> e: adjList.get(v)) {
        weight += e.getWeight();
      }
    }
    return isOriented ? weight : weight / 2;
  }
  
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Vertex<T> v : adjList.keySet()) {
      for (Edge<T> e: adjList.get(v)) {
        s.append("from vertex ").
        append(e.getVertex1()).append(" to vertex ").
        append(e.getVertex2()).append(" weight ").
        append(e.getWeight()).append("\n");
      }
    }
    return s.toString();
  }
}
