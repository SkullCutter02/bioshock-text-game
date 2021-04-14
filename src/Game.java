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
    private int coins = 0;

    private void action() {
        System.out.println();
        int n = ThreadLocalRandom.current().nextInt(0, 101);
        System.out.println(n);

        if (n < 30) {
            RandomCollection<DropItem> collection = new RandomCollection<DropItem>()
                    .add(45, new DropItem("coins", "coins"))
                    .add(15, new DropItem("pistol ammo", "pistol"))
                    .add(15, new DropItem("machine gun ammo", "machine gun"))
                    .add(10, new DropItem("health pack", "health pack"))
                    .add(5, new DropItem("eve hypo", "eve hypo"))
                    .add(10, new DropItem("shotgun ammo", "shotgun"));
            DropItem item = collection.next();

            if(item.getName().equals("coins")) {
                int r = ThreadLocalRandom.current().nextInt(5, 15);
                coins += r;
                System.out.println("You found " + r + " coins lying on the floor. You took the coins");
            } else if(item.getName().equals("health pack")) {
                inventory.addHealthPack();
                System.out.println("You found a health pack lying on the floor. You took the health pack");
            } else if(item.getName().equals("eve hypo")) {
                inventory.addEveHypo();
                System.out.println("You found an eve hypo lying on the floor. You took the eve hypo");
            } else if(item.getName().split(" ")[item.getName().split(" ").length - 1].equals("ammo")) {
                Weapon weapon = inventory.getWeapon(item.getTarget());

                if(weapon != null) {
                    if(weapon.getName().equalsIgnoreCase("pistol")) {
                        int r = ThreadLocalRandom.current().nextInt(1, 3);
                        weapon.addAmmo(r);
                        System.out.println("You found " + r + " pistol ammo lying on the floor. You took the ammo");
                    } else if(weapon.getName().equalsIgnoreCase("machine gun")) {
                        int r = ThreadLocalRandom.current().nextInt(5, 30);
                        weapon.addAmmo(r);
                        System.out.println("You found " + r + " machine gun ammo lying on the floor. You took the ammo");
                    } else if(weapon.getName().equalsIgnoreCase("shotgun")) {
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

        Scanner scanner = new Scanner(System.in);

        while (true) {
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
                System.out.println("Try again with a valid input!\n");
            }
        }
    }
}
