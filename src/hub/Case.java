package hub;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Case {
	private int id;
	private float x;
	private float y;
	
	/* liste des cases :
	 * 0 : Normal (sans effet)
	 * 1 : Fin
	 * 2 : Super-jeu (meilleure récompense & difficulté supérieure)
	 * 3 : Duel (bonus pour le gagnant, malus pour le perdant, le défié choisi le mini jeu dans une petite liste)
	 * 4 : Bonus pour le mini-jeu suivant (valeur du dé x2)
	 * 5 : Collin : recule du nombre de case indiqué par ton prochain dé (dé inversé par rapport à la position)
	 * 6 : Sabeur : si vous voulez : avancez de 2 cases ou faites reculer un adversaire de 2 cases (en appliquant l’effet de la nouvelle case)
	 * 7 : Consolidations : (impossible de bouger jusqu’à gagner un mini-jeu)
	 * 8 : Da Silva : tu peux te faire offrir un café par le dernier joueur
	 * 9 : Tomczak : retour au début de l’année
	 * 10 : Heurtel : la description du mini-jeu suivant n'apparaît qu’0.5 seconde
	 * 11 : Festor : passe ton tour (on ne joue plus après 19h)
	 * 12 : Rémi Bachelet : tp sur une autre case Rémi Bachelet aléatoire
	 */
	
	private Image sprite;
	
	public Case(int id) throws SlickException {
		this.id = id;
		// sprite=new Image("images/case" + id);
		// x = id * WorldPlateau.gridWidth;
		// y = id * WorldPlateau.gridHeight;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
