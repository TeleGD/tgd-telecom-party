package hub;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class JoueurPlateau {
	private int place;
	private String color;
	
	public JoueurPlateau(String color) {
		place=0;
		this.color = color;
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

	public String getcolor() {
		return color;
	}

	public void setcolor(String color) {
		this.color = color;
	}
	
}
