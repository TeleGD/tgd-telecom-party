package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerSpeedUp extends Bonus {

	public PlayerSpeedUp (World world) {
		super (world, new Color (1f, .4f, 1f), "S");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			player.setSpeed (player.getSpeed () + 8);
		}
	}

}
