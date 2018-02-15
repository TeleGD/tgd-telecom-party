package hub;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;


public class JoueurPlateau {
	
	private Dice playerDice;
	private int place,number;
	private String name;
	private Image sprite;
	private float x;
	private float y;
	
	public JoueurPlateau(int num, String name, String nameSprite, int height, int width) {
		place=0;
		Dice playerDice = new Dice(4, new int[] {1,2,3,4},1); // Initialise le dé du joueur : 4 faces
		this.number=num;
		this.name = name;
		try {
			sprite = new Image(nameSprite);
			sprite = sprite.getScaledCopy(height , width);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite, x, y);
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta, WorldPlateau worldP ) throws SlickException {
		updateCoordonnees(worldP);
	}
	
	public void avance(int n) {
		place+=n;
		
	}

	public void updateCoordonnees(WorldPlateau worldP ) {
		// Met à jour x et y en fonction du numéro de case sur lequel le joueur se trouve
		int[] coord = WorldPlateau.track.getCoordinates(place);
		x = coord[0] * worldP.getGridWidth() + Main.width/2 - worldP.getGridWidth()/2 ;
		y = coord[1] * worldP.getGridHeight() + Main.height/2 - worldP.getGridHeight()/2 ;
		
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
	
	public void playRound() {
		// Permet de faire jouer un tour au joueur
		
		
	}

	
	
}
