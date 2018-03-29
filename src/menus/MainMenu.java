package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

import menus.ui.Menu;
import menus.ui.MenuItem;

public class MainMenu extends Menu {

	private int ID;

	public MainMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("INSERER TITRE ICI");
		this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.HUB_WORLD_PLATEAU]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_MAIN_MENU);
					playersMenu.setNextID (AppGame.HUB_WORLD_PLATEAU);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				};
			},
			new MenuItem (AppGame.TITLES [AppGame.MENUS_GAMES_MENU]) {
				public void itemSelected () {
					game.enterState (AppGame.MENUS_GAMES_MENU);
				};
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					AppGame appGame = (AppGame) game;
					for (int i = appGame.appPlayers.size () - 1; i >= 0; i--) {
						appGame.availableColorIDs.add (0, appGame.appPlayers.remove (i).getColorID ());
						appGame.appPlayersControls.remove (i);
					};
					game.enterState (AppGame.MENUS_WELCOME_MENU, new FadeOutTransition (), new FadeInTransition ());
				};
			}
		}));
		this.setHint ("PRESS ENTER");
	}

}
