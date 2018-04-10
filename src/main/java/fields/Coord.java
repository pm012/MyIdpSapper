package fields;

public class Coord {
	private int x;
	private int y;

	public Coord(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// override equals to compare coordinates
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coord) {
			Coord to = (Coord) obj;
			return to.getX() == x && to.getY() == y;
		}
		return false;
	}
	
	

}
