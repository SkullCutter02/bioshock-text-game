import javax.sound.sampled.Clip;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("This is a text remake of the 2007 shooter BioShock");
        System.out.println("Please play with sound on for the best experience!\n");

        System.out.println("Do you wish to view the prologue? No will take you straight to the game (Y/N)");
        String input = scanner.nextLine().trim().toLowerCase();

        if(input.equals("y"))
            (new Prologue()).start();

        Clip ambience = AudioManager.playSound("sounds/effects/ambience.wav");
        AudioManager.setVolume(ambience, -20f);
        ambience.loop(Integer.MAX_VALUE);
        (new Game()).main();
    }
}
