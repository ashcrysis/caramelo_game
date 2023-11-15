package scripts.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {
	public int vidas = 3;
	private int[] yPositions = { 100, 200, 300 };
	public int targetY = 100;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP && vidas > 0) {
			String pos = Integer.toString(getY());
			pos = pos.replace("0", "");
			int pos2 = Integer.parseInt(pos) - 1;
			if (getY() != yPositions[0]) {
				pos2 = pos2 - 1;
				try {
					targetY = yPositions[pos2];
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		if (key == KeyEvent.VK_DOWN && vidas > 0) {
			String pos = Integer.toString(getY());
			pos = pos.replace("0", "");
			int pos2 = Integer.parseInt(pos) - 1;
			if (getY() != yPositions[2]) {
				pos2 = pos2 + 1;
				try {
					targetY = yPositions[pos2];
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method
		// 'keyReleased'");
	}
}
