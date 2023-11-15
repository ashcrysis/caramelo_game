package scripts.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGame extends JPanel implements ActionListener {
    // Player related variables
    private int targetY = 100;
    private int targetX = -70;
    private int animalForm = 0;
    private int lastY;
    private int lastY2;
    static Player player = new Player(100, 100, 50, 50);
    private int spriteX = player.getX();
    private int spriteY = player.getY();
    private int MIN_Y_DISTANCE = 100;

    // Scene related variables
    private Timer timer;
    private int obstacleType;
    static GameObject obstacle = new GameObject(500, 100, 50, 50);
    static GameObject obstacle2 = new GameObject(500, 300, 50, 50);
    static GameObject[] obstacles = { obstacle, obstacle2 };
    private JLabel label;
    // Image definitions
    ImageIcon cachorro_icon = new ImageIcon("src\\scripts\\main\\res\\dog (1).gif");
    Image cachorro_imagem = cachorro_icon.getImage();

    ImageIcon peixe_icon = new ImageIcon("src\\scripts\\main\\res\\pexinho (1).gif");
    Image peixe_imagem = peixe_icon.getImage();

    ImageIcon passaro_icon = new ImageIcon("src\\scripts\\main\\res\\pombo (1).gif");
    Image passaro_imagem = passaro_icon.getImage();

    ImageIcon pedra_icon = new ImageIcon("src\\scripts\\main\\res\\pedra (1).png");
    Image pedra_imagem = pedra_icon.getImage();

    ImageIcon canoa_icon = new ImageIcon("src\\scripts\\main\\res\\\\canoa (1).gif");
    Image canoa_imagem = canoa_icon.getImage();

    ImageIcon pasaro_icon = new ImageIcon("src\\scripts\\main\\res\\pasaro (1).gif");
    Image pasaro_imagem = pasaro_icon.getImage();

    ImageIcon ded_icon = new ImageIcon("src\\scripts\\main\\res\\ded (1).png");
    Image ded_imagem = ded_icon.getImage();

    ImageIcon background_icon = new ImageIcon("src\\scripts\\main\\res\\mapa (1).gif");
    Image background_imagem = background_icon.getImage();

    ImageIcon background_s_icon = new ImageIcon("src\\scripts\\main\\res\\mapa_parado.png");
    Image background_s_imagem = background_s_icon.getImage();

    ImageIcon heart_icon = new ImageIcon("src\\scripts\\main\\res\\coracao (1).png");
    Image heart_imagem = heart_icon.getImage();

    // Handlers variables
    private static int segundos = 0;
    private static int pontos = 0;
    private static boolean colisao = true;

    public SimpleGame() {
        timer = new Timer(10, this);
        timer.start();

        setPreferredSize(new Dimension(600, 400));
        setFocusable(true);

        label = new JLabel("Posição: " + player.getY());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);

        setLayout(new BorderLayout());

        add(label, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background = new JLabel("", new
        // ImageIcon(getClass().getResource("mapa-feio.gif")), JLabel.CENTER);
        // background.setPreferredSize(new Dimension(600, 400));
        // add(background);

        // Player logic to switch up colors / forms
        if (player.getVidas() > 0) {
            g.drawImage(background_imagem, 0, 0, 600, 400, null);

            switch (animalForm) {
                case 0:
                    g.drawImage(cachorro_imagem, spriteX, spriteY, player.getWidth(), player.getHeight(), null);
                    break;
                case 1:
                    g.drawImage(passaro_imagem, spriteX, spriteY, player.getWidth(), player.getHeight(), null);
                    break;
                case 2:
                    g.drawImage(peixe_imagem, spriteX, spriteY, player.getWidth(), player.getHeight(), null);
                    break;
            }

        } else {
            g.drawImage(background_s_imagem, 0, 0, 600, 400, null);
            g.drawImage(ded_imagem, spriteX, spriteY, player.getWidth(), player.getHeight(), null);

        }

        heartsHandler(g);

        for (GameObject obstacle : obstacles) {
            switch (obstacle.getY()) {

                case 100:
                    g.drawImage(pasaro_imagem, obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
                            obstacle.getHeight(), null);
                    break;
                case 200:
                    g.drawImage(pedra_imagem, obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
                            obstacle.getHeight(), null);
                    break;
                case 300:
                    g.drawImage(canoa_imagem, obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
                            obstacle.getHeight(), null);
                    break;

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (player.getVidas() <= 0) {
            showGameOverDialog();

        }
        targetY = player.targetY;

        updatePlayerPosition(targetY);

        updateObstaclePosition(obstacle, obstacle2, lastY, 6);
        updateObstaclePosition(obstacle2, obstacle, lastY2, 6);

        handleCollision(obstacle);
        handleCollision(obstacle2);

        // Atualize o valor da JLabel
        scoreHandler score = new scoreHandler();
        label.setText("Pontuação: " + pontos);

        repaint();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Caramelo Adventures : Três espiritos, uma missão!");
            SimpleGame game = new SimpleGame();
            frame.add(game);
            game.addKeyListener(player);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
            TocaSom music = new TocaSom();
            game.requestFocus();

            music.startAudioLoop();
        });

        while (true) {
            if (player.getVidas() <= 0) {
                segundos = 0;
                pontos = 0;
                colisao = false;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            segundos++;

            if (segundos % 2 == 0) {
                Random rand = new Random();
                pontos += 50 + rand.nextInt(55);
            }

            if (segundos % 2 == 0) {
                // Logic to spawn game obstacle
                colisao = true;
            }
        }

    }

    private void handleCollision(GameObject obstacle) {
        if (player.isTouching(obstacle) && colisao) {
            if (colisao) {
                player.setVidas(player.getVidas() - 1);
            }
            colisao = false;

            System.out.println("Vidas: " + player.getVidas());
            Random rand = new Random();
            pontos -= rand.nextInt(40);
        }
    }

    private void updateObstaclePosition(GameObject obstacle, GameObject otherObstacle, int lastY, int speed) {
        if (obstacle.getX() > targetX && player.getVidas() > 0) {
            obstacle.setX(obstacle.getX() - speed);
        }

        if (obstacle.getX() == targetX) {
            obstacle.setX(620);
            Random rand = new Random();

            int pos = obstacle.getY();
            pos = getRandomYPosition(pos, lastY);

            // Ensure the new Y position is different from the other obstacle
            if (Math.abs(pos - otherObstacle.getY()) < MIN_Y_DISTANCE) {
                pos = getRandomYPosition(pos, otherObstacle.getY());
            }

            obstacle.setType(obstacleType);
            obstacle.setY(pos);
        }
    }

    private void updatePlayerPosition(int targetY) {
        if (player.getY() < targetY) {
            spriteY += 5;
            player.setY(spriteY);
        } else if (player.getY() > targetY) {
            spriteY -= 5;
            player.setY(spriteY);
        }

        if (player.getY() == 100) {
            animalForm = 1;
        } else if (player.getY() == 200) {
            animalForm = 0;
        } else if (player.getY() == 300) {
            animalForm = 2;
        }
    }

    private void heartsHandler(Graphics g) {
        for (int i = 0; i < player.getVidas(); i++) {
            int x = 10 + i * 40; // Adjust the spacing between hearts as needed
            int y = 360;
            int width = 32;
            int height = 32;

            g.drawImage(heart_imagem, x, y, width, height, null);
        }

    }

    private int getRandomYPosition(int currentY, int lastY) {
        Random rand = new Random();
        int newY = rand.nextInt(3) + 1;

        // Ensure newY is different from currentY and lastY
        while (newY == currentY || newY == lastY) {
            newY = rand.nextInt(3) + 1;
        }
        lastY = newY;
        return Integer.parseInt(Integer.toString(newY) + "00");
    }

    private void showGameOverDialog() {
        int option = JOptionPane.showOptionDialog(
                this,
                "You are dead!",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[] { "Restart", "Exit" },
                "Restart");

        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        // Reset game variables and start a new game
        player.setVidas(3);
        segundos = 0;
        pontos = 0;
        colisao = true;
        obstacle.setX(620);
        obstacle2.setX(620);
        updateObstaclePosition(obstacle, obstacle2, lastY, 6);
        updateObstaclePosition(obstacle2, obstacle, lastY2, 6);
        // Repaint the game
        repaint();
    }

}
