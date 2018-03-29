package games.battle;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import general.AppGame;
import menus.PlayersMenu;
public class World extends BasicGameState implements general.PlayersHandler {
	static private float jump (float x, float h, float d) {
		// y = (4h / d) (x - x² / d)
		return (float) ((0 <= x && x < d) ? (4 * h * (Math.pow (x, 2) / d - x) / d) : 0);
	};
	private int ID;
	private int width;
	// private int height;
	private Color backgroundColor;
	private Color fillColor;
	private Color strokeColor;
	private List <Player> players;
	public World (int ID) {
		this.ID = ID;
	};
	public int getID () {
		return this.ID;
	};
	public void init (GameContainer container, StateBasedGame game) {
		int radius = 32;
		this.width = container.getWidth () + radius * 2;
		// this.height = container.getHeight () + radius * 2;
		this.backgroundColor = new Color (255, 255, 255, 0);
		this.fillColor = new Color (51, 153, 102);
		this.strokeColor = new Color (0, 102, 51);
	};
	public void enter (GameContainer container, StateBasedGame game) {
		// this.init (container, game);
		container.getInput ().clearKeyPressedRecord ();
	};
	public void update (GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput ();
		PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
		int gameMasterID = playersMenu.players.get (0).getControllerID ();
		if (input.isKeyPressed (Input.KEY_ESCAPE) || input.isButtonPressed (AppGame.BUTTON_PLUS, gameMasterID)) {
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
			for (Player player: this.players) {
				boolean keyGape = input.isButtonPressed (AppGame.BUTTON_A, player.controllerID);
				boolean keyJump = input.isButtonPressed (AppGame.BUTTON_B, player.controllerID) || input.isButtonPressed (AppGame.BUTTON_Y, player.controllerID) || input.isButtonPressed (AppGame.BUTTON_X, player.controllerID);
				// float axisY = input.getAxisValue (controllerID, 0);
				float axisX = input.getAxisValue (player.controllerID, AppGame.AXIS_XL);
				player.jumpDuration -= delta;
				player.gape = !keyGape;
				player.jump = keyJump;
				// player.x += Math.round (axisX * 5) / 5 * delta * .48;
				player.x += axisX * delta * .48;
				player.x = (player.x >= this.width / 2) ? player.x % this.width - this.width : player.x;
				player.x = (player.x < -this.width / 2) ? player.x % this.width + this.width : player.x;
				player.y = World.jump ((float) player.jumpDuration / 1000, (float) player.radius * 4, (float) .8);
				player.direction = axisX > 0 ? 0 : (axisX < 0 ? 1 : player.direction);
				if (player.jump && player.jumpDuration <= 0) {
					player.jumpDuration = 800;
				};
			};
		};
	};
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int width = container.getWidth ();
		int height = container.getHeight ();
		// context.setAntiAlias (true);
		context.setBackground (this.backgroundColor);
		context.setLineWidth (2);
		context.setColor (this.fillColor);
		context.fillRect (0, height / 2, width, height / 2);
		context.setColor (this.strokeColor);
		context.drawLine (0, height / 2, width, height / 2);
		for (Player player: this.players) {
			player.render (container, game, context);
		};
	};
	public void setPlayers (List <general.Player> players) {
		this.players = new ArrayList <Player> ();
		for (general.Player player : players) {
			this.players.add (new Player (player));
		};
	};
};
