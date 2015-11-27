package environment;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import epam.saratov.homeWork.testng.objects.GeometricObjects;
import epam.saratov.homeWork.testng.objects.GeometricObjects.Rectangle;

public class NegativeTest {
	private double sideOne = 5;
	private double sideTwo = 10;
	private Rectangle rectangle;

	@BeforeTest
	public void testPreparation() {
		this.rectangle = new GeometricObjects().getRectangle(sideOne, sideTwo);
	}

	@AfterTest
	public void testFinalisation() {
		this.rectangle = null;
	}

	@Test
	public void testRectangleIsNotQuadrate() {
		// Assert.assertFalse(this.rectangle.isQuadrate());
		Assert.assertTrue(this.rectangle.isQuadrate());// temp
														// view
	}
}
