package games.aztecPyramids;

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

	private Image aztecPyramid;
	private Image aztecCalendar;
	private Image aztecSnake;
	private Image aztecHead1;
	private Image aztecHead2;
	private Image aztecHead3;
	private Image aztecHead4;

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
			aztecPyramid = new Image("images/AztecPyramids/aztec.jpg");
			aztecCalendar = new Image("images/AztecPyramids/aztec-calendar.jpg");
			aztecSnake = new Image("images/AztecPyramids/snake.jpg");
			aztecHead1 = new Image("images/AztecPyramids/bluehead.png");
			aztecHead2 = new Image("images/AztecPyramids/pinkhead.png");
			aztecHead3 = new Image("images/AztecPyramids/yellowhead.png");
			aztecHead4 = new Image("images/AztecPyramids/greenhead.png");
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
