package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.World;

public class BallSpeed extends Bonus {

	public BallSpeed (World world) {
		super (world, new Color (.8f, .8f, .8f), "@");
	}

	public void apply (Ball ball) {
		ball.setSpeed (ball.getSpeed () + 4);
	}

}
