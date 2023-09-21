package scripts;

public class handler {

public static void main(String[] args) {
	// Create two GameObject instances
	GameObject object1 = new GameObject(10, 10, 25, 10);
	GameObject object2 = new GameObject(25, 25, 30, 30);

	// Check if object1 is touching object2
	if (object1.isTouching(object2)) {
	    System.out.println("object1 is touching object2");
	} else {
	    System.out.println("object1 is not touching object2");
	}

}
	
}
