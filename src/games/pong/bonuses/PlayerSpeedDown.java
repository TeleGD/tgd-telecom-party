package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerSpeedDown extends Bonus {

	public PlayerSpeedDown (World world) {
		super (world, new Color (.4f, 1f, .4f), "s");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			int speed = player.getSpeed ();
			if (speed > 8) {
				player.setSpeed (speed - 8);
			}
		}
	}

}
