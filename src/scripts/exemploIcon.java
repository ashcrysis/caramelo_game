package scripts;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class exemploIcon extends JFrame implements KeyListener {

    private JPanel contentPane;
    private JLabel player;
    private int playerX;
    private int playerY;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	exemploIcon frame = new exemploIcon();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public exemploIcon() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 414, 240);
        contentPane.add(panel);
        panel.setLayout(null);

        player = new JLabel("");
        player.setBounds(184, 101, 32, 32);
        player.setIcon(new ImageIcon(exemploIcon.class.getResource("/scripts/New_Piskel.gif")));
        panel.add(player);

        // Inicializa a posição do jogador
        playerX = player.getX();
        playerY = player.getY();

        // Adiciona o KeyListener ao frame
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Cria um temporizador para mover o jogador
        Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Move o jogador
                player.setLocation(playerX, playerY);
            }
        });

        // Inicia o temporizador
        timer.start();
    }

    // Implementa os métodos do KeyListener
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        // Verifica a tecla pressionada
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                playerY -= 5;
                break;
            case KeyEvent.VK_A:
                playerX -= 5;
                break;
            case KeyEvent.VK_S:
                playerY += 5;
                break;
            case KeyEvent.VK_D:
                playerX += 5;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

}
