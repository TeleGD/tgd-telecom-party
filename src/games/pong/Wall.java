package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Wall extends Side {

	private Color color;

	public Wall (World world, int sideID) {
		super (world, sideID);
		this.setSpan (this.boxSize [this.axis]);
		this.color = World.WALL_COLOR;
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int x = this.boxPos [0] + this.pos [0];
		int y = this.boxPos [1] + this.pos [1];
		context.setColor (this.color);
		context.fillRect (x, y, this.size [0], this.size [1]);
	}

}
