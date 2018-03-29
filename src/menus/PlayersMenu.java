package menus;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.AppPlayer;
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
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			if (this.previousID == -1) {
				System.exit (0);
			} else {
				game.enterState (this.previousID);
			};
		} else if ((input.isKeyPressed (Input.KEY_ENTER) || input.isButtonPressed (AppGame.BUTTON_PLUS, gameMasterID))/* && appGame.appPlayers.size () > 2*/) {
			if (this.nextID == -1) {
				System.exit (0);
			} else {
				((PlayersHandler) game.getState (this.nextID)).setPlayers (appGame.appPlayers);
				game.enterState (this.nextID, new FadeOutTransition (), new FadeInTransition ());
			};
		} else {
			for (int i = 0, l = input.getControllerCount (); i < l; i++) {
				boolean buttonAdd = input.isButtonPressed (AppGame.BUTTON_A, i);
				boolean appPlayerFound = false;
				for (int j = appGame.appPlayers.size () - 1; j >= 0; j--) {
					AppPlayer appPlayer = appGame.appPlayers.get (j);
					if (appPlayer.getControllerID () == i) {
						appPlayerFound = true;
						if (buttonAdd == (appGame.appPlayersControls.get (j) >> AppGame.BUTTON_A == 0)) {
							appGame.appPlayersControls.set (j, appGame.appPlayersControls.get (j) ^ (1 << AppGame.BUTTON_A));
							if (buttonAdd) {
								appGame.availableColorIDs.add (appPlayer.getColorID ());
								int colorID = appGame.availableColorIDs.remove (0);
								String name = "Joueur " + AppPlayer.COLOR_NAMES [colorID]; // TODO: set user name
								appPlayer.setColorID (colorID);
								appPlayer.setName (name);
							};
						};
						break;
					};
				};
				if (!appPlayerFound && buttonAdd && appGame.appPlayers.size () < 4) {
					int colorID = appGame.availableColorIDs.remove (0);
					String name = "Joueur " + AppPlayer.COLOR_NAMES [colorID]; // TODO: set user name
					appGame.appPlayers.add (new AppPlayer (colorID, i, name));
					appGame.appPlayersControls.add (1 << AppGame.BUTTON_A);
					if (appGame.appPlayers.size () == 2) {
						this.hintVisibility = true;
					};
				};
			};
			for (int i = appGame.appPlayers.size () - 1; i >= 0; i--) {
				boolean buttonRemove = input.isButtonPressed (AppGame.BUTTON_B, appGame.appPlayers.get (i).getControllerID ());
				if (buttonRemove == (appGame.appPlayersControls.get (i) >> AppGame.BUTTON_B == 0)) {
					appGame.appPlayersControls.set (i, appGame.appPlayersControls.get (i) ^ (1 << AppGame.BUTTON_B));
					if (buttonRemove) {
						if (i == gameMasterID) {
							if (this.previousID == -1) {
								System.exit (0);
							} else {
								game.enterState (this.previousID);
							};
						} else {
							appGame.availableColorIDs.add (0, appGame.appPlayers.remove (i).getColorID ());
							appGame.appPlayersControls.remove (i);
							if (appGame.appPlayers.size () == 1) {
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
				if (i < appGame.appPlayers.size ()) {
					AppPlayer appPlayer = appGame.appPlayers.get (i);
					int color = appPlayer.getColorID ();
					String name = appPlayer.getName ();
					int playerWidth = PlayersMenu.playersFont.getWidth (name);
					int playerHeight = PlayersMenu.playersFont.getHeight (name);
					int playerX = x + (this.playersColumnWidth - playerWidth) / 2;
					int playerY = y + (this.playersBoxHeight - playerHeight) / 2;
					context.setFont (PlayersMenu.playersFont);
					context.setColor (AppPlayer.STROKE_COLORS [color]);
					context.fillRect (x - 4, y - 4, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (AppPlayer.FILL_COLORS [color]);
					context.fillRect (x + 4, y + 4, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (AppPlayer.STROKE_COLORS [color]);
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
