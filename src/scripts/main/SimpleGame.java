package scripts.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private JLabel background;
    ImageIcon cachorro_icon = new ImageIcon("src\\scripts\\main\\dog (1).gif");
    Image cachorro_imagem = cachorro_icon.getImage();

    ImageIcon peixe_icon = new ImageIcon("src\\scripts\\main\\pexinho (1).gif");
    Image peixe_imagem = peixe_icon.getImage();

    ImageIcon passaro_icon = new ImageIcon("src\\scripts\\main\\pombo (1).gif");
    Image passaro_imagem = passaro_icon.getImage();
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
        if (player.vidas > 0) {
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
        }
        for (GameObject obstacle : obstacles) {
            switch (obstacle.getY()) {

                case 100:
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
                    break;
                case 200:
                    g.setColor(Color.ORANGE);
                    g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
                    break;
                case 300:
                    g.setColor(Color.CYAN);
                    g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
                    break;

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            JFrame frame = new JFrame("Simple Game");
            SimpleGame game = new SimpleGame();
            frame.add(game);
            game.addKeyListener(player);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);

        });

        while (player.vidas > 0) {
            try {
                Thread.sleep(1000); // Espera 1 segundo (1000 milissegundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            segundos++;

            if (segundos % 1 == 0) {
                pontos += 50;

            }

            if (segundos % 2 == 0) {
                // logica de spawnar gameobstacle
                colisao = true;
            }

        }

    }

    private void handleCollision(GameObject obstacle) {
        if (player.isTouching(obstacle) && colisao) {
            System.out.println("Player está colidindo com o obstáculo");
            colisao = false;
            player.vidas--;
            System.out.println("Vidas: " + player.vidas);
        }
    }

    private void updateObstaclePosition(GameObject obstacle, GameObject otherObstacle, int lastY, int speed) {
        if (obstacle.getX() > targetX && player.vidas > 0) {
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

}
