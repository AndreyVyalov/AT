package environment;

import org.testng.annotations.Factory;

public class TestFactory extends PositiveTest {

	@Factory(dataProvider = "sidesValueDataProvider")
	public TestFactory(double sideOne, double sideTwo) {
		super.sideOne = sideOne;
		super.sideTwo = sideTwo;
	}
}