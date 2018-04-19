package games.pathPainting;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Board {

	private int cellSize;
	private int rows;
	private int columns;
	private Cell[][] grid;

	public Board(World w, int minSide) {
		int height = w.getHeight();
		int width = w.getWidth();
		if (width<height) {
			cellSize = width/minSide;
			rows = height/cellSize;
			columns = minSide;
		} else {
			cellSize = height/minSide;
			rows = minSide;
			columns = width/cellSize;
		}
		this.grid=new Cell[rows][columns];
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				grid[i][j] = new Cell(w, j, i, cellSize);
			}
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				grid[i][j].render(container, game, context);
			}
		}
	}

	public int getCellSize() {
		return cellSize;
	}

	public Cell getCell(int posX, int posY) {
		return grid[posY][posX];
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

}
