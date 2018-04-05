package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Wall extends Player {

	public Wall(World world, int id) {
		super(world, id);
		this.id=~this.id;
		this.couleur=new Color(30,30,30);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(couleur);
		if (id==-1 || id==-2) {
			context.fillRect(barPosFixe-largBarInit/2, barPosMove-taille/2+16, largBarInit, taille-32);
		} else if (id==-3 || id==-4){
			context.fillRect(barPosMove-taille/2+16, barPosFixe+largBarInit/2-8, taille-32, largBarInit);
		}
	}

}
