
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Dice {
	private ArrayList<ImageIcon> diceFaces = new ArrayList<ImageIcon>();
	private int value;
	private boolean locked = false;

	public int rollDice() {

		int n = (int) (Math.random() * 6) + 1;

		// System.out.println(n);
		setValue(n);
		return n;

	}

	public void setValue(int n) {

		value = n;
		// System.out.println("Tärningens värde är: " + value);

	}

	public int getValue() {

		// System.out.println("Tärningens värde är: " + value);
		return value;

	}

	public void setLockedStatus() {

		if (locked == true) {

			locked = false;

		} else {

			locked = true;

		}

	}

	public void lockDice() {

		locked = true;

	}

	public void unLockDice() {

		locked = false;

	}

	public boolean getLockedStatus() {

		return locked;
	}

	public void addImages(int index, ImageIcon face) {

		diceFaces.add(index, face);

	}

	public ImageIcon getDiceFace(int diceFace) {

		return diceFaces.get(diceFace);

	}

}
