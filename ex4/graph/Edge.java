package graph;

import java.util.*;

// 
public class Edge<T> implements Comparable<Edge<T>> {
  private Vertex<T> vertex1, vertex2;
  private double weight;

    /**
     * Costructor of Edge Object.
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @param weight the weight of the edge
     */
  public Edge(Vertex<T> v1, Vertex<T> v2, double weight){
    vertex1 = v1;
    vertex2 = v2;
    this.weight = weight;
  }


  public Vertex<T> getVertex1() {
    return vertex1;
  }

  public Vertex<T> getVertex2() {
    return vertex2;
  }

  public double getWeight() {
    return weight;
  }

  public int compareTo(Edge<T> other) {
    return (int)this.weight - (int)other.getWeight();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Edge))
      return false;

    Edge _obj = (Edge) obj;
    return _obj.vertex1.equals(vertex1) && _obj.vertex2.equals(vertex2)&& _obj.weight == weight;
    }

    @Override
  public String toString(){
      
    StringBuilder s = new StringBuilder();
    s.append("from vertex ").
    append(this.getVertex1()).append(" to vertex ").
    append(this.getVertex2()).append(" weight ").
    append(this.getWeight()).append("\n");

    
    return s.toString();

  }

 
  /*@Override
  public int hashCode() {
    int hash = 28;
    hash = 5 * hash + Objects.hashCode(vertex1);
    hash = 5 * hash + Objects.hashCode(vertex2);
    hash = 5 * hash + Objects.hashCode(this.weight);
    return hash;
  }*/
}