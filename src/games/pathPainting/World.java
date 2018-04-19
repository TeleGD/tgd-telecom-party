package games.pathPainting;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppWorld;
import app.utils.FontUtils;

public class World extends AppWorld {

	private int id;

	public final static String GAME_FOLDER_NAME="pathPaint";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static TrueTypeFont Font = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 20, true);

	Board board;
	private int width;
	private int height;
	private ArrayList<Player> players;

	public World(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		board.render(container, game, context);
		for (Player p : players) {
			p.render(container, game, context);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (app.AppGame.PAGES_GAMES, new FadeOutTransition (), new FadeInTransition ());
		} else {
			for (Player p : players) {
				p.update(container, game, delta);
			}
		}
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		width = container.getWidth();
		height = container.getHeight();
		int boardMinSize = 12;
		board = new Board(this, boardMinSize);

		AppGame appGame = (AppGame) game;
		this.players = new ArrayList<Player>();
		int w = board.getColumns ();
		int h = board.getRows ();
		for (int i = 0; i < appGame.appPlayers.size(); i++) {
			this.players.add(new Player(this, (-i >> 1 & 1) * (w-1), (i & 1) * (h-1), appGame.appPlayers.get(i)));
		}
	}

	@Override
	public int getID() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
