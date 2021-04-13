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
        AudioManager.setVolume(bubbles, -20.0f);
        bubbles.loop(Integer.MAX_VALUE);
        scanner.nextLine();

        // introduction to Rapture
        System.out.println("The bathysphere continues sinking, you see a few statues in front of you " +
                "through the small window of the bathysphere. Schools of fish swim by");
        scanner.nextLine();
        System.out.println("All of a sudden, a telescreen pops up, covering the small window of the telescreen. " +
                "A man appeared at the screen and introduced himself");
        scanner.nextLine();
        bubbles.stop();
        Clip welcomeToRapture = AudioManager.playSound("sounds/music/welcome_to_rapture.wav");
        AudioManager.setVolume(welcomeToRapture, -10f);
        System.out.println(ANSI_CYAN + "I am Andrew Ryan, and I'm here to ask you a question: ");
        scanner.nextLine();
        System.out.println("Is a man not entitled to the sweat of his brow?");
        scanner.nextLine();
        System.out.println("No! says the man in Washington, it belongs to the poor");
        scanner.nextLine();
        System.out.println("No! says the man in Vatican, it belongs to god");
        scanner.nextLine();
        System.out.println("No! says the man in Moscow, it belongs to everyone");
        scanner.nextLine();
        System.out.println("I rejected those answers. Instead, I chose something different. " +
                "I chose the impossible. I chose -");
        scanner.nextLine();
        AudioManager.setVolume(welcomeToRapture, 0f);
        System.out.println("RAPTURE!" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("The window reveals a huge underwater city with bright lights. " +
                "Whales and school of fish swim by gracefully");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "The city where the artist would not fear the censor");
        scanner.nextLine();
        System.out.println("Where the scientist would not be bound by petty morality");
        scanner.nextLine();
        System.out.println("Where the great would not be constraint by the small!");
        scanner.nextLine();
        System.out.println("And with the sweat of your brow, Rapture can become your city as well" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("The voice died down, and you start to marvel at the wonder that's " +
                "appearing right in front of your eyes");
        scanner.nextLine();
        System.out.println("In the radio, you could hear two people talking in the distance");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: ...but the lighthouse is all lit up like hellfire! Looks like some" +
                "kind of plane crash");
        scanner.nextLine();
        System.out.println("Johnny: We're in the middle of the Atlantic Ocean... How could it?");
        scanner.nextLine();
        System.out.println("Atlas: Dunno, you best get o'er there, and be quick about it! The splicers are coming" + ANSI_RESET);
        scanner.nextLine();
        AudioManager.fadeClip(welcomeToRapture, 50);
        System.out.println("The bathysphere starts docking in some sort of submarine port. " +
                "The city logo with the words RAPTURE, and its slogan \"Ad Idem - Meeting of the minds\" appears");
        welcomeToRapture.stop();
        scanner.nextLine();

        // surface
        System.out.println(ANSI_CYAN + "Johnny: You've gotta be kidding! How do you know someone's coming?");
        scanner.nextLine();
        System.out.println("Atlas: Cause we got a bathysphere on its way down... That means we've got company");
        scanner.nextLine();
        System.out.println("Johnny: O-okay, just one more minute! The sphere is coming up now");
        scanner.nextLine();
        System.out.println("Atlas: Johnny, security's banging off all over, get a move on!" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("The bathysphere finally came to a halt. You see Johnny, " +
                "who's being attacked by something with hooks instead of hands");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Johnny: Please, lady... I didn't mean to trespass. Just don't hurt me, just let me go!");
        scanner.nextLine();
        System.out.println("Johnny: You can keep my gun, you can - " + ANSI_RESET);
        scanner.nextLine();
        System.out.println("Johnny gets brutally lacerated in the neck by the lady with the hook. " +
                "He gurgles and drops to the floor");
        scanner.nextLine();
        System.out.println("The lady with hooks starts looking at you and hisses");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Lady with Hooks: Is it someone new???" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("The lady starts jumping onto the bathysphere, attempting to break it so it can get to you");
        scanner.nextLine();
        System.out.println("The lady rages and shrieks but couldn't get into the bathysphere, " +
                "in frustration she leaps away and left");
        scanner.nextLine();
        System.out.println("You were terrified at the scene, but luckily the other fella starts talking to you");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: Would you kindly pick up that shortwave radio?" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("You immediately picked up the radio without hesitation");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: I don't know how you survived that plane crash, " +
                "but I've never been one to question Providence");
        scanner.nextLine();
        System.out.println("I am Atlas, and I aim to keep you alive. Now keep on moving! " +
                "We're gonna have to get you to higher ground");
        scanner.nextLine();
        System.out.println("Take a deep breath and step out of that bathysphere. I won't leave you twisting in the wind");
        scanner.nextLine();
        System.out.println("We're gonna need to draw her out of hiding, but first you need to trust me" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("Atlas sets out a flying security robot, killing the lady with the hook. He then says, ");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Now would you kindly find a crowbar or something? " +
                "Bloody splicers sealed Johnny in before they... god damn splicers!" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("A wrench is lying on the floor. You picked it up");
        scanner.nextLine();
        System.out.println("Moving forward, you arrived at a gatherer's garden. " +
                "You saw a plasmid injection tube in front of you, and injected the liquid into your body");
        scanner.nextLine();
        System.out.println("Immediately, you started to feel pain, and at the same time, surges of energy");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: Steady now! Your genetic code is being rewritten - just hold on and " +
                "everything will be fine!" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("You finally couldn't stand the pain and collapsed. Everything turned black");
        scanner.nextLine();
        System.out.println("When you woke up, you found yourself with the ability to shoot electric bolts from your hands. " +
                "Time to teach those damn splicers a good lesson");
        scanner.nextLine();
        System.out.println("Prologue End --------------");
    }
}
