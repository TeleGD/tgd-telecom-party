package games.telecomParty;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Player {

	private World plateau;
	private Dice playerDice;
	private int place;
	private int colorID;
	private int controllerID; // place : nb de cases depuis le début (début = 0) ; controllerID : numéro du joueur
	private String name;
	private Image sprite;
	private float x;
	private float y;

	public Player (World plateau, int colorID, int controllerID, String name, String nameSprite) {
		this.plateau = plateau;
		place=0;
		playerDice = new Dice(6, new int[] {1,2,3,4,5,6},1); // Initialise le dé du joueur : 4 faces
		this.colorID = colorID;
		this.controllerID = controllerID;
		this.name = name;
		sprite = AppLoader.loadPicture(nameSprite);
		sprite = sprite.getScaledCopy( (int) (plateau.getGridHeight()*0.9), (int) (plateau.getGridWidth()*0.9) );
	}

	public void render(GameContainer container,StateBasedGame game, Graphics g) {
		g.drawImage(sprite, x, y);
	}

	public void update(GameContainer container,StateBasedGame game, int delta) {
		updateCoordonnees();
	}

	public void avance(int n) {
		place+=n;
	}

	public void updateCoordonnees() {
		// Met à jour x et y en fonction du numéro de case sur lequel le joueur se trouve
		int[] coord = this.plateau.getTrack().getCoordinates(place);
//		x = coord[0] * this.plateau.getGridWidth() + AppGame.width/2 - this.plateau.getGridWidth()/2 ;
//		y = coord[1] * this.plateau.getGridHeight() + AppGame.height/2 - this.plateau.getGridHeight()/2 ;
		x = coord[0];
		y = coord[1];
	}

	public void playRound() {
		// Permet de faire jouer un tour au joueur

		/*Règle du tour :
		 * 1 : 	le joueur lance son dé (peut être modifié par des effets) il avance du nombre de cases correspondant
		 * 2 : 	arrive sur une case :
		 * 			si c’est une case à effet : applique l’effet de la case
		 */

		// Phase 1 : lancer de dé
		playerDice.roll();
		//TODO : affiche résultat du dé
		System.out.println("Joueur " + controllerID + "Avance de " + playerDice.getValue());
		avance(playerDice.getValue());

		// Phase 2 :
		plateau.getTrack().getCase(place).doEffect(this); // On applique l'effet de la case d'arrivée

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

	public int getControllerID() {
		return controllerID;
	}
}
