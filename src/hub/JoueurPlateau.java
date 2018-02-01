package hub;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class JoueurPlateau {
	private int place;
	private Image sprite;
	
	public JoueurPlateau(String color) {
		place=0;
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void avance(int n) {
		place+=n;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	
	
}
