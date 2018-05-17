package games.bomberman.bonuses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Bonus;
import games.bomberman.Player;
import games.bomberman.World;

public class Range extends Bonus {

	private boolean activated, deleted;
	private boolean add;
	private static Image sprite0;
	private static Image sprite1;
	private static Sound sound0;
	private static Sound sound1;

	static {
		try {
			sprite0 = new Image (World.DIRECTORY_IMAGES + "bonus_range0.png");
			sprite1 = new Image (World.DIRECTORY_IMAGES + "bonus_range1.png");
			sound0 = new Sound (World.DIRECTORY_SOUNDS_BONUS + "charger.ogg");
			sound1 = new Sound (World.DIRECTORY_SOUNDS_BONUS + "bemol.ogg");
		} catch (SlickException e) {
			e.printStackTrace ();
		}
	}

	public Range (int caseX, int caseY) {
		super (caseX, caseY);
		add = Math.random () < 0.7;
		super.setSprite (add ? sprite0 : sprite1);
		this.activated = false;
		this.deleted = false;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
	}

	public void activate (Player player) {
		if (!activated) {
			this.activated = true;
			if (add) {
				player.addRange (1);
				sound0.play (1, (float) 1);
			} else {
				player.addRange (-1);
				sound1.play (1, (float) 0.4);
			}
			this.deleted = true;
			//sound.play (1, (float) 0.4);
		}
	}

	public boolean isDeleted () {
		return this.deleted;
	}
	public boolean isActivated () {
		return this.activated;
	}

}
