package uiStaff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fields.Coord;
import fields.GameController;

public class MinerUI extends JFrame {

	private GameController game;

	private static final long serialVersionUID = -4325352426024932585L;
	private final int COLS = 9;
	private final int ROWS = 9;
	private final int BOMBS = 10;
	private final int IMAGE_SIZE = 50;
	private JPanel panel;
	private JLabel label;
	private Drawings draw = new Drawings(); // need review!!!!

	public MinerUI() {
		formBuilder();
	}

	private void formBuilder() {
		game = new GameController(COLS, ROWS, BOMBS);
		game.start();
		draw.setImages();

		// build frame
		initLabel().initPanel().buildFrame();

	}

	private MinerUI initLabel() {
		label = new JLabel("Hello");
		add(label, BorderLayout.SOUTH);
		return this;
	}

	private MinerUI initPanel() {
		panel = new JPanel() {
			private static final long serialVersionUID = 1;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int col = 0; col < COLS; col++) {
					for (int row = 0; row < ROWS; row++) {
						g.drawImage(game.getBox(col, row).getImage(), IMAGE_SIZE * col, IMAGE_SIZE * row, this);
					}
				}
			}
		};

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX() / IMAGE_SIZE;
				int y = e.getY() / IMAGE_SIZE;
				Coord coord = new Coord(x, y);
				if (e.getButton() == MouseEvent.BUTTON1)
					game.pressedLeftButton(coord);
				if (e.getButton() == MouseEvent.BUTTON3)
					game.pressedRightButton(coord);
				if (e.getButton() == MouseEvent.BUTTON2)
					game.start();

				label.setText(getMessage());
				new Dialog(game, MinerUI.this).askContinue();

				panel.repaint();

			}

			private String getMessage() {
				return game.getState().getMessage();
			}
		});
		panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
		add(panel);
		return this;

	}

	public void setGameController(GameController game) {
		this.game = game;
	}

	private void buildFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setIconImage(draw.getImage("icon"));
		pack();

	}

}
