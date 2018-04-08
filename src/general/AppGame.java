package general;

import java.util.ArrayList;
import java.util.List;

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
	public static final int GAMES_KOMTUVE_WORLD = 8;

	@Deprecated
	public static final int BUTTON_A = 1;
	@Deprecated
	public static final int BUTTON_B = 2;
	@Deprecated
	public static final int BUTTON_X = 4;
	@Deprecated
	public static final int BUTTON_Y = 8;
	@Deprecated
	public static final int BUTTON_L = 16;
	@Deprecated
	public static final int BUTTON_R = 32;
	@Deprecated
	public static final int BUTTON_MINUS = 64;
	@Deprecated
	public static final int BUTTON_PLUS = 128;
	@Deprecated
	public static final int BUTTON_SL = 256;
	@Deprecated
	public static final int BUTTON_SR = 512;
	// @Deprecated
	// public static final int BUTTON_ZL = 1024;
	// @Deprecated
	// public static final int BUTTON_ZR = 2048;
	// @Deprecated
	// public static final int BUTTON_UP = 4096;
	// @Deprecated
	// public static final int BUTTON_DOWN = 80192;
	// @Deprecated
	// public static final int BUTTON_LEFT = 16384;
	// @Deprecated
	// public static final int BUTTON_RIGHT = 32768;
	@Deprecated
	public static final int AXIS_YL = 0;
	@Deprecated
	public static final int AXIS_XL = 1;
	@Deprecated
	public static final int AXIS_YR = 2;
	@Deprecated
	public static final int AXIS_XR = 3;

	public static final String [] TITLES = new String [] {
		"Accueil",
		"Menu principal",
		"Menu des joueurs",
		"Mini-jeux",
		"Jeu de plateau",
		"Bataille PacMan",
		"Pyramides aztecs",
		"Pong multijoueur",
		"Comme tu veux"
	};

	@Deprecated
	public static int width = 1280;
	@Deprecated
	public static int height = 720;

	public List <AppPlayer> appPlayers;
	public List <Integer> availableColorIDs;

	public AppGame (String name) {
		super (name);
		this.appPlayers = new ArrayList <AppPlayer> ();
		this.availableColorIDs = new ArrayList <Integer> ();
		for (int i = 0, l = AppPlayer.COLOR_NAMES.length; i < l; i++) {
			this.availableColorIDs.add (i);
		}
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
		this.addState (new games.komtuve.World (AppGame.GAMES_KOMTUVE_WORLD));
		container.setShowFPS (false);
	}

}
