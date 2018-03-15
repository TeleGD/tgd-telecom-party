package menus;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.utils.FontUtils;

/**
 *
 * Pour faire un menu c'est simple, il suffit de faire une classe qui herite de
 * celle-la et de personnaliser via les setters setTitle, setSubtitle setMenu et
 * setHint.
 * Et c'est tout.
 * Vous recevrez l'index de l'item selectionné dans la méthode
 * onOptionItemSelected.
 *
		super.setTitle ("TELE-ARCADE DESIGN");
		super.setSubtitle ("Menu Principal");
		super.setMenu ("Jouer", "Editeur", "Quitter");
		super.enableBlink ();
 *
 *
 *@author Jérôme, Tristan
 */

public abstract class Menu extends BasicGameState {

	static private Color foregroundColor = Color.white;
	static private Color backgroundColor = Color.black;
	static private Color highlightColor = Color.red;

	static private Font titleFont = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.BOLD, 40, false);
	static private Font subtitleFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 24, true);
	static private Font menuFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);
	static protected Font hintFont = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.PLAIN, 20, false); // TODO: private ?

	private String title;
	private String subtitle;
	private List <MenuItem> menu;
	private String hint;

	private int titleWidth;
	private int subtitleWidth;
	private int menuWidth;
	private int hintWidth;

	// private int titleHeight;
	// private int subtitleHeight;
	// private int menuHeight;
	// private int hintHeight;

	private int boxWidth;
	private int boxHeight;

	private boolean blinkEnabled;
	private int blinkPeriod;
	private int blinkCountdown;

	private int menuHeight;
	private int startMenuX;
	private int startMenuY;
	private int endMenuY;
	private int visibleItemsMax;
	private int selection;
	private int scrollY;

	protected StateBasedGame game;

	public Menu () {}

	@Override
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		this.setTitle ("INSERER TITRE ICI");
		this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setMenu (new ArrayList <MenuItem> ());
		this.setHint ("PRESS ENTER");
		this.boxWidth = 600;
		this.boxHeight = 37;
		this.blinkEnabled = false;
		this.blinkPeriod = 800;
		this.blinkCountdown = this.blinkPeriod;

		this.menuHeight = 30;
		this.startMenuX = (container.getWidth () - this.boxWidth) / 2;
		this.startMenuY = container.getHeight () / 2 - 130;
		this.endMenuY = container.getHeight () - 200;
		this.visibleItemsMax = (this.endMenuY - this.startMenuY) / this.menuHeight - 3;
		this.selection = 0;
		this.scrollY = 0;

		this.game = game;
		container.setShowFPS (false);
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		container.getInput ().clearKeyPressedRecord ();
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (this.blinkEnabled) {
			this.blinkCountdown = (this.blinkCountdown + this.blinkPeriod - delta) % this.blinkPeriod;
		};
		Input input = container.getInput ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			int size = this.menu.size ();
			if (size == 0) {
				System.exit (0);
			} else {
				this.menu.get (size - 1).itemSelected ();
			};
		} else if (input.isKeyPressed (Input.KEY_ENTER)) {
			this.menu.get (this.selection).itemSelected ();
		} else {
			if (input.isKeyPressed (Input.KEY_DOWN)) {
				if (this.selection < menu.size () - 1) {
					this.selection++;
				} else {
					this.selection = 0;
				};
				if (this.selection >= this.visibleItemsMax) {
					this.scrollY = this.selection - this.visibleItemsMax + 1;
				} else {
					this.scrollY = 0;
				};
			};
			if (input.isKeyPressed (Input.KEY_UP)) {
				if (this.selection > 0) {
					this.selection--;
				} else {
					this.selection = menu.size () - 1;
				};
				if (this.selection >= this.visibleItemsMax) {
					this.scrollY = this.selection - this.visibleItemsMax + 1;
				} else {
					this.scrollY = 0;
				};
			};
		};
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) throws SlickException {
		this.renderBackground (container, game, context);
		this.renderTitle (container, game, context);
		this.renderSubtitle (container, game, context);
		this.renderMenu (container, game, context);
		this.renderHint (container, game, context);
	}

	private void renderBackground (GameContainer container, StateBasedGame game, Graphics context) {
		context.setBackground (Menu.backgroundColor);
	}

	private void renderTitle (GameContainer container, StateBasedGame game, Graphics context) {
		int x = (container.getWidth () - this.titleWidth) / 2;
		int y = 120;
		context.setColor (Menu.highlightColor);
		context.setFont (Menu.titleFont);
		context.drawString (this.title, x - 2, y - 2);
		context.setColor (Menu.foregroundColor);
		context.drawString (this.title, x + 2, y + 2);
	}

	private void renderSubtitle (GameContainer container, StateBasedGame game, Graphics context) {
		int x = (container.getWidth () - this.subtitleWidth) / 2;
		int y = 232;
		context.drawRect (this.startMenuX, this.startMenuY, this.boxWidth, this.boxHeight);
		context.setFont (Menu.subtitleFont);
		context.setColor (Menu.foregroundColor);
		context.drawString (this.subtitle, x, y);
	}

	private void renderMenu (GameContainer container, StateBasedGame game, Graphics context) {
		int itemMin = this.scrollY;
		int itemMax = Math.min (this.menu.size (), this.scrollY + this.visibleItemsMax);
		int x = (container.getWidth () - this.menuWidth) / 2;
		int y = (this.endMenuY + this.startMenuY + this.boxHeight - this.menuHeight * Math.min (this.menu.size (), this.visibleItemsMax)) / 2;
		int dx = -35;
		context.setColor (Menu.foregroundColor);
		context.setFont (Menu.menuFont);
		for (int i = itemMin; i < itemMax; i++) {
			int dy = this.menuHeight * (i - this.scrollY);
			context.drawString (this.menu.get (i).getContent (), x, y + dy);
			if (i == this.selection) {
				boolean blinkEnabled = this.blinkEnabled && this.blinkCountdown <= this.blinkPeriod / 2;
				if (!blinkEnabled) {
					context.setColor (Menu.highlightColor);
				};
				context.drawString (">>", x + dx, y + dy);
				context.drawString ("<<", x + this.menuWidth - dx, y + dy);
				if (!blinkEnabled) {
					context.setColor (Menu.foregroundColor);
				};
			};
		};
	}

	private void renderHint (GameContainer container, StateBasedGame game, Graphics context) {
		int x = (container.getWidth () - this.hintWidth) / 2;
		int y = 530;
		context.drawRect (this.startMenuX, this.endMenuY, this.boxWidth, this.boxHeight);
		context.setFont (Menu.hintFont);
		context.setColor (Menu.foregroundColor);
		context.drawString (this.hint, x, y);
	}

	public void setTitle (String title) {
		this.title = title;
		this.titleWidth = Menu.titleFont.getWidth (title);
		// this.titleHeight = Menu.titleFont.getHeight (title);
	}

	public String getTitle () {
		return this.title;
	}

	public void setSubtitle (String subtitle) {
		this.subtitle = subtitle;
		this.subtitleWidth = Menu.subtitleFont.getWidth (subtitle);
		// this.subtitleHeight = Menu.subtitleFont.getHeight (subtitle);
	}

	public String getSubtitle () {
		return this.subtitle;
	}

	public void setMenu (List <MenuItem> menu) {
		this.menu = new ArrayList <MenuItem> ();
		this.menu.addAll (menu);
		this.menuWidth = 0;
		for (MenuItem item: this.menu) {
			int width = Menu.menuFont.getWidth (item.getContent ());
			if (width > this.menuWidth) {
				this.menuWidth = width;
			};
		};
		// this.menuHeight = Menu.menuFont.getHeight (hint);
	}

	public List <MenuItem> getMenu () {
		List <MenuItem> menu = new ArrayList <MenuItem> ();
		menu.addAll (this.menu);
		return menu;
	}

	public void setHint (String hint) {
		this.hint = hint;
		this.hintWidth = Menu.hintFont.getWidth (hint);
		// this.hintHeight = Menu.hintFont.getHeight (hint);
	}

	public String getHint () {
		return this.hint;
	}

	public void enableBlink () {
		this.blinkEnabled = true;
	}

	public void disableBlink () {
		this.blinkEnabled = true;
	}

	public boolean isBlinkEnabled () {
		return this.blinkEnabled;
	}

}
