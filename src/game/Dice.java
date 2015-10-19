package game;
import java.util.Random;


public class Dice {
	DiceResult previousPair = new DiceResult(0, 0);
	DiceResult latestPair = new DiceResult(0, 0);
	Random psudoGen = new Random(System.currentTimeMillis());

	
	
	public DiceResult rollDice()
	{
		previousPair = latestPair;
		int firstRoll = psudoGen.nextInt(6)+1; //Returns a number between 1 and 6 since 6 is exclusive. 
		int secondRoll = psudoGen.nextInt(6)+1;
		latestPair = new DiceResult(firstRoll, secondRoll);//= new DiceResult(firstRoll, secondRoll);
		return latestPair;
 	}
	
	public DiceResult getPreviousPair()
	{
		return previousPair;
	}
	public DiceResult getLatestPair()
	{
		return latestPair;
	}
}
