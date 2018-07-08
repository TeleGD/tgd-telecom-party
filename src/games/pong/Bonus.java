package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus {

	protected int [] boxPos;
	protected int boxSize;
	protected int [] pos;
	protected int size;

	private Color fillColor;
	private Color strokeColor;

	private String label;
	private Font labelFont;
	private int [] labelPos;

	public Bonus (World world, Color color, String label) {
		int start = World.WORLD_MARGIN + World.WORLD_BORDER + World.WORLD_PADDING;
		int span = world.size - start * 2;
		this.boxPos = new int [] {
			world.pos [0] + start,
			world.pos [1] + start
		};
		this.boxSize = span;
		this.pos = new int [] {
			World.RNG.nextInt (span - World.BONUS_SIZE),
			World.RNG.nextInt (span - World.BONUS_SIZE)
		};
		this.size = World.BONUS_SIZE;
		this.fillColor = color;
		this.strokeColor = new Color (color.r - .4f, color.g - .4f, color.b - .4f);
		this.label = label;
		this.labelFont = World.BONUS_FONT;
		this.labelPos = new int [] {
			(World.BONUS_SIZE - World.BONUS_FONT.getWidth (label)) / 2,
			(World.BONUS_SIZE - World.BONUS_FONT.getHeight (label)) / 2
		};
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int x = this.boxPos [0] + this.pos [0];
		int y = this.boxPos [1] + this.pos [1];
		int labelX = x + this.labelPos [0];
		int labelY = y + this.labelPos [1];
		context.setFont (this.labelFont);
		context.setColor (this.fillColor);
		context.fillOval (x, y, this.size, this.size);
		context.setColor (this.strokeColor);
		context.drawOval (x, y, this.size, this.size);
		context.drawString (this.label, labelX, labelY);
	}

	public abstract void apply (Ball ball);

	public void setPos (int [] pos) {
		this.pos = pos;
	}

	public int [] getPos () {
		return this.pos;
	}

	public void setSize (int size) {
		this.size = size;
	}

	public int getSize () {
		return this.size;
	}

}
