package menus.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.utils.FontUtils;

public abstract class Page extends BasicGameState {

	static protected Color foregroundColor = Color.white;
	static protected Color backgroundColor = Color.black;
	static protected Color highlightColor = Color.red;

	static private Font titleFont = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.BOLD, 40, false);
	static private Font subtitleFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 24, true);
	static protected Font hintFont = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.PLAIN, 20, false); // TODO: private ?

	static private int titleLineHeight = 50;
	static private int subtitleLineHeight = 30;
	static private int hintLineHeight = 30;

	static protected int gap = 40;

	private String title;
	private String subtitle;
	private String hint;

	protected int contentWidth;
	protected int contentHeight;
	protected int contentX;
	protected int contentY;

	protected int titleBoxWidth;
	protected int titleBoxHeight;
	protected int titleBoxX;
	protected int titleBoxY;

	protected int subtitleBoxWidth;
	protected int subtitleBoxHeight;
	protected int subtitleBoxX;
	protected int subtitleBoxY;

	protected int hintBoxWidth;
	protected int hintBoxHeight;
	protected int hintBoxX;
	protected int hintBoxY;

	private int titleWidth;
	private int titleHeight;
	private int titleX;
	private int titleY;

	private int subtitleWidth;
	private int subtitleHeight;
	private int subtitleX;
	private int subtitleY;

	private int hintWidth;
	private int hintHeight;
	private int hintX;
	private int hintY;

	public Page () {}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		this.contentWidth = 600;
		this.contentHeight = 400;
		this.contentX = (container.getWidth () - this.contentWidth) / 2;
		this.contentY = (container.getHeight () - this.contentHeight) / 2;

		this.titleBoxWidth = this.contentWidth;
		this.titleBoxHeight = Page.titleLineHeight;
		this.titleBoxX = this.contentX;
		this.titleBoxY = this.contentY;

		this.subtitleBoxWidth = this.contentWidth;
		this.subtitleBoxHeight = Page.subtitleLineHeight;
		this.subtitleBoxX = this.contentX;
		this.subtitleBoxY = this.titleBoxY + this.titleBoxHeight + Page.gap;

		this.hintBoxWidth = this.contentWidth;
		this.hintBoxHeight = Page.hintLineHeight;
		this.hintBoxX = this.contentX;
		this.hintBoxY = this.contentY + this.contentHeight - this.hintBoxHeight;

		this.setTitle ("");
		this.setSubtitle ("");
		this.setHint ("");
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		this.renderBackground (container, game, context);
		this.renderTitle (container, game, context);
		this.renderSubtitle (container, game, context);
		this.renderHint (container, game, context);
	}

	private void renderBackground (GameContainer container, StateBasedGame game, Graphics context) {
		context.setBackground (Page.backgroundColor);
	}

	private void renderTitle (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor (Page.highlightColor);
		context.setFont (Page.titleFont);
		context.drawString (this.title, this.titleX - 2, this.titleY - 2);
		context.setColor (Page.foregroundColor);
		context.drawString (this.title, this.titleX + 2, this.titleY + 2);
	}

	private void renderSubtitle (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawRect (this.subtitleBoxX, this.subtitleBoxY, this.subtitleBoxWidth, this.subtitleBoxHeight);
		context.setFont (Page.subtitleFont);
		context.setColor (Page.foregroundColor);
		context.drawString (this.subtitle, this.subtitleX, this.subtitleY);
	}

	private void renderHint (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawRect (this.hintBoxX, this.hintBoxY, this.hintBoxWidth, this.hintBoxHeight);
		context.setFont (Page.hintFont);
		context.setColor (Page.foregroundColor);
		context.drawString (this.hint, this.hintX, this.hintY);
	}

	public void setTitle (String title) {
		this.title = title;
		this.titleWidth = Page.titleFont.getWidth (title);
		this.titleHeight = Page.titleFont.getHeight (title);
		this.titleX = this.titleBoxX + (this.titleBoxWidth - this.titleWidth) / 2;
		this.titleY = this.titleBoxY + (this.titleBoxHeight - this.titleHeight) / 2;
	}

	public String getTitle () {
		return this.title;
	}

	public void setSubtitle (String subtitle) {
		this.subtitle = subtitle;
		this.subtitleWidth = Page.subtitleFont.getWidth (subtitle);
		this.subtitleHeight = Page.subtitleFont.getHeight (subtitle);
		this.subtitleX = this.subtitleBoxX + (this.subtitleBoxWidth - this.subtitleWidth) / 2;
		this.subtitleY = this.subtitleBoxY + (this.subtitleBoxHeight - this.subtitleHeight) / 2;
	}

	public String getSubtitle () {
		return this.subtitle;
	}

	public void setHint (String hint) {
		this.hint = hint;
		this.hintWidth = Page.hintFont.getWidth (hint);
		this.hintHeight = Page.hintFont.getHeight (hint);
		this.hintX = this.hintBoxX + (this.hintBoxWidth - this.hintWidth) / 2;
		this.hintY = this.hintBoxY + (this.hintBoxHeight - this.hintHeight) / 2;
	}

	public String getHint () {
		return this.hint;
	}

}
