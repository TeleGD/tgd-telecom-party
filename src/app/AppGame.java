package app;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AppGame extends StateBasedGame {

	public static final int PAGES_WELCOME = 0;
	public static final int PAGES_GAMES = 1;
	public static final int PAGES_PLAYERS = 2;
	public static final int PAGES_PAUSE = 3;
	// public static final int PAGES_SCORES = 4;
	public static final int GAMES_TELECOM_PARTY_WORLD = 4;
	public static final int GAMES_AZTEC_PYRAMIDS_WORLD = 5;
	public static final int GAMES_BOMBERMAN_WORLD = 6;
	public static final int GAMES_CLICKER_WORLD = 7;
	public static final int GAMES_CODE_FALL_WORLD = 8;
	public static final int GAMES_KOMTUVE_WORLD = 9;
	public static final int GAMES_MAZE_WORLD = 10;
	public static final int GAMES_PACMAN_BATTLE_WORLD = 11;
	public static final int GAMES_PATH_PAINTING_WORLD = 12;
	public static final int GAMES_PONG_WORLD = 13;
	public static final int GAMES_PRECISE_LOCK_WORLD = 14;
	public static final int GAMES_REFLEX_WORLD = 15;
	public static final int GAMES_SNAKE3000_WORLD = 16;
	public static final int GAMES_T7_LASER_WORLD = 17;

	public static final String[] TITLES = new String[] {
		"Accueil",
		"Menu des jeux",
		"Menu des joueurs",
		"Pause",
		"Telecom Party",
		"Aztecs Pyramids",
		"Bomberman",
		"Clicker",
		"Code Fall",
		"Komtuve",
		"Maze",
		"Pacman Battle",
		"Path Painting",
		"Pong",
		"Precise Lock",
		"Reflex",
		"Snake3000",
		"T7 Laser"
	};

	public List<AppPlayer> appPlayers;
	public List<Integer> availableColorIDs;

	public AppGame(String name, int width, int height, boolean fullscreen) {
		super(name);
		this.appPlayers = new ArrayList<AppPlayer>();
		this.availableColorIDs = new ArrayList<Integer>();
		for (int i = 0, l = AppPlayer.COLOR_NAMES.length; i < l; i++) {
			this.availableColorIDs.add(i);
		}
		try {
			AppContainer container = new AppContainer(this, width, height, fullscreen);
			container.setTargetFrameRate(60);
			container.setVSync(true);
			container.setShowFPS(false);
			container.setIcon(AppLoader.resolve("/images/icon.png"));
			container.start();
		} catch (SlickException error) {}
	}

	@Override
	public void initStatesList(GameContainer container) {
		this.init();
	}

	public abstract void init();

	public final void poll(GameContainer container, Input i) {
		((AppState) super.getCurrentState()).poll(container, this, i);
	}

}
