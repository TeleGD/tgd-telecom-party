package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.Main;

public class WelcomeMenu extends Menu implements OnClickListener {

	public static int ID = 0;
	public static String name = "Accueil";

	private static final String CONFIRM_TEXT = "PRESS ENTER";

	private Image background;
	private int blinkPeriod;

	@Override
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		super.init (container, game);

		this.background = new Image ("images/logo.png");
		this.blinkPeriod = 10;
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {

	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {

	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) throws SlickException {
		// context.drawImage (new Image ("img/accueil.png"), 0, 0);
		context.setColor (Color.white);

		context.drawRect (Main.longueur / 2 - 300, 25, 600, 37);

		context.setFont (this.fontConfirmText);
		int alpha = (int) ((System.currentTimeMillis () / blinkPeriod) % 1000);
		if (alpha > 255) {
			alpha = 500 - alpha;
		};
		if (alpha > 500) {
			alpha = 0;
		};
		context.setColor (new Color (255 - alpha, 255 - alpha, 255 - alpha));
		context.drawString (WelcomeMenu.CONFIRM_TEXT, Main.longueur / 2 - this.fontConfirmText.getWidth (WelcomeMenu.CONFIRM_TEXT) / 2, 35);
		context.drawImage (this.background, Main.longueur / 2 - this.background.getWidth () / 2, Main.hauteur / 2 - this.background.getHeight () / 2);

		context.setColor (Color.white);
	}

	@Override
	public void onOptionItemFocusedChanged (int position){
		this.time = System.currentTimeMillis ();
	}

	@Override
	public void onOptionItemSelected (int position) {
		this.game.enterState (MainMenu.ID, new FadeOutTransition (), new FadeInTransition ());
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ENTER:
				onOptionItemSelected (0);
				break;
			case Input.KEY_ESCAPE:
				System.exit (0);
				break;
			default:
				super.keyPressed (key, c);
		};
	}

	@Override
	public void onClick (TGDComponent component) {

	}

	@Override
	public int getID () {
		return WelcomeMenu.ID;
	}

}
