package fields;

public class GameController {
	//This is Fasade class
	private Flag flag;
	private	Bomb bomb;
	private GameState state;
	public GameState getState() {
		return state;
	}

	public GameController(int cols, int rows, int bombs) {
		Ranges.setSize(new Coord(cols, rows));		
		bomb=new Bomb(bombs);
		flag=new Flag();
	}
	
	public void start() {
		bomb.start();
		flag.start();
		state=GameState.PLAYED;
		
	}
	
	public Box getBox(Coord coord) {
		if(flag.get(coord)==Box.OPENED)
			return bomb.get(coord);
		else
		return	flag.get(coord);
	}

	public void pressedLeftButton(Coord coord) {		
		if(gameOver()) return;
		openBox(coord);
		checkWinner();
		
	}
	
	private void checkWinner() {
		if(state==GameState.PLAYED)
			if(flag.getCountOfClosedBoxes()==bomb.getTotalBombs())
				state=GameState.WINNER;
	}
	
	private void openBox(Coord coord) {
		switch (flag.get(coord)) {
		case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
		case FLAGED: return;
		case CLOSED:
			switch(bomb.get(coord)) {
			case ZERO: openBoxesAround(coord); return;
			case BOMB: openBombs(coord);return;			
			default: flag.setOpenedToBox(coord); return;
			}
		default:
			return;
		}
		
	}

	private void openBombs(Coord coord) {
		state = GameState.BOMBED;
		flag.setBombedToBox(coord);
		for(Coord location: Ranges.getAllCoords())
			if(bomb.get(location)==Box.BOMB)
				flag.setOpenedToClosedBombBox(location);
			else
				flag.setNoBombToFlagedSafeBox(location);
		
		
	}

	private void openBoxesAround(Coord coord) {
		flag.setOpenedToBox(coord);
		for(Coord around: Ranges.getCoordsAround(coord))
			openBox(around);
		
	}

	public void pressedRightButton(Coord coord) {
		if(gameOver()) return;
		flag.toggleFlaggedToBox(coord);
		
	}

	private boolean gameOver() {		
		return state==GameState.BOMBED;
		
	}
	
	public void setOpenedToClosedBoxesAroundNumber(Coord coord) {
		if(bomb.get(coord)!=Box.BOMB)
		if(flag.getCountOfFlaggedBoxesAround(coord)==bomb.get(coord).getNumber())
			for(Coord around: Ranges.getCoordsAround(coord))
				if((flag.get(around)==Box.CLOSED)&&(bomb.get(around)!=Box.BOMB))
					openBox(around  );
		
	}

}
