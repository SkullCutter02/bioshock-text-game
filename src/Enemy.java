import java.util.*;

public class Enemy {
    private int health;

    private final int dodgeRate; // between 0 and 100

    private final String name;
    private final String description;

    private final List<Attack> attacks;

    private boolean stunned = false;
    private boolean burnt = false;

    private final RandomCollection<DropItem> randomCollection;

    public Enemy(int health, String name, String description, List<Attack> attacks,
                 int dodgeRate, RandomCollection<DropItem> randomCollection) {
        this.health = health;
        this.name = name;
        this.description = description;
        this.attacks = attacks;
        this.dodgeRate = dodgeRate;
        this.randomCollection = randomCollection;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDodgeRate() {
        return dodgeRate;
    }

    public boolean isStunned() {
        return stunned;
    }

    public boolean isBurnt() {
        return burnt;
    }

    public void damage(int amount) {
        this.health = this.health - amount;
    }

    public void stun() {
        this.stunned = true;
    }

    public void unstun() {
        this.stunned = false;
    }

    public void burn() {
        this.burnt = true;
    }

    public void unburn() {
        this.burnt = false;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public Attack getRandomAttack() {
        return attacks.get(new Random().nextInt(attacks.size()));
    }

    public DropItem getDropItem() {
        return randomCollection.next();
    }
}
