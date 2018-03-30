package games.pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	protected int id;
	
	protected int vies;
	protected Color couleur;
	protected int barPosFixe;
	protected int barPosMove;
	private int speed;
		
	private int longueurBarre;
	private int hauteurBarre;
	
	private int longBarInit = 80;
	protected int largBarInit = 8;
	
	protected int taille;
	protected int milieu[];

	private boolean toucheGauche;
	private boolean toucheDroite;
	
	
	
	public Player(World world, int id, Color couleur) {
		this.id = id;
		this.vies = 5;
		this.couleur=couleur;
		this.milieu = world.milieu;
		this.taille = world.taille;
		
		
		switch (id) {
			case 0: 
				/*
				*Joueur à gauche :
				* - Bouge selon l'axe Y
				* - Position fixe : bord gauche + 20
				* - Barre verticale
				*/	
				this.barPosFixe = world.milieu[0]-taille/2+20;
				this.barPosMove = world.milieu[1];
				this.longueurBarre = largBarInit;
				this.hauteurBarre = longBarInit;
				break;
			case 1: 
				/*
				*Joueur à droite :
				* - Bouge selon l'axe Y
				* - Position fixe : bord droit - 20
				* - Barre verticale
				*/	
				this.barPosFixe = world.milieu[0]+taille/2-20;
				this.barPosMove = world.milieu[1];
				this.longueurBarre = largBarInit;
				this.hauteurBarre = longBarInit;
				break;
			case 2: 
				/*
				*Joueur en haut :
				* - Bouge selon l'axe X
				* - Position fixe : bord haut + 20
				* - Barre verticale
				*/	
				this.barPosFixe = world.milieu[1]-taille/2+20;
				this.barPosMove = world.milieu[0];
				this.longueurBarre = longBarInit;
				this.hauteurBarre = largBarInit;
				break;
			case 3:
				/*
				*Joueur en bas :
				* - Bouge selon l'axe X
				* - Position fixe : bord bas - 20
				* - Barre verticale
				*/	
				this.barPosFixe = world.milieu[1]+taille/2-20;
				this.barPosMove = world.milieu[0];
				this.longueurBarre = longBarInit;
				this.hauteurBarre = largBarInit;
				break;
			default:
				this.vies=0;
				break;
		}
		
		System.out.println("Player "+id+" : "+barPosMove+", "+barPosFixe+", "+longueurBarre+", "+hauteurBarre+", "+couleur);
	}
		
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(couleur);
		if (id==0 || id==1) {
			g.fillRect(barPosFixe-longueurBarre/2, barPosMove-hauteurBarre/2, longueurBarre, hauteurBarre);
		} else if (id==2 || id==3){
			g.fillRect(barPosMove-longueurBarre/2, barPosFixe-hauteurBarre/2, longueurBarre, hauteurBarre);
		}
		// Affiche vies
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.move();
	}

	public void move() {
		if (toucheGauche && (id==2 | id==3)) {
			if (barPosMove-longueurBarre/2-speed < milieu[0] - taille/2 + 20 ) {
				barPosMove=milieu[0]-taille/2+20+longueurBarre/2;
			} else {
				barPosMove=barPosMove-speed;
			}
		} else if (toucheDroite && (id==2 || id==3)) {
			if (barPosMove+longueurBarre/2+speed > milieu[0] + taille/2 - 20 ) {
				barPosMove=milieu[0]+taille/2-20-longueurBarre/2;
			} else {
				barPosMove=barPosMove+speed;
			}
		} else if (toucheGauche && (id==0 || id==1)) {
			if (barPosMove-longueurBarre/2-speed < milieu[1] - taille/2 + 20 ) {
				barPosMove=milieu[1]-taille/2+20+longueurBarre/2;
			} else {
				barPosMove=barPosMove-speed;
			}
		} else if (toucheDroite && (id==0 || id==1)) {
			if (barPosMove+longueurBarre/2+speed > milieu[1] + taille/2 - 20 ) {
				barPosMove=milieu[1]+taille/2-20-longueurBarre/2;
			} else {
				barPosMove=barPosMove+speed;
			}
		}
	}
	
	public void loseVie() {
		this.vies--;
	}
	
	public int getBarPosMove() {
		return barPosMove;
	}
	
	public int getBarPosFixe() {
		return barPosFixe;
	}
	
	public int getLongueurBarre() {
		return longueurBarre;
	}
	
	public int getHauteurBarre() {
		return hauteurBarre;
	}
	
	public int getVies() {
		return vies;
	}
	
	public int getId() {
		return this.id;
	}
}
