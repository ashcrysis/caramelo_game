package scripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//game logic testing on java - Asher Gabriela

public class SimpleGame extends JPanel implements ActionListener {
    private int spriteX = 100; // X pos initial position
    private int spriteY = 100;   // Y pos initial position
    private int targetY = 100;   // Y destine position
    private int animalForm = 0; // (0,1,2) = (dog,bird,fish)  
    private Timer timer;

    public SimpleGame() {
        timer = new Timer(10, this);
        timer.start();

        // Set up the window size
        setPreferredSize(new Dimension(400, 400));
                      
        // Set the possible positions on the Y Axis
        int[] yPositions = {100, 200, 300};

        // Creates an action listener to change the Y position whenever an key is pressed
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int key = evt.getKeyCode();
                if (key == java.awt.event.KeyEvent.VK_1) {
                    targetY = yPositions[0];
                } else if (key == java.awt.event.KeyEvent.VK_2) {
                    targetY = yPositions[1];
                } else if (key == java.awt.event.KeyEvent.VK_3) {
                    targetY = yPositions[2];
                }
            }
        });

        setFocusable(true);
    }
    

@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the sprite according to the current shape (dog, fish or bird)
        if (animalForm == 0) {
            g.setColor(Color.RED);
            g.fillRect(spriteX, spriteY, 50, 50); // DOG
        } else if (animalForm == 1) {
            g.setColor(Color.GREEN);
            g.fillOval(spriteX, spriteY, 50, 50); // BIRD
        } else if (animalForm == 2) {
            g.setColor(Color.BLUE);
            g.fillOval(spriteX, spriteY, 50, 50); // FISH
        }
    }
/* FORMA UTILIZANDO SWITCH CASE

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (animalForm) {
            case 0: // DOG
                g.setColor(Color.RED);
                g.fillRect(spriteX, spriteY, 50, 50);
                break;
            case 1: // BIRD
                g.setColor(Color.GREEN);
                g.fillOval(spriteX, spriteY, 50, 50);
                break;
            case 2: // FISH
                g.setColor(Color.BLUE);
                g.fillOval(spriteX, spriteY, 50, 50);
                break;
            default:
                break;
        }
    }
*/
    @Override
    public void actionPerformed(ActionEvent e) {
        // Moves the Sprite to the desired position
        if (spriteY < targetY) {
            spriteY= spriteY +5;
        } else if (spriteY > targetY) {
            spriteY = spriteY - 5;
        }
        // Checks the sprite's current position and sets the animal's shape
        if (spriteY == 100) {
            animalForm = 1; // BIRD
        } else if (spriteY == 200) {
            animalForm = 0; // DOG
        } else if (spriteY == 300) {
            animalForm = 2; // FISH
        }
        repaint();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Game");
            SimpleGame game = new SimpleGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


