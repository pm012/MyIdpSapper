package fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Utils {

	private static Random rnd = new Random();

	public static Boolean inRange(Coord size, Coord coord) {
		return coord.getX() >= 0 && coord.getX() < size.getX() && coord.getY() >= 0 && coord.getY() < size.getY();
	}

	public static Coord getRandomCoord(int cols, int rows) {
		return new Coord(rnd.nextInt(cols), rnd.nextInt(rows));
	}

	// draw around each mine
	public static Collection<Coord> getCoordsAround(Coord size, Coord coord) {
		ArrayList<Coord> coordListAround = new ArrayList<Coord>();
		for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++) {
			for (int y = coord.getY() - 1; y <= coord.getY() + 1; y++) {
				Coord around;
				if (inRange(size, around = new Coord(x, y))) {
					if (!around.equals(coord)) {
						coordListAround.add(around);
					}
				}
			}
		}

		return coordListAround;
	}

}
