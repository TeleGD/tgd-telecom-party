package games.reflex;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppWorld;
import app.utils.FontUtils;

public class World extends AppWorld {

	public static final Font BUTTON_FONT = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 18, true);
	protected final static int GOAL=20;
	private ArrayList<Player> players;

	public World (int ID) {
		super (ID);
	}

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
			context.fillRect(0, 550, 1280, 170);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for (Player p:players) {
			p.update(container, game, delta);
		}

	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		this.players = new ArrayList <Player> ();
		int n = ((AppGame) game).appPlayers.size ();
		for (int i = 0; i < n; i++) {
			this.players.add (new Player (((AppGame) game).appPlayers.get (i), n, i));
		};
	}

}
