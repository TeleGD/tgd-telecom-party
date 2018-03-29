package menus;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.Player;
import general.PlayersHandler;
import general.utils.FontUtils;

import menus.ui.Page;

public class PlayersMenu extends Page {

	static private Font playersFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);

	// static private int playersLineHeight = 30;

	private int ID;
	private int previousID;
	private int nextID;

	private boolean playersVisibility;

	protected int playersBoxWidth;
	protected int playersBoxHeight;
	protected int playersBoxX;
	protected int playersBoxY;

	private int playersColumnWidth;

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

		this.previousID = -1;
		this.nextID = -1;

		this.playersBoxX = this.contentX;
		this.playersBoxY = this.subtitleBoxY + this.subtitleBoxHeight + Page.gap;
		this.playersBoxWidth = this.contentWidth;
		this.playersBoxHeight = this.hintBoxY - this.playersBoxY - Page.gap;

		this.playersColumnWidth = (this.playersBoxWidth + Page.gap) / 4 - Page.gap;

		this.playersVisibility = true;

		this.hintVisibility = false;
		this.hintBlink = true;

		this.setTitle ("INSERER TITRE ICI");
		this.setSubtitle ("INSERER SOUS-TITRE ICI");
		this.setHint ("PRESS ENTER");
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.players.get (0).getControllerID ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			if (this.previousID == -1) {
				System.exit (0);
			} else {
				game.enterState (this.previousID);
			};
		} else if ((input.isKeyPressed (Input.KEY_ENTER) || input.isButtonPressed (AppGame.BUTTON_PLUS, gameMasterID))/* && appGame.players.size () > 2*/) {
			if (this.nextID == -1) {
				System.exit (0);
			} else {
				((PlayersHandler) game.getState (this.nextID)).setPlayers (appGame.players);
				game.enterState (this.nextID, new FadeOutTransition (), new FadeInTransition ());
			};
		} else {
			for (int i = 0, l = input.getControllerCount (); i < l; i++) {
				boolean buttonAdd = input.isButtonPressed (AppGame.BUTTON_A, i);
				boolean playerFound = false;
				for (int j = appGame.players.size () - 1; j >= 0; j--) {
					Player player = appGame.players.get (j);
					if (player.getControllerID () == i) {
						playerFound = true;
						if (buttonAdd == (appGame.playersControls.get (j) >> AppGame.BUTTON_A == 0)) {
							appGame.playersControls.set (j, appGame.playersControls.get (j) ^ (1 << AppGame.BUTTON_A));
							if (buttonAdd) {
								appGame.availableColorIDs.add (player.getColorID ());
								int colorID = appGame.availableColorIDs.remove (0);
								String name = "Joueur " + Player.COLOR_NAMES [colorID]; // TODO: set user name
								player.setColorID (colorID);
								player.setName (name);
							};
						};
						break;
					};
				};
				if (!playerFound && buttonAdd && appGame.players.size () < 4) {
					int colorID = appGame.availableColorIDs.remove (0);
					String name = "Joueur " + Player.COLOR_NAMES [colorID]; // TODO: set user name
					appGame.players.add (new Player (colorID, i, name));
					appGame.playersControls.add (1 << AppGame.BUTTON_A);
					if (appGame.players.size () == 2) {
						this.hintVisibility = true;
					};
				};
			};
			for (int i = appGame.players.size () - 1; i >= 0; i--) {
				boolean buttonRemove = input.isButtonPressed (AppGame.BUTTON_B, appGame.players.get (i).getControllerID ());
				if (buttonRemove == (appGame.playersControls.get (i) >> AppGame.BUTTON_B == 0)) {
					appGame.playersControls.set (i, appGame.playersControls.get (i) ^ (1 << AppGame.BUTTON_B));
					if (buttonRemove) {
						if (i == gameMasterID) {
							if (this.previousID == -1) {
								System.exit (0);
							} else {
								game.enterState (this.previousID);
							};
						} else {
							appGame.availableColorIDs.add (0, appGame.players.remove (i).getColorID ());
							appGame.playersControls.remove (i);
							if (appGame.players.size () == 1) {
								this.hintVisibility = false;
							};
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
		AppGame appGame = (AppGame) game;
		if (this.playersVisibility) {
			int y = this.playersBoxY;
			for (int i = 0; i < 4; i++) {
				int x = this.playersBoxX + (this.playersColumnWidth + Page.gap) * i;
				if (i < appGame.players.size ()) {
					Player player = appGame.players.get (i);
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

	public void setPreviousID (int ID) {
		this.previousID = ID;
	}

	public int getPreviousID () {
		return this.previousID;
	}

	public void setNextID (int ID) {
		this.nextID = ID;
	}

	public int getNextID () {
		return this.nextID;
	}

}
