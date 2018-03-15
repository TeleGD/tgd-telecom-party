package hub;

import org.newdawn.slick.Image;
//import org.newdawn.slick.Music;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

// import general.AppGame;


public class JoueurPlateau {

	private WorldPlateau plateau;
	private Dice playerDice;
	private int place;
	private int number; // place : nb de cases depuis le début (début = 0) ; number : numéro du joueur
	private String name;
	private Image sprite;
	private float x;
	private float y;

	public JoueurPlateau(WorldPlateau plateau, int num, String name, String nameSprite) {
		this.plateau = plateau;
		place=0;
		playerDice = new Dice(4, new int[] {1,2,3,4},1); // Initialise le dé du joueur : 4 faces
		this.number=num;
		this.name = name;
		try {
			sprite = new Image(nameSprite);
			sprite = sprite.getScaledCopy( (int) (plateau.getGridHeight()*0.9), (int) (plateau.getGridWidth()*0.9) );
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
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
		 * 			sinon : fait le mini-jeu du type de la case (duel, normal…)
		 * 3 : 	le gagnant d’un mini-jeu gagne un avantage (meilleur dé pour son prochain lancer)
		 */

		playerDice.roll();
		//TODO : affiche résultat du dé
		System.out.println("Joueur " + number + "Avance de " + playerDice.getValue());
		avance(playerDice.getValue());

		//TODO : gérer l'étape 2 et 3

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
