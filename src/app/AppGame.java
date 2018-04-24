package app;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class AppGame extends StateBasedGame {

	public static final int PAGES_WELCOME = 0;
	public static final int PAGES_TITLES = 1;
	public static final int PAGES_GAMES = 2;
	public static final int PAGES_PLAYERS = 3;
	public static final int HUB_WORLD = 4;
	public static final int GAMES_AZTECPYRAMIDS_WORLD = 5;
	public static final int GAMES_BATTLE_WORLD = 6;
	public static final int GAMES_BOMBERMAN_WORLD = 7;
	public static final int GAMES_CLICKER_WORLD = 8;
	public static final int GAMES_CODEFALL_WORLD = 9;
	public static final int GAMES_KOMTUVE_WORLD = 10;
	public static final int GAMES_MAZE_WORLD = 11;
	public static final int GAMES_PATHPAINTING_WORLD = 12;
	public static final int GAMES_PONG_WORLD = 13;
	public static final int GAMES_PRECISELOCK_WORLD = 14;
	public static final int GAMES_REFLEX_WORLD = 15;
	public static final int GAMES_SNAKE_WORLD = 16;
	public static final int GAMES_T7LASER_WORLD = 17;

	public static final String [] TITLES = new String [] {
		"Accueil",
		"Titres",
		"Mini-jeux",
		"Joueurs",
		"Jeu de plateau",
		"Pyramides aztecs",
		"Bataille PacMan",
		"Bomberman",
		"Clicker",
		"Code Fall",
		"Comme tu veux",
		"Labyrinthe",
		"Path Painting",
		"Pong multijoueur",
		"Precise lock",
		"Reflex",
		"Snake",
		"T7Laser Remake"
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
		this.addState (new pages.Welcome (AppGame.PAGES_WELCOME));
		this.addState (new pages.Titles (AppGame.PAGES_TITLES));
		this.addState (new pages.Games (AppGame.PAGES_GAMES));
		this.addState (new pages.Players (AppGame.PAGES_PLAYERS));
		this.addState (new hub.World (AppGame.HUB_WORLD));
		this.addState (new games.aztecPyramids.World (AppGame.GAMES_AZTECPYRAMIDS_WORLD));
		this.addState (new games.battle.World (AppGame.GAMES_BATTLE_WORLD));
		this.addState (new games.bomberman.World (AppGame.GAMES_BOMBERMAN_WORLD));
		this.addState (new games.clicker.World (AppGame.GAMES_CLICKER_WORLD));
		this.addState (new games.codeFall.World (AppGame.GAMES_CODEFALL_WORLD));
		this.addState (new games.komtuve.World (AppGame.GAMES_KOMTUVE_WORLD));
		this.addState (new games.maze.World (AppGame.GAMES_MAZE_WORLD));
		this.addState (new games.pathPainting.World (AppGame.GAMES_PATHPAINTING_WORLD));
		this.addState (new games.pong.World (AppGame.GAMES_PONG_WORLD));
		this.addState (new games.preciseLock.World (AppGame.GAMES_PRECISELOCK_WORLD));
		this.addState (new games.reflex.World (AppGame.GAMES_REFLEX_WORLD));
		this.addState (new games.snake.World (AppGame.GAMES_SNAKE_WORLD));
		this.addState (new games.t7Laser.World (AppGame.GAMES_T7LASER_WORLD));
	}

}
