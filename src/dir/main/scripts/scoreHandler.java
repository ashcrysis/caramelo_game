package dir.main.scripts;

public class scoreHandler {
	/**
	 * The score function increases the score by 50 every 2 seconds.
	 * 
	 * @return The method is not returning anything. It has a return type of `void`,
	 *         which means it does
	 *         not return a value.
	 */
	public static int score() {

		int segundos = 0;
		int score = 0;

		while (true) {
			try {
				Thread.sleep(1000); // Espera 1 segundo (1000 milissegundos)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			segundos++;

			if (segundos % 2 == 0) {
				score += 50;

			}
			System.out.println(segundos);

		}
	}
}
