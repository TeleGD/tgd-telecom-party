package games.pong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.EmptyImageData;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppWorld;
import app.utils.FontUtils;

import games.pong.bonuses.BallRandomDir;
import games.pong.bonuses.BallRandomPos;
import games.pong.bonuses.BallSpeed;
import games.pong.bonuses.PlayerLifeDown;
import games.pong.bonuses.PlayerLifeUp;
import games.pong.bonuses.PlayerSpanDown;
import games.pong.bonuses.PlayerSpanUp;
import games.pong.bonuses.PlayerSpeedDown;
import games.pong.bonuses.PlayerSpeedUp;

public class World extends AppWorld {

	public static final String DIRECTORY = "pong" + java.io.File.separator;
	public static final String IMAGES = "images" + java.io.File.separator + World.DIRECTORY;

	public static final Random RNG = new Random ();

	public static final int WORLD_MARGIN = 24;
	public static final int WORLD_BORDER = 8;
	public static final int WORLD_PADDING = 32;

	public static final int BALL_SIZE = 8;
	public static final Color BALL_FILL_COLOR = new Color (.8f, .8f, .8f);
	public static final Color BALL_STROKE_COLOR = new Color (0f, 0f, 0f);

	public static final int BONUS_SIZE = 24;
	public static final Font BONUS_FONT = FontUtils.loadFont ("Kalinga", java.awt.Font.PLAIN, 16, true);

	public static final int SCORE_LINE_HEIGHT = 32;
	public static final Font SCORE_FONT = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 16, true);

	public static final int PLAYER_LENGTH = 80;
	public static final Color WALL_COLOR = new Color (.2f, .2f, .2f);

	public static /* final */ Image BACKGROUND_IMAGE;
	public static /* final */ int [] BACKGROUND_SIZE;

	static {
		try {
			World.BACKGROUND_IMAGE = new Image (IMAGES + "backgroundDuck.png");
		} catch (SlickException exception) {
			World.BACKGROUND_IMAGE = new Image (new EmptyImageData (0, 0));
		}
		World.BACKGROUND_SIZE = new int [] {
			World.BACKGROUND_IMAGE.getWidth (),
			World.BACKGROUND_IMAGE.getHeight ()
		};
	}

	public int size;
	public int [] pos;

	private List <Player> players;
	private List <Wall> walls;
	private List <Ball> balls;
	private List <Bonus> bonuses;

	public World (int ID) {
		super (ID);
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		int width = container.getWidth ();
		int height = container.getHeight ();
		int size = Math.min (width, height);
		this.pos = new int [] {
			(width - size) / 2,
			(height - size) / 2
		};
		this.size = size;
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		container.getInput ().clearKeyPressedRecord ();
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		if (bonuses.size () < 5 && World.RNG.nextFloat () * delta * 19.2f < 1f) {
			switch (World.RNG.nextInt (9)) {
				case 0:
					bonuses.add (new BallRandomDir (this));
					break;
				case 1:
					bonuses.add (new BallRandomPos (this));
					break;
				case 2:
					bonuses.add (new BallSpeed (this));
					break;
				case 3:
					bonuses.add (new PlayerSpanDown (this));
					break;
				case 4:
					bonuses.add (new PlayerSpanUp (this));
					break;
				case 5:
					bonuses.add (new PlayerLifeDown (this));
					break;
				case 6:
					bonuses.add (new PlayerLifeUp (this));
					break;
				case 7:
					bonuses.add (new PlayerSpeedDown (this));
					break;
				case 8:
					bonuses.add (new PlayerSpeedUp (this));
			}
		}
		for (int i = this.players.size () - 1; i >= 0; i--) {
			this.players.get (i).update (container, game, delta);
		}
		for (int i = this.balls.size () - 1; i >= 0; i--) {
			this.balls.get (i).update (container, game, delta);
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		this.renderBackground (container, game, context);
		for (Player player: this.players) {
			player.render (container, game, context);
		}
		for (Wall wall: this.walls) {
			wall.render (container, game, context);
		}
		for (Bonus bonus: this.bonuses) {
			bonus.render (container, game, context);
		}
		for (Ball ball: this.balls) {
			ball.render (container, game, context);
		}
	}

	private void renderBackground (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage (World.BACKGROUND_IMAGE, this.pos [0], this.pos [1], this.pos [0] + this.size, this.pos [1] + this.size, 0, 0, World.BACKGROUND_SIZE [0], World.BACKGROUND_SIZE [1]);
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int livingPlayers = appGame.appPlayers.size ();
		List <Player> players = new ArrayList <Player> ();
		List <Wall> walls = new ArrayList <Wall> ();
		for (int i = 0; i < 4; i++) {
			if (i < livingPlayers) {
				players.add (new Player (this, i, appGame.appPlayers.get (i)));
			} else {
				walls.add (new Wall (this, i));
			}
		}
		List <Ball> balls = new ArrayList <Ball> ();
		balls.add (new Ball (this));
		List <Bonus> bonuses = new ArrayList <Bonus> ();
		this.setPlayers (players);
		this.setWalls (walls);
		this.setBalls (balls);
		this.setBonuses (bonuses);
	}

	public void setPlayers (List <Player> players) {
		this.players = players;
	}

	public List <Player> getPlayers () {
		return this.players;
	}

	public void setWalls (List <Wall> walls) {
		this.walls = walls;
	}

	public List <Wall> getWalls () {
		return this.walls;
	}

	public void setBalls (List <Ball> balls) {
		this.balls = balls;
	}

	public List <Ball> getBalls () {
		return this.balls;
	}

	public void setBonuses (List <Bonus> bonuses) {
		this.bonuses = bonuses;
	}

	public List <Bonus> getBonuses () {
		return this.bonuses;
	}

}
