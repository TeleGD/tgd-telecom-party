package games.contestFall;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Platform {
	
	private int size;
	private Cell[][] grid;
	private Random r = new Random();
	
	public Platform(World w, int size) {
		this.size=(size%2==0)?size+1:size;
		this.grid=new Cell[size][size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				grid[i][j] = new Cell(w,(i+j+2<=size/3+1) || (i+j+2>(2*size)-size/3) || (Math.abs(i-j)>=2*(size/3)),i,j, size);
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
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (r.nextInt(50)==0) {
			int state = 0;
			int cpt = 0;
			int i=0;
			int j=0;
			while (state == 0 && cpt<10) {
				cpt++;
				i = r.nextInt(size);
				j = r.nextInt(size);
				state=grid[i][j].getState();
			}
			grid[i][j].degrade(r.nextInt(4));
		}
		
		
	}
	
}
