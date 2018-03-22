package hub;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

// import org.newdawn.slick.Image;

public class Case {
	private WorldPlateau plateau;
	private int id;
	private Color backgroundColor;
	private Color textColor;
	private int x;
	private int y;
	private int type;
	private int width;
	private int height;
	private int radius;

	/* liste des cases :
	 * 0 : Normal (sans effet)
	 * 1 : Fin
	 * 2 : Sabeur : si vous voulez : avancez de 2 cases ou faites reculer un adversaire de 2 cases (en appliquant l’effet de la nouvelle case)
	 * 3 : Bonus pour le mini-jeu suivant (valeur du dé x2)
	 * 4 : Collin : recule du nombre de case indiqué par ton prochain dé (dé inversé par rapport à la position)
	 * 5 : Consolidations : (impossible de bouger jusqu’à gagner un mini-jeu)
	 * 6 : Tomczak : retour au début de l’année
	 * 7 : Festor : passe ton tour (on ne joue plus après 19h)
	 * 8 : Rémi Bachelet : tp sur une autre case Rémi Bachelet aléatoire
	 */

	// private Image sprite;

	public Case (WorldPlateau plateau, int id, int x, int y, int type) {
		this.plateau = plateau;
		this.type = type;
		// sprite=new Image("images/case" + id + ".jpg");
		this.x = x;
		this.y = y;
		this.id = id;
		
		switch (type) {
		case 0 :
			backgroundColor = Color.gray;
			break;
		case 1 :
			backgroundColor = Color.black;
			break;
		case 2 :
			backgroundColor = Color.red;
			break;
		case 3 :
			backgroundColor = Color.cyan;
			break;
		case 4 :
			backgroundColor = Color.pink;
			break;
		case 5 :
			backgroundColor = Color.yellow;
			break;
		case 6 :
			backgroundColor = Color.green;
			break;
		case 7 :
			backgroundColor = Color.orange;
			break;
		case 8 :
			backgroundColor = Color.blue;
			break;
		}
		this.textColor = new Color(backgroundColor.getRed()/3,backgroundColor.getGreen()/3,backgroundColor.getBlue()/3);

		width = this.plateau.getGridWidth() - this.plateau.getGridGap() / 2;
		height = this.plateau.getGridHeight() - this.plateau.getGridGap() / 2;
		radius = this.plateau.getGridGap() / 2;
	}

	public void render(GameContainer container,StateBasedGame game, Graphics g) {
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
