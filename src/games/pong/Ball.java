package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

public class Ball {
	
	private float speed;
	private float direction[];
	private float posX;
	private float posY;
	private int radius;
	
	public Ball(World world) {
		this.posX = world.milieu[0];
		this.posY = world.milieu[1];
		this.speed = 2;
		this.radius = 4;
		
		Random r = new Random();
		this.direction = new float[2];
		
		this.direction[0]=r.nextFloat();
		this.direction[1]=1-this.direction[0];
		if (r.nextBoolean()) {
			this.direction[0]=-this.direction[0];
		} 
		if (r.nextBoolean()) {
			this.direction[1]=-this.direction[1];
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillOval(posX,posY,radius,radius);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.move();
		this.collide();
	}
	
	public void move() {
		posX=posX+speed*direction[0];
		posY=posY+speed*direction[1];
	}
	
	public void collide() {
		speed=(float) (speed+0.05);
	}
}
