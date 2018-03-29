package general;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class AppGame extends StateBasedGame {

	public static final int MENUS_WELCOME_MENU = 0;
	public static final int MENUS_MAIN_MENU = 1;
	public static final int MENUS_PLAYERS_MENU = 2;
	public static final int MENUS_GAMES_MENU = 3;
	public static final int HUB_WORLD_PLATEAU = 4;
	public static final int GAMES_BATTLE_WORLD = 5;
	public static final int GAMES_AZTEC_PYRAMIDS_WORLD = 6;
	public static final int GAMES_PONG_MULTI_WORLD = 7;

	// TODO: remapping
	public static final int BUTTON_A = 0;
	public static final int BUTTON_B = 1;
	public static final int BUTTON_X = 2;
	public static final int BUTTON_Y = 3;
	public static final int BUTTON_L = 4;
	public static final int BUTTON_R = 5;
	public static final int BUTTON_MINUS = 6;
	public static final int BUTTON_PLUS = 7;
	public static final int BUTTON_SL = 8;
	public static final int BUTTON_SR = 9;
	// public static final int BUTTON_ZL = 10;
	// public static final int BUTTON_ZR = 11;
	// public static final int BUTTON_UP = 12;
	// public static final int BUTTON_DOWN = 13;
	// public static final int BUTTON_LEFT = 14;
	// public static final int BUTTON_RIGHT = 15;
	public static final int AXIS_YL = 0;
	public static final int AXIS_XL = 1;
	public static final int AXIS_YR = 2;
	public static final int AXIS_XR = 3;

	public static final String [] TITLES = new String [] {
		"Accueil",
		"Menu principal",
		"Menu des joueurs",
		"Mini-jeux",
		"Jeu de plateau",
		"Bataille PacMan",
		"Pyramides aztecs",
		"Pong multijoueur"
	};

	public static int width = 1280;
	public static int height = 720;

	public AppGame (String name) {
		super (name);
	}

	@Override
	public void initStatesList (GameContainer container) {
		this.addState (new menus.WelcomeMenu (AppGame.MENUS_WELCOME_MENU));
		this.addState (new menus.MainMenu (AppGame.MENUS_MAIN_MENU));
		this.addState (new menus.PlayersMenu (AppGame.MENUS_PLAYERS_MENU));
		this.addState (new menus.GamesMenu (AppGame.MENUS_GAMES_MENU));
		this.addState (new hub.WorldPlateau (AppGame.HUB_WORLD_PLATEAU));
		this.addState (new games.battle.World (AppGame.GAMES_BATTLE_WORLD));
		this.addState (new games.aztecPyramids.World (AppGame.GAMES_AZTEC_PYRAMIDS_WORLD));
		this.addState (new games.pong.World (AppGame.GAMES_PONG_MULTI_WORLD));
		container.setShowFPS (false);
	}

}
