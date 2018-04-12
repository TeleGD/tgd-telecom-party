package games.clicker;

import general.AppGame;
import general.AppInput;
import general.AppPlayer;
import general.utils.FontUtils;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Playable;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

public class World extends BasicGameState implements Playable{

	public static final org.newdawn.slick.Font Font = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 18, true);
	private final int ID;
	private ArrayList<Player> players;
	private int count = 10000;
	private boolean finished = false;
	private int height;
	private int width;
	private int nbJoueursInit;
	
	public World(int id) {
		this.ID=id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// TODO Auto-generated method stub
		if (finished){
			for (int i=players.size()-1; i>=0;i--) {
				context.setColor(players.get(i).getColor());
				String s = players.get(i).getName()+" : "+players.get(i).getScore();
				context.drawString(s,width/2-Font.getWidth(s)/2,height/2-(Font.getHeight(s)+5)*(nbJoueursInit/2-i));
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition(), new FadeInTransition());
		} else if (!finished){
			count -= delta;
			for (Player player : players) {
				player.update(container, game, delta);
			}
			if (count < 0){
				finished = true;
			}
		}
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		players = new ArrayList<Player>();
		for(AppPlayer player : appGame.appPlayers){
			players.add(new Player(player)) ;
		}
		height = container.getHeight();
		width = container.getWidth();
		nbJoueursInit = appGame.appPlayers.size();
	}

	@Override
	public int getID() {
		return ID;
	}

}
