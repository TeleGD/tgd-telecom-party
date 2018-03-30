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
	private Player[] players;
	
	public Ball(World world) {
		this.milieu=world.milieu;
		this.taille=world.taille;
		this.players=world.players;
		this.posX = world.milieu[0];
		this.posY = world.milieu[1];
		this.speed = 2;
		this.radius = 10;
		
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
		g.setColor(new Color(200,200,200));
		g.fillOval(posX-radius/2,posY-radius/2,radius,radius);
		g.setColor(Color.black);
		g.drawOval(posX-radius/2,posY-radius/2,radius,radius);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.collide();
		this.move();
		// this.isOut();
	}
	
	public void move() {
		posX=posX+speed*direction[0];
		posY=posY+speed*direction[1];
	}
	
	public void collide() {
		
		speed=(float) (speed+0.002);
		
		
		if (posX-radius/2<milieu[0]-taille/2+20+players[0].getLongueurBarre()/2) {
			/*
			 * A gauche 
		 	 */
			Player p = players[0];
			if (p.getId()<0) {
				// Il y a un rebond sur un mur
				direction[0]=-direction[0];
			} else if (posY>p.getBarPosMove()-p.getHauteurBarre()/2 && posY<p.getBarPosMove()+p.getHauteurBarre()/2 && posX+radius/2>milieu[0]-taille/2+20-p.getLongueurBarre()/2){ 
				// Il y a rebond sur le joueur
				direction[0]=-direction[0];
			}
		} else if (posX+radius/2<milieu[0]+taille/2-20-players[1].getLongueurBarre()/2) {
			/*
			 * A droite
		 	 */
			Player p = players[1];
			if (p.getId()<0) {
				// Il y a un rebond sur un mur
				direction[0]=-direction[0];
			} else if (posY>p.getBarPosMove()-p.getHauteurBarre()/2 && posY<p.getBarPosMove()+p.getHauteurBarre()/2 && posX-radius/2>milieu[0]+taille/2-20+p.getLongueurBarre()/2){ 
				// Il y a rebond sur le joueur
				direction[0]=-direction[0];
			}
		} else if (posY-radius/2<milieu[1]-taille/2+20+players[2].getHauteurBarre()/2) {
			/*
			 * En haut
		 	 */
			Player p = players[2];
			if (p.getId()<0) {
				// Il y a un rebond sur un mur
				direction[1]=-direction[1];
			} else if (posX>p.getBarPosMove()-p.getLongueurBarre()/2 && posX<p.getBarPosMove()+p.getLongueurBarre()/2 && posY+radius/2>milieu[1]-taille/2+20-p.getHauteurBarre()/2){ 
				// Il y a rebond sur le joueur
				direction[1]=-direction[1];
			}
		} else if (posY+radius/2<milieu[1]+taille/2-20-players[3].getHauteurBarre()/2) {
			/*
			 * En bas
		 	 */
			Player p = players[3];
			if (p.getId()<0) {
				// Il y a un rebond sur un mur
				direction[1]=-direction[1];
			} else if (posX>p.getBarPosMove()-p.getLongueurBarre()/2 && posX<p.getBarPosMove()+p.getLongueurBarre()/2 && posY+radius/2>milieu[1]+taille/2-20+p.getHauteurBarre()/2){ 
				// Il y a rebond sur le joueur
				direction[1]=-direction[1];
			}
		} 

	}
}
