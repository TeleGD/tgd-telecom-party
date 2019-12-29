package games.pathPainting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppGame;
import app.AppInput;
import app.AppLoader;
import app.AppWorld;

public class World extends AppWorld {

	public final static String GAME_FOLDER_NAME="pathPainting";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static TrueTypeFont Font = AppLoader.loadFont("/fonts/vt323.ttf", AppFont.BOLD, 20);

	Board board;
	private int width;
	private int height;
	private ArrayList<Player> players;

	private static Music music;

	static {
		try {
			music = new Music(DIRECTORY_MUSICS + "Akira_vs_Konono.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public World (int ID) {
		super (ID);
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
		super.update (container, game, delta);
		int blockedPlayers=0;
		for (Player p : players) {
			p.update(container, game, delta);
			blockedPlayers+=p.isBlocked()?1:0;
		}
		if (blockedPlayers==players.size()) {
			// fin de la partie
			music.stop();
			int[] scores = board.countScore(players);
			HashMap<Integer,Integer> classement = new HashMap<Integer,Integer>();
			for (int i=0 ; i<players.size();i++) {
				classement.put(players.get(i).getControllerID(),scores[i]);
			}
			System.out.println(classement.toString());
		}
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		width = container.getWidth();
		height = container.getHeight();
		int boardMinSize = 12;
		board = new Board(this, boardMinSize);

		AppGame appGame = (AppGame) game;
		AppInput appInput = (AppInput) container.getInput ();
		appInput.clearKeyPressedRecord ();
		appInput.clearControlPressedRecord ();
		this.players = new ArrayList<Player>();
		int w = board.getColumns ();
		int h = board.getRows ();
		for (int i = 0; i < appGame.appPlayers.size(); i++) {
			this.players.add(new Player(this, (-i >> 1 & 1) * (w-1), (i & 1) * (h-1), appGame.appPlayers.get(i)));
		}
		music.loop(1, (float) 0.5);
	}

	@Override
	public void pause (GameContainer container, StateBasedGame game) {
		music.pause ();
	}

	@Override
	public void resume (GameContainer container, StateBasedGame game) {
		music.resume ();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
