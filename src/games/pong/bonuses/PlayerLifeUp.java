package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerLifeUp extends Bonus {

	public PlayerLifeUp (World world) {
		super (world, new Color (1f, 1f, 1f), "<>");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			player.setLives (player.getLives () + 1);
		}
	}

}
