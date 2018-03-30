package games.pong;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class World extends BasicGameState {

	private int ID;
	
	int milieu[];
	int taille;
	
	Player[] players;
	private ArrayList<Ball> balls;
	
	private Image background;
	
	public World (int ID) {
		this.ID = ID;
	};
	public int getID() {
		return this.ID;
	};
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		
		//Il faudra voir s'il faut bouger ces inits dans enter(...) si ca prend trop de temps
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		players = new Player[4];
		balls = new ArrayList<Ball>();
		milieu = new int[2];
		
		milieu[0]=container.getWidth()/2;
		milieu[1]=container.getHeight()/2;
		taille = Math.min(milieu[0]*2,milieu[1]*2);
		
		background = new Image("images/Pong/backgroundDuck.png");
		
//		players.add(new Wall(this, 0));
//		players.add(new Wall(this, 1));
//		players.add(new Wall(this, 2));
//		players.add(new Wall(this, 3));
		players[0] = (new Player(this, 0, Color.blue));
		players[1] = (new Player(this, 1, Color.red));
		players[2] = (new Player(this, 2, Color.green));
		players[3] = (new Player(this, 3, Color.yellow));
		for (int i=0; i<1; i++) {
			balls.add(new Ball(this));
		}
	}


	public void startGame(){
	}

	public void startAgain() throws SlickException{
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(background,milieu[0]-taille/2,milieu[1]-taille/2,milieu[0]+taille/2,milieu[1]+taille/2,0,0,background.getWidth(),background.getHeight());
		for (Player p : players) {
			p.render(container, game, g);
		}
		for (Ball b : balls) {
			b.render(container, game, g);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (balls.size() == 0) {
			balls.add(new Ball(this));
		}
		for (int i=0; i < players.length ; i++) {
			players[i].update(container, game, delta);
			if (players[i].getVies()<=0 && players[i].getId()>=0) {
				players[i]=new Wall(this, i);
			}
		}
		for (Ball b : balls) {
			b.update(container, game, delta);
		}
		for (int i=0 ; i<balls.size() ; i++) {
			int out = balls.get(i).isOut();
			if (out != -1) {
				balls.remove(i);
				if (out<4) {
					players[out].loseVie();
				}
			}
		}
	}

	public void keyReleased(int key, char c) {
	}


	public void keyPressed(int key, char c) {
		if(key==Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}

}
