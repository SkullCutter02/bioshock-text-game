public class Weapon {
    private final String name;
    private final String description;
    private final int damage;
    private final int ammoReductionRate;
    private final int maxAmmo;

    private int ammoCount;
    private int currentAmmoCount;

    public Weapon(String name, String description, int damage, int initialAmmoCount, int ammoReductionRate, int maxAmmo) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.ammoCount = initialAmmoCount;
        this.ammoReductionRate = ammoReductionRate;
        this.maxAmmo = maxAmmo;
        this.currentAmmoCount = Math.min(this.maxAmmo, this.ammoCount);
        this.ammoCount = Math.max(0, this.ammoCount - currentAmmoCount);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public int getCurrentAmmoCount() {
        return currentAmmoCount;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getAmmoReductionRate() {
        return ammoReductionRate;
    }

    public void addAmmo(int amount) {
        this.ammoCount += amount;
    }

    public void reload() {
        if(ammoCount <= 0)
            System.out.println("You ran out of ammo for your " + name + "!");
        else {
            if(currentAmmoCount == maxAmmo)
                System.out.println(name + " is already full!");
            else {
                int temp = currentAmmoCount;
                this.currentAmmoCount = Math.min(maxAmmo, currentAmmoCount + ammoCount);
                this.ammoCount = Math.max(0, ammoCount - (currentAmmoCount - temp));
                System.out.println(name + " successfully reloaded!");
            }
        }
    }

    public void use() {
        this.currentAmmoCount = Math.max(0, currentAmmoCount - ammoReductionRate);
    }
}
