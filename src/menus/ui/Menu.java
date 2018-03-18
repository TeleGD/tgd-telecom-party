package menus.ui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import general.utils.FontUtils;

public abstract class Menu extends Page {

	static private Font menuFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);

	static private int menuLineHeight = 30;

	private List <MenuItem> menu;

	private boolean menuVisibility;

	protected int menuBoxWidth;
	protected int menuBoxHeight;
	protected int menuBoxX;
	protected int menuBoxY;

	private int menuWidth;
	private int menuHeight;
	private int menuX;
	private int menuY;

	private int menuScrollY;
	private int menuScrollHeight;
	private int itemHeight;
	private int selectedItem;

	private boolean menuBlink;
	private int menuBlinkPeriod;
	private int menuBlinkCountdown;

	public Menu () {}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.init (container, game);
		this.menuBoxX = this.contentX;
		this.menuBoxY = this.subtitleBoxY + this.subtitleBoxHeight + Page.gap;
		this.menuBoxWidth = this.contentWidth;
		this.menuBoxHeight = this.hintBoxY - this.menuBoxY - Page.gap;

		this.itemHeight = Menu.menuLineHeight;

		this.menuVisibility = true;

		this.menuBlink = false;
		this.menuBlinkPeriod = 1000;
		this.menuBlinkCountdown = 0;

		this.setMenu (new ArrayList <MenuItem> ());
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		container.getInput ().clearKeyPressedRecord ();
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			int size = this.menu.size ();
			if (size == 0) {
				System.exit (0);
			} else {
				this.menu.get (size - 1).itemSelected ();
			};
		} else if (input.isKeyPressed (Input.KEY_ENTER)) {
			this.menu.get (this.selectedItem).itemSelected ();
		} else {
			if (input.isKeyPressed (Input.KEY_DOWN)) {
				if (this.selectedItem < menu.size () - 1) {
					this.selectedItem++;
					if (this.selectedItem == this.menuScrollY + this.menuScrollHeight) {
						this.menuScrollY++;
					};
				} else {
					this.selectedItem = 0;
   					this.menuScrollY = 0;
				};
			};
			if (input.isKeyPressed (Input.KEY_UP)) {
				if (this.selectedItem > 0) {
					this.selectedItem--;
					if (this.selectedItem == this.menuScrollY - 1) {
						this.menuScrollY--;
					};
				} else {
					this.selectedItem = menu.size () - 1;
					this.menuScrollY = menu.size () - this.menuScrollHeight;
				};
			};
		};
		if (this.menuBlink) {
			this.menuBlinkCountdown = (this.menuBlinkCountdown + this.menuBlinkPeriod - delta) % this.menuBlinkPeriod;
		};
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
		this.renderMenu (container, game, context);
	}

	private void renderMenu (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.menuVisibility) {
			int dx = -35;
			context.setFont (Menu.menuFont);
			context.setColor (Page.foregroundColor);
			for (int i = this.menuScrollY, l = i + this.menuScrollHeight; i < l; i++) {
				int dy = this.itemHeight * (i - this.menuScrollY);
				context.drawString (this.menu.get (i).getContent (), this.menuX, this.menuY + dy);
				if (i == this.selectedItem) {
					boolean menuBlink = this.menuBlink && this.menuBlinkCountdown <= this.menuBlinkPeriod / 2;
					if (!menuBlink) {
						context.setColor (Page.highlightColor);
					};
					context.drawString (">>", this.menuX + dx, this.menuY + dy);
					context.drawString ("<<", this.menuX + this.menuWidth - dx, this.menuY + dy);
					if (!menuBlink) {
						context.setColor (Page.foregroundColor);
					};
				};
			};
		};
	}

	public void setMenu (List <MenuItem> menu) {
		this.menu = new ArrayList <MenuItem> ();
		this.menu.addAll (menu);
		this.selectedItem = 0;
		this.menuScrollY = 0;
		this.menuScrollHeight = Math.min (this.menuBoxHeight / this.itemHeight, this.menu.size ());
		this.menuWidth = 0;
		for (MenuItem item: this.menu) {
			int width = Menu.menuFont.getWidth (item.getContent ());
			if (width > this.menuWidth) {
				this.menuWidth = width;
			};
		};
		this.menuHeight = this.menuScrollHeight * this.itemHeight;
		this.menuX = this.menuBoxX + (this.menuBoxWidth - this.menuWidth) / 2;
		this.menuY = this.menuBoxY + (this.menuBoxHeight - this.menuHeight) / 2;
	}

	public List <MenuItem> getMenu () {
		List <MenuItem> menu = new ArrayList <MenuItem> ();
		menu.addAll (this.menu);
		return menu;
	}

}
