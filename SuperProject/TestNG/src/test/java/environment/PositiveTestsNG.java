package environment;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import epam.saratov.homeWork.testng.objects.GeometricObjects;
import epam.saratov.homeWork.testng.objects.GeometricObjects.Circle;
import epam.saratov.homeWork.testng.objects.GeometricObjects.Quadrate;
import epam.saratov.homeWork.testng.objects.GeometricObjects.Rectangle;

public class PositiveTestsNG {
	protected double sideOne = 5.0;
	protected double sideTwo = 5.0;
	private double zeroSide = 0.0;
	private Quadrate quadrate;
	private Circle circle;
	private Rectangle rectangle;

	@DataProvider(name = "sidesValueDataProvider")
	public static Object[][] getValueForSides() {
		return new Object[][] { { 1.0, 1.0 }, { 2.0, 2.0 },
				{ 1000000.0, 123456.0 } };
	}

	@BeforeTest
	public void testPreparation() {
		this.quadrate = new GeometricObjects().getQuadrate(sideOne);
		this.circle = new GeometricObjects().getCircle(sideOne);
		this.rectangle = new GeometricObjects().getRectangle(sideOne, sideTwo);
	}

	@AfterTest
	public void testFinalisation() {
		this.quadrate = null;
		this.circle = null;
		this.rectangle = null;
	}

	@Test(groups = "circle", expectedExceptions = NullPointerException.class, priority = 0)
	public void testCircleNullPointer() {
		Circle thisCircle = null;
		Assert.assertEquals(thisCircle.getCircumference(), zeroSide);
	}

	@Test(groups = "circle", priority = 1)
	public void testCircleGetCircumference() {
		this.circle = new GeometricObjects().getCircle(sideOne);
		double calculatedCircumference = new BigDecimal(2 * Math.PI * sideOne)
				.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		Assert.assertTrue(this.circle.getCircumference() == calculatedCircumference);
	}

	@Test(groups = "circle", priority = 2)
	public void testCircleGetSquare() {
		System.out.println("sideOne = " + sideOne + ", sideTwo = " + sideTwo);
		double calculatedCircleSquare = new BigDecimal(Math.PI
				* (sideOne * sideOne)).setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		Assert.assertTrue(this.circle.getSquare() == calculatedCircleSquare);
	}

	@Test(groups = "quadrate", dependsOnGroups = { "circle" })
	public void testQuadrateGetPerimeter() {
		this.quadrate = new GeometricObjects().getQuadrate(sideOne);
		Assert.assertTrue(this.quadrate.getPerimeter() == sideOne * 4);
	}

	@Test(groups = "quadrate", dependsOnGroups = { "circle" })
	public void testQuadrateGetSquare() {
		Assert.assertTrue(this.quadrate.getSquare() == sideOne * sideOne);
	}

	@Test(dataProvider = "sidesValueDataProvider", groups = "rectangle", dependsOnGroups = { "quadrate" })
	public void testRectangleGetPerimeter(double providerSideOne,
			double providerSideTwo) {
		Rectangle thisRectangle = new GeometricObjects().getRectangle(
				providerSideOne, providerSideTwo);
		Assert.assertTrue(thisRectangle.getPerimeter() == (providerSideOne + providerSideTwo) * 2);
	}

	@Parameters({ "parameterSideOne", "parameterSideTwo" })
	@Test(groups = "rectangle", dependsOnMethods = { "testRectangleGetPerimeter" })
	public void testRectangleGetSquare(
			@Optional("5.0") double parameterSideOne,
			@Optional("5.0") double parameterSideTwo) {
		Rectangle parameteredThisRectangle = new GeometricObjects()
				.getRectangle(parameterSideOne, parameterSideTwo);
		Assert.assertTrue(parameteredThisRectangle.getSquare() == parameterSideOne
				* parameterSideTwo);
	}

	@Test(groups = "rectangle", dependsOnMethods = { "testRectangleGetSquare" })
	public void testRectangleIsQuadrate() {
		this.rectangle = new GeometricObjects().getRectangle(sideOne, sideTwo);
		Assert.assertTrue(this.rectangle.isQuadrate() == (sideOne == sideTwo)); // temp
																				// view
	}
}
