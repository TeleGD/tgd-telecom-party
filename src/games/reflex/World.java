package games.reflex;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState implements general.PlayersHandler{
	
	private int ID;
	public final static int GOAL=20;
	private ArrayList<Player> players;
	
	public World (int ID) {
		this.ID = ID;
	};
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) throws SlickException {
		for (Player p:players) {
			p.render(container, game, context);
			context.setBackground(Color.lightGray);
			context.setColor(Color.black);
			context.drawRect(0, 550, 1280, 170);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void setPlayers (List <general.Player> players) {
		this.players = new ArrayList <Player> ();
		int n = players.size();
		for (int i=0;i<n;i++) {
			this.players.add(new Player(players.get(i),n,i));
		}
	}
	

}
