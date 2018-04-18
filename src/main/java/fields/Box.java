package fields;

import java.awt.Image;

public enum Box {
	ZERO,
	NUM1,
	NUM2,
	NUM3,
	NUM4,
	NUM5,
	NUM6,
	NUM7,
	NUM8,
	BOMB,
	CLOSED,
	FLAGGED,
	BOMBED,
	INFORM,
	OPENED,
	NOBOMB;
	
	private Image image;

	Box getNextNumberBox() {
		return Box.values()[this.ordinal() + 1];
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public static Box getByNumber(int number) {
		for (Box box : Box.values()) {
			if (box.ordinal() == number) {
				return box;
			}
		}
		return null;
	}

}
