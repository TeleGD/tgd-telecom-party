package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

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
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		super.init (container, game);
		// this.setTitle ("INSERER TITRE ICI");
		// this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.HUB_WORLD_PLATEAU]) {
				public void itemSelected () {
					game.enterState (AppGame.HUB_WORLD_PLATEAU, new FadeOutTransition (), new FadeInTransition ());
				};
			},
			new MenuItem (AppGame.TITLES [AppGame.MENUS_GAMES_MENU]) {
				public void itemSelected () {
					game.enterState (AppGame.MENUS_GAMES_MENU);
				};
			},
			new MenuItem ("Quitter") {
				public void itemSelected () {
					System.exit (0);
				};
			}
		}));
		// this.setHint ("PRESS ENTER);
		// this.enableBlink ();
	}

}
