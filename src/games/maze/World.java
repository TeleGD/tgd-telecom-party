package games.maze;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppWorld;

import games.maze.players.HunterPlayer;
import games.maze.players.VictimPlayer;

public class World extends AppWorld {

	private int ID;

	public final static String GAME_FOLDER_NAME = "maze";
	public final static String DIRECTORY_SOUNDS = "musics" + File.separator + GAME_FOLDER_NAME + File.separator;
	public final static String DIRECTORY_MUSICS = "musics" + File.separator + GAME_FOLDER_NAME + File.separator;
	public final static String DIRECTORY_IMAGES = "images" + File.separator + GAME_FOLDER_NAME + File.separator;
	public Board board;
	public ArrayList <Player> players;
	private int height;
	private int width;
	private int timer;

	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size ();
		height = container.getHeight ();
		width = container.getWidth ();
		board = new Board (this, 50);
		players = new ArrayList <Player> ();
		players.add (new VictimPlayer (this, appGame.appPlayers.get (0)));
		for (int i = 1; i < n; i++) {
			players.add (new HunterPlayer (this, appGame.appPlayers.get (i)));
		}
		timer = 90000; //temps de la partie (90s)
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {}

	@Override
	public void leave (GameContainer container, StateBasedGame game) {}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE)) {
			appGame.enterState (AppGame.PAGES_GAMES, new FadeOutTransition (), new FadeInTransition ());
		} else {
			board.update (container, game, delta);
			for (Player p: players) {
				p.update (container, game, delta);
			}
		}
		timer -= delta;
		if (timer <= 0) {
			endGame (players.get (0));
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		board.render (container, game, context);
		for (Player p: players) {
			p.render (container, game, context);
		}
	}

	public void endGame (Player p) {
		System.out.println ("Le player " + p.getID () + " a gagné");
		// Met fin au jeu
		// le joueur p est le gagnant
	}

	public int getHeight () {
		return height;
	}
	public int getWidth () {
		return width;
	}

}
