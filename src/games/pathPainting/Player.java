package games.pathPainting;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;

public class Player {
	private World w;
	private int posX;
	private int posY;
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
	private int controllerID;
	private Color couleur;
	private String name;
	private boolean blocked;
	private static Sound sound;
	
	static {
		try {
			sound = new Sound("musics/pathPainting/critical_stop.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Player(World w, int startPosX, int startPosY, AppPlayer appPlayer) {
		this.controllerID = appPlayer.getControllerID();
		this.couleur = AppPlayer.FILL_COLORS[appPlayer.getColorID()];
		this.name = appPlayer.getName();
		this.w=w;
		posX = startPosX;
		posY = startPosY;
		direction = 0;
		size = w.board.getCellSize();
		x=posX*size;
		x2=(posX+1)*size;
		y=posY*size;
		y2=(posY+1)*size;
		w.board.getCell(posX,posY).setPlayer(this);
		blocked=false;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(couleur);
		context.fillRect(x+size/4, y+size/4, size/2, size/2);
		context.setColor(Color.black);
		context.drawRect(x+size/4, y+size/4, size/2, size/2);

	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput();
		move(appInput);
		if (!blocked && (
				(posX==0 || w.board.getCell(posX-1,posY).hasPlayer()) && (posX==w.board.getColumns()-1 || w.board.getCell(posX+1,posY).hasPlayer())
				&& (posY==0 || w.board.getCell(posX,posY-1).hasPlayer()) && (posY==w.board.getRows()-1 || w.board.getCell(posX,posY+1).hasPlayer()))) {
			sound.play(1, (float) 0.5);
		}
		blocked = (blocked || (
				(posX==0 || w.board.getCell(posX-1,posY).hasPlayer()) && (posX==w.board.getColumns()-1 || w.board.getCell(posX+1,posY).hasPlayer())
				&& (posY==0 || w.board.getCell(posX,posY-1).hasPlayer()) && (posY==w.board.getRows()-1 || w.board.getCell(posX,posY+1).hasPlayer())));
	}

	private void move(AppInput appInput) {
		// Déplacement à gauche
		if (appInput.isControlPressed(AppInput.BUTTON_LEFT,controllerID) && posX>0 && (!w.board.getCell(posX-1,posY).hasPlayer())) {
			direction=3;
			forceMove(direction);
		}
		// Déplacement à droite
		if (appInput.isControlPressed(AppInput.BUTTON_RIGHT,controllerID) && posX<w.board.getColumns()-1 && (!w.board.getCell(posX+1,posY).hasPlayer())) {
			direction=1;
			forceMove(direction);
		}
		// Déplacement en haut
		if (appInput.isControlPressed(AppInput.BUTTON_UP,controllerID) && posY>0 && (!w.board.getCell(posX,posY-1).hasPlayer())) {
			direction=2;
			forceMove(direction);
		}
		// Déplacement en bas
		if (appInput.isControlPressed(AppInput.BUTTON_DOWN,controllerID) && posY<w.board.getRows()-1 && (!w.board.getCell(posX,posY+1).hasPlayer())) {
			direction=0;
			forceMove(direction);
		}
	}

	public void forceMove(int dir) {
		int nextPosX=posX+((-direction+2)%2);
		int nextPosY=posY+((-direction+1)%2);
		posX=nextPosX;
		posY=nextPosY;
		w.board.getCell(posX,posY).setPlayer(this);
		x=x+size*((-direction+2)%2);
		y=y+size*((-direction+1)%2);
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public int getControllerID() {
		return controllerID;
	}
}
