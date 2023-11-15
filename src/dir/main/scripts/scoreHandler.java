package dir.main.scripts;

public class scoreHandler {
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
