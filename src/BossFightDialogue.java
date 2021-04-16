import java.util.Scanner;

public class BossFightDialogue {
    public void start() {
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";

        Scanner scanner = new Scanner(System.in);

        System.out.println("You entered a room with a huge machine-like creature, accompanying a little girl");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: It's a Little One... Here's your chance to get some Adam" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("You started approaching the Little Sister, suddenly, a woman appears in the balcony above");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Tenenbaum: Stay away from her or it is you who will be shot next!" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("You immediately backed down, you heard Atlas reply in the radio");
        scanner.nextLine();
        System.out.println(ANSI_CYAN + "Atlas: Easy now, Doctor! He's just looking for a wee bit of Adam, just enough to get by");
        scanner.nextLine();
        System.out.println("Tenenbaum: I'll not have him hurt my Little Ones");
        scanner.nextLine();
        System.out.println("Atlas: It's okay, lad. That's not a child, not anymore it ain't. Dr. Tenenbaum saw to that");
        scanner.nextLine();
        System.out.println("Atlas: Aye, that's a pretty sermon coming from the ghoul who cooked up them creatures in the first place. Took fine little girls and turned them into that didn't you?");
        scanner.nextLine();
        System.out.println("Atlas: Listen to me, boyo: you won't survive without the Adam those... things... are carrying. Are you prepared to trade your life, the lives of my wife and child for Tenenbaum's little Frankensteins?");
        scanner.nextLine();
        System.out.println("Tenenbaum: Here! There is another way... use this, free them from their torment" + ANSI_RESET);
        scanner.nextLine();
        System.out.println("Tenenbaum throws you a plasmid from the balcony, which allows you to absorb ADAM from the Little Sisters");
        scanner.nextLine();
        System.out.println("Now it is up to you to save or harvest the Little Sister (Save means side with Tenenbaum, Harvest means side with Atlas). However, you would first need to defeat her guardian, the Big Daddy");
        scanner.nextLine();
    }
}
