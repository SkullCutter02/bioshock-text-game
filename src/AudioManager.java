import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    public static Clip playSound(String soundFile) {
        Clip clip = null;

        try {
            File f = new File("./" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch(Exception e) {
            System.out.println(e);
        }

        return clip;
    }

    public static void setVolume(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }

    public static void fadeClip(Clip clip, int fadeSpeed) {
        try {
            for (int i = 0; i < 80; i++) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(i * -1f);
                Thread.sleep(fadeSpeed);
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
