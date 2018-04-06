package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerSpeedDown extends Bonus {

	public PlayerSpeedDown(World world) {
		super(world);
		this.name="s";
		this.couleur=new Color(255,204,51);
	}

	@Override
	public void modify(Player player) {
		player.setSpeed(player.getSpeed()-1);
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
