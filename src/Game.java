import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private final int MAP_X = 11;
    private final int MAP_Y = 7;

    private final Map map = new Map(MAP_X, MAP_Y);
    private Inventory inventory = new Inventory();

    private int currentXSpot = 5;
    private int currentYSpot = 6;

    private int health = 100;
    private int eve = 5;
    private int coins = 20;

    private boolean hasWon = false;
    private boolean hasEncounteredBoss = false;

    private final Scanner scanner = new Scanner(System.in);

    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_RESET = "\u001B[0m";

    private void action() {
        System.out.println();
        int n = ThreadLocalRandom.current().nextInt(0, 101);

        if(map.isBossFightSpot(currentXSpot, currentYSpot)) {
            if(!hasEncounteredBoss) {
                (new BossFightDialogue()).start();
                hasEncounteredBoss = true;
            }

            System.out.println("You are about to face the final boss, are you ready? Make sure you have enough ammo and equipment to fight this tough boss (Y/N)");
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("y")) {
                List<Attack> attacks = Arrays.asList(new Attack(10, "Drill Dash", "charged at you, drilling its big, mean drill into your stomach"));

                Enemy bigDaddy = new Enemy(10, "Big Daddy", "Heavily spliced human beings that have been grafted into heavily armoured diving suits. Also equipped with a big drill",
                        Arrays.asList(attacks.get(0)), 10, null);

                Battle battle = new Battle(health, eve, coins, inventory, bigDaddy);
                battle.start();
                health = battle.getRemainingHealth();
                eve = battle.getRemainingEve();
                coins = battle.getRemainingCoins();
                inventory = battle.getRemainingInventory();

                if(health > 0) hasWon = true;
            }
        } else if (map.isVendingMachineSpot(currentXSpot, currentYSpot)) {
            VendingMachine machine = new VendingMachine(coins, inventory);
            machine.enterMode();
            coins = machine.getRemainingCoins();
            inventory = machine.getRemainingInventory();
        } else if (n < 25) {
            RandomCollection<Item> collection = new RandomCollection<Item>()
                    .add(20, new Item("coins", "coins"))
                    .add(15, new Item("pistol ammo", "pistol"))
                    .add(15, new Item("machine gun ammo", "machine gun"))
                    .add(15, new Item("health pack", "health pack"))
                    .add(20, new Item("eve hypo", "eve hypo"))
                    .add(15, new Item("shotgun ammo", "shotgun"));
            Item item = collection.next();

            if (item.getName().equals("coins")) {
                int r = ThreadLocalRandom.current().nextInt(5, 15);
                coins += r;
                System.out.println("You found " + r + " coins lying on the floor. You took the coins");
            } else if (item.getName().equals("health pack")) {
                inventory.addHealthPack();
                System.out.println("You found a health pack lying on the floor. You took the health pack");
            } else if (item.getName().equals("eve hypo")) {
                inventory.addEveHypo();
                System.out.println("You found an eve hypo lying on the floor. You took the eve hypo");
            } else if (item.getName().split(" ")[item.getName().split(" ").length - 1].equals("ammo")) {
                Weapon weapon = inventory.getWeapon(item.getTarget());

                if (weapon != null) {
                    if (weapon.getName().equalsIgnoreCase("pistol")) {
                        int r = ThreadLocalRandom.current().nextInt(1, 3);
                        weapon.addAmmo(r);
                        System.out.println("You found " + r + " pistol ammo lying on the floor. You took the ammo");
                    } else if (weapon.getName().equalsIgnoreCase("machine gun")) {
                        int r = ThreadLocalRandom.current().nextInt(5, 30);
                        weapon.addAmmo(r);
                        System.out.println("You found " + r + " machine gun ammo lying on the floor. You took the ammo");
                    } else if (weapon.getName().equalsIgnoreCase("shotgun")) {
                        weapon.addAmmo(1);
                        System.out.println("You found a shotgun ammo lying on the floor. You took the ammo");
                    }
                }
            }

            System.out.println();
        } else {
            Battle battle = new Battle(health, eve, coins, inventory);
            battle.start();
            health = battle.getRemainingHealth();
            eve = battle.getRemainingEve();
            coins = battle.getRemainingCoins();
            inventory = battle.getRemainingInventory();
        }
    }

    private void movePlayer(String command) {
        switch (command) {
            case "u" -> {
                if (currentYSpot - 1 < 0)
                    System.out.println("You cannot go up any further!");
                else {
                    currentYSpot -= 1;
                    action();
                }
            }
            case "d" -> {
                if (currentYSpot + 1 >= MAP_Y)
                    System.out.println("You cannot go down any further!");
                else {
                    currentYSpot += 1;
                    action();
                }
            }
            case "l" -> {
                if (currentXSpot - 1 < 0)
                    System.out.println("You cannot go left any further!");
                else {
                    currentXSpot -= 1;
                    action();
                }
            }
            case "r" -> {
                if (currentXSpot + 1 >= MAP_X)
                    System.out.println("You cannot go right any further!");
                else {
                    currentXSpot += 1;
                    action();
                }
            }
        }
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

        while (health > 0 && !hasWon) {
            try {
                System.out.println("Type L for left, R for right, U for up and D for down. " +
                        "Type commands to show all the other commands you can type");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("u") || input.equals("d") || input.equals("r") || input.equals("l")) {
                    movePlayer(input);
                } else if (input.equals("commands")) {
                    showCommands();
                } else if (input.equals("map")) {
                    map.displayMap(currentXSpot, currentYSpot);
                } else if (input.equals("status")) {
                    System.out.println();
                    System.out.println("Health: " + health + " | EVE: " + eve + " | Coins: " + coins);
                    System.out.println();
                } else if (input.equals("inventory")) {
                    inventory.show();
                } else if (input.equals("use-health")) {
                    boolean canUse = inventory.useHealthPack();
                    if (canUse) {
                        health = Math.min(100, health + 50);
                        System.out.println("You restored your health to " + health);
                    }
                } else if (input.equals("use-eve")) {
                    boolean canUse = inventory.useEveHypo();
                    if (canUse) {
                        eve = Math.min(5, eve + 3);
                        System.out.println("You restored your EVE level to " + eve);
                    }
                } else if (input.split(" ")[0].equals("get-info")) {
                    String[] splitted = input.split(" ", 2);
                    inventory.getItemDescription(splitted[1]);
                } else
                    System.out.println("Try again with a valid input!\n");
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }

        if(health <= 0) {
            System.out.println("You died! Respawning you to the Vita Chamber");
            System.out.println("Because you died, you have lost all your coins");
            System.out.println("Please press enter to continue your journey");
            scanner.nextLine();
            coins = 0;
            health = 100;
            eve = 5;
            currentXSpot = 5;
            currentYSpot = 6;
            main();
        } else if(hasWon) {
            System.out.println("The Big Daddy collapsed onto the floor. The Little Sister he is guarding starts screaming. You grabbed hold of the Little Sister");
            System.out.println("You have two choices: save or harvest the Little Sister. The choice is yours (S to save, H to harvest)");
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("s")) {
                System.out.println("Using the harvest plasmid, you rest your hands on top of the Little Sister's head");
                scanner.nextLine();
                System.out.println("The Little Sister stops struggling as you absorbed the ADAM inside of her");
                scanner.nextLine();
                System.out.println(ANSI_CYAN + "Tenenbaum: The path of the righteous is not always easy, yes? The reward will become clear in time... be patient");
                scanner.nextLine();
                System.out.println("Atlas: Tenenbaum's playing you for a sap. Those things may look like wee little girls, but looks don't make it so. You'll need all the Adam you can get to survive" + ANSI_RESET);
                scanner.nextLine();
            } else if(input.equals("h")) {
                System.out.println("You seized the Little Sister and ripped open her stomach. In there reveals a huge slug, glowing under the dark light");
                scanner.nextLine();
                System.out.println("You reached into her stomach and grabbed hold of the slug, greedily absorbing all the ADAM within. The Little Sister's now dead body limply lies on the floor");
                scanner.nextLine();
                System.out.println(ANSI_CYAN + "Tenenbaum: No! My Little One! How dare you...");
                scanner.nextLine();
                System.out.println("Atlas: Don't listen to her whimpers, you will need all the ADAM you can get to survive" + ANSI_RESET);
                scanner.nextLine();
            }

            System.out.println("You exited the room, entering the next area of the fallen city of Rapture - the Medical Pavilion");
            scanner.nextLine();

            System.out.println("You won the game!");
            System.out.println("Click run on your IDE to play the game again! Thanks for playing!");
        }
    }
}
