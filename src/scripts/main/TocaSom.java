package scripts.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TocaSom {

    private Clip audioClip;

    void startAudioLoop() {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File("src\\scripts\\main\\audio\\game.wav"));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}