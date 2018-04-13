package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {
	
	private int status;
	private int posX;
	private int posY;
	private int x;
	private int y;
	private int taille = 40;
	
	public Cell(boolean notExist, int X, int Y) {
		this.status=notExist?0:5;
		this.posX=X;
		this.posY=Y;
		this.x=taille*posX;
		this.y=taille*posY;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		if (status > 0) {
			context.setColor(Color.black);
			context.drawRect(x,y,taille,taille);
		}
		
	}
	
}
