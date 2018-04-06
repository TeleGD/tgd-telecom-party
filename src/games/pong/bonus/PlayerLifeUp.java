package games.pong.bonus;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class PlayerLifeUp extends Bonus {

	public PlayerLifeUp(World world) {
		super(world);
		this.name="+";
		this.couleur=new Color(255,0,0);
	}

	@Override
	public void modify(Player player) {
		player.setVies(player.getVies()+1);
	}

	@Override
	public void modify(Ball b) {
		// Pas d'effet sur la balle
	}
}
