package games.reflex;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.AppGame;
import general.Playable;

public class World extends BasicGameState implements Playable {

	private int ID;
	public final static int GOAL=20;
	private ArrayList<Player> players;

	public World (int ID) {
		this.ID = ID;
	};

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void enter(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) throws SlickException {
		for (Player p:players) {
			p.render(container, game, context);
			context.setBackground(Color.lightGray);
			context.setColor(Color.black);
			context.drawRect(0, 550, 1280, 170);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void initPlayers (GameContainer container, StateBasedGame game) {
		this.players = new ArrayList <Player> ();
		int n = ((AppGame) game).appPlayers.size ();
		for (int i = 0; i < n; i++) {
			this.players.add (new Player (((AppGame) game).appPlayers.get (i), n, i));
		};
	}

}
