package ex2;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;

//
public class EditDistanceTest {


	@Test
	public void editDistance() {
		assertEquals(0, EditDistance.edit_distance("", ""));
		assertEquals(7, EditDistance.edit_distance("alfonso", ""));
		assertEquals(5, EditDistance.edit_distance("", "giuda"));
		assertEquals(2, EditDistance.edit_distance("stringa", "strong"));
		assertEquals(3, EditDistance.edit_distance("pecora", "eco"));
		assertEquals(1, EditDistance.edit_distance("tassa", "passa"));
		assertEquals(3, EditDistance.edit_distance("pippo", "filippo"));
	}

	@Test
	public void editDistanceDyn() {
		assertEquals(0, EditDistance.edit_distance_dyn("", ""));
		assertEquals(7, EditDistance.edit_distance_dyn("alfonso", ""));
		assertEquals(5, EditDistance.edit_distance_dyn("", "giuda"));
		assertEquals(2, EditDistance.edit_distance_dyn("stringa", "strong"));
		assertEquals(3, EditDistance.edit_distance_dyn("pecora", "eco"));
		assertEquals(1, EditDistance.edit_distance_dyn("tassa", "passa"));
		assertEquals(3, EditDistance.edit_distance_dyn("pippo", "filippo"));
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(EditDistanceTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("\nTests result: " + result.wasSuccessful());
	}
}