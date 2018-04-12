package games.reflex;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonP extends Button {
	private Random r = new Random();
	private int radius;
	private String letter;

	public ButtonP(Player player, int num) {
		super(player, num);
		radius = 30;
		assert(numero>=0 && numero<4) : "erreur d'input";
		
		switch (numero) {
		case 0 :
			letter="A";
			color = Color.green;
			break;
		case 1 :
			letter="B";
			color = Color.red;
			break;
		case 2 :
			letter="X";
			color = Color.blue;
			break;
		case 3 :
			letter="Y";
			color = Color.yellow;
			break;
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(color.brighter((float) 0.5));
		g.fillOval(x, y, radius, radius);
		g.setColor(color);
		g.drawOval(x, y, radius, radius);
		g.drawString(letter,x ,y);
	}

}
