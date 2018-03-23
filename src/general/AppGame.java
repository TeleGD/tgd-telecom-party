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
