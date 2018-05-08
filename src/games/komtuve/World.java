package games.komtuve;

//import java.io.File;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	private int ID;

	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) {

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game){
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}

	public void startGame(){
	}

	public void startAgain(){
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {

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
