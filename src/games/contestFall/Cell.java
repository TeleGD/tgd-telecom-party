package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {
	
	private int state;
	private int posX;
	private int posY;
	private int x;
	private int y;
	private int x2;
	private int y2;
	private int srcx;
	private int srcx2;
	private int srcy;
	private int srcy2;
	private int taille = 40;
	private float tailleSegImg;
	
	public Cell(World w, boolean notExist, int X, int Y, int size) {
		this.state=notExist?0:5;
		this.posX=X;
		this.posY=Y;
		int startX = w.startX+(w.taille-size*taille)/2;
		int startY = w.startY+(w.taille-size*taille)/2;
		this.x=startX+taille*posX;
		this.y=startY+taille*posY;
		this.x2=startX+taille*(posX+1);
		this.y2=startY+taille*(posY+1);
		tailleSegImg = ((float) World.fondPlateforme.getWidth())/size;
		this.srcx=(int) (tailleSegImg*posX);
		this.srcy=(int) (tailleSegImg*posY);
		this.srcx2=(int) (tailleSegImg*(posX+1));
		this.srcy2=(int) (tailleSegImg*(posY+1));
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		if (state > 0) {
			context.setColor(Color.black);
			context.drawImage(World.fondPlateforme,x, y, x2, y2, srcx, srcy, srcx2, srcy2);
			context.drawRect(x,y,taille,taille);
			if (state<5) {
				float l = World.casse[state-1].getWidth();
				context.drawImage(World.casse[state-1],x,y,x2,y2,0,0,l,l);
			}
		}
	}
	
	public void degrade(int damages) {
		this.state=(state-(damages+1)<=0)?0:state-(damages+1);
	}

	public int getState() {
		return state;
	}
	
	
}
