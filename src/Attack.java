public class Attack {
    private int damage;
    private String name;
    private String description;

    public Attack(int damage, String name, String description) {
        this.damage = damage;
        this.name = name;
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
