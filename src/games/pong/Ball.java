package games.pong;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Ball {

	private World world;

	private int [] boxPos;
	private int boxSize;
	private int [] pos;
	private int size;

	private float [] dir;
	private int speed;

	private int hitCount;
	private Player lastHit;

	private Color fillColor;
	private Color strokeColor;

	public Ball (World world) {
		int start = World.WORLD_MARGIN + World.WORLD_BORDER;
		int span = world.size - start * 2;
		double theta = World.RNG.nextDouble () * Math.PI * 2;
		this.world = world;
		this.boxPos = new int [] {
			world.pos [0] + start,
			world.pos [1] + start
		};
		this.boxSize = span;
		this.pos = new int [] {
			(span - World.BALL_SIZE) / 2,
			(span - World.BALL_SIZE) / 2
		};
		this.size = World.BALL_SIZE;
		this.setDir (new float [] {
			(float) Math.cos (theta),
			(float) Math.sin (theta)
		});
		this.setSpeed (16);
		this.hitCount = 0;
		this.lastHit = null;
		this.fillColor = World.BALL_FILL_COLOR;
		this.strokeColor = World.BALL_STROKE_COLOR;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		float speed = this.speed * delta * .015f;
		this.pos [0] += speed * this.dir [0];
		this.pos [1] += speed * this.dir [1];
		List <Player> players = this.world.getPlayers ();
		List <Wall> walls = this.world.getWalls ();
		List <Side> sides = new ArrayList <Side> ();
		sides.addAll (players);
		sides.addAll (walls);
		List <Ball> balls = this.world.getBalls ();
		List <Bonus> bonuses = this.world.getBonuses ();
		for (Side side: sides) {
			int axis0 = side.getAxis ();
			int axis1 = axis0 ^ 1;
			int alignment = side.getAlignment ();
			if (alignment == 1 ? this.pos [axis1] + this.size >= this.boxSize : this.pos [axis1] < 0) {
				if (side instanceof Wall) {
					this.dir [axis1] *= -1;
					if (this.hitCount > 8) {
						double theta = World.RNG.nextDouble () * Math.PI;
						this.dir [axis0] = (float) Math.cos (theta);
						this.dir [axis1] = (float) Math.sin (theta) * Math.signum (this.dir [axis1]);
						this.hitCount = 0;
					} else {
						this.hitCount++;
					}
				} else {
					Player player = (Player) side;
					int playerStart = player.getStart ();
					int playerSpan = player.getSpan ();
					if (this.pos [axis0] >= playerStart && this.pos [axis0] + this.size < playerStart + playerSpan && (alignment == 1 ? this.pos [axis1] + this.size < this.boxSize + World.WORLD_BORDER : this.pos [axis1] >= -World.WORLD_BORDER)) {
						this.dir [axis1] *= -1;
						double distance = (double) (this.pos [axis0] - playerStart) * 2 + this.size - playerSpan;
						double theta = (Math.asin (this.dir [axis0]) + distance / playerSpan) / 2;
						this.dir [axis0] = (float) Math.sin (theta);
						this.dir [axis1] = (float) Math.cos (theta) * Math.signum (this.dir [axis1]);
						this.lastHit = player;
						this.hitCount = 0;
					} else if (alignment == 1 ? this.pos [axis1] + this.size >= this.boxSize + World.WORLD_MARGIN + World.WORLD_BORDER : this.pos [axis1] < -World.WORLD_MARGIN - World.WORLD_BORDER) {
						int playerLives = player.getLives ();
						if (playerLives > 1) {
							player.setLives (playerLives - 1);
						} else if (this.world.getPlayers ().size () > 0) { // TODO: test if > 1
							players.remove (player);
							walls.add (new Wall (this.world, axis1 << 1 | alignment));
						}
						balls.remove (this);
						if (balls.size () == 0) {
							balls.add (new Ball (this.world));
						}
					}
				}
				this.speed += 1;
			}
		}
		for (int i = bonuses.size () - 1; i >= 0; i--) {
			Bonus bonus = bonuses.get (i);
			int [] bonusPos = bonus.getPos ();
			int bonusSize = bonus.getSize ();
			if (Math.hypot (bonusPos [0] - this.pos [0] + (bonusSize - this.size) / 2 + World.WORLD_PADDING, bonusPos [1] - this.pos [1] + (bonusSize - this.size) / 2 + World.WORLD_PADDING) < (bonusSize + this.size) / 2) {
				bonus.apply (this);
				bonuses.remove (bonus);
			}
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		int x = this.boxPos [0] + this.pos [0];
		int y = this.boxPos [1] + this.pos [1];
		context.setColor (this.fillColor);
		context.fillOval (x, y, this.size, this.size);
		context.setColor (this.strokeColor);
		context.drawOval (x, y, this.size, this.size);
	}

	public void setPos (int [] pos) {
		this.pos = pos;
	}

	public int [] getPos () {
		return this.pos;
	}

	public void setSize (int size) {
		this.size = size;
	}

	public int getSize () {
		return this.size;
	}

	public void setDir (float [] dir) {
		this.dir = dir;
	}

	public float [] getDir () {
		return this.dir;
	}

	public void setSpeed (int speed) {
		this.speed = Math.max (speed, 0);
	}

	public int getSpeed () {
		return this.speed;
	}

	public Player getLastHit () {
		return this.lastHit;
	}

}
