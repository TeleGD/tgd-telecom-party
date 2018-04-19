package games.maze.players;

import app.AppPlayer;

import games.maze.Player;
import games.maze.World;

public class HunterPlayer extends Player {

	public HunterPlayer (World w, AppPlayer aplayer) {
		super (w, aplayer);
		// TODO Auto - generated constructor stub
	}

	public void collideWithPlayer (Player p) {
		if (p instanceof VictimPlayer) {
			super.world.endGame (this);
		}
	}

}
