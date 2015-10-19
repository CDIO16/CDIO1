package test;

import desktop_resources.GUI;
import game.Board;
import game.DiceResult;
import game.Player;


class BoardTest extends Board
{
	int errorCount = 0;
	void setupPlayers()
	{
		players.add(new Player("Test Subject 1"));
		players.add(new Player("Chell"));
		currentPlayer = players.get(0);
	}
	boolean verifyScore(int expected, Player player)
	{
		if(player.getPoints()!=expected)
		{
			System.out.println("Error! Score wasn't properly calculated!");
			++errorCount;
			return false;
		}
		return true;
	}
	int testDiceResults()
	{
		setupPlayers();
		DiceResult res = new DiceResult(1, 2);
		DiceResult prevRes = new DiceResult(0, 0);
		//See if it swaps player: 
		evaluateRoll(res, prevRes);
		if(currentPlayer==players.get(0))
		{
			System.out.println("Fejl! Spilleren bliver ikke skiftet hvis terningerne ikke er i par!");
			++errorCount;
		}
		//Tests to see if the score was added correctly: 
		verifyScore(3, players.get(0));
		//Tests pair of one condition: 
		res = new DiceResult(1,1);
		currentPlayer = players.get(0);
		evaluateRoll(res, prevRes);
		if(players.get(0).getPoints()!=0)
		{
			System.out.println("Fejl! Spilleren får ikke sine points resat ved par af ét!");
			++errorCount;
		}
		if(currentPlayer!=players.get(0))
		{
			System.out.println("Fejl! Spilleren får ikke en ekstra tur ved par af ét!");
			++errorCount;
		}
		
		//Tests pair: 
		res = new DiceResult(5, 5);
		evaluateRoll(res, prevRes);
		currentPlayer = players.get(0);
		if(currentPlayer!=players.get(0))
		{
			System.out.println("Fejl! Spilleren fik ikke en ekstra tur ved to ens!");
			++errorCount;
		}
		verifyScore(10, players.get(0));
		
		//Test pair of six, twice in a row:
		prevRes = new DiceResult(6,6);
		res = new DiceResult(6,6);
		currentPlayer = players.get(0);
		currentRound = 1;
		evaluateRoll(res, prevRes);		
		if(!winners.contains(players.get(0).getName()))
		{
			System.out.println("Fejl! Spilleren vandt ikke ved at slå to 6'ere i træk!");
			++errorCount;
		}
		
		//Tjekker om spiller 2 får sin chance for at slå uafgjort
		if(currentPlayer==players.get(0))
		{
			System.out.println("Fejl! Den anden spiller fik ikke en chance for at blive uafgjort med spiller 1!");
			++errorCount;
		}
		winners.clear();
		currentPlayer = players.get(0);
		
		//Tjekker om spilleren vinder ved at komme over 40 med et par. 
		players.get(0).setPoints(38);
		res = new DiceResult(4, 4);
		evaluateRoll(res, prevRes);		
		if(winners.contains(players.get(0).getName()))
		{
			System.out.println("Fejl! En spiller kan først vinde ved at få to ens EFTER han har fået 40 points!");
			++errorCount;
		}
		
		//Tjekker om spilleren vinder, når han har 40 points(finder brug af < i stedet for <=)
		players.get(0).setPoints(40);
		currentPlayer = players.get(0);
		res = new DiceResult(2,2);
		evaluateRoll(res, prevRes);		
		if(!winners.contains(players.get(0).getName()))
		{
			System.out.println("Fejl! Spilleren vandt ikke selvom han havde over 40 points!");
			++errorCount;
		}
		return errorCount;
	}
	
}


public class TestBoardLogic	{

	
	public static void main(String[] args) {
		BoardTest test = new BoardTest();
		int errors = test.testDiceResults();
		if(errors==0)
		{
			System.out.println("Der blev ingen fejl fundet");
		}
		else
		{
			System.out.println("Fandt " + errors + " fejl!");
		}
		GUI.close();
	}

}
