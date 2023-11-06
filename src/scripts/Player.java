package scripts;

public class Player extends GameObject {
    public int vidas = 3;
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
}
