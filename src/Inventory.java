import java.util.*;

public class Inventory {
    private int healthPackCount = 2;
    private int eveHypoCount = 1;

    private final List<Weapon> weapons = new ArrayList<>();

    public Inventory() {
        weapons.add(new Weapon("Wrench",
                "A melee weapon that deals a large amount of damage", 10,
                Integer.MAX_VALUE, 0, Integer.MAX_VALUE, "wrench.wav"));
        weapons.add(new Weapon("Electro Bolt",
                "A plasmid that allows you to shoot electricity from your hands and stun your enemy. While stunned, enemies take 5 more damage than usual and cannot move. Uses one EVE", 5,
                Integer.MAX_VALUE, 0, Integer.MAX_VALUE, "electro_bolt.wav"));
        weapons.add(new Weapon("Incinerate", "A plasmid that allows you to shoot fire from your hands and burn your enemy. While burnt, enemies take 5 damage every turn. Uses one EVE", 5,
                Integer.MAX_VALUE, 0, Integer.MAX_VALUE, "incinerate.wav"));
        weapons.add(new Weapon("Pistol", "A lightweight ranged weapon that fires one bullet at a time", 15,
                15, 1, 10, "pistol.wav"));
        weapons.add(new Weapon("Machine Gun", "A ranged weapon that fires a rapid sprays of bullets", 20,
                40, 10, 50, "machine_gun.wav"));
        weapons.add(new Weapon("Shotgun", "A closed-range weapon that fires a slug", 30,
                3, 1, 1, "shotgun.wav"));
    }

    public int getHealthPackCount() {
        return healthPackCount;
    }

    public int getEveHypoCount() {
        return eveHypoCount;
    }

    public void addHealthPack() {
        this.healthPackCount++;
    }

    public void addEveHypo() {
        this.eveHypoCount++;
    }

    public boolean useHealthPack() {
        if(healthPackCount <= 0) {
            System.out.println("You have no more health packs!");
            return false;
        }
        else {
            this.healthPackCount--;
            return true;
        }
    }

    public boolean useEveHypo() {
        if(eveHypoCount <= 0) {
            System.out.println("You have no more EVE hypos!");
            return false;
        }
        else {
            this.eveHypoCount--;
            return true;
        }
    }

    public Weapon getWeapon(String input) {
        Weapon cur = null;
        boolean hasFound = false;

        for(Weapon weapon : weapons) {
            if(weapon.getName().toLowerCase().trim().equals(input.toLowerCase().trim())) {
                cur = weapon;
                hasFound = true;
            }
        }

        if(!hasFound) {
            System.out.println("No weapon with such name is found");
            return null;
        } else
            return cur;
    }

    public void getItemDescription(String input) {
        System.out.println();

        if(input.equals("health pack"))
            System.out.println("Health Pack: Restores 50 health");
        else if(input.equals("eve hypo"))
            System.out.println("Eve Hypo: Injection tube that restores 3 EVE points");
        else {
            boolean hasFound = false;

            for(Weapon weapon : weapons) {
                if(weapon.getName().toLowerCase().trim().equals(input.toLowerCase().trim())) {
                    System.out.println(weapon.getName() + ": " + weapon.getDescription());
                    System.out.println("Damage amount: " + weapon.getDamage());

                    if(weapon.getMaxAmmo() < 1000) {
                        System.out.println("Max ammo in weapon count: " + weapon.getMaxAmmo());
                        System.out.println("Current unused ammo count: " + weapon.getAmmoCount());
                        System.out.println("Current ammo in weapon count: " + weapon.getCurrentAmmoCount());
                        System.out.println("Ammo reduction rate: " + weapon.getAmmoReductionRate());
                    }

                    hasFound = true;
                    break;
                }
            }

            if(!hasFound) System.out.println("No weapon with such name is found");
        }

        System.out.println();
    }

    private String getWeaponNames() {
        List<String> weaponNames = new ArrayList<>();

        for(Weapon weapon : weapons)
            weaponNames.add(weapon.getName());

        return String.join(", ", weaponNames);
    }

    public void show() {
        System.out.println();
        System.out.println("Health Pack Count: " + healthPackCount);
        System.out.println("Eve Hypo Count: " + eveHypoCount);
        System.out.println("Weapons/Plasmids: " + getWeaponNames());
        System.out.println();
    }
}
