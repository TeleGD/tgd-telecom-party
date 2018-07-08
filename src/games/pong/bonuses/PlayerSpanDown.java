package games.pong.bonuses;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Bonus;
import games.pong.Player;
import games.pong.World;

public class PlayerSpanDown extends Bonus {

	public PlayerSpanDown (World world) {
		super (world, new Color (1f, .4f, .4f), "p");
	}

	public void apply (Ball ball) {
		Player player = ball.getLastHit ();
		if (player != null) {
			int span = player.getSpan ();
			if (span > 20) {
				player.setSpan (span - 20);
			}
		}
	}

}
