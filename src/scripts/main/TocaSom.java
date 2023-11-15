package scripts.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TocaSom {
    private Clip clip;

    public TocaSom(String caminhoDoArquivo) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminhoDoArquivo));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void tocarEmLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void parar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public static void main(String[] args) {
        String caminhoDoSom = "src\\scripts\\main\\audio\\game.wav"; // Substitua pelo caminho do seu arquivo de áudio

        TocaSom tocador = new TocaSom(caminhoDoSom);
        tocador.tocarEmLoop();

        // Aguarde algum tempo (você pode usar Thread.sleep) ou até que algum evento
        // externo indique a parada
        // Exemplo: Thread.sleep(10000); // Aguarda 10 segundos

        // Pare a reprodução
        tocador.parar();
    }
}