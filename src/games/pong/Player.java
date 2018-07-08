package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;

public class Player extends Side {

	private int controllerID;
	private String name;

	private int lives;
	private int speed;

	private String label;
	private int [] labelPos;

	private Color fillColor;
	private Color strokeColor;

	public Player (World world, int sideID, AppPlayer appPlayer) {
		super (world, sideID);
		this.controllerID = appPlayer.getControllerID ();
		this.name = appPlayer.getName ();
		this.setStart (this.boxSize [this.axis] / 2);
		this.setSpan (World.PLAYER_LENGTH);
		this.setLives (5);
		this.setSpeed (64);
		this.label = this.name + " : " + this.lives + " vie(s)";
		this.labelPos = new int [] {
			World.WORLD_MARGIN,
			World.WORLD_MARGIN + World.SCORE_LINE_HEIGHT * sideID
		};
		this.fillColor = AppPlayer.STROKE_COLORS [appPlayer.getColorID ()];
		this.strokeColor = World.WALL_COLOR;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		this.setStart (this.getStart () + (int) (this.speed * delta * .015f * appInput.getAxisValue (this.axis, this.controllerID)));
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int x = this.boxPos [0] + this.pos [0];
		int y = this.boxPos [1] + this.pos [1];
		context.setColor (this.fillColor);
		context.drawString (this.label, this.labelPos [0], this.labelPos [1]);
		context.fillRect (x, y, this.size [0], this.size [1]);
		context.setColor (this.strokeColor);
		context.drawRect (x, y, this.size [0], this.size [1]);
	}

	public void setLives (int lives) {
		this.lives = Math.max (lives, 0);
		this.label = this.name + " : " + this.lives + " vies";
	}

	public int getLives () {
		return lives;
	}

	public void setSpeed (int speed) {
		this.speed = Math.max (speed, 0);
	}

	public int getSpeed () {
		return this.speed;
	}

}
