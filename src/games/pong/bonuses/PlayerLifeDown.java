package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerLifeDown extends Bonus {

	public PlayerLifeDown (World world) {
		super (world, new Color (.4f, .4f, .4f), "><");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			int lives = player.getLives ();
			if (lives > 1) {
				player.setLives (lives - 1);
			}
		}
	}

}
