package map;

import java.util.Random;

import math.Position;

public class Map {
	public final int _width;
	public final int _height;
	public final int defaultSize = 10;
	private final int _threshold = 1000;
	private Cell[][] _cells;
	
	public Map(int width, int height) {
		if (width > 0 && height > 0) {
			this._width = width;
			this._height = height;
			this._cells = new Cell[width][height];
		}
		else {
			this._width = defaultSize;
			this._height = defaultSize;
			this._cells = new Cell[defaultSize][defaultSize];
		}
	}
	
	public Cell getCell(int x, int y) {
		return this._cells[x][y];
	}
	
	/**
	 * Generate the map by creating the cell matrix
	 * @author Clemator
	 * @param earthRate Integer from 0 to 999
	 */
	public void generateMap(int earthRate) {
		Random r = new Random();
		for (int i = 0; i < this._width; i++) {
			for (int j = 0; j < this._height; j++) {
				if (r.nextInt(this._threshold) < earthRate) {
					Cell c = new Cell(new Position(i, j), Cell.Type.DIRT, Cell.State.EMPTY);
					this._cells[i][j] = c;
				}
				else {
					Cell c = new Cell(new Position(i, j), Cell.Type.WATER, Cell.State.EMPTY);
					this._cells[i][j] = c;
				}
			}
		}
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i < this._width; i++) {
			for (int j = 0; j < this._height; j++) {
				res += this._cells[i][j].toString();
			}
			res += System.lineSeparator();
		}
		return res;
	}
	
	public String resourceToDisplay() {
		String res = "";
		for (int i = 0; i < this._width; i++) {
			for (int j = 0; j < this._height; j++) {
				if (j == 0)
					res += "|";
				res += this._cells[i][j].resourceToDisplay() + "|";
			}
			res += System.lineSeparator();
		}
		return res;
	}
	
	public String typeToDisplay() {
		String res = "";
		for (int i = 0; i < this._width; i++) {
			for (int j = 0; j < this._height; j++) {
				if (j == 0)
					res += "|";
				res += this._cells[i][j].typeToDisplay() + "|";
			}
			res += System.lineSeparator();
		}
		return res;
	}
}
