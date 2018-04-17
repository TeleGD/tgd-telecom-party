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
		// TODO Auto-generated method stub
		
	}

	private void move(AppInput appInput) {
		// Déplacement à gauche
		if (appInput.isControlPressed(AppInput.BUTTON_LEFT,controllerID) && !(w.platform.getCell(posX-1,posY).hasPlayer())) {
			posX=posX-1;
			x=x-size;
		}
		// Déplacement à droite
		if (appInput.isControlPressed(AppInput.BUTTON_RIGHT,controllerID) && !(w.platform.getCell(posX+1,posY).hasPlayer())) {
			posX=posX+1;
			x=x+size;
		}
		// Déplacement en haut
		if (appInput.isControlPressed(AppInput.BUTTON_UP,controllerID) && !(w.platform.getCell(posX,posY-1).hasPlayer())) {
			posY=posY-1;
			y=y-size;
		}
		// Déplacement en bas
		if (appInput.isControlPressed(AppInput.BUTTON_DOWN,controllerID) && !(w.platform.getCell(posX,posY+1).hasPlayer())) {
			posY=posY+1;
			y=y+size;
		}
		// Ramassage des munitions au sol
		if (w.platform.getCell(posX,posY).hasAmmo() && ammo<maxAmmo) {
			ammo++;
			w.platform.getCell(posX,posY).removeAmmo();
		}
	}
	
	public boolean isDead() {
		return fallCount<=0;
	}
}
