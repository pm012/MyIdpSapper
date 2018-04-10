package fields;

public enum GameState {
	PLAYED("Go ahead!"),
	BOMBED("Game over!"),
	WINNER("Congratulations, you have won the game!");
	
	private String message;

	private GameState(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
