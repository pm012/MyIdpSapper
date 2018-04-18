package uiStaff;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fields.GameController;
import fields.GameState;

public class Dialog extends JFrame implements Modalable {
	GameController game;
	MinerUI ui;

	public Dialog(GameController game, MinerUI ui) {
		this.game = game;
		this.ui = ui;

	}

	public GameController askContinue() {
		GameState state = game.getState();
		if ((state == GameState.BOMBED) || (state == GameState.WINNER))
			if (JOptionPane.YES_OPTION == new Dialog(game, ui).showDialog("Message",
					state.getMessage() + "\n Would you like to continue?")) {
				game.start();

			}

		return game;
	}

	@Override
	public int showDialog(String messageHeader, String messageBody) {

		return JOptionPane.showConfirmDialog(ui, messageBody, messageHeader, JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

}
