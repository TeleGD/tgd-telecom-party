package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerSizeDown extends Bonus {

	public PlayerSizeDown(World world) {
		super(world);
		this.name="d";
		this.couleur=new Color(51,255,255);
	}

	@Override
	public void modify(Player player) {
		if (player.getId()==0 || player.getId()==1) {
			player.setHauteurBarre(player.getHauteurBarre()-20);
		} else {
			player.setLongueurBarre(player.getLongueurBarre()-20);
		}
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
