package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import desktop_board.Center;
import desktop_fields.Field;
import desktop_resources.GUI;

/**
 * 
 * Contains the logic for the gameplay 
 * Controls who gets points and who's turn it is. 
 *
 */
public class Board {
	/**
	 * We use an array list to easily extend the amount of supported players. 
	 * This forces us to use a generic method on keeping track of the currentPlayer 
	 * instead of just swapping between two. 
	 */
	List<Player> players = new ArrayList<Player>();
	
	/**
	 * Contains a reference to the current player from the List
	 */
	private Player currentPlayer;
	/**
	 * Variable which contains the amount of rounds played. 
	 * A single round is when all players has had their turn.
	 * Is used to see if one of the players are lucky enough to win first round
	 */
	int currentRound = 1;
	
	/**
	 * Contains a list of winners since more than one player can win each round. 
	 */
	List<String> winners = new ArrayList<String>();
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	/**
	 * Constructs a new player and adds it to the board. 
	 * 
	 * @param name
	 * the name of the player
	 */
	void createPlayer(String name)
	{
		Player newPlayer = new Player(name);
		players.add(newPlayer);
		GUI.addPlayer(name, 0);
		//Updates the list of players to contain the recent one: 
		desktop_board.Board.getInstance().updatePlayers();
	}
	//Evaluates a player's roll to figure out what should happen
	void evaluateRoll()
	{
		DiceResult roll = currentPlayer.getDice().getLatestPair();
		currentPlayer.addPoints(roll.snakeEyes());
		if(roll.isPair())
		{
			//No need to check both dice as we know they are a pair. 
			if(roll.getFirstDice()==1)
			{
				currentPlayer.setPoints(0);
			}
			else if((currentPlayer.getPoints())>=40)
			{
				//Adds the player to the list of winners
				winners.add(currentPlayer.getName());
				//Give the other player a chance. 
				swapPlayers();
			}
			else if(roll.getFirstDice()==6)
			{
				DiceResult prevRoll = currentPlayer.getDice().getPreviousPair();
				//If previous roll also was a pair of 6's then the player wins
				if(prevRoll.getFirstDice()==6 && prevRoll.getSecondDice()==6)
				{
					//Adds the player to the list of winners
					winners.add(currentPlayer.getName());
					//Give the other player a chance. 
					swapPlayers();
				}
			}
			//The display doesn't dissapear until you mouseover the middle. DISABLED: 
			//GUI.displayChanceCard("Spiller: " + currentPlayer.getName() + " slog to ens og får en ekstra tur!");
		}
		else //Player didn't run anything interesting.. Pass the turn onto the next player. 
		{
			swapPlayers();
		}

	}
	
	/**
	 * Advances to the next player. 
	 */
	private void swapPlayers()
	{
		int pos = players.indexOf(currentPlayer);
		//Checks to see if we will surpass the bonds 
		if(pos+1 >= players.size())
		{
			//If it does, then we have reached the end of the list and shall therefore start over
			currentPlayer = players.get(0);
			//Since all players had had their turn, make it a new round. 
			++currentRound;
		}
		else
		{
			//If it doesn't, then advance to the next player. 
			currentPlayer = players.get(pos+1);
		}
	}
	
	/**
	 * Changes the layout from default
	 */
	private void initializeBoard()
	{
		Center.getInstance().getCenterPanel().getComponent(0).setVisible(false);
		Field[] fields = new Field[40];
		
		//Draws the sourrounding area with tiles of two. 
		for (int i = 0; i <fields.length; i++) {
			Color col;
			if(i%2==0)
			{
				//A dark-green color every second tile
				col = new Color(0, 125, 0);
			}
			else
			{
				//A red color every first tile
				col = Color.red;
			}
			//Has to be done each loop as it sets the position on the board at creation
			ColorField f = new ColorField.Builder().setBgColor(col).build();
			fields[i] = f;
		}
		//Informs the board of the new layout 
		GUI.create(fields);
	}
	
	public void startGame()
	{
		String user1 = GUI.getUserString("Velkommen!\nIndtast navn på spiller 1:");
		createPlayer(user1);
		String user2 = GUI.getUserString("Indtast navn på spiller 2:");
		//Player2 cannot be called the same as player1
		while(user2.equals(user1))
		{
			user2 = GUI.getUserString("Navnet kan ikke være det samme som spiller 1. Prøv igen!");
		}
		createPlayer(user2);
		//Starting player is the first player:
		currentPlayer = players.get(0);
		
		
		
		do
		{
			//Waits for the player to roll the dice
			GUI.getUserButtonPressed("Det er " + currentPlayer.getName() + "s tur! ", "Slå");
			
			DiceResult res = currentPlayer.getDice().rollDice();
			GUI.setDice(res.getFirstDice(), 0, 6, 5, res.getSecondDice(), 0, 5, 6);	
			//Advances the gameplay 
			evaluateRoll();
			
			//If its rounds 1 and we have a winner, this will still be true, hence giving the other players a chance
		}while(winners.size()==0 || currentRound==1);
		
		//We only have one winner
		if(winners.size()==1)
		{
			GUI.displayChanceCard(winners.get(0) + " vandt spillet!");
		}
		else 
		{
			//Prints the players who tied
			String strWinners = winners.get(0);
			for(int i = 1;i<winners.size();++i)
			{
				strWinners += " og " + winners.get(i)  ;
			}
			GUI.displayChanceCard("Spillet blev uafgjort mellem: " + strWinners);
		}
		
	GUI.showMessage("Spillet er slut!");
	}
	public static void main(String[] args) 
	{
			Board board = new Board();
			board.initializeBoard();
			
			if(GUI.getUserLeftButtonPressed("Velkommen!", "Start spil!", "Exit"))
			{
				board.startGame();
			}	
			GUI.close();
	}
	
}
