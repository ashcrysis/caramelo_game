package scripts;import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGame extends JPanel implements ActionListener {
    private int targetY = 100;
    private int animalForm = 0;
    private Timer timer;
    static GameObject player = new GameObject(100, 100, 50, 50);
    private int spriteX = player.getX();
    private int spriteY = player.getY();
    private JLabel label;
    private static int segundos = 0;
    private static int pontos = 0;

    public SimpleGame() {
        timer = new Timer(10, this);
        timer.start();

        setPreferredSize(new Dimension(400, 400));

        int[] yPositions = {100, 200, 300};

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

        // Crie a JLabel e a centralize no JPanel
        label = new JLabel("Posição: " + player.getY());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (animalForm == 0) {
            g.setColor(Color.RED);
            g.fillRect(spriteX, spriteY, player.getWidth(), player.getHeight());
        } else if (animalForm == 1) {
            g.setColor(Color.GREEN);
            g.fillOval(spriteX, spriteY, player.getWidth(), player.getHeight());
        } else if (animalForm == 2) {
            g.setColor(Color.BLUE);
            g.fillOval(spriteX, spriteY, player.getWidth(), player.getHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player.getY() < targetY) {
            spriteY = spriteY + 5;
            player.setY(spriteY + 5);
        } else if (player.getY() > targetY) {
            spriteY = spriteY - 5;
            player.setY(spriteY);
        }

        if (player.getY() == 100) {
            animalForm = 1;
        } else if (player.getY() == 200) {
            animalForm = 0;
        } else if (player.getY() == 300) {
            animalForm = 2;
        }

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
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            
            
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
	        System.out.println(segundos);    
	        
	    
	    }
        
    }
}
