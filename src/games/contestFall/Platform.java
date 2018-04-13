package games.contestFall;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Platform {
	
	private int size;
	private Cell[][] grid;
	
	public Platform(World w, int size) {
		this.size=(size%2==0)?size+1:size;
		this.grid=new Cell[size][size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				grid[i][j] = new Cell((i+j+2<=size/3+1) || (i+j+2>(2*size)-size/3) || (Math.abs(i-j)>=2*(size/3)),i,j);
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				grid[i][j].render(container, game, context);
			}
		}
	}
	
}
