package menus;

import java.util.ArrayList;
import java.util.List;
// import java.util.Random;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.Player;
import general.PlayersHandler;
import general.utils.FontUtils;

import menus.ui.Page;

public class PlayersMenu extends Page {

	static private final int BUTTON_ADD = 0;
	static private final int BUTTON_REMOVE = 1;
	static private final int BUTTON_ENTER = 2;

	static private Font playersFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);

	// static private int playersLineHeight = 30;

	private int ID;

	private List <Player> players;

	private boolean playersVisibility;

	protected int playersBoxWidth;
	protected int playersBoxHeight;
	protected int playersBoxX;
	protected int playersBoxY;

	private int gameID;
	private int playersColumnWidth;
	private List <Integer> availableColorIDs;
	private List <Integer> playersControls;

	public PlayersMenu (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);

		this.playersBoxX = this.contentX;
		this.playersBoxY = this.subtitleBoxY + this.subtitleBoxHeight + Page.gap;
		this.playersBoxWidth = this.contentWidth;
		this.playersBoxHeight = this.hintBoxY - this.playersBoxY - Page.gap;

		this.playersColumnWidth = (this.playersBoxWidth + Page.gap) / 4 - Page.gap;
		this.players = new ArrayList <Player> ();
		this.availableColorIDs = new ArrayList <Integer> ();
		for (int i = 0, l = Player.COLOR_NAMES.length; i < l; i++) {
			this.availableColorIDs.add (i);
		};
		this.playersControls = new ArrayList <Integer> ();

		this.playersVisibility = true;

		this.hintVisibility = false;
		this.hintBlink = true;

		this.setTitle ("INSERER TITRE ICI");
		this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setHint ("PRESS ENTER");
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int  delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		/*if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			System.exit (0);
		} else */if ((input.isKeyPressed (Input.KEY_ENTER) || input.isButtonPressed (PlayersMenu.BUTTON_ENTER, Input.ANY_CONTROLLER)) && this.players.size () > 2) {
			((PlayersHandler) game.getState (this.gameID)).setPlayers (this.players);
			game.enterState (this.gameID, new FadeOutTransition (), new FadeInTransition ());
		} else {
			for (int i = 0, l = input.getControllerCount (); i < l; i++) {
				boolean buttonAdd = input.isButtonPressed (PlayersMenu.BUTTON_ADD, i);
				boolean playerFound = false;
				for (int j = this.players.size () - 1; j >= 0; j--) {
					Player player = this.players.get (j);
					if (player.getControllerID () == i) {
						playerFound = true;
						if (buttonAdd == (this.playersControls.get (j) >> PlayersMenu.BUTTON_ADD == 0)) {
							this.playersControls.set (j, this.playersControls.get (j) ^ (1 << PlayersMenu.BUTTON_ADD));
							if (buttonAdd) {
								this.availableColorIDs.add (player.getColorID ());
								player.setColorID (this.availableColorIDs.remove (0));
							};
						};
						break;
					};
				};
				if (!playerFound && buttonAdd && this.players.size () < 4) {
					// Random rng = new Random ();
					// int j = rng.nextInt (this.availableColorIDs.size ());
					// int colorID = this.availableColorIDs.remove (j);
					int colorID = this.availableColorIDs.remove (0);
					String name = "Joueur " + Player.COLOR_NAMES [colorID]; // TODO: set user name
					this.players.add (new Player (colorID, i, name));
					this.playersControls.add (1 << PlayersMenu.BUTTON_ADD);
					if (this.players.size () == 2) {
						this.hintVisibility = true;
					};
				};
			};
			for (int i = this.players.size () - 1; i >= 0; i--) {
				boolean buttonRemove = input.isButtonPressed (PlayersMenu.BUTTON_REMOVE, this.players.get (i).getControllerID ());
				if (buttonRemove == (this.playersControls.get (i) >> PlayersMenu.BUTTON_REMOVE == 0)) {
					this.playersControls.set (i, this.playersControls.get (i) ^ (1 << PlayersMenu.BUTTON_REMOVE));
					if (buttonRemove) {
						this.availableColorIDs.add (0, this.players.remove (i).getColorID ());
						this.playersControls.remove (i);
						if (this.players.size () == 1) {
							this.hintVisibility = false;
						};
					};
				};
			};
		};
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
		this.renderPlayers (container, game, context);
	}

	private void renderPlayers (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.playersVisibility) {
			int y = this.playersBoxY;
			for (int i = 0; i < 4; i++) {
				int x = this.playersBoxX + (this.playersColumnWidth + Page.gap) * i;
				if (i < this.players.size ()) {
					Player player = players.get (i);
					int color = player.getColorID ();
					String name = player.getName ();
					int playerWidth = PlayersMenu.playersFont.getWidth (name);
					int playerHeight = PlayersMenu.playersFont.getHeight (name);
					int playerX = x + (this.playersColumnWidth - playerWidth) / 2;
					int playerY = y + (this.playersBoxHeight - playerHeight) / 2;
					context.setFont (PlayersMenu.playersFont);
					context.setColor (Player.STROKE_COLORS [color]);
					context.fillRect (x - 4, y - 4, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (Player.FILL_COLORS [color]);
					context.fillRect (x + 4, y + 4, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (Player.STROKE_COLORS [color]);
					context.drawString (name, playerX + 4, playerY + 4);
				} else {
					int playerWidth = Page.titleFont.getWidth ("+");
					int playerHeight = Page.titleFont.getHeight ("+");
					int playerX = x + (this.playersColumnWidth - playerWidth) / 2;
					int playerY = y + (this.playersBoxHeight - playerHeight) / 2;
					context.setFont (Page.titleFont);
					context.setColor (Page.foregroundColor);
					context.drawRect (x, y, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (Page.highlightColor);
					context.drawString ("+", playerX - 2, playerY - 2);
					context.setColor (Page.foregroundColor);
					context.drawString ("+", playerX + 2, playerY + 2);
				};
			};
		};
	}

	public void setGameID (int ID) {
		this.gameID = ID;
	}

	public int getGameID () {
		return this.gameID;
	}

}
