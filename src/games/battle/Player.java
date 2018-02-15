package games.battle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
public class Player {
	public int radius;
	public int x;
	public int y;
	public int direction;
	public boolean gape;
	public boolean jump;
	public int jumpDuration;
	private Color fillColor;
	private Color strokeColor;
	public Player (int id, int radius) {
		this.radius = radius;
		this.x = 0;
		this.y = 0;
		this.direction = 0;
		this.gape = true;
		this.jump = false;
		this.jumpDuration = 0;
		switch (id) {
			case 0:
				this.fillColor = new Color (255, 204, 51);
				this.strokeColor = new Color (204, 153, 0);
				break;
			case 1:
				this.fillColor = new Color (255, 204, 204);
				this.strokeColor = new Color (255, 153, 153);
				break;
			case 2:
				this.fillColor = new Color (153, 204, 255);
				this.strokeColor = new Color (102, 153, 204);
			default:
				throw new RuntimeException ("Mauvais identifiant de joueur");
		};
	};
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int maxRadius;
		float alpha;
		maxRadius = this.radius - this.y / 8;
		alpha = (float) .5 + this.y / 512;
		context.setColor (new Color (0f, 0f, 0f, alpha));
		context.drawOval (this.x, this.radius, this.radius - this.y / 8, (this.radius - this.y / 8) / 2);
		context.fillArc (this.x - maxRadius, this.y - maxRadius / 2, maxRadius * 2, maxRadius, 0, 360);
		context.setColor (this.fillColor);
		context.fillArc (this.x - this.radius, this.y - this.radius, this.radius * 2, this.radius * 2, (float) ((this.gape ? 0 : 1) + 8 * this.direction) * 360 / 8, (float) (16 - (this.gape ? 1 : 0) + 8 * this.direction) * 360 / 8);
		context.setColor (this.strokeColor);
		context.drawArc (this.x - this.radius, this.y - this.radius, this.radius * 2, this.radius * 2, (float) ((this.gape ? 0 : 1) + 8 * this.direction) * 360 / 8, (float) (16 - (this.gape ? 1 : 0) + 8 * this.direction) * 360 / 8);
	}
};
