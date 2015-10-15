package game;

import java.util.Random;

/*
 * A compact OO-way of storing the eyes of the two dices. Is mainly a storage class, but also has some utility functions. 
 */
class DiceResult
{
	int[] eyes = new int[2];
	DiceResult(int first, int second)
	{
		eyes[0] = first;
		eyes[1] = second;
	}
	boolean isPair()
	{
		return eyes[0]==eyes[1];
	}
	int snakeEyes()
	{
		return eyes[0] + eyes[1];
	}
	int getFirstDice()
	{
		return eyes[0];
	}
	int getSecondDice()
	{
		return eyes[1];
	}
};

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
