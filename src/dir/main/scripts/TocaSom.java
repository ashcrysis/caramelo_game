package dir.main.scripts;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TocaSom {

    private Clip audioClip;

    /**
     * The function "startAudioLoop" plays an audio file in a continuous loop.
     * 
     * @param arquivo The "arquivo" parameter is a string that represents the name
     *                of the audio file that
     *                you want to play in a loop.
     */
    void startAudioLoop(String arquivo) {
        var path = "src\\dir\\main\\audio\\";
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(path + arquivo));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}