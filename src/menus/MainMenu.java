package menus;

import org.newdawn.slick.Color;
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

		this.setTitrePrincipal ("INSERER TITRE ICI");
		this.setTitreSecondaire ("SOUS TITRE");

		this.setItems (AppGame.TITLES [AppGame.HUB_WORLD_PLATEAU], AppGame.TITLES [AppGame.MENUS_GAMES_MENU], "Quitter");

		this.setEnableClignote (false);
		this.setCouleurClignote (Color.red);
		this.setTempsClignote (400);
	}

	@Override
	public void onOptionItemFocusedChanged (int position) {
		this.time = System.currentTimeMillis ();
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

}
