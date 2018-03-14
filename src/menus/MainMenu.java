package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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
		this.setMenu (Arrays.asList (new String [] {
			AppGame.TITLES [AppGame.HUB_WORLD_PLATEAU],
			AppGame.TITLES [AppGame.MENUS_GAMES_MENU],
			"Quitter"
		}));
		// this.setHint ("PRESS ENTER);
		// this.enableBlink ();
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ESCAPE:
				System.exit (0);
				break;
			default:
				super.keyPressed (key, c);
		};
	}

	@Override
	public void onOptionItemSelected (int position) {
		switch (position) {
			case 0:
				this.game.enterState (AppGame.HUB_WORLD_PLATEAU, new FadeOutTransition (), new FadeInTransition ());
				break;
			case 1:
				this.game.enterState (AppGame.MENUS_GAMES_MENU);
				break;
			case 2:
				System.exit (0);
		};
	}

}
