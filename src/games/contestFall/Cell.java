package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
	private int taille;
	private float tailleSegImg;
	private Player player;
	private boolean hasAmmo;
	private int resistance;
	private World world;
	
	public Cell(World w, boolean notExist, int X, int Y, int size) {
		this.state=notExist?0:5;
		this.world=w;
		this.posX=X;
		this.posY=Y;
		this.taille=w.taille/(size);
		int startX = w.startX;
		int startY = w.startY;
		this.x=startX+taille*posX;
		this.y=startY+taille*posY;
		this.x2=startX+taille*(posX+1);
		this.y2=startY+taille*(posY+1);
		tailleSegImg = ((float) World.fondPlateforme.getWidth())/size;
		this.srcx=(int) (tailleSegImg*posX);
		this.srcy=(int) (tailleSegImg*posY);
		this.srcx2=(int) (tailleSegImg*(posX+1));
		this.srcy2=(int) (tailleSegImg*(posY+1));
		this.player=null;
		this.hasAmmo=false;
		this.resistance=1000;
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
			if (hasAmmo) {
				float l = World.ammo.getWidth();
				context.drawImage(World.ammo,x+taille/10,y+taille/10,x2-taille/10,y2-taille/10,0,0,l,l,Color.black);
			}
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (player!=null) {
			resistance-=delta;
			if (resistance<=0) {
				resistance=1000+resistance;
				degrade(1);
			}
		}
	}
	
	public void degrade(int damages) {
		if (state>0) {
			state-=damages;
			if (state<=0) {
				state=0;
				hasAmmo=false;
				player=null;
				world.platform.getCell(posX-1,posY).degrade(Math.random()<0.5?1:0);
				world.platform.getCell(posX+1,posY).degrade(Math.random()<0.5?1:0);
				world.platform.getCell(posX,posY-1).degrade(Math.random()<0.5?1:0);
				world.platform.getCell(posX,posY+1).degrade(Math.random()<0.5?1:0);
			}
		}
	}

	public int getState() {
		return state;
	}
	
	public boolean hasPlayer() {
		return (player!=null);
	}
	
	public void setPlayer(Player p) {
		if (state>0) {
			player=p;
		} else {
			player=null;
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean hasAmmo() {
		return hasAmmo;
	}

	public void removeAmmo() {
		hasAmmo=false;		
	}

	public void addAmmo() {
		hasAmmo=true;
	}
	
}
