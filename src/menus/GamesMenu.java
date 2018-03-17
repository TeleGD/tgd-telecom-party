package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

import menus.ui.Menu;
import menus.ui.MenuItem;

public class GamesMenu extends Menu {

	private int ID;

	public GamesMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.init (container, game);
		this.setTitle ("INSERER TITRE ICI");
		this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.GAMES_BATTLE_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_BATTLE_WORLD, new FadeOutTransition (), new FadeInTransition ());
				};
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_AZTEC_PYRAMIDS_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_AZTEC_PYRAMIDS_WORLD);
				};
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					game.enterState (AppGame.MENUS_MAIN_MENU);
				};
			}
		}));
		this.setHint ("PRESS ENTER");
		// this.enableBlink ();
	}

}
