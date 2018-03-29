package games.reflex;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import general.AppPlayer;

public class Player {
	private static final int nbImages=3;
	private int pos; //pos designe le milieu
	private int state;
	private Color color;
	private Image image;
	private Random rand;

	public Player(AppPlayer appPlayer, int n, int i) {
		state=0;
		rand = new Random();
		pos=1280*(2*i+1)/(2*n);
		try {
			image = new Image("image/reflex/p"+rand.nextInt(nbImages)+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		image.draw(pos-image.getWidth()/2, 550-state*image.getHeight()/World.GOAL);
	}
}
