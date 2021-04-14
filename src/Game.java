import java.util.*;

public class Game {
    private final int MAP_X = 11;
    private final int MAP_Y = 7;

    private final Map map = new Map(MAP_X, MAP_Y);
    private Inventory inventory = new Inventory();

    private int currentXSpot = 5;
    private int currentYSpot = 6;

    private int health = 100;
    private int eve = 5;


    private void movePlayer(String command) {
        switch (command) {
            case "u" -> {
                if (currentYSpot - 1 < 0)
                    System.out.println("You cannot go up any further!");
                else
                    currentYSpot -= 1;
            }
            case "d" -> {
                if (currentYSpot + 1 >= MAP_Y)
                    System.out.println("You cannot go down any further!");
                else
                    currentYSpot += 1;
            }
            case "l" -> {
                if (currentXSpot - 1 < 0)
                    System.out.println("You cannot go left any further!");
                else
                    currentXSpot -= 1;
            }
            case "r" -> {
                if (currentXSpot + 1 >= MAP_X)
                    System.out.println("You cannot go right any further!");
                else
                    currentXSpot += 1;
            }
            default -> System.out.println("This shouldn't happen lol");
        }

        Battle battle = new Battle(health, eve, inventory);
        battle.start();
        health = battle.getRemainingHealth();
        eve = battle.getRemainingEve();
        inventory = battle.getRemainingInventory();
    }

    private void showCommands() {
        System.out.println();
        System.out.println("commands: displays a list of commands you can type");
        System.out.println("map: displays the map and your current position");
        System.out.println("status: displays your health and EVE value");
        System.out.println("inventory: displays your inventory");
        System.out.println("get-info <inventory item>: displays information about an item in your inventory");
        System.out.println("use-health: use a health pack from your inventory that restores your health by 50 points");
        System.out.println("use-eve: use an eve hypo from your inventory that restores your EVE by 3 points");
        System.out.println();
    }

    public void main() {
        map.displayMap(currentXSpot, currentYSpot);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Type L for left, R for right, U for up and D for down. " +
                        "Type commands to show all the other commands you can type");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("u") || input.equals("d") || input.equals("r") || input.equals("l")) {
                    movePlayer(input);
                } else if(input.equals("commands")) {
                    showCommands();
                } else if (input.equals("map")) {
                    map.displayMap(currentXSpot, currentYSpot);
                } else if(input.equals("status")) {
                    System.out.println();
                    System.out.println("Health: " + health + " | EVE: " + eve);
                    System.out.println();
                } else if(input.equals("inventory")) {
                    inventory.show();
                } else if(input.equals("use-health")) {
                    boolean canUse = inventory.useHealthPack();
                    if(canUse) health = Math.min(100, health + 50);
                    System.out.println("You restored your health to " + health);
                } else if(input.equals("use-eve")) {
                    boolean canUse = inventory.useEveHypo();
                    if(canUse) eve = Math.min(5, eve + 3);
                    System.out.println("You restored your EVE level to " + eve);
                } else if(input.split(" ")[0].equals("get-info")) {
                    String[] splitted = input.split(" ", 2);
                    inventory.getItemDescription(splitted[1]);
                } else
                    System.out.println("Try again with a valid input!\n");
            } catch (Exception e) {
                System.out.println("Try again with a valid input!\n");
            }
        }
    }
}
