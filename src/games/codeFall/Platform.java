package games.codeFall;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Platform {

	private int size;
	private Cell[][] grid;
	private Random r = new Random();

	public Platform(World w, int s) {
		this.size=s;
		while (!(this.size%3==2 && this.size%2==1)) {
			this.size++;
		}
		this.grid=new Cell[this.size][this.size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				grid[i][j] = new Cell(w,(i+j<=size/3) || (i+j+2>(2*size)-size/3-1) || (Math.abs(i-j)+1>=2*(size/3+1) || i==0 || j==0 || i==size-1 || j==size-1),i,j, size);
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
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				grid[i][j].update(container, game, delta);
			}
		}
		if (r.nextInt((50-size>0)?50-size:1)==0) {
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
		if (r.nextInt(300)==0) {
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
			grid[i][j].addAmmo();
		}
	}

	public int getSize() {
		return size;
	}

	public Cell getCell(int posX, int posY) {
		return grid[posX][posY];
	}

}
