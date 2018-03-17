package menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.EmptyImageData;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;

import menus.ui.Page;

public class WelcomeMenu extends Page {

	private int ID;

	private Image logo;

	private boolean logoVisibility;

	protected int logoBoxWidth;
	protected int logoBoxHeight;
	protected int logoBoxX;
	protected int logoBoxY;

	private int logoWidth;
	private int logoHeight;
	private int logoX;
	private int logoY;

	private int naturalWidth;
	private int naturalHeight;

	public WelcomeMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, container.getHeight () - Page.gap * 2);
		super.init (container, game);

		this.logoBoxX = this.contentX;
		this.logoBoxY = this.subtitleBoxY + this.subtitleBoxHeight + Page.gap;
		this.logoBoxWidth = this.contentWidth;
		this.logoBoxHeight = this.hintBoxY - this.logoBoxY - Page.gap;

		this.logoVisibility = true;

		// this.titleVisibility = false;
		// this.subtitleVisibility = false;
		this.hintBlink = true;

		this.setTitle ("TeleGame Design");
		this.setSubtitle ("Projet_2018 #NoName");
		this.setHint ("PRESS ENTER");
		Image logo;
		try {
			logo = new Image ("images/logo.png");
		} catch (SlickException exception) {
			logo = new Image (new EmptyImageData (0, 0));
		};
		this.setLogo (logo);
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int  delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			System.exit (0);
		} else if (input.isKeyPressed (Input.KEY_ENTER)) {
			game.enterState (AppGame.MENUS_MAIN_MENU, new FadeOutTransition (), new FadeInTransition ());
		};
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
		this.renderLogo (container, game, context);
	}

	private void renderLogo (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.logoVisibility) {
			context.drawImage (
				this.logo,
				this.logoX,
				this.logoY,
				this.logoX + this.logoWidth,
				this.logoY + this.logoHeight,
				0,
				0,
				this.naturalWidth,
				this.naturalHeight
			);
		};
	}

	public void setLogo (Image logo) {
		this.logo = logo.copy ();
		this.naturalWidth = logo.getWidth ();
		this.naturalHeight = logo.getHeight ();
		this.logoWidth = Math.min (this.logoBoxWidth, this.naturalWidth);
		this.logoHeight = Math.min (this.logoBoxHeight, this.naturalHeight);
		if (this.logoWidth * this.naturalHeight < this.naturalWidth * this.logoHeight) {
			this.logoHeight = this.naturalHeight * this.logoWidth / this.naturalWidth;
		} else {
			this.logoWidth = this.naturalWidth * this.logoHeight / this.naturalHeight;
		};
		this.logoX = this.logoBoxX + (this.logoBoxWidth - this.logoWidth) / 2;
		this.logoY = this.logoBoxY + (this.logoBoxHeight - this.logoHeight) / 2;
	}

	public Image getLogo () {
		return logo.copy ();
	}

}
