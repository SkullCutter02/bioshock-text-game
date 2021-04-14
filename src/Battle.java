import java.util.*;

public class Battle {
    private int health;
    private int eve;

    private final Enemy enemy;

    private final Inventory inventory;

    private final Scanner scanner = new Scanner(System.in);

    public Battle(int health, int eve, Inventory inventory) {
        SplicerFactory factory = new SplicerFactory();

        this.enemy = factory.create();
        this.health = health;
        this.eve = eve;
        this.inventory = inventory;
    }

    private String getEnemyAttacks() {
        List<String> attackNames = new ArrayList<>();

        for (Attack attack : enemy.getAttacks())
            attackNames.add(attack.getName());

        return String.join(", ", attackNames);
    }

    private void endBattle() {
        if (enemy.getHealth() <= 0) {
            System.out.println("The " + enemy.getName() + " collapsed and died, you won!");
            System.out.println();
        }
    }

    private void showCommands() {
        System.out.println();
        System.out.println("commands: displays a list of commands you can type");
        System.out.println("status: displays your health and EVE value");
        System.out.println("inventory: displays your inventory");
        System.out.println("get-info <inventory item>: displays information about an item in your inventory");
        System.out.println("use-health: use a health pack from your inventory that restores your health by 50 points");
        System.out.println("use-eve: use an eve hypo from your inventory that restores your EVE by 3 points");
        System.out.println("reload <weapon name>: reload selected weapon");
        System.out.println();
    }

    public void start() {
        System.out.println("A " + enemy.getName() + " appeared!");

        while (enemy.getHealth() > 0 && health > 0) {
            System.out.println("Type \"attack <weapon name>\" to damage the " + enemy.getName() + ". " +
                    "Type description to get the enemy's description. Type commands to show all the other commands you can type");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.split(" ")[0].equals("attack")) {
                // player attack
                Weapon playerWeapon = inventory.getWeapon(input.split(" ", 2)[1]);

                if (playerWeapon != null) {
                    if(playerWeapon.getCurrentAmmoCount() <= 0) {
                        System.out.println("You don't have any ammo in your " + playerWeapon.getName().toLowerCase() +
                                ". Please type \"reload <weapon name>\" to reload your weapon");
                    } else {
                        System.out.println("You attacked with your " + playerWeapon.getName().toLowerCase());
                        int r = (new Random()).nextInt(100);
                        int headshotRate = (new Random()).nextInt(100);

                        if (r < enemy.getDodgeRate()) {
                            System.out.println("The " + enemy.getName() + " dodged your attack");
                        } else {
                            if(headshotRate <= 10) System.out.println("It's a headshot!");
                            int damage = headshotRate <= 10 ? playerWeapon.getDamage() * 2 : playerWeapon.getDamage();
                            enemy.damage(damage);
                            playerWeapon.use();
                            System.out.println("You dealt " + damage + " damage to the " + enemy.getName());
                            System.out.println("The " + enemy.getName() + " is now on " + enemy.getHealth() + "HP");

                            if (enemy.getHealth() <= 0) break;
                        }
                    }

                    // enemy attack
                    Attack attack = enemy.getRandomAttack();
                    int r1 = (new Random()).nextInt(100);
                    System.out.println("The " + enemy.getName() + " " + attack.getDescription());

                    if (r1 < 5) {
                        System.out.println("You successfully dodged the attack!");
                    } else {
                        health -= attack.getDamage();
                        System.out.println("The " + enemy.getName() + " dealt " + attack.getDamage() + " to you");
                        System.out.println("You are now on " + health + "HP");
                    }
                }

                System.out.println();
            } else if(input.split(" ")[0].equals("reload")) {
                Weapon weapon = inventory.getWeapon(input.split(" ", 2)[1]);

                if(weapon != null)
                    weapon.reload();
            } else if (input.equals("description")) {
                System.out.println();
                System.out.println("Name: " + enemy.getName());
                System.out.println("Current Health: " + enemy.getHealth());
                System.out.println("Description: " + enemy.getDescription());
                System.out.println("Attacks: " + getEnemyAttacks());
                System.out.println();
            } else if(input.equals("commands")) {
                showCommands();
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
            } else if(input.equals("status")) {
                System.out.println();
                System.out.println("Health: " + health + " | EVE: " + eve);
                System.out.println();
            } else {
                System.out.println("Try again with a valid input!");
            }
        }

        endBattle();
    }

    public int getRemainingHealth() {
        return health;
    }

    public int getRemainingEve() {
        return eve;
    }

    public Inventory getRemainingInventory() {
        return inventory;
    }
}
