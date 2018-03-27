package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
	
	private float speed;
	private float direction[];
	private float posX;
	private float posY;
	private int radius;
	private int milieu[];
	private int taille;
	private ArrayList<Player> players;
	
	public Ball(World world) {
		this.milieu=world.milieu;
		this.taille=world.taille;
		this.players=world.players;
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
		
		if (posX<milieu[0]-taille/2+20+radius && posX>milieu[0]-taille/2+20-players.get(0).getLongueurBarre()) {
			Player p = players.get(0);
			if (p.getId()<0 || (posY>p.getBarPosMove()-p.getHauteurBarre()/2 && posY<p.getBarPosMove()+p.getHauteurBarre()/2) ) {
				if (p.getId()<0) {
					direction[0]=-direction[0];
				} else {
					
				}
			}
		} else if (posX>milieu[0]+taille/2-20-radius && posX<milieu[0]+taille/2-20+players.get(1).getLongueurBarre()) {
			Player p = players.get(1);
			if (p.getId()<0 || (posY>p.getBarPosMove()-p.getHauteurBarre()/2 && posY<p.getBarPosMove()+p.getHauteurBarre()/2)) {
				if (p.getId()<0) {
					direction[0]=-direction[0];
				} else {
					
				}
			}
		} else if (posY<milieu[1]-taille/2+20+radius && posY>milieu[1]-taille/2+20-players.get(2).getLongueurBarre()) {
			Player p = players.get(2);
			if (p.getId()<0 || (posX>p.getBarPosMove()-p.getLongueurBarre()/2 && posY<p.getBarPosMove()+p.getLongueurBarre()/2) ) {
				if (p.getId()<0) {
					direction[1]=-direction[1];
				} else {
					
				}
			}
		} else if (posY>milieu[1]+taille/2-20-radius && posY<milieu[1]+taille/2-20+players.get(0).getLongueurBarre()) {
			Player p = players.get(3);
			if (p.getId()<0 || (posY>p.getBarPosMove()-p.getLongueurBarre()/2 && posY<p.getBarPosMove()+p.getLongueurBarre()/2)) {
				if (p.getId()<0) {
					direction[1]=-direction[1];
				} else {
					
				}
			}
		}

	}
}
