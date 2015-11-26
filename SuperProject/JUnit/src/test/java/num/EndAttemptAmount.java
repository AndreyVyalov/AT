package num;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EndAttemptAmount {
	NumberGuessBean numberGuessInstance;
	int max = 100;
	int min = 1;

	@Before
	public void setUp() {
		this.numberGuessInstance = new NumberGuessBean();
	}

	@After
	public void tearDown() {
		this.numberGuessInstance = null;
	}

	@Test
	public void testAttemptingToWin() {
		Random random = new Random();
		int amountOfAttempts = 10;
		System.out.println("You have " + amountOfAttempts + " attempts.");
		while ((this.numberGuessInstance.getSuccess() != true)
				& (this.numberGuessInstance.getNumGuesses() != amountOfAttempts)) {
			Integer guessNumber = random.nextInt(max - min + 1) + min;

			System.out.println("Attempt "
					+ this.numberGuessInstance.getNumGuesses()
					+ " with number '" + guessNumber + "'");

			this.numberGuessInstance.setGuess(guessNumber.toString());

			if (this.numberGuessInstance.getSuccess() == true) {
				System.out.println("YOU ARE THE CHAMPION! YOU WIN!");
			} else {

				if (this.numberGuessInstance.getHint().equals("higher")) {
					min = guessNumber;
				} else {
					max = guessNumber;
				}

				if (this.numberGuessInstance.getNumGuesses() == amountOfAttempts) {
					System.out.println("You lose. Game over. Go away.");
					Assert.fail("Sad, but true - you lose.");

				} else {
					System.out.println("Wrong number, trying again..");
				}

			}
		}
	}

}
