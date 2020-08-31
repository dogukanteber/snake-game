package snake;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class Sound {

    public static void playMusic( String url ) {
            SwingUtilities.invokeLater( () -> {
                try {
                    File path = new File(url);
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(path);
                    clip.open(inputStream);
                    clip.start();
                } catch ( Exception e ) {
                    System.out.println(e.getMessage());
                }
            });
    }
}
