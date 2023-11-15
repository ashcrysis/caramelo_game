package scripts.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends GameObject implements ActionListener {
	public int vidas = 3;
	private int[] yPositions = { 100, 200, 300 };
	private Player player;
	public int targetY = 100;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				int key = evt.getKeyCode();

				if (key == java.awt.event.KeyEvent.VK_UP && player.vidas > 0) {
					String pos = Integer.toString(player.getY());
					pos = pos.replace("0", "");
					int pos2 = Integer.parseInt(pos) - 1;
					if (player.getY() != yPositions[0]) {
						pos2 = pos2 - 1;
						try {
							targetY = yPositions[pos2];
						} catch (Exception e) {

						}
					}

				}
				if (key == java.awt.event.KeyEvent.VK_DOWN && player.vidas > 0) {
					String pos = Integer.toString(player.getY());
					pos = pos.replace("0", "");
					int pos2 = Integer.parseInt(pos) - 1;
					if (player.getY() != yPositions[2]) {
						pos2 = pos2 + 1;
						try {
							targetY = yPositions[pos2];
						} catch (Exception e) {

						}

					}

				}
			}
		});
	}

	private void addKeyListener(KeyAdapter keyAdapter) {
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}
}
