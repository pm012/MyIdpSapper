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
import fields.Ranges;

public class MinerUI extends JFrame {

	private GameController game;
	
	private static final long serialVersionUID = -4325352426024932585L;
	private final int COLS = 9;
	private final int ROWS = 9;
	private final int BOMBS=10;
	private final int IMAGE_SIZE=50;
	private JPanel panel;
	private JLabel label;
	private Drawings draw=new Drawings(); //need review!!!!
	
	public MinerUI() {
		formBuilder();
	}

	private void formBuilder() {
		game=new GameController(COLS, ROWS, BOMBS);
		game.start();		
		draw.setImages();	
		
		//build frame
		initLabel().initPanel().buildFrame();
		
	}
	
	private MinerUI initLabel() {
		label=new JLabel("Hello");
		add(label, BorderLayout.SOUTH);
		return this;
	}
	
	private MinerUI initPanel() {
		panel= new JPanel() {			
			private static final long serialVersionUID = 1;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(Coord coord: Ranges.getAllCoords()) {
					g.drawImage(game.getBox(coord).getImage(), IMAGE_SIZE*coord.getX(), IMAGE_SIZE* coord.getY(),this);					
				}
			}
		};
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/IMAGE_SIZE;
				int y= e.getY()/IMAGE_SIZE;
				Coord coord = new Coord(x,y);
				if(e.getButton()==MouseEvent.BUTTON1)
					game.pressedLeftButton(coord);
				if(e.getButton()==MouseEvent.BUTTON3) 
					game.pressedRightButton(coord);
				if(e.getButton()==MouseEvent.BUTTON2)
					game.start();
				
				label.setText(getMessage());
				
				panel.repaint();
				
			}

			private String getMessage() {
				return game.getState().getMessage();
			}
		});
		panel.setPreferredSize(new Dimension(Ranges.getSize().getX()*IMAGE_SIZE, Ranges.getSize().getY()*IMAGE_SIZE));		
		add(panel);
		return this;
		
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
