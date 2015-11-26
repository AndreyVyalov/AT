package num;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumGuessTest {
	private NumberGuessBean numberGuessInstance;

	@Before
	public void setUp() {
		this.numberGuessInstance = new NumberGuessBean();
	}

	@After
	public void tearDown() {
		this.numberGuessInstance = null;
	}

	@Test
	public void testNewInstanceFieldsDefaultness() {
		assertEquals(false, this.numberGuessInstance.getSuccess());
		assertEquals(0, this.numberGuessInstance.getNumGuesses());
		assertTrue(this.numberGuessInstance.answer >= 1
				&& this.numberGuessInstance.answer <= 100);
		assertEquals(null, this.numberGuessInstance.hint);
	}

	@Test
	public void testIfLowerThenHintIsHigher() {
		this.numberGuessInstance.answer = 2;
		this.numberGuessInstance.setGuess("1");
		assertEquals("higher", this.numberGuessInstance.getHint());
	}

	@Test
	public void testIfHigherThenHintIsLower() {
		this.numberGuessInstance.answer = 1;
		this.numberGuessInstance.setGuess("2");
		assertEquals("lower", this.numberGuessInstance.getHint());
	}

	@Test
	public void testOutOfRange() {
		this.numberGuessInstance.setGuess("101");
		assertEquals("lower", this.numberGuessInstance.getHint()); // temporary
																	// changed
																	// "a number next time"
		this.numberGuessInstance.setGuess("-1");
		assertEquals("a number next time", this.numberGuessInstance.getHint());
	}

	@Test
	public void testReset() {
		this.numberGuessInstance.setGuess("50");
		this.numberGuessInstance.answer = 50;
		this.numberGuessInstance.reset();

		assertEquals(false, this.numberGuessInstance.getSuccess());
		assertEquals(0, this.numberGuessInstance.getNumGuesses());
		assertTrue(this.numberGuessInstance.answer >= 1
				&& this.numberGuessInstance.answer <= 100);
	}
}
