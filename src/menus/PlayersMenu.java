package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	static private Font playersFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);

	static private int playersLineHeight = 30;

	private int ID;

	private List <Player> players;

	private boolean playersVisibility;

	protected int playersBoxWidth;
	protected int playersBoxHeight;
	protected int playersBoxX;
	protected int playersBoxY;

	private int gameID;
	private int playersColumnWidth;
	private List <Integer> availableColors;

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
		this.playersBoxHeight = this.hintBoxY - this.subtitleBoxY - Page.gap;

		this.playersColumnWidth = (this.playersBoxWidth + Page.gap) / 4 - Page.gap;
		this.players = new ArrayList <Player> ();
		this.availableColors = new ArrayList <Integer> ();
		for (int i = 0, l = Player.COLOR_NAMES.length; i < l; i++) {
			this.availableColors.add (i);
		};

		this.playersVisibility = true;

		// this.titleVisibility = false;
		// this.subtitleVisibility = false;
		this.hintBlink = true;

		this.setHint ("PRESS ENTER");
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int  delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		/*if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			System.exit (0);
		} else */if (input.isKeyPressed (Input.KEY_ENTER) || input.isButtonPressed (2, Input.ANY_CONTROLLER)) {
			((PlayersHandler) game.getState (this.gameID)).setPlayers (this.players);
			game.enterState (this.gameID, new FadeOutTransition (), new FadeInTransition ());
		} else {
			for (int i = 0, l = Math.min (input.getControllerCount (), 4); i < l; i++) {
				boolean keyAdd = input.isButtonPressed (0, i);
				if (keyAdd && this.players.size () < 4) {
					boolean controllerFound = false;
					for (int j = this.players.size (); j > -1; j--) {
						if (this.players.get (j).getControllerID () == j) {
							controllerFound = true;
							break;
						};
					};
					if (!controllerFound) {
						Random rng = new Random ();
						int j = rng.nextInt (this.availableColors.size ());
						int colorID = this.availableColors.remove (j);
						String name = "Joueur " + Player.COLOR_NAMES [colorID];
						this.players.add (new Player (colorID, i, name));
					};
				};
			};
			for (int i = this.players.size (); i > -1; i--) {
				boolean keyRemove = input.isButtonPressed (this.players.get (i).getControllerID (), i);
				if (keyRemove) {
					this.availableColors.add (this.players.remove (i).getColorID ());
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
			context.setFont (PlayersMenu.playersFont);
			for (int i = 0; i < 4; i++) {
				int x = this.playersBoxX + this.playersColumnWidth * i / 4;
				if (i < this.players.size ()) {
					Player player = players.get (i);
					int color = player.getColorID ();
					String name = player.getName ();
					int playerWidth = PlayersMenu.playersFont.getWidth (name);
					int playerHeight = PlayersMenu.playersFont.getHeight (name);
					int playerX = x + (this.playersColumnWidth - playerWidth) / 2;
					int playerY = y + (PlayersMenu.playersLineHeight - playerHeight) / 2;
					context.setColor (Player.STROKE_COLORS [color]);
					context.fillRect (x - 2, y - 2, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (Player.FILL_COLORS [color]);
					context.fillRect (x + 2, y + 2, this.playersColumnWidth, this.playersBoxHeight);
					context.setColor (Player.STROKE_COLORS [color]);
					context.drawString (name, playerX + 2, playerY + 2);
					context.setColor (Player.FILL_COLORS [color]);
					context.drawString (name, playerX - 2, playerY - 2);
				} else {
					int playerWidth = PlayersMenu.playersFont.getWidth ("+");
					int playerHeight = PlayersMenu.playersFont.getHeight ("+");
					int playerX = x + (this.playersColumnWidth - playerWidth) / 2;
					int playerY = y + (PlayersMenu.playersLineHeight - playerHeight) / 2;
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
