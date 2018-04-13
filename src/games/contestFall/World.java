package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Playable;

public class World extends BasicGameState implements Playable {

	private int id;
	private Platform platform;
	
	public World(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(Color.white);
		context.fillRect(0, 0, container.getWidth(), container.getHeight());
		platform.render(container, game, context);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		platform = new Platform(this, 15);
	}

	@Override
	public int getID() {
		return id;
	}

}
