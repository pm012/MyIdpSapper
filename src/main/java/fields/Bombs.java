package fields;

import java.util.HashSet;
import java.util.Set;

class Bombs {
	
	private int cols;
	private int rows;
	private int bombsCount;
	private Set<Coord> coordsOfBombs;
	
	public Bombs(int cols, int rows, int bombsCount) {
		this.cols = cols;
		this.rows = rows;
		this.bombsCount = bombsCount;
	}

	public boolean isBomb(Coord coord) {
		return coordsOfBombs.contains(coord);
	}
	
	//deploy bomb
	public void init() {
		fixBombsCount();
		coordsOfBombs = new HashSet<Coord>();
		for (int i = 0; i < bombsCount; i++) {
			placeBomb();
		}
	}

	public Set<Coord> getCoordsOfBombs() {
		return coordsOfBombs;
	}
	
	private void fixBombsCount() {
		int maxBombs = cols * rows / 2;
		if (bombsCount > maxBombs)
			bombsCount = maxBombs;
	}
	
	//place bomb 
	private void placeBomb() {
		Coord coord;
		do {
			coord = Utils.getRandomCoord(cols, rows);
		} while (coordsOfBombs.contains(coord));
		coordsOfBombs.add(coord);
	}

	public int getBombsCount() {
		return bombsCount;
	}
	

}
