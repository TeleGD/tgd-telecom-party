package hub;

import org.newdawn.slick.Image;

public class JoueurPlateau {
	private int place;
	private Image sprite;
	
	public JoueurPlateau() {
		place=0;
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
