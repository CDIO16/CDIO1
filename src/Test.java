import java.awt.Color;
import java.awt.Graphics2D;

import desktop_board.Board;
import desktop_board.Center;
import desktop_codebehind.Player;
import desktop_fields.Field;
import desktop_resources.GUI;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Hides the questionmark in center. 
		Center.getInstance().getCenterPanel().getComponent(0).setVisible(false);
		
		
		ColorField f = new ColorField.Builder().setBgColor(Color.red).build();
		Field[] fields = {f};
		GUI.create(fields);
		GUI.addPlayer("Bjarne", 0);
		GUI.addPlayer("Benny", 0);
		GUI.setBalance("Bjarne", 5);
		
		//Fjerner bilen fra scoreboarded
		Player plr = Board.getInstance().getPlayer("Bjarne");
		Graphics2D img = plr.getImage().createGraphics();
		img.setBackground(new Color(0, 0, 0, 0));
		img.clearRect(0, 0, plr.getImage().getWidth(),plr.getImage().getHeight());
		Board.getInstance().updatePlayers();
		/*
		
		
		FieldFactory.fields.clear();
		FieldFactory.fields.add(f);
		//GUI.create(fields);
		
		
		//GUI.setDice(2, 5);
		 * 
		 */
		f.setColor(Color.blue);
		GUI.setDice(5, 0, 6, 5, 2, 0, 5, 6);
	}

}
