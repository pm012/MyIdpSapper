package fields;

class Flag {
	private Matrix flagMap;
	private int countOfClosedBoxes;
	
	void start() {
		flagMap=new Matrix(Box.CLOSED);
		countOfClosedBoxes = Ranges.getSize().getX()*Ranges.getSize().getY();
		//for(Coord around: Ranges.getCoordsAround(new Coord(4,4)))
		//flagMap.set(new Coord(around.getX(),around.getY()), Box.OPENED);
	}
	
	Box get(Coord coord) {
		return flagMap.get(coord);
	}

	public void setOpenedToBox(Coord coord) {
		flagMap.set(coord, Box.OPENED);
		countOfClosedBoxes--;
		
	}
	void toggleFlaggedToBox(Coord coord) {
		switch(flagMap.get(coord)) {
		case FLAGED: setQuestionToBox(coord); break;
		case INFORM: setClosedToBox(coord); break;
		case CLOSED: setFlaggedToBox(coord); break;
		
		}
	}
	
	
	private void setQuestionToBox(Coord coord) {
		flagMap.set(coord, Box.INFORM);
		
	}

	private void setClosedToBox(Coord coord) {
		flagMap.set(coord, Box.CLOSED);
		
	}

	private void setFlaggedToBox(Coord coord) {
		flagMap.set(coord, Box.FLAGED);
		
	}

	int getCountOfClosedBoxes() {
		
		return countOfClosedBoxes;//make calculation of closed boxes
	}

	public void setBombedToBox(Coord coord) {
		flagMap.set(coord, Box.BOMBED);
		
	}

	public void setOpenedToClosedBombBox(Coord location) {
		if(flagMap.get(location)==Box.CLOSED)
			flagMap.set(location, Box.OPENED);
		
	}

	public void setNoBombToFlagedSafeBox(Coord location) {
		if(flagMap.get(location)==Box.FLAGED)
			flagMap.set(location, Box.NOBOMB);
		
	}
	
	

	int getCountOfFlaggedBoxesAround(Coord coord) {
		int count =0;
		for(Coord around: Ranges.getCoordsAround(coord))
			if(flagMap.get(around)==Box.FLAGED);
		count++;
		return count;
	}
	

}
