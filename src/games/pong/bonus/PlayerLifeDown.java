package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerLifeDown extends Bonus {

	public PlayerLifeDown(World world) {
		super(world);
		this.name="><";
		this.couleur=new Color(255,255,255);
	}

	@Override
	public void modify(Player player) {
		if (player.getVies() > 1) {
			player.setVies(player.getVies()-1);
		}
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
