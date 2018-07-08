package games.pong;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Side {

	protected int axis;
	protected int alignment;

	protected int [] boxPos;
	protected int [] boxSize;
	protected int [] pos;
	protected int [] size;

	public Side (World world, int sideID) {
		int [] start = new int [] {
			World.WORLD_MARGIN,
			World.WORLD_MARGIN + World.WORLD_BORDER,
		};
		int [] span = new int [] {
			world.size - start [0] * 2,
			world.size - start [1] * 2
		};
		this.axis = (sideID >> 1 & 1) ^ 1;
		this.alignment = sideID & 1;
		this.pos = new int [] {
			this.alignment * (span [0] - World.WORLD_BORDER),
			span [1] / 2
		};
		this.size = new int [] {
			World.WORLD_BORDER,
			0
		};
		if (this.axis == 0) {
			int temp;
			temp = this.pos [0];
			this.pos [0] = this.pos [1];
			this.pos [1] = temp;
			temp = this.size [0];
			this.size [0] = this.size [1];
			this.size [1] = temp;
			temp = start [0];
			start [0] = start [1];
			start [1] = temp;
			temp = span [0];
			span [0] = span [1];
			span [1] = temp;
		}
		this.boxPos = new int [] {
			world.pos [0] + start [0],
			world.pos [1] + start [1]
		};
		this.boxSize = new int [] {
			span [0],
			span [1]
		};
	}

	public abstract void render (GameContainer container, StateBasedGame game, Graphics context);

	public int getAxis () {
		return this.axis;
	}

	public int getAlignment () {
		return this.alignment;
	}

	public void setStart (int start) {
		this.pos [this.axis] = Math.max (Math.min (start, this.boxSize [this.axis]), 0);
	}

	public int getStart () {
		return this.pos [this.axis];
	}

	public void setSpan (int span) {
		int delta = Math.max (Math.min (span, this.boxSize [this.axis] + this.size [this.axis]), 0) - this.size [this.axis];
		this.boxSize [this.axis] -= delta;
		this.size [this.axis] += delta;
		this.setStart (this.getStart () - delta / 2);
	}

	public int getSpan () {
		return this.size [this.axis];
	}

}
