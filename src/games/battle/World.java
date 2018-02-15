package games.battle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class World extends BasicGameState {
	static private float jump (float x, float h, float d) {
		// y=(4h/d)(x-x²/d)
		return (float) ((0 <= x && x < d) ? (4 * h * (Math.pow (x, 2) / d - x) / d) : 0);
	};
	private int ID;
	private int radius;
	private int width;
	private int height;
	private boolean key_enter;
	private boolean key_space;
	private boolean key_left;
	private boolean key_right;
	private Player player;
	public void init (GameContainer container, StateBasedGame game) {
		this.ID = 42;
		this.width = general.Main.width + radius * 2;
		this.height = general.Main.height + radius * 2;
		this.key_enter = false;
		this.key_space = false;
		this.key_left = false;
		this.key_right = false;
		this.player = new Player (0, 32);
	};
	public void enter (GameContainer container, StateBasedGame game) {};
	public void update (GameContainer container, StateBasedGame game, int delta) {
		this.player.jumpDuration -= delta;
		this.player.gape = !this.key_enter;
		this.player.jump = this.key_space;
		this.player.x += Math.round ((this.key_enter ? 1 : 0) * 5) / 5 * delta * .48;
		this.player.x = (this.player.x >= width / 2) ? this.player.x % this.width - this.width : this.player.x;
		this.player.x = (this.player.x < -width / 2) ? this.player.x % this.width + this.width : this.player.x;
		this.player.y = (int) World.jump ((float) this.player.jumpDuration / 1000, (float) this.player.radius * 4, .8f);
		this.player.direction = this.key_right && !this.key_left ? 0 : (!this.key_right && this.key_left ? 1 : this.player.direction);
		if (this.player.jump && this.player.jumpDuration <= 0) {
			this.player.jumpDuration = 800;
		};
	};
	public void render (GameContainer container, StateBasedGame game, Graphics context) {};
	public void keyReleased (int key, char c) {
		switch (key) {
			case Input.KEY_ENTER:
				this.key_enter = false;
				break;
			case Input.KEY_SPACE:
				this.key_space = false;
				break;
			case Input.KEY_LEFT:
				this.key_left = false;
				break;
			case Input.KEY_RIGHT:
				this.key_right = false;
				break;
		};
	};
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ENTER:
				this.key_enter = true;
				break;
			case Input.KEY_SPACE:
				this.key_space = true;
				break;
			case Input.KEY_LEFT:
				this.key_left = true;
				break;
			case Input.KEY_RIGHT:
				this.key_right = true;
				break;
		};
	};
	public int getID () {
		return this.ID;
	};
};
