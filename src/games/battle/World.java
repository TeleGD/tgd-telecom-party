package games.battle;
import java.util.Arrays;
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
public class World extends BasicGameState {
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
		// this.players = new ArrayList <MenuItem> ();
		this.players = Arrays.asList (new Player [] {
			new Player (0, radius)
		});
		// container.getInput ().initControllers ();
	};
	public void enter (GameContainer container, StateBasedGame game) {
		// this.init (container, game);
		container.getInput ().clearControlPressedRecord ();
	};
	public void update (GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput ();
		if (input.isKeyPressed (Input.KEY_ESCAPE)) {
			game.enterState (AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
			for (int i = 0, l = this.players.size (); i < l; i++) {
				boolean keyGape = input.isButtonPressed (0, i);
				boolean keyJump = input.isButtonPressed (1, i) || input.isButtonPressed (2, i) || input.isButtonPressed (3, i);
				// float axisY = input.getAxisValue (i, 0);
				float axisX = input.getAxisValue (i, 1);
				Player player = this.players.get (i);
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
		for (int i = 0, l = this.players.size (); i < l; i++) {
			this.players.get (i).render (container, game, context);
		};
	};
};
