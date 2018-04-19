package games.pathPainting;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private int posX;
	private int posY;
	private int x;
	private int y;
	private int taille;
	private Player player;
	private Color couleur;

	public Cell(World w, int X, int Y, int size) {
		this.posX=X;
		this.posY=Y;
		this.taille=size;
		this.x=taille*posX;
		this.y=taille*posY;
		this.player=null;
		this.couleur=Color.white;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(couleur);
		context.fillRect(x,y,taille,taille);
	}

	public boolean hasPlayer() {
		return (player!=null);
	}

	public void setPlayer(Player p) {
		if (player==null) {
			player=p;
			couleur=p.getCouleur();
		} else if (player!=p){
			throw new RuntimeException("Tentative de changement de couleur d'une case");
		}
	}

	public Player getPlayer() {
		return player;
	}
}
