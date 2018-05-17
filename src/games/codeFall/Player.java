package games.codeFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;

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
		x=w.startX+(posX)*size;
		x2=w.startX+(posX+1)*size;
		y=w.startY+(posY)*size;
		y2=w.startY+(posY+1)*size;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(couleur);
		context.fillRect(x+size/4, y+size/4, size/2, size/2);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!fallen) {
			AppInput appInput = (AppInput) container.getInput();
			move(appInput);
			shoot(appInput);
			appInput.clearControlPressedRecord();
		} else {
			fallCount-=delta;
		}
	}

	private void shoot(AppInput appInput) {
		if (ammo>0 && appInput.isControlPressed(AppInput.BUTTON_A,controllerID)) {
			ammo--;
			w.projectiles.add(new Projectile(w, this,direction, x+size/2, y+size/2, size));
		}
	}

	private void move(AppInput appInput) {
		// Déplacement à gauche
		if (appInput.isControlPressed(AppInput.BUTTON_LEFT,controllerID) && !(w.platform.getCell(posX-1,posY).hasPlayer())) {
			direction=3;
			forceMove(direction);
		}
		// Déplacement à droite
		if (appInput.isControlPressed(AppInput.BUTTON_RIGHT,controllerID) && !(w.platform.getCell(posX+1,posY).hasPlayer())) {
			direction=1;
			forceMove(direction);
		}
		// Déplacement en haut
		if (appInput.isControlPressed(AppInput.BUTTON_UP,controllerID) && !(w.platform.getCell(posX,posY-1).hasPlayer())) {
			direction=2;
			forceMove(direction);
		}
		// Déplacement en bas
		if (appInput.isControlPressed(AppInput.BUTTON_DOWN,controllerID) && !(w.platform.getCell(posX,posY+1).hasPlayer())) {
			direction=0;
			forceMove(direction);
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
		int nextPosX=posX+((-direction+2)%2);
		int nextPosY=posY+((-direction+1)%2);
		if (!w.platform.getCell(nextPosX,nextPosY).hasPlayer()) {
			w.platform.getCell(posX,posY).setPlayer(null);
			posX=nextPosX;
			posY=nextPosY;
			w.platform.getCell(posX,posY).setPlayer(this);
			x=x+size*((-direction+2)%2);
			y=y+size*((-direction+1)%2);
		}
	}

	public boolean isDead() {
		return fallCount<=0;
	}

	public Color getColor() {
		return this.couleur;
	}
	
	public int getControllerID() {
		return this.controllerID;
	}

}
