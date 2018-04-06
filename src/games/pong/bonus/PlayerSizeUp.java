package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerSizeUp extends Bonus {

	public PlayerSizeUp(World world) {
		super(world);
		this.name="e";
		this.couleur=new Color(102,255,255);
	}

	@Override
	public void modify(Player player) {
		if (player.getId()==0 || player.getId()==1) {
			player.setHauteurBarre(player.getHauteurBarre()+20);
		} else {
			player.setLongueurBarre(player.getLongueurBarre()+20);
		}
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
