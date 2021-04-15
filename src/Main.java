import javax.sound.sampled.Clip;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIOSHOCK\n");
        System.out.println("Please play with sound on for the best experience!\n\n");
//        (new Prologue()).start();
        Clip ambience = AudioManager.playSound("sounds/effects/ambience.wav");
        AudioManager.setVolume(ambience, -20f);
        ambience.loop(Integer.MAX_VALUE);
        (new Game()).main();
    }
}
