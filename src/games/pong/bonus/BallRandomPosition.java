package games.pong.bonus;

import java.util.Random;

import org.newdawn.slick.Color;

import games.pong.Ball;
import games.pong.Player;
import games.pong.World;

public class BallRandomPosition extends Bonus {

	public BallRandomPosition(World world) {
		super(world);
		this.name="r";
		this.couleur=new Color(102,102,255);
	}
	
	@Override
	public void modify(Player player) {
		// Pas d'effet sur le joueur
	}

	@Override
	public void modify(Ball b) {
		Random r = new Random();
		float posX=milieu[0]-taille/2+80+r.nextInt(taille-160);
		float posY=milieu[1]-taille/2+80+r.nextInt(taille-160);
		b.setPosX(posX);
		b.setPosY(posY);
	}
}
