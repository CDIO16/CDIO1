package game;
/*
 * A compact OO-way of storing the eyes of the two dices. Is mainly a storage class, but also has some utility functions. 
 */
public class DiceResult
{
	int[] eyes = new int[2];
	public DiceResult(int first, int second)
	{
		eyes[0] = first;
		eyes[1] = second;
	}
	public boolean isPair()
	{
		return eyes[0]==eyes[1];
	}
	public int snakeEyes()
	{
		return eyes[0] + eyes[1];
	}
	public int getFirstDice()
	{
		return eyes[0];
	}
	public int getSecondDice()
	{
		return eyes[1];
	}
};

