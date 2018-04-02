import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fields.Box;

public class JavaSweeper extends JFrame {
	
	private static final long serialVersionUID = -4325352426024932585L;
	private final int COLS = 15;
	private final int ROWS = 15;
	private final int IMAGE_SIZE=50;
	JPanel panel;

	public static void main(String[] args) {
		new JavaSweeper();

	}
	
	private JavaSweeper() {
		setImages();
		initPanel();
		initFrame();
	}
	
	private void initPanel() {
		panel= new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(Box box: Box.values()) {
					g.drawImage((Image)box.image, box.ordinal()*IMAGE_SIZE,0 ,this);
					
				}
				
			}
		};
		panel.setPreferredSize(new Dimension(COLS*IMAGE_SIZE, ROWS*IMAGE_SIZE));
		add(panel);
		
	}
	
	private void initFrame() {
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void setImages() {
		for(Box box : Box.values()) {
			box.image=getImage(box.name().toLowerCase());
			
		}
	}
	
	private Image getImage(String name) {
		 String filename = "img/"+name+".png";		 
		 ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		 
		 return icon.getImage();
	}

}
