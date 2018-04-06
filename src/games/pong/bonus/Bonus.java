package games.pong.bonus;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public abstract class Bonus {

	protected int x;
	protected int y;
	protected int milieu[];
	protected int taille;
	protected int radius;
	protected Color couleur;
	protected String name;
	protected boolean picked;
	private Random r = new Random();

	public Bonus(World world) {
		this.milieu=world.milieu;
		this.taille=world.taille;
		this.x=milieu[0]-taille/2+80+r.nextInt(taille-160);
		this.y=milieu[1]-taille/2+80+r.nextInt(taille-160);
		this.radius=30;
		this.picked=false;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setFont(World.bonusFont);
		context.setColor(couleur);
		context.fillOval(x-radius/2,y-radius/2,radius,radius);
		context.setColor(couleur.darker((float) 0.5));
		context.drawOval(x-radius/2,y-radius/2,radius,radius);
		context.drawString(name,x-World.bonusFont.getWidth(name)/2,y-World.bonusFont.getHeight(name)/2);
	}

	public void update(GameContainer container, StateBasedGame game, int delta, Ball b) {
		if (Math.sqrt(Math.pow((b.getPosX()-this.x),2)+Math.pow((b.getPosY()-this.y),2))<=b.getRadius()/2+this.radius/2 && !picked) {
			this.modify(b);
			if (b.getLastHit()!=-1 ) {
				this.modify(b.getPlayers()[b.getLastHit()]);
			}
			this.picked=true;
		}
	}

	public abstract void modify(Player player);

	public abstract void modify(Ball b);

	public boolean isPicked() {
		return this.picked;
	}

}
