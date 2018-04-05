package games.pong;

import java.util.ArrayList;
import java.util.List;

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

import general.AppPlayer;
import general.PlayersHandler;

public class World extends BasicGameState implements PlayersHandler {

	private int ID;
	
	int milieu[];
	int taille;
	
	Player[] players;
	private ArrayList<Ball> balls;
	private int nbJoueurs;
	private Image background;
	
	public World (int ID) {
		this.ID = ID;
	};
	public int getID() {
		return this.ID;
	};
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		milieu = new int[2];
		milieu[0]=container.getWidth()/2;
		milieu[1]=container.getHeight()/2;
		taille = Math.min(milieu[0]*2,milieu[1]*2);
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		balls = new ArrayList<Ball>();		
		background = new Image("images/Pong/backgroundDuck.png");
		
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
				nbJoueurs--;
			}
		}
		if (nbJoueurs<1) {
			System.out.println("Partie terminée");
			System.exit(0);
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
	@Override
	public void setPlayers(List<AppPlayer> appPlayers) {
		this.players = new Player[4];
		this.nbJoueurs=appPlayers.size();
		for (int i=0; i<4; i++) {
			if (i<appPlayers.size()) {
				this.players[i] = new Player(this, i, appPlayers.get(i));
			} else {
				this.players[i] = new Wall(this, i);
			}
		}
		
	}

}
