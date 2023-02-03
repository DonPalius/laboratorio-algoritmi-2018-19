package ex3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;


//
public class TestUnionFind {

    int[] array = {0,1,2,3,4,5,6,7};
    UnionFind UF = new UnionFind<Integer>(); 
  

  @Before
  public void MakeSetUp() {
    for(int i = 0; i< array.length; i++) 
      UF.MakeSet(array[i]);   
  }

  @Test
    public void testFind() {
      assertEquals(1, UF.Find(1).value);
      assertEquals(2, UF.Find(2).value);
    }

  @Test
    public void testUnion() {

      UF.Union(UF.get(1), UF.get(2));
      UF.Union(UF.get(2), UF.get(3));
      UF.Union(UF.get(5), UF.get(6));
      UF.Union(UF.get(6), UF.get(7));   

      assertEquals(1, UF.Find(3).value);
      assertEquals(5, UF.Find(7).value);

      UF.Union(UF.get(3), UF.get(7));
      assertEquals(1, UF.Find(7).value);

    }

    // For automaticly run all the tests
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestUnionFind.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("\nTestUnionFind result: " + result.wasSuccessful());
    } 
}