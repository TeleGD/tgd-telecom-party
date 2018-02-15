package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GamesMenu extends Menu {

	public static int ID = 2;
	public static String name = "Mini-jeux";

	public GamesMenu () {
		super.setTitrePrincipal ("INSERER TITRE ICI");
		super.setTitreSecondaire ("SOUS TITRE");
		
		super.setItems (games.battle.World.name, "Retour");

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
				this.game.enterState (games.battle.World.ID, new FadeOutTransition (), new FadeInTransition ());
				break;
			case 1:
				this.game.enterState (MainMenu.ID);
		};
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ESCAPE:
				this.game.enterState (menus.MainMenu.ID);
				break;
			default:
				super.keyPressed (key, c);
		};
	}
	
	@Override
	public int getID () {
		return GamesMenu.ID;
	}

}