package fields;

import java.util.Collection;

public class GameController {
	private Board board;
	private Bombs bombs;
	private GameState state;

	public GameController(int cols, int rows, int bombsCount) {
		board = new Board(cols, rows);
		bombs = new Bombs(cols, rows, bombsCount);
	}

	public GameState getState() {
		return state;
	}

	public void start() {
		board.init();
		bombs.init();
		state = GameState.PLAYED;
	}

	public Box getBox(Coord coord) {
		return board.getBox(coord);
	}

	public void pressedLeftButton(Coord coord) {
		if (gameOver()) {
			return;
		}
		openBox(coord);
		checkWinner();
	}

	private void checkWinner() {
		int closedCount = board.getClosedCount();
		System.out.println("ClosedCount = " + closedCount);
		if (board.getClosedCount() > 0) {
			return;
		}
		System.out.println("Board Flagged = " + coolectionToString(board.getFlagged()));
		System.out.println("Bombs = " + coolectionToString(bombs.getCoordsOfBombs()));
		if (!isEquals(board.getFlagged(), bombs.getCoordsOfBombs())) {
			System.out.println("NOT EQUALS");
			return;
		}
		System.out.println("WIIIIIIIIIIIIIIIIN");
		state = GameState.WINNER;
	}

	private String coolectionToString(Collection<Coord> coords) {
		StringBuilder result = new StringBuilder();
		for(Coord coord : coords) {
			result.append(coord.toString());
		}
		return result.toString();
	}
	private boolean isEquals(Collection<Coord> collection1, Collection<Coord> collection2) {
		if (collection1.size() != collection2.size()) {
			return false;
		}
		for (Coord coord1 : collection1) {
			if (!collection2.contains(coord1)) {
				return false;
			}
		}
		for (Coord coord2 : collection2) {
			if (!collection1.contains(coord2)) {
				return false;
			}
		}
		return true;
	}

	private void openBox(Coord coord) {
		if (board.getBox(coord) != Box.CLOSED) {
			return;
		}

		if (bombs.isBomb(coord)) {
			openBombs(coord);
		} else {
			Collection<Coord> coordsAround = Utils.getCoordsAround(board.getSize(), coord);
			int bombCount = getBombsCount(coordsAround);
			if (bombCount > 0) {
				board.setBox(coord, Box.getByNumber(bombCount));
			} else {
				board.setBox(coord, Box.ZERO);
				openBoxes(coordsAround);
			}
		}
	}
	
	private int getBombsCount(Collection<Coord> coords) {
		int result = 0;
		for (Coord coord : coords) {
			if (bombs.getCoordsOfBombs().contains(coord)) {
				result++;
			}
		}
		return result;
	}

	/*
	 * Попали на бомбу
	 */
	private void openBombs(Coord coord) {
		state = GameState.BOMBED;
		board.setBox(coord, Box.BOMBED);

		for (Coord coordOfBomb : bombs.getCoordsOfBombs()) {
			Box currentBox = board.getBox(coordOfBomb);
			if (currentBox != Box.FLAGGED && currentBox != Box.BOMBED) {
				board.setBox(coordOfBomb, Box.NOBOMB);
			} else {
				board.setBox(coordOfBomb, Box.BOMBED);
			}
		}
	}

	private void openBoxes(Collection<Coord> coords) {
		for (Coord coord : coords) {
			openBox(coord);
		}
	}

	public void pressedRightButton(Coord coord) {
		if (gameOver()) {
			return;
		}

		switch (board.getBox(coord)) {
		case FLAGGED:
			board.setBox(coord, Box.INFORM);
			break;
		case INFORM:
			board.setBox(coord, Box.CLOSED);
			break;
		case CLOSED:
			if (board.getFlagCount() < bombs.getBombsCount()) {
				board.setBox(coord, Box.FLAGGED);
			}
			break;
		default:

		}
		checkWinner();
	}

	private boolean gameOver() {
		return state == GameState.BOMBED;

	}

	public Box getBox(int col, int row) {
		return board.getBox(new Coord(col, row));
	}

}
