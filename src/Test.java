import java.awt.Color;
import desktop_fields.Field;
import desktop_resources.GUI;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorField f = new ColorField.Builder().setBgColor(Color.red).build();
		Field[] fields = {f};
		GUI.create(fields);
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
