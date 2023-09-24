package scripts;import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGame extends JPanel implements ActionListener {
    private int targetY = 100;
    private int targetX = -70;
    private int animalForm = 0;
    private Timer timer;
    static GameObject obstacle = new GameObject(500,100,50,50);
    static GameObject obstacle2 = new GameObject(500,200,50,50);
    static GameObject player = new GameObject(100, 100, 50, 50);
    static GameObject[] obstacles = {obstacle,obstacle2};
    private int spriteX = player.getX();
    private int spriteOX = obstacle.getX();
    private int spriteOY = obstacle.getY();
    private int spriteOY2 = obstacle2.getY();
    private int spriteY = player.getY();
    private JLabel label;
    private static int segundos = 0;
    private static int pontos = 0;
    
    public SimpleGame() {
        timer = new Timer(10, this);
        timer.start();

        setPreferredSize(new Dimension(600, 400));

        int[] yPositions = {100, 200, 300};

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int key = evt.getKeyCode();
                
                if (key == java.awt.event.KeyEvent.VK_UP) {
                	String pos = Integer.toString(player.getY());	// recebendo posição atual do jogador como String
                	pos = pos.replace("0",""); 	// removendo os zeros, para ter um numero entre 1,2,3 
                	int pos2 = Integer.parseInt(pos) - 1; // criando variavel auxiliar subtraindo um ( já que as variaveis de posicao sao 0 , 1 ,2
                	if (player.getY() != yPositions[0]) {
                		pos2 = pos2 - 1;
                		try {
                    		targetY = yPositions[pos2];
                    		}catch (Exception e) {
                    			
                    		}
                	}
                	
                }
                if (key == java.awt.event.KeyEvent.VK_DOWN) {
                	String pos = Integer.toString(player.getY());// recebendo posição atual do jogador como String
                	pos = pos.replace("0",""); // removendo os zeros, para ter um numero entre 1,2,3 
                	int pos2 = Integer.parseInt(pos) - 1; // criando variavel auxiliar subtraindo um ( já que as variaveis de posicao sao 0 , 1 ,2
                	if (player.getY() != yPositions[2]) {
                		pos2 =pos2 + 1;
                		try {
                		targetY = yPositions[pos2];
                		}catch (Exception e) {
                			
                		}
                		
                	}
                	
                }
                	
                }
                
            }
        );

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
        
       switch (obstacle.getY()) {
    	   case 100:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    	   case 200:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    	   case 300:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    		   
       }
       
       
       switch (obstacle2.getY()) {
    	   case 100:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    		   break;
    	   case 200:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    		   break;
    	   case 300:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    		   break;
    		   
       }
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//player handler
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
        //objects handler
        
        if (obstacle.getX() > targetX) {
            spriteOX = spriteOX - 3;
            obstacle.setX(spriteOX);
            
        } 
        if (obstacle.getX() == (targetX)) {
        	spriteOX = 620;
        	Random rand = new Random();
        	spriteOY = rand.nextInt(1,4);
        	String spriteOYaux = Integer.toString(spriteOY) + "00";
        	spriteOY = Integer.parseInt(spriteOYaux);
        	while (spriteOY == spriteOY2) {
        		spriteOY = rand.nextInt(1,4);
        		spriteOYaux = Integer.toString(spriteOY) + "00";
        		spriteOY = Integer.parseInt(spriteOYaux);
        	}
        	System.out.println(spriteOYaux);
        	obstacle.setY(spriteOY2);
        	obstacle.setX(spriteOX);
        	
        }
     
        
        if (obstacle2.getX() > targetX) {
            spriteOX = spriteOX - 3;
            obstacle2.setX(spriteOX);
            
        } 
        if (obstacle2.getX() == (targetX)) {
        	spriteOX = 620;
        	Random rand = new Random();
        	spriteOY2 = rand.nextInt(1,4);
        	String spriteOYaux2 = Integer.toString(spriteOY2) + "00";
        	spriteOY2 = Integer.parseInt(spriteOYaux2);
        	while (spriteOY2 == spriteOY) {
        		spriteOY2 = rand.nextInt(1,4);
        		spriteOYaux2 = Integer.toString(spriteOY2) + "00";
        		spriteOY2 = Integer.parseInt(spriteOYaux2);
        	}
        	System.out.println(spriteOYaux2);
        	obstacle2.setY(spriteOY2);
        	obstacle2.setX(spriteOX);
        	
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
	        
	        if (segundos % 3 == 0) {
	           //logica de spawnar gameobstacle
	        	
	        }
	        //System.out.println(segundos);    
	        
	    
	    }
        
    }
}
