package graph;
import java.util.Objects;

// 
public class Vertex<T> {
    private T label;

   /**
     * Costructor of Edge Object.
     *
     * @param label of the vertex
     *
     */
    public Vertex(T label) {
      this.label = label;
    }

    public T getLabel() {
      return label;
    }

    @Override
    public String toString() {
      return label.toString();
    }

  
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof Vertex))
        return false;
      Vertex _obj = (Vertex) obj;
      return (_obj.label == null ? label == null : _obj.label.equals(label));
    }

    
    @Override
    public int hashCode() {
      int hash = 28;
      hash = 10 * hash + Objects.hashCode(label);
      return hash;
    }

}
