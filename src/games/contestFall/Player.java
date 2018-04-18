package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import general.AppInput;
import general.AppPlayer;

public class Player {
	private World w;
	private int posX;
	private int posY;
	private int ammo=0;
	private int maxAmmo=5;
	private int direction;
	/* 0 -> bas
	 * 1 -> droite
	 * 2 -> haut
	 * 3 -> gauche
	 */
	private int size;
	private int x;
	private int y;
	private int x2;
	private int y2;
	private boolean fallen=false;
	private int fallCount=1000;
	private int controllerID;
	private Color couleur;
	private String name;
	
	public Player(World w, int startPosX, int startPosY, int startDirection, AppPlayer appPlayer) {
		this.controllerID = appPlayer.getControllerID();
		this.couleur = AppPlayer.FILL_COLORS[appPlayer.getColorID()];
		this.name = appPlayer.getName();
		this.w=w;
		posX = startPosX;
		posY = startPosY;
		direction = startDirection;
		size = w.taille/(w.platform.getSize());
		x=(posX)*size;
		x2=(posX+1)*size;
		y=(posY)*size;
		y2=(posY+1)*size;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// TODO
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!fallen) {
			AppInput appInput = (AppInput) container.getInput();
			move(appInput);
			shoot(appInput);
		} else {
			fallCount-=delta;
		}
	}

	private void shoot(AppInput appInput) {
		if (ammo>0 && appInput.isControlPressed(AppInput.BUTTON_A,controllerID)) {
			ammo--;
			// TODO w.projectile.add(new Projectile(this,direction, x+size/2, y+size/2, size));
		}
	}

	private void move(AppInput appInput) {
		// Déplacement à gauche
		if (appInput.isControlPressed(AppInput.BUTTON_LEFT,controllerID) && !(w.platform.getCell(posX-1,posY).hasPlayer())) {
			direction=3;
			w.platform.getCell(posX,posY).setPlayer(null);
			posX=posX-1;
			w.platform.getCell(posX,posY).setPlayer(this);
			x=x-size;
		}
		// Déplacement à droite
		if (appInput.isControlPressed(AppInput.BUTTON_RIGHT,controllerID) && !(w.platform.getCell(posX+1,posY).hasPlayer())) {
			direction=1;
			w.platform.getCell(posX,posY).setPlayer(null);
			posX=posX+1;
			w.platform.getCell(posX,posY).setPlayer(this);
			x=x+size;
		}
		// Déplacement en haut
		if (appInput.isControlPressed(AppInput.BUTTON_UP,controllerID) && !(w.platform.getCell(posX,posY-1).hasPlayer())) {
			direction=2;
			w.platform.getCell(posX,posY).setPlayer(null);
			posY=posY-1;
			w.platform.getCell(posX,posY).setPlayer(this);
			y=y-size;
		}
		// Déplacement en bas
		if (appInput.isControlPressed(AppInput.BUTTON_DOWN,controllerID) && !(w.platform.getCell(posX,posY+1).hasPlayer())) {
			direction=0;
			w.platform.getCell(posX,posY).setPlayer(null);
			posY=posY+1;
			w.platform.getCell(posX,posY).setPlayer(this);
			y=y+size;
		}
		// Tombe dans le vide
		if (w.platform.getCell(posX,posY).getState()<=0) {
			fallen=true;
			ammo=0;
		}
		// Ramassage des munitions au sol
		if (w.platform.getCell(posX,posY).hasAmmo() && ammo<maxAmmo) {
			ammo++;
			w.platform.getCell(posX,posY).removeAmmo();
		}
	}
	
	public void forceMove(int dir) {
		if (dir==0 && !(w.platform.getCell(posX,posY-1).hasPlayer())) {
			w.platform.getCell(posX,posY).setPlayer(null);
			posY=posY-1;
			w.platform.getCell(posX,posY).setPlayer(this);
			y=y-size;
		} else if (dir==1 && !(w.platform.getCell(posX+1,posY).hasPlayer())) {
			w.platform.getCell(posX,posY).setPlayer(null);
			posX=posX+1;
			w.platform.getCell(posX,posY).setPlayer(this);
			x=x+size;
		} else if (dir==2 && !(w.platform.getCell(posX,posY+1).hasPlayer())) {
			w.platform.getCell(posX,posY).setPlayer(null);
			posY=posY+1;
			w.platform.getCell(posX,posY).setPlayer(this);
			y=y+size;
		} else if (dir==3 && !(w.platform.getCell(posX-1,posY).hasPlayer())) {
			w.platform.getCell(posX,posY).setPlayer(null);
			posX=posX-1;
			w.platform.getCell(posX,posY).setPlayer(this);
			x=x-size;
		}
		
	}
	
	public boolean isDead() {
		return fallCount<=0;
	}

	public Color getColor() {
		return this.couleur;
	}
	
}
