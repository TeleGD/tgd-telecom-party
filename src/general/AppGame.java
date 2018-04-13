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
	public static final int GAMES_T7LASER_WORLD =9;
	public static final int GAMES_SNAKE_WORLD = 10;
	public static final int GAMES_CLICKER_WORLD = 11;
	public static final int GAMES_REFLEX_WORLD = 12;
	public static final int GAMES_FALL_WORLD = 13;
	public static final int TESTER_WORLD = 14;

	public static final String [] TITLES = new String [] {
		"Accueil",
		"Menu principal",
		"Menu des joueurs",
		"Mini-jeux",
		"Jeu de plateau",
		"Bataille PacMan",
		"Pyramides aztecs",
		"Pong multijoueur",
		"Comme tu veux",
		"T7Laser Remake",
		"Snake",
		"Clicker",
		"Reflex",
		"Contest Fall",
		"Gamepad Tester"
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
		this.addState (new games.t7Laser.World (AppGame.GAMES_T7LASER_WORLD));
		this.addState (new games.snake.World (AppGame.GAMES_SNAKE_WORLD));
		this.addState (new games.clicker.World (AppGame.GAMES_CLICKER_WORLD));
		this.addState (new games.reflex.World (AppGame.GAMES_REFLEX_WORLD));
		this.addState (new games.contestFall.World (AppGame.GAMES_FALL_WORLD));
		this.addState (new tester.World (AppGame.TESTER_WORLD));
		container.setShowFPS (false);
	}

}
