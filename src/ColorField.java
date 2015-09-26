import java.awt.Color;

import desktop_board.Board;
import desktop_fields.Field;
import desktop_fields.iBuilder;

public final class ColorField extends Field {
	public static class Builder extends Field.Builder<ColorField.Builder> implements
	iBuilder {
		public Builder() {
			this.title = "";
			this.subText = "";
			this.description = "";
			this.bgColor = Board.BASECOLOR;
			this.fgColor = Color.BLACK;
		}

		@Override
		@SuppressWarnings("synthetic-access")
		public ColorField build() {
			return new ColorField(this.bgColor, this.fgColor, this.title, this.subText, this.description);
		}

		@Override
		@SuppressWarnings("unused")
		public ColorField.Builder setFgColor(Color fgColor) {
			return this;
		}
	}
	
	  private ColorField(Color bgColor, Color fgColor, String title, String subText,
		        String description) {
		        super(bgColor, fgColor, title, subText, description, null);
		    }
	  public void setColor(Color c)
	  {
		  layered.setForeground(c);
		  layered.setBackground(c);
	  }
}
