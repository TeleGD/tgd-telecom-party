package games.clicker;

import general.AppGame;
import general.AppPlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Playable;

import java.util.ArrayList;

public class World extends BasicGameState implements Playable{

	private final int ID;
	private ArrayList<Player> players;
	
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
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		for (Player player : players){
			player.update(container,game,delta);
		}
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		players = new ArrayList<Player>();
		for(AppPlayer player : appGame.appPlayers){
			players.add(new Player(player)) ;
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
