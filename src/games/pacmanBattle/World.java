package games.pacmanBattle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import app.AppGame;
import app.AppInput;
import app.AppWorld;
public class World extends AppWorld {
	static private float jump (float x, float h, float d) {
		// y = (4h / d) (x - x² / d)
		return 0f <= x && x < d ? ((float) Math.pow (x, 2f) / d - x) * h / d * 4f : 0f;
	};
	private int width;
	// private int height;
	private Color backgroundColor;
	private Color fillColor;
	private Color strokeColor;
	private Player [] players;
	public World (int ID) {
		super (ID);
	};
	public void init (GameContainer container, StateBasedGame game) {
		int radius = 32;
		this.width = container.getWidth () + radius * 2;
		// this.height = container.getHeight () + radius * 2;
		this.backgroundColor = new Color (255, 255, 255, 0);
		this.fillColor = new Color (51, 153, 102);
		this.strokeColor = new Color (0, 102, 51);
	};
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		AppInput appInput = (AppInput) container.getInput ();
		for (Player player: this.players) {
			boolean keyGape = appInput.isButtonPressed (AppInput.BUTTON_A, player.controllerID);
			boolean keyJump = appInput.isButtonPressed (AppInput.BUTTON_B | AppInput.BUTTON_Y | AppInput.BUTTON_X, player.controllerID);
			// float axisY = appInput.getAxisValue (0, controllerID);
			float axisX = appInput.getAxisValue (AppInput.AXIS_XL, player.controllerID);
			player.jumpDuration -= delta;
			player.gape = !keyGape;
			player.jump = keyJump;
			// player.x += Math.round (axisX * 5) / 5 * delta * .48f;
			player.x += axisX * delta * .48f;
			player.x = (player.x >= this.width / 2) ? player.x % this.width - this.width : player.x;
			player.x = (player.x < -this.width / 2) ? player.x % this.width + this.width : player.x;
			player.y = World.jump (player.jumpDuration / 1000f, player.radius * 4f, .8f);
			player.direction = axisX > 0f ? 0 : (axisX < 0f ? 1 : player.direction);
			if (player.jump && player.jumpDuration <= 0) {
				player.jumpDuration = 800;
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
	public void play (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size ();
		this.players = new Player [n];
		for (int i = 0; i < n; i++) {
			this.players [i] = new Player (appGame.appPlayers.get (i));
		};
	};
};
