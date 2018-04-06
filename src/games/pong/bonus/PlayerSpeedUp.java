package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerSpeedUp extends Bonus {

	public PlayerSpeedUp(World world) {
		super(world);
		this.name="q";
		this.couleur=new Color(255,153,51);
	}

	@Override
	public void modify(Player player) {
		player.setSpeed(player.getSpeed()+1);
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
