package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

public class WelcomeMenu extends Menu {

	private int ID;

	private String confirmText;
	private Image background;
	private int blinkPeriod;

	public WelcomeMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		super.init (container, game);

		this.confirmText = "PRESS ENTER";
		this.background = new Image ("images/logo.png");
		this.blinkPeriod = 10;
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) throws SlickException {
		Color previousColor = context.getColor ();
		Font previousFont = context.getFont ();

		int width = container.getWidth ();
		int height = container.getHeight ();

		context.setColor (Color.white);

		context.drawRect (width / 2 - 300, 25, 600, 37);

		context.setFont (Menu.hintFont);
		int alpha = (int) ((System.currentTimeMillis () / blinkPeriod) % 1000);
		if (alpha > 255) {
			alpha = 500 - alpha;
		};
		if (alpha > 500) {
			alpha = 0;
		};
		context.setColor (new Color (255 - alpha, 255 - alpha, 255 - alpha));
		context.drawString (this.confirmText, width / 2 - Menu.hintFont.getWidth (this.confirmText) / 2, 35);
		context.drawImage (this.background, width / 2 - this.background.getWidth () / 2, height / 2 - this.background.getHeight () / 2);

		context.setColor (previousColor);
		context.setFont (previousFont);
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ENTER:
				this.onOptionItemSelected (0);
				break;
			case Input.KEY_ESCAPE:
				System.exit (0);
				break;
			default:
				super.keyPressed (key, c);
		};
	}

	@Override
	public void onOptionItemSelected (int position) {
		this.game.enterState (AppGame.MENUS_MAIN_MENU, new FadeOutTransition (), new FadeInTransition ());
	}

}
