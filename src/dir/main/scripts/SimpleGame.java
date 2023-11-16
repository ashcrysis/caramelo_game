package dir.main.scripts;

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
    // The below code is creating ImageIcons and Images for various objects such as
    // a dog, fish, bird,
    // rock, canoe, bird, finger, background, and heart. These images are being
    // loaded from specific file
    // paths in the project's directory.
    static ImageIcon jogo_icon = new ImageIcon("src\\dir\\main\\res\\icon (1).gif");
    static Image jogo_imagem = jogo_icon.getImage();

    ImageIcon cachorro_icon = new ImageIcon("src\\dir\\main\\res\\dog (1).gif");
    Image cachorro_imagem = cachorro_icon.getImage();

    ImageIcon peixe_icon = new ImageIcon("src\\dir\\main\\res\\pexinho (1).gif");
    Image peixe_imagem = peixe_icon.getImage();

    ImageIcon passaro_icon = new ImageIcon("src\\dir\\main\\res\\pombo (1).gif");
    Image passaro_imagem = passaro_icon.getImage();

    ImageIcon pedra_icon = new ImageIcon("src\\dir\\main\\res\\pedra (1).png");
    Image pedra_imagem = pedra_icon.getImage();

    ImageIcon canoa_icon = new ImageIcon("src\\dir\\main\\res\\\\canoa (1).gif");
    Image canoa_imagem = canoa_icon.getImage();

    ImageIcon pasaro_icon = new ImageIcon("src\\dir\\main\\res\\pasaro (1).gif");
    Image pasaro_imagem = pasaro_icon.getImage();

    ImageIcon ded_icon = new ImageIcon("src\\dir\\main\\res\\ded (1).png");
    Image ded_imagem = ded_icon.getImage();

    ImageIcon background_icon = new ImageIcon("src\\dir\\main\\res\\mapa (1).gif");
    Image background_imagem = background_icon.getImage();

    ImageIcon background_s_icon = new ImageIcon("src\\dir\\main\\res\\mapa_parado.png");
    Image background_s_imagem = background_s_icon.getImage();

    ImageIcon heart_icon = new ImageIcon("src\\dir\\main\\res\\coracao (1).png");
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

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);

        setLayout(new BorderLayout());

        add(label, BorderLayout.CENTER);

    }

    /**
     * This function is responsible for painting the game components on the screen,
     * including the player,
     * obstacles, and background images.
     * 
     * @param g The parameter "g" is an instance of the Graphics class, which is
     *          used for drawing
     *          graphics on the component. It provides methods for drawing images,
     *          shapes, and text on the
     *          component's surface. In this code, it is used to draw images on the
     *          component based on certain
     *          conditions and positions.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Player logic to switch up colors / forms
        if (player.getVidas() > 0) {
            g.drawImage(background_imagem, 0, 0, 600, 400, null);

            switch (animalForm) {
                case 0:
                    g.drawImage(cachorro_imagem, spriteX, spriteY, 74, 74, null);
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
                    g.drawImage(canoa_imagem, obstacle.getX(), obstacle.getY(), 160,
                            160, null);
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
            frame.setIconImage(jogo_imagem);
            TocaSom sound = new TocaSom();
            game.requestFocus();
            sound.startAudioLoop("game.wav");
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

    /**
     * The function handles collisions between the player and an obstacle,
     * decreasing the player's
     * lives and subtracting points if a collision occurs.
     * 
     * @param obstacle The "obstacle" parameter is a GameObject representing the
     *                 object that the player
     *                 has collided with.
     */
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

    /**
     * The function updates the position of an obstacle in a game, ensuring it moves
     * towards a target
     * position and generates a new random Y position if it reaches the target.
     * 
     * @param obstacle      The obstacle object that needs to be updated.
     * @param otherObstacle The "otherObstacle" parameter is a reference to another
     *                      GameObject
     *                      representing another obstacle in the game.
     * @param lastY         The lastY parameter represents the previous Y position
     *                      of the obstacle. It is used
     *                      to generate a new random Y position for the obstacle.
     * @param speed         The "speed" parameter represents the speed at which the
     *                      obstacle moves
     *                      horizontally.
     */
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

    /**
     * The function updates the player's position based on a target Y coordinate and
     * changes the animal
     * form based on the player's Y coordinate.
     * 
     * @param targetY The targetY parameter is the desired y-coordinate that the
     *                player should move
     *                towards.
     */
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

    /**
     * The `heartsHandler` function draws hearts on the screen based on the number
     * of lives the player
     * has.
     * 
     * @param g The parameter "g" is of type Graphics and represents the graphics
     *          context for drawing on
     *          the screen. It is used to draw the heart image on the screen.
     */
    private void heartsHandler(Graphics g) {
        for (int i = 0; i < player.getVidas(); i++) {
            int x = 10 + i * 40; // Adjust the spacing between hearts as needed
            int y = 360;
            int width = 32;
            int height = 32;

            g.drawImage(heart_imagem, x, y, width, height, null);
        }

    }

    /**
     * The getRandomYPosition function generates a random y-position that is
     * different from the current
     * y-position and the last y-position.
     * 
     * @param currentY The currentY parameter represents the current Y position. It
     *                 is used to ensure
     *                 that the new Y position generated is different from the
     *                 current Y position.
     * @param lastY    The lastY parameter is the previous Y position that was
     *                 generated.
     * @return The method is returning an integer value.
     */
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

    /**
     * The function shows a dialog box with a message "You are dead!" and two
     * options "Restart" and
     * "Exit", and performs different actions based on the user's choice.
     */
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

    /**
     * The restartGame() function resets game variables and starts a new game.
     */

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
