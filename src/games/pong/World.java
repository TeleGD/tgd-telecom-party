package games.pong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.EmptyImageData;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.AppInput;
import general.Playable;
import general.utils.FontUtils;

import games.pong.bonus.*;

public class World extends BasicGameState implements Playable {

	public static final Font bonusFont = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 18, true);

	private int ID;

	public int milieu[];
	public int taille;

	Player[] players;
	private List<Ball> balls;
	private List<Bonus> bonus;
	private int nbJoueurs;
	private Image background;
	private Random r;

	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		try {
			background = new Image ("images/Pong/backgroundDuck.png");
		} catch (SlickException exception) {
			background = new Image (new EmptyImageData (0, 0));
		}
		milieu = new int[2];
		milieu[0]=container.getWidth()/2;
		milieu[1]=container.getHeight()/2;
		taille = Math.min(milieu[0]*2,milieu[1]*2);
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		container.getInput ().clearKeyPressedRecord ();
		r = new Random();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
			if (balls.size() == 0) {
				balls.add(new Ball(this));
			}
			if (bonus.size()<5 && r.nextInt(300)<1) {
				int nb = r.nextInt(9);
				switch (nb) {
				case 0:
					bonus.add(new BallRandomDirection(this));
					break;
				case 1:
					bonus.add(new BallRandomPosition(this));
					break;
				case 2:
					bonus.add(new BallSpeed(this));
					break;
				case 3:
					bonus.add(new PlayerSizeDown(this));
					break;
				case 4:
					bonus.add(new PlayerSizeUp(this));
					break;
				case 5:
					bonus.add(new PlayerSpeedDown(this));
					break;
				case 6:
					bonus.add(new PlayerSpeedUp(this));
					break;
				case 7:
					bonus.add(new PlayerLifeDown(this));
					break;
				case 8:
					bonus.add(new PlayerLifeUp(this));
					break;
				default:
					break;
				}
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
				game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
			}
			for (Ball b : balls) {
				b.update(container, game, delta);
				for (Bonus bo : bonus) {
					bo.update(container, game, delta ,b);
				}
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
			for (int i=0 ; i<bonus.size() ; i++) {
				if (bonus.get(i).isPicked()) {
					bonus.remove(i);
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(background,milieu[0]-taille/2,milieu[1]-taille/2,milieu[0]+taille/2,milieu[1]+taille/2,0,0,background.getWidth(),background.getHeight());
		for (Player p : players) {
			p.render(container, game, context);
		}
		for (Bonus b : bonus) {
			b.render(container, game, context);
		}
		for (Ball b : balls) {
			b.render(container, game, context);
		}
	}

	@Override
	public void initPlayers (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		nbJoueurs = appGame.appPlayers.size ();
		this.players = new Player [4];
		for (int i = 0; i < 4; i++) {
			this.players [i] = i < nbJoueurs ? new Player (this, i, appGame.appPlayers.get (i)) : new Wall (this, i);
		};
		this.balls = new ArrayList <Ball> ();
		for (int i = 0; i < 1; i++) {
			balls.add (new Ball (this));
		}
		this.bonus = new ArrayList <Bonus> ();
	}

}
