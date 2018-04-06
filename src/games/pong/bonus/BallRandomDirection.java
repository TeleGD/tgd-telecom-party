package games.pong.bonus;

import java.util.Random;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class BallRandomDirection extends Bonus {

	public BallRandomDirection(World world) {
		super(world);
		this.name="R";
		this.couleur=new Color(153,0,153);
	}
	
	@Override
	public void modify(Player player) {
		// Pas d'effet sur le joueur
	}

	@Override
	public void modify(Ball b) {
		Random r = new Random();
		float direction[] = new float[2];
		direction[0]=r.nextFloat();
		direction[1]=(float) Math.sqrt(1-direction[0]*direction[0]);
		b.setDirection(direction);
	}
}
