package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class BallSpeed extends Bonus {

	public BallSpeed(World world) {
		super(world);
		this.name="s";
		this.couleur=new Color(51,255,51);
	}

	@Override
	public void modify(Player player) {
		// Pas d'effet sur le joueur
	}

	@Override
	public void modify(Ball b) {
		b.setSpeed((float) (b.getSpeed()+0.5));
	}
}
