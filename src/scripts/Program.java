package scripts;

public class Program { 
	  public static void main(String[] args) {
		  
		  Player player = new Player(100, 100, 300, 50);
		  GameObject obstaculo = new Player(200, 100, 50, 50);
		  
		  System.out.println(player.isTouching(obstaculo));
		  
	  }
}