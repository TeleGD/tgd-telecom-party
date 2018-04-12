package games.aztecPyramids;

import java.io.File;

//import java.io.File;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	private int ID;
	
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
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(aztecPyramid,0,0);
		g.drawImage(aztecCalendar,1080,0);
		g.drawImage(aztecSnake,1080,610);
		g.drawString("AZTEC PYRAMID",1120,220);
		g.drawString("Score :",1150,250);
		g.drawImage(aztecHead1,500,400);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	}

	@Override
	public void keyReleased(int key, char c) {
	}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE)
			System.exit(0);
	}

}
