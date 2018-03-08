package hub;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

// import org.newdawn.slick.Image;

public class Case {
	private int id;
	private int x;
	private int y;
	private int type;
	private int width, height, radius;
	
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
	
	// private Image sprite;
	
	public Case (int id, int x, int y, int type) {
		this.id = id;
		this.type = type;
		// sprite=new Image("images/case" + id + ".jpg");
		this.x = x;
		this.y = y;
		
		width = WorldPlateau.getGridWidth() - WorldPlateau.getGridGap() / 2;
		height = WorldPlateau.getGridHeight() - WorldPlateau.getGridGap() / 2;
		radius = WorldPlateau.getGridGap() / 2;
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) {
		
		Color backgroundColor = new Color (255, 0, 0);
		Color textColor = new Color (127, 0, 0);
		g.setColor (backgroundColor);
		g.fillRoundRect ((float) x , (float) y, width, height, radius);
		g.setColor (textColor);
		g.drawString ("[" + id + "]", x, y);
	}

	public int getType() {
		return type;
	}

	public float getX () {
		return x;
	}

	public float getY () {
		return y;
	}
	
	
	
}
