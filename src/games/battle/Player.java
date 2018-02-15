package games.battle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
public class Player {
	public float radius;
	public float x;
	public float y;
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
		float x;
		float y;
		float maxRadius;
		float start;
		float end;
		x = this.x + general.Main.width / 2;
		y = this.y + general.Main.height / 2;
		maxRadius = this.radius - this.y / 8;
		start = (float) ((this.gape ? 1f : 0f) / 8 + this.direction) * 180;
		end = (float) (2 - (this.gape ? 1f : 0f) / 8 + this.direction) * 180;
		context.setLineWidth (2);
		context.setColor (new Color (0f, 0f, 0f, (float) .5 + this.y / (this.radius * 16)));
		context.fillArc (x - maxRadius, y + maxRadius / 2 - this.y, maxRadius * 2, maxRadius, 0, 360);
		context.setColor (this.fillColor);
		context.fillArc (x - this.radius, y - this.radius, this.radius * 2, this.radius * 2, start, end);
		context.setColor (this.strokeColor);
		context.drawLine (x, y, (float) (x + Math.cos (Math.toRadians (start)) * this.radius), (float) (y + Math.sin (Math.toRadians (start)) * this.radius));
		context.drawLine (x, y, (float) (x + Math.cos (Math.toRadians (end)) * this.radius), (float) (y + Math.sin (Math.toRadians (end)) * this.radius));
		context.drawArc (x - this.radius, y - this.radius, this.radius * 2, this.radius * 2, start, end);
	};
};
