import java.util.*;

public class EnemyFactory {
    private final List<Attack> attacks = Arrays.asList(
            new Attack(4, "Light Wrench Attack", "smacked you with its wrench"), // 0
            new Attack(8, "Heavy Wrench Attack", "held up its wrench and brutally hit you in the stomach"), // 1
            new Attack(5, "Pistol Shot", "fired a bullet from its pistol"), // 2
            new Attack(11, "Machine Gun Shot", "fired multiple times from its machine gun"), // 3
            new Attack(7, "Hook Throw", "threw a bright orange hook at you"), // 4
            new Attack(15, "Hook Slice", "sliced its hook at you and lacerated your neck"), // 5
            new Attack(10, "Fireball", "shot a big ball of fire towards you"), // 6
            new Attack(5, "Iceball", "shot a big ball of ice towards you"), // 7
            new Attack(20, "Grenade Throw", "threw a grenade that exploded in front of you"), // 8
            new Attack(5, "Molotov Cocktail", "threw a molotov cocktail that exploded in front of you"), // 9
            new Attack(4, "Rock Throw", "picked up a huge piece of rock and hurled it at you"), // 10
            new Attack(6, "Smash", "held up its big, meaty hands and smashed it on you") // 11
    );

    private final List<Enemy> enemies = Arrays.asList(
            new Enemy(30, "Thuggish Splicer", "A fast moving splicer who focuses on melee attacks",
                    Arrays.asList(attacks.get(0), attacks.get(1)), 15,
                    new RandomCollection<DropItem>()
                            .add(80, new DropItem("coins", "coins"))
                            .add(20, new DropItem("health pack", "health pack"))),
            new Enemy(40, "Leadhead Splicer", "A tough splicer who attacks from a distance using pistols and machine guns",
                    Arrays.asList(attacks.get(0), attacks.get(1), attacks.get(2), attacks.get(3)), 5,
                    new RandomCollection<DropItem>()
                            .add(45, new DropItem("coins", "coins"))
                            .add(15, new DropItem("pistol ammo", "pistol"))
                            .add(15, new DropItem("machine gun ammo", "machine gun"))
                            .add(10, new DropItem("health pack", "health pack"))
                            .add(5, new DropItem("eve hypo", "eve hypo"))
                            .add(10, new DropItem("shotgun ammo", "shotgun"))),
            new Enemy(20, "Spider Splicer", "This splicer can crawl on ceilings and throw hooks at you from afar. They are also fast and hard to hit",
                    Arrays.asList(attacks.get(4), attacks.get(4), attacks.get(5)), 30,
                    new RandomCollection<DropItem>()
                            .add(80, new DropItem("coins", "coins"))
                            .add(5, new DropItem("health pack", "health pack"))
                            .add(15, new DropItem("eve hypo", "eve hypo"))),
            new Enemy(30, "Houdini Splicer", "This splicer can shoot fireballs and ice balls from its hands",
                    Arrays.asList(attacks.get(6), attacks.get(7), attacks.get(0), attacks.get(1)), 15,
                    new RandomCollection<DropItem>()
                            .add(45, new DropItem("coins", "coins"))
                            .add(15, new DropItem("pistol ammo", "pistol"))
                            .add(15, new DropItem("machine gun ammo", "machine gun"))
                            .add(10, new DropItem("health pack", "health pack"))
                            .add(5, new DropItem("eve hypo", "eve hypo"))
                            .add(10, new DropItem("shotgun ammo", "shotgun"))),
            new Enemy(30, "Nitro Splicer", "A splicer that throws grenades and molotov cocktails at you",
                    Arrays.asList(attacks.get(8), attacks.get(9), attacks.get(9), attacks.get(9)), 20,
                    new RandomCollection<DropItem>()
                            .add(70, new DropItem("coins", "coins"))
                            .add(15, new DropItem("health pack", "health pack"))
                            .add(15, new DropItem("eve hypo", "eve hypo"))),
            new Enemy(80, "Brute Splicer", "A huge splicer that has a ton of health and brute force",
                    Arrays.asList(attacks.get(10), attacks.get(11)), 5,
                    new RandomCollection<DropItem>()
                            .add(45, new DropItem("coins", "coins"))
                            .add(15, new DropItem("pistol ammo", "pistol"))
                            .add(15, new DropItem("machine gun ammo", "machine gun"))
                            .add(15, new DropItem("health pack", "health pack"))
                            .add(10, new DropItem("shotgun ammo", "shotgun")))
    );

    public Enemy create() {
        return enemies.get(new Random().nextInt(enemies.size()));
    }
}
