package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.World;

public class BallRandomPos extends Bonus {

	public BallRandomPos (World world) {
		super (world, new Color (1f, 1f, .4f), "R");
	}

	public void apply (Ball ball) {
		int start = World.WORLD_PADDING;
		int span = this.boxSize - World.BALL_SIZE;
		ball.setPos (new int [] {
			World.RNG.nextInt (span - World.BALL_SIZE) + start,
			World.RNG.nextInt (span - World.BALL_SIZE) + start
		});
	}

}
