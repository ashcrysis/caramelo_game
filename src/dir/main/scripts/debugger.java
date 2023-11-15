package dir.main.scripts;

import dir.main.scripts.GameObject;

public class debugger {

	public static void main(String[] args) {
		// The code is creating two instances of the `GameObject` class, named `object1`
		// and `object2`. The
		// constructor of the `GameObject` class takes four parameters: `x`, `y`,
		// `width`, and `height`.
		GameObject object1 = new GameObject(10, 10, 25, 25);
		GameObject object2 = new GameObject(25, 25, 30, 0);

		// Check if object1 is touching object2
		if (object1.isTouching(object2)) {
			System.out.println("object1 is touching object2");
		} else {
			System.out.println("object1 is not touching object2");
		}

	}

}
