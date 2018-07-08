package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.World;

public class BallRandomDir extends Bonus {

	public BallRandomDir (World world) {
		super (world, new Color (.4f, .4f, 1f), "r");
	}

	public void apply (Ball ball) {
		double theta = World.RNG.nextDouble () * Math.PI * 2;
		ball.setDir (new float [] {
			(float) Math.cos (theta),
			(float) Math.sin (theta)
		});
	}

}
