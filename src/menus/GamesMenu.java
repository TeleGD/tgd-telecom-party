package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

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
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		super.init (container, game);
		// this.setTitle ("INSERER TITRE ICI");
		// this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setMenu (Arrays.asList (new String [] {
			AppGame.TITLES [AppGame.GAMES_BATTLE_WORLD],
			AppGame.TITLES [AppGame.GAMES_AZTEC_PYRAMIDS_WORLD],
			"Retour"
		}));
		// this.setHint ("PRESS ENTER");
		// this.enableBlink ();
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ESCAPE:
				this.game.enterState (AppGame.MENUS_MAIN_MENU);
				break;
			default:
				super.keyPressed (key, c);
		};
	}

	@Override
	public void onOptionItemSelected (int position) {
		switch (position) {
			case 0:
				this.game.enterState (AppGame.GAMES_BATTLE_WORLD, new FadeOutTransition (), new FadeInTransition ());
				break;
			case 1:
				this.game.enterState(AppGame.GAMES_AZTEC_PYRAMIDS_WORLD);
				break;
			case 2 :
				this.game.enterState (AppGame.MENUS_MAIN_MENU);
		};
	}

}
