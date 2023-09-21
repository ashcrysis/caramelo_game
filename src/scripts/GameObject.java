package scripts;

public class GameObject {
    private int x; // X coordinate
    private int y; // Y coordinate
    private int width; // Width of the object
    private int height; // Height of the object

    // Constructor
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

	public void setY(int y) {
		this.y = y;
	}

	// Method to check if this object is touching another object
    public boolean isTouching(GameObject otherObject) {
        // Check if this object's bounding box intersects with the other object's bounding box
        return this.x < otherObject.x + otherObject.width &&
               this.x + this.width > otherObject.x &&
               this.y < otherObject.y + otherObject.height &&
               this.y + this.height > otherObject.y;
    }
}
