/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
import java.util.Random;

public class Dice {
	
	// Attribute(s)
	private static Random diceNo = new Random();
	
	// Other Method(s)
	public static int generateDiceNo()
	{
		return diceNo.nextInt(6) + 1; // A dice has numbers from 1 to 6, this method rolls the dice to get a random number from 1 to 6.
	}
}