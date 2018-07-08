package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerSpanUp extends Bonus {

	public PlayerSpanUp (World world) {
		super (world, new Color (.4f, 1f, 1f), "P");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			player.setSpan (player.getSpan () + 20);
		}
	}

}
