package fields;

public class GameController {
	//This is Fasade class
	
	private	Bomb bomb;
	public GameController(int cols, int rows, int bombs) {
		Ranges.setSize(new Coord(cols, rows));
		bomb=new Bomb(bombs);
	}
	
	public void start() {
		bomb.start();
		
	}
	
	public Box getBox(Coord coord) {
		return	bomb.get(coord);
	}

}
