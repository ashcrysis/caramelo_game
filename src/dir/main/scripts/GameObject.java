package dir.main.scripts;

public class GameObject {
	private int x; // X coordinate
	private int y; // Y coordinate
	private int width; // Width of the object
	private int height; // Height of the object
	private int type;
	// Constructor

	// The `public GameObject(int x, int y, int width, int height)` is a constructor
	// for the `GameObject`
	// class. It is used to create a new instance of the `GameObject` class and
	// initialize its properties
	// (`x`, `y`, `width`, and `height`) with the values passed as arguments to the
	// constructor.
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setY(int y) {
		this.y = y;
	}

	// The `isTouching` method is used to check if the current `GameObject` is
	// touching another
	// `GameObject`. It takes another `GameObject` as a parameter and checks if
	// their bounding boxes
	// intersect.
	public boolean isTouching(GameObject otherObject) {
		// Check if this object's bounding box intersects with the other object's
		// bounding box
		return this.x < otherObject.x + otherObject.width &&
				this.x + this.width > otherObject.x &&
				this.y < otherObject.y + otherObject.height &&
				this.y + this.height > otherObject.y;
	}
}
