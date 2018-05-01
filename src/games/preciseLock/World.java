package games.preciseLock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppWorld;

public class World extends AppWorld {

	private Random r;
	private ArrayList<Player> players;
	private HashMap<Integer, Integer> classement;

	public World (int ID) {
		super (ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for (Player p : players) {
			p.render(container, game, context);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get(0).getControllerID();
		if (appInput.isKeyPressed(AppInput.KEY_ESCAPE)
				|| appInput.isButtonPressed(AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState(app.AppGame.PAGES_GAMES, new FadeOutTransition(), new FadeInTransition());
		} else {
			for (Player p : players) {
				if (!p.hasFinished()) {
					p.update(container, game, delta);
					if (p.hasFinished()) {
						int stillPlaying = 0;
						for (Player pl : players) {
							pl.slowRotation();
							stillPlaying+=(!pl.hasFinished())?1:0;
						}
						classement.put(p.getControllerID(),stillPlaying);
						if (classement.size()==players.size()) {
							end();
						}
					}
				}
			}
		}
	}

	public void end() {
		System.out.println(classement.toString());
	}

	@Override
	public void play(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		r = new Random();
		int nbPlayers = appGame.appPlayers.size();
		int width = (nbPlayers > 2) ? (container.getWidth()) / 2 : container.getWidth();
		int height = container.getHeight() / 2;
		int angle = -100 + r.nextInt(200);
		players = new ArrayList<Player>();
		classement = new HashMap<Integer, Integer>();
		for (int i = 0; i < appGame.appPlayers.size(); i++) {
			players.add(new Player(appGame.appPlayers.get(i), angle, (i < 2) ? 0 : width, (i % 2 == 0) ? 0 : height,
					width, height));
		}
	}

}
