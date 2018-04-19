package games.aztecPyramids;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	private int ID;
	private int compteur;

	public final static String GAME_FOLDER_NAME="aztecPyramids";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;

	private static Image aztecPyramid;
	private static Image aztecCalendar;
	private static Image aztecSnake;
	private static Image aztecHead1;
	private static Image aztecHead2;
	private static Image aztecHead3;
	private static Image aztecHead4;

	public Player tabPlay[];

	static {
		try {
			aztecPyramid = new Image(DIRECTORY_IMAGES+"aztec.jpg");
			aztecCalendar = new Image(DIRECTORY_IMAGES+"aztec-calendar.jpg");
			aztecSnake = new Image(DIRECTORY_IMAGES+"snake.jpg");
			aztecHead1 = new Image(DIRECTORY_IMAGES+"bluehead.png");
			aztecHead2 = new Image(DIRECTORY_IMAGES+"pinkhead.png");
			aztecHead3 = new Image(DIRECTORY_IMAGES+"yellowhead.png");
			aztecHead4 = new Image(DIRECTORY_IMAGES+"greenhead.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		this.tabPlay = new Player [] {new Player(this, Input.KEY_LEFT,Input.KEY_UP,Input.KEY_RIGHT),new Player(this, Input.KEY_A,Input.KEY_Z,Input.KEY_E)};
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}

	public void startGame(){
	}

	public void startAgain() throws SlickException{
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		compteur += delta;

		if (compteur == 5000) {
			for (int j=0;j<this.tabPlay.length;j++){
				this.tabPlay[j].keyEnabled();
			}
		}
		if (compteur == 16500) {
			for (int j=0;j<this.tabPlay.length;j++){
				this.tabPlay[j].keyDisabled();
			}
		}

		if (compteur >= 20000) {
			for (int j=0;j<this.tabPlay.length;j++){
				this.tabPlay[j].valueChange();
				this.tabPlay[j].afterChange();
			}
			for (int j=0;j<this.tabPlay.length;j++){
				for (int i=0;i<this.tabPlay.length;i++){
					if (i!=j){
						this.tabPlay[j].isEqual(this.tabPlay[i]);
					}
			}

		}
			for (int j=0;j<this.tabPlay.length;j++){
				this.tabPlay[j].finalChange();
			}
			for (int j=0;j<this.tabPlay.length;j++){
				this.tabPlay[j].climb();
				compteur =0;
			}

		}
		if (compteur == 1) {
		for (int j=0;j<this.tabPlay.length;j++){
			this.tabPlay[j].isWinner();
			}
		}
		}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(aztecPyramid,0,0);
		g.drawImage(aztecCalendar,1080,0);
		g.drawImage(aztecSnake,1080,610);
		g.drawString("AZTEC PYRAMID",1120,220);
		g.drawString("Score :",1150,250);
		g.drawImage(aztecHead1,500,600-this.tabPlay[0].getFloor()*40);
		g.drawImage(aztecHead2,480,600-this.tabPlay[1].getFloor()*40);
		g.drawString(Integer.toString(this.tabPlay[0].getFloor()),1150,450);
		g.drawString(Integer.toString(compteur),1150,350);
		if (compteur >= 3000 && compteur <= 3500) {
			g.drawString("3",500,250);
		}
		if (compteur >= 3500 && compteur <= 4000) {
			g.drawString("2",500,250);
		}
		if (compteur >= 4000 && compteur <= 4500) {
			g.drawString("1",500,250);
		}
		if (compteur >= 4500 && compteur <= 5000) {
			g.drawString("Choisissez",500,250);
		}
		if (compteur >= 14000 && compteur <= 14500) {
			g.drawString("3",500,250);
		}
		if (compteur >= 14500 && compteur <= 15000) {
			g.drawString("2",500,250);
		}
		if (compteur >= 15000 && compteur <= 15500) {
			g.drawString("1",500,250);
		}
		if (compteur >= 15500 && compteur <= 16000) {
			g.drawString("Terminé",500,250);
		}

	}

	@Override
	public void keyReleased(int key, char c) {
	}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE){
			System.exit(0);
			}
		for (int j=0;j<this.tabPlay.length;j++){
			this.tabPlay[j].keyPressed(key,c);
		}

	}

}
