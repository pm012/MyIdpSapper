package uiStaff;

import java.awt.Image;

import javax.swing.ImageIcon;

import fields.Box;

public class Drawings {
	
	public void setImages() {
		for(Box box : Box.values()) {
			box.setImage(getImage(box.name().toLowerCase()));
			
		}
		
	}
	
	public  Image getImage(String name) {
		 String filename = "/img/"+name+".png";		 
		 ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		 return icon.getImage();
	}

}
