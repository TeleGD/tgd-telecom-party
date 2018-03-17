package menus;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

import menus.ui.Menu;
import menus.ui.MenuItem;

public class WelcomeMenu extends Menu {

	private int ID;

	private String confirmText;
	private Image background;
	private int blinkPeriod;

	protected StateBasedGame game;

	public WelcomeMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.init (container, game);
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.HUB_WORLD_PLATEAU]) {
				public void itemSelected () {
					game.enterState (AppGame.MENUS_MAIN_MENU, new FadeOutTransition (), new FadeInTransition ());
				};
			}
		}));
		this.confirmText = "PRESS ENTER";
		try {
			this.background = new Image ("images/logo.png");
		} catch (SlickException exception) {
			this.background = null;
		};
		this.blinkPeriod = 10;
		this.game = game;
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
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

}
