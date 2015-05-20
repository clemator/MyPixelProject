package math;

public class MyVector {
	
	private int[][] _vec;
	private int _width;
	private int _height;
	
	public MyVector() {
		this._vec = new int[10][10];
		this._width = 10;
		this._height = 10;
	}
	
	public MyVector(int width, int height) {
		this._vec = new int[width][height];
		this._width = width;
		this._height = height;
	}

	public int[][] getVec() {
		return this._vec;
	}
	
	public void setVec(int line, int column, int value) {
		this._vec[line][column] = value;
	}
	
	public int getWidth() {
		return this._width;
	}
	
	public int getHeight() {
		return this._height;
	}
	
	public void setInt(int x, int y, int value) {
			this._vec[x][y] = value;
	}
}
