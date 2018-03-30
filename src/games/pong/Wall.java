package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Wall extends Player {
	
	public Wall(World world, int id) {
		super(world, id, new Color(250,250,250));
		this.id=-this.id-1;
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(couleur);
		if (id==-1 || id==-2) {
			g.fillRect(barPosFixe-largBarInit/2, barPosMove-taille/2+16, largBarInit, taille-32);
		} else if (id==-3 || id==-4){
			g.fillRect(barPosMove-taille/2+16, barPosFixe+largBarInit/2-8, taille-32, largBarInit);
		}
	}
	
}
