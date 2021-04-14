import java.util.*;

public class Battle {
    private int health;
    private int eve;

    private final Enemy enemy;

    private final Scanner scanner = new Scanner(System.in);

    public Battle(int health, int eve) {
        SplicerFactory factory = new SplicerFactory();

        this.enemy = factory.create();
        this.health = health;
        this.eve = eve;
    }

    private String getEnemyAttacks() {
        List<String> attackNames = new ArrayList<>();

        for(Attack attack : enemy.getAttacks()) {
            attackNames.add(attack.getName());
        }

        return String.join(", ", attackNames);
    }

    private void endBattle() {
        if(enemy.getHealth() <= 0) {
            System.out.println("The " + enemy.getName() + " collapsed and died, you won!");
            System.out.println();
        }
    }

    public void start() {
        System.out.println("A " + enemy.getName() + " appeared!");

        while (enemy.getHealth() > 0 && health > 0) {
            System.out.println();
            System.out.println("Type attack to damage the " + enemy.getName() + ". Type description to get the enemy's description");
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("attack")) {
                // player attack
                System.out.println("You attacked with your wrench");
                int r = (new Random()).nextInt(100);

                if(r < enemy.getDodgeRate()) {
                    System.out.println("The " + enemy.getName() + " dodged your attack");
                } else {
                    enemy.damage(10);
                    System.out.println("You dealt 10 damage to the " + enemy.getName());
                    System.out.println("The " + enemy.getName() + " is now on " + enemy.getHealth() + "HP");
                }

                // enemy attack
                Attack attack = enemy.getRandomAttack();
                int r1 = (new Random()).nextInt(100);
                System.out.println("The " + enemy.getName() + " " + attack.getDescription());

                if(r1 < 5) {
                    System.out.println("You successfully dodged the attack!");
                } else {
                    health -= attack.getDamage();
                    System.out.println("The " + enemy.getName() + " dealt " + attack.getDamage() + " to you");
                    System.out.println("You are now on " + health + "HP");
                }
            } else if(input.equals("description")) {
                System.out.println();
                System.out.println("Name: " + enemy.getName());
                System.out.println("Health: " + enemy.getHealth());
                System.out.println("Description: " + enemy.getDescription());
                System.out.println("Attacks: " + getEnemyAttacks());
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
}
