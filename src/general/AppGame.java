package general;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class AppGame extends StateBasedGame {

	public static final int MENUS_WELCOME_MENU = 0;
	public static final int MENUS_MAIN_MENU = 1;
	public static final int MENUS_GAMES_MENU = 2;
	public static final int HUB_WORLD_PLATEAU = 3;
	public static final int GAMES_BATTLE_WORLD = 4;
	public static final int GAMES_AZTEC_PYRAMIDS_WORLD = 5;

	public static final String [] TITLES = {
		"Accueil",
		"Menu principal",
		"Mini-jeux",
		"Jeu de plateau",
		"Bataille PacMan",
		"Pyramides aztecs"
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
		this.addState (new menus.GamesMenu (AppGame.MENUS_GAMES_MENU));
		this.addState (new hub.WorldPlateau (AppGame.HUB_WORLD_PLATEAU));
		this.addState (new games.battle.World (AppGame.GAMES_BATTLE_WORLD));
		this.addState (new games.aztecPyramids.World (AppGame.GAMES_AZTEC_PYRAMIDS_WORLD));
		container.setShowFPS (false);
	}

}
