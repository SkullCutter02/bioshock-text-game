import java.util.*;

public class SplicerFactory {
    private final List<Attack> attacks = Arrays.asList(
            new Attack(4, "Light Wrench Attack", "smacked you with its wrench"), // 0
            new Attack(10, "Heavy Wrench Attack", "held up its wrench and brutally hit you in the stomach"), // 1
            new Attack(5, "Pistol Shot", "fired a bullet from its pistol"), // 2
            new Attack(11, "Machine Gun Shot", "fired multiple times from its machine gun"), // 3
            new Attack(7, "Hook Throw", "threw a bright orange hook at you") // 4
    );

    private final List<Enemy> splicers = Arrays.asList(
            new Enemy(15, "Thuggish Splicer", "A fast moving splicer who focuses on melee attacks",
                    Arrays.asList(attacks.get(0), attacks.get(1)), 15,
                    new RandomCollection<DropItem>()
                            .add(80, new DropItem("coins", "coins"))
                            .add(20, new DropItem("health pack", "health pack"))),
            new Enemy(25, "Leadhead Splicer", "A tough splicer who attacks from a distance using pistols and machine guns",
                    Arrays.asList(attacks.get(2), attacks.get(3)), 5,
                    new RandomCollection<DropItem>()
                            .add(45, new DropItem("coins", "coins"))
                            .add(20, new DropItem("pistol ammo", "pistol"))
                            .add(15, new DropItem("machine gun ammo", "machine gun"))
                            .add(7, new DropItem("health pack", "health pack"))
                            .add(7, new DropItem("eve hypo", "eve hypo"))
                            .add(3, new DropItem("pistol", "pistol"))
                            .add(3, new DropItem("machine gun", "machine gun"))),
            new Enemy(10, "Spider Splicer", "This splicer can crawl on ceilings and throw hooks at you from afar. They are also fast and hard to hit",
                    Arrays.asList(attacks.get(4)), 30,
                    new RandomCollection<DropItem>()
                            .add(80, new DropItem("coins", "coins"))
                            .add(5, new DropItem("health pack", "health pack"))
                            .add(15, new DropItem("eve hypo", "eve hypo")))
    );

    public Enemy create() {
        return splicers.get(new Random().nextInt(splicers.size()));
    }
}
