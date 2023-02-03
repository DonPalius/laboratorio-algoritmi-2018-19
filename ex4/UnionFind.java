
import java.util.*;

//

public class UnionFind<T> {

  public class Nodo {
    public Nodo parent;
    public T value;
    public int size; 

    public Nodo(T value) {
      this.value = value;
      parent = this; 
      size = 1; 
    }

  //ToString Override
  public String ToString() {
     return String.format("{0}, size:{1}", value, size);
    }

  }
  
  HashMap<T, Nodo> index;
  
  //constructor of UnionFind class
  public UnionFind() {
    index = new HashMap<T, Nodo>(); 
  }
  
  /**
  * @MakeSet: create a new set whose only element, and representative, is x.
  */
  
  public Nodo MakeSet(T value) {
    Nodo element = new Nodo(value); 
    index.put(value,element);
    return element;
  }
  /**
  * @Find: returns a pointer to the only representative
  * set containing x.
  */
  public Nodo Find(T value) {
    Nodo element = index.get(value);
    Nodo root = element; 
    while(root.parent != root) {
      root = root.parent; 
    }
    Nodo z = element; 
    while(z.parent != z) {
      Nodo temp = z; 
      z = z.parent;
      temp.parent = root;
    }
    return root; 
  }
  
/**
*  @Union: Combines the disjoint dynamic sets containing root1 and
*  2 in a single set that is
*  the union of the two sets. The two sets are
*  eliminated from the collection
*/
  public Nodo Union(Nodo root1, Nodo root2) {
    if (root2.size > root1.size) {
      root2.size += root1.size;
      root1.parent = root2;
      return root2;
    } else {
      root1.size += root2.size;
      root2.parent = root1;
      return root1;
    }
  }
  public Nodo get(T value){
    return index.get(value);
  }
}