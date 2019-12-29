package games.komtuve;

//import java.io.File;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppState;

public class World extends AppState {

	public World(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE)
			System.exit(0);
	}

}
