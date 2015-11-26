package environment;

import org.testng.annotations.Factory;

public class TestFactory extends PositiveTestsNG {

	@Factory(dataProvider = "sidesValueDataProvider")
	public TestFactory(double sideOne, double sideTwo) {
		super.sideOne = sideOne;
		super.sideTwo = sideTwo;
	}
}