package fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

	private int cols;
	private int rows;
	private Box[][] matrix;

	public Board(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
	}

	public void init() {
		matrix = new Box[cols][rows];
		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				matrix[col][row] = Box.CLOSED;
			}
		}
	}

	public Box getBox(Coord coord) {
		return matrix[coord.getX()][coord.getY()];
	}

	public void setBox(Coord coord, Box box) {
		matrix[coord.getX()][coord.getY()] = box;
	}

	public Coord getSize() {
		return new Coord(cols, rows);
	}

	public int getFlagCount() {
		return getBoxCount(Box.FLAGGED);
	}
	
	public int getClosedCount() {
		return getBoxCount(Box.CLOSED);
	}

	private int getBoxCount(Box box) {
		int result = 0;
		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				if (box.equals(matrix[col][row])) {
					result++;
				}
			}
		}
		return result;
	}

	public Collection<Coord> getFlagged() {
		return getBoxCoords(Box.FLAGGED);
	}

	private Collection<Coord> getBoxCoords(Box box) {
		List<Coord> result = new ArrayList<Coord>();
		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				if (box.equals(matrix[col][row])) {
					result.add(new Coord(col, row));
				}
			}
		}
		return result;
	}

}
