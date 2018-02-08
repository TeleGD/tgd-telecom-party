package hub;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class JoueurPlateau {
	private int place,number;
	private String name;
	private Image sprite;
	private float x;
	private float y;
	
	public JoueurPlateau(int num, String name, String nameSprite) {
		place=0;
		this.number=num;
		this.name = name;
		try {
			sprite = new Image(nameSprite);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite, x, y);
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
