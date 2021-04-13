import javax.sound.sampled.Clip;
import java.util.*;

public class Prologue {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("PROLOGUE --------------");
        System.out.println("Enter to move onto the next line");
        scanner.nextLine();

        // plane scene
        System.out.println("You are on a plane. You took out your wallet and looked at the photo of your family. " +
                "You hesitated a bit and finally drew out a gun");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "You: They told me, son, you're special, you're born to do great things. " +
                "You know what? They were right" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("The plane crashes as chaos and screaming ensues on the plane");
        scanner.nextLine();

        // after plane crash
        System.out.println("You've crash landed into the ocean. Your eyesight has blurred from the crash, " +
                "but you can faintly see a lighthouse shining a bright light in front of you");
        scanner.nextLine();
        System.out.println("You waded across the ocean water, now red from the blood of the passengers of the plane");
        scanner.nextLine();
        System.out.println("You arrived at the lighthouse, now shining as brightly as ever. You opened the door and entered it");
        scanner.nextLine();

        // lighthouse
        System.out.println("The lighthouse creates a creaky noise as the lights turn on one by one. " +
                "Immediately, you're struck by an image of a huge statue of a man, with a banner saying " +
                "\"NO GODS OR KINGS, ONLY MAN\"");
        scanner.nextLine();
        System.out.println("Faint music starts playing below");
        Clip underTheSea = AudioManager.playSound("sounds/music/beyond_the_sea.wav");
        scanner.nextLine();
        System.out.println("You walked down the stairs, and saw a submarine-like transportation device called the bathysphere. " +
                "The music was coming from it");
        scanner.nextLine();
        System.out.println("You walked into the bathysphere and flipped the lever. " +
                "The bathysphere starts and dives downwards into the ocean. Air bubbles form around the bathysphere as it sinks");
        scanner.nextLine();
        AudioManager.fadeClip(underTheSea, 50);
        underTheSea.stop();
        Clip bubbles = AudioManager.playSound("sounds/effects/bubbles.wav");
        AudioManager.setVolume(bubbles, -15.0f);
        bubbles.loop(Integer.MAX_VALUE);
        scanner.nextLine();

        // introduction to Rapture
        System.out.println("The bathysphere continues sinking, you see a few statues in front of you " +
                "through the small window of the bathysphere");
    }
}
