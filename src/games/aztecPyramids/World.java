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

	public static int ID=11;
	public static String name = "aztecPyramids";
	private Image aztecPyramid;
	private Image aztecCalendar;
	private Image aztecSnake;
	 
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		aztecPyramid = new Image("images/AztecPyramids/Aztec.jpg");
		aztecCalendar = new Image("images/AztecPyramids/aztec-calendar.jpg");
		aztecSnake = new Image("images/AztecPyramids/snake.jpg");
	
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps

	}


	public void startGame(){
	}

	public void startAgain() throws SlickException{
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(aztecPyramid,0,0);
		g.drawImage(aztecCalendar,1080,0);
		g.drawImage(aztecSnake,1080,630);
		g.drawString("AZTEC PYRAMID",1100,230);
		g.drawString("Score :",1200,240);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}

	public void keyReleased(int key, char c) {
	}


	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE)
			System.exit(0);
	}

	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}

}