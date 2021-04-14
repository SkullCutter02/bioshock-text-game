import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private int health;
    private int eve;
    private int coins;

    private final Enemy enemy;

    private final Inventory inventory;

    private final Scanner scanner = new Scanner(System.in);

    public Battle(int health, int eve, int coins, Inventory inventory) {
        SplicerFactory factory = new SplicerFactory();

        this.enemy = factory.create();
        this.health = health;
        this.eve = eve;
        this.coins = coins;
        this.inventory = inventory;
    }

    private String getEnemyAttacks() {
        List<String> attackNames = new ArrayList<>();

        for (Attack attack : enemy.getAttacks())
            attackNames.add(attack.getName());

        return String.join(", ", attackNames);
    }

    private void handleDrop(DropItem item) {
        if(item.getName().equals("coins")) {
            int r = ThreadLocalRandom.current().nextInt(0, 11);
            coins += r;
            System.out.println("You received " + r + " coins");
        } else if(item.getName().equals("health pack")) {
            inventory.addHealthPack();
            System.out.println("You received a health pack");
        } else if(item.getName().equals("eve hypo")) {
            inventory.useEveHypo();
            System.out.println("You received an eve hypo");
        } else if(item.getName().split(" ")[item.getName().split(" ").length - 1].equals("ammo")) {
            Weapon weapon = inventory.getWeapon(item.getTarget());

            if(weapon != null) {
                weapon.addAmmo(1);
                System.out.println("You received a " + item.getName());
            }
            else
                System.out.println("You received a " + item.getName() +
                        ". However, since you don't have a " + item.getTarget() + ", you cannot receive this type of ammo");
        }
    }

    private void endBattle() {
        if (enemy.getHealth() <= 0) {
            System.out.println("The " + enemy.getName() + " collapsed and died, you won!");

            DropItem item = enemy.getDropItem();
            handleDrop(item);

            System.out.println();
        } else if(health <= 0) {
            System.out.println("You died!");
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
        int stunRound = 2;

        System.out.println("A " + enemy.getName() + " appeared!");

        while (enemy.getHealth() > 0 && health > 0) {
            System.out.println("Type \"attack <weapon name>\" to damage the " + enemy.getName() + ". " +
                    "Type description to get the enemy's description. Type commands to show all the other commands you can type");
            String input = scanner.nextLine().trim().toLowerCase();

            if(enemy.isStunned() && stunRound <= 0) {
                System.out.println("The " + enemy.getName() + " has recovered from your electro bolt attack");
                enemy.unstun();
                stunRound = 2;
            }

            if (input.split(" ")[0].equals("attack")) {
                // player attack
                Weapon playerWeapon = inventory.getWeapon(input.split(" ", 2)[1]);

                if (playerWeapon != null) {
                    if(playerWeapon.getCurrentAmmoCount() <= 0) {
                        System.out.println("You don't have any ammo in your " + playerWeapon.getName().toLowerCase() +
                                ". Please type \"reload <weapon name>\" to reload your weapon");
                    } else if(playerWeapon.getName().equalsIgnoreCase("electro bolt") && eve <= 0) {
                        System.out.println("You don't have any EVE left to cast the electro bolt. " +
                                "Please type use-eve to replenish your EVE level");
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

                            if(playerWeapon.getName().equalsIgnoreCase("electro bolt")) {
                                enemy.stun();
                                stunRound = 2;
                                eve--;
                            }

                            if (enemy.getHealth() <= 0) break;
                        }
                    }

                    // enemy attack
                    if(!enemy.isStunned()) {
                        Attack attack = enemy.getRandomAttack();
                        int r1 = (new Random()).nextInt(100);
                        System.out.println("The " + enemy.getName() + " " + attack.getDescription());

                        if (r1 < 5) {
                            System.out.println("You successfully dodged the attack!");
                        } else {
                            health -= attack.getDamage();
                            System.out.println("The " + enemy.getName() + " dealt " + attack.getDamage() + " damage to you");
                            System.out.println("You are now on " + health + "HP");
                        }
                    } else {
                        System.out.println("The " + enemy.getName() + " is now stunned by your electro bolt. It cannot attack!");
                        stunRound--;
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
                if(canUse) {
                    health = Math.min(100, health + 50);
                    System.out.println("You restored your health to " + health);
                }
            } else if(input.equals("use-eve")) {
                boolean canUse = inventory.useEveHypo();
                if(canUse) {
                    eve = Math.min(5, eve + 3);
                    System.out.println("You restored your EVE level to " + eve);
                }
            } else if(input.split(" ")[0].equals("get-info")) {
                String[] splitted = input.split(" ", 2);
                inventory.getItemDescription(splitted[1]);
            } else if(input.equals("status")) {
                System.out.println();
                System.out.println("Health: " + health + " | EVE: " + eve + " | Coins: " + coins);
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

    public int getRemainingCoins() {
        return coins;
    }

    public Inventory getRemainingInventory() {
        return inventory;
    }
}
