package scripts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentoImagens extends JFrame {
    private List<ImageIcon> imagens;
    private List<JLabel> labels;
    private Timer timer;
    private int currentIndex = 0;
  
    public MovimentoImagens() {
        imagens = new ArrayList<>();
        labels = new ArrayList<>();
       
        // Adicione os caminhos das suas imagens aqui
        imagens.add(new ImageIcon("src/scripts/full_map (1).gif"));
        
        for (ImageIcon imagem : imagens) {
            JLabel label = new JLabel(imagem);
            label.setSize(imagem.getIconWidth(), imagem.getIconHeight());
            labels.add(label);
            System.out.println("Imagem carregada : " + imagem.getDescription());
        }
        int pontuacao = 0;

	  timer = new Timer( 15, e -> moverImagens());

        // Configuração básica da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(740, 640);
        getContentPane().setLayout(null);

        // Adicione as labels à janela
        for (JLabel label : labels) {
            getContentPane().add(label);
           
        }

        // Inicie o timer
        timer.start();
    }

    private void moverImagens() {
    	currentIndex = (currentIndex + 1) % imagens.size();
    	
        for (JLabel label : labels) {
        	
            Point posicao = label.getLocation();
            //System.out.println("Posicao X anterior: " + posicao.x);
            label.setLocation(posicao.x - 10, posicao.y);
           
            // Se a imagem saiu completamente da tela, reposicione-a à direita
            if (posicao.x + label.getWidth() < 0 ) {
            	label.setLocation(getWidth(), posicao.y);
                
               
                label.setIcon(imagens.get(currentIndex));
            
                
            }
        }
     
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovimentoImagens movimentoImagens = new MovimentoImagens();
            
            movimentoImagens.setVisible(true);
        });
    }
}