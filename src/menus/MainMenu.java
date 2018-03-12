package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends Menu {

	public static int ID = 1;
	public static String name = "Menu principal";

	public MainMenu () {
		super.setTitrePrincipal ("INSERER TITRE ICI");
		super.setTitreSecondaire ("SOUS TITRE");

		super.setItems (hub.WorldPlateau.name, menus.GamesMenu.name, "Quitter");

		super.setEnableClignote (false);
		super.setCouleurClignote (Color.red);
		super.setTempsClignote (400);
	}

	@Override
	public void onOptionItemFocusedChanged (int position) {
		this.time = System.currentTimeMillis ();
	}

	@Override
	public void onOptionItemSelected (int position) {
		switch (position) {
			case 0:
				this.game.enterState (hub.WorldPlateau.ID, new FadeOutTransition (), new FadeInTransition ());
				break;
			case 1:
				this.game.enterState (menus.GamesMenu.ID);
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

	@Override
	public int getID () {
		return MainMenu.ID;
	}

}
