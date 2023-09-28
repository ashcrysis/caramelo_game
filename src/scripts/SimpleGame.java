package scripts;import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGame extends JPanel implements ActionListener {
    private int targetY = 100;
    private int targetX = -70;
    private int animalForm = 0;
    private int lastY;
    private int lastY2;
    private Timer timer;
    private int obstacle2Type = 3;
    private int obstacleType  =  1;
    static GameObject obstacle = new GameObject(500,100,50,50);
    static GameObject obstacle2 = new GameObject(500,300,50,50);
    static GameObject player = new GameObject(100, 100, 50, 50);
    static GameObject[] obstacles = {obstacle , obstacle2};
    private int spriteX = player.getX();
    private int spriteOX = obstacle.getX();
    private int spriteOY = obstacle.getY();
    private int spriteOY2 = obstacle2.getY();
    private int spriteY = player.getY();
    private JLabel label;
    private static int segundos = 0;
    private static int pontos = 0;
    private static boolean colisao = true;
    
    public SimpleGame() {
        timer = new Timer(10, this);
        timer.start();

        setPreferredSize(new Dimension(600, 400));

        int[] yPositions = {100, 200, 300};

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int key = evt.getKeyCode();
                
                if (key == java.awt.event.KeyEvent.VK_UP && player.vidas > 0) {
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
                if (key == java.awt.event.KeyEvent.VK_DOWN && player.vidas > 0) {
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
        
       switch (obstacleType){
    	   case 1:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    	   case 2:
    		   g.setColor(Color.CYAN);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    	   case 3:
    		   g.setColor(Color.MAGENTA);
    		   g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    		   break;
    		   
       }
       
       
       switch (obstacle2Type) {
    	   case 1:
    		   g.setColor(Color.DARK_GRAY);
    		   g.fillRect(obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    		   break;
    	   case 2:
    		   g.setColor(Color.CYAN);
    		   g.fillRect(obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    		   break;
    	   case 3:
    		   g.setColor(Color.MAGENTA);
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
        
        if (obstacle.getX() > targetX && (  player.vidas > 0 )){
            spriteOX = spriteOX - 3;
            obstacle.setX(spriteOX);
            
        } 
        if (obstacle.getX() == (targetX)) {
        	spriteOX = 620;
        	Random rand = new Random();
        	spriteOY = rand.nextInt(1,4);
        	lastY = spriteOY;
        	obstacleType = spriteOY;
        	String spriteOYaux = Integer.toString(spriteOY) + "00";
        	spriteOY = Integer.parseInt(spriteOYaux);
        	
        	while (spriteOY == spriteOY2 && spriteOY != lastY) {
        		
        		spriteOY = rand.nextInt(1,4);
        		spriteOYaux = Integer.toString(spriteOY) + "00";
        		obstacleType = spriteOY;
        		spriteOY = Integer.parseInt(spriteOYaux);
        	}
        	lastY = spriteOY;
        	obstacle.setY(spriteOY);
        	obstacle.setX(spriteOX);
        	
        }
     
        for ( int i = 0 ; i < obstacles.length ; i++) {
            if (player.isTouching(obstacles[i]) && colisao == true){
            	System.out.println("O jogador está tocando o objeto na posição " + obstacles[i].getY());
            }
            
            }
        if (obstacle2.getX() > targetX && (  player.vidas > 0 )) {
            spriteOX = spriteOX - 3;
            obstacle2.setX(spriteOX);
            
            
        } 
        if (obstacle2.getX() == (targetX)) {
        	spriteOX = 620;
        	Random rand = new Random();
        	spriteOY2 = rand.nextInt(1,4);
        	lastY2 = spriteOY2;
        	String spriteOYaux2 = Integer.toString(spriteOY2) + "00";
        	obstacle2Type = spriteOY2;
        	spriteOY2 = Integer.parseInt(spriteOYaux2);
        	while (spriteOY2 == spriteOY && spriteOY2 != lastY2) {
        		spriteOY2 = rand.nextInt(1,4);
        		spriteOYaux2 = Integer.toString(spriteOY2) + "00";
        		obstacle2Type = spriteOY2;
        		spriteOY2 = Integer.parseInt(spriteOYaux2);
        	}
        	lastY2 = spriteOY2;
        	obstacle2.setY(spriteOY2);
        	obstacle2.setX(spriteOX);
        	
        	
        }
        
        if (player.isTouching(obstacle) && colisao == true){
        	System.out.println("Player está colidindo com o obstaculo 1");
        	colisao = false;
        	player.vidas = player.vidas -= 1;
        	System.out.println("Vidas: " + player.vidas);
        }       
        
        if (player.isTouching(obstacle2) && colisao == true){
        	System.out.println("Player está colidindo com o obstaculo 2");
        	colisao = false;
        	player.vidas = player.vidas -= 1;
        	System.out.println("Vidas: " + player.vidas);
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
	        
	        if (segundos % 2 == 0) {
	           //logica de spawnar gameobstacle
	        	colisao = true;
	        }
	        //System.out.println(segundos);    
	        
	    
	    }
        
    }
}
