import java.util.*;

public class SplicerFactory {
    private final List<Attack> attacks = Arrays.asList(
            new Attack(4, "Light Wrench Attack", "smacked you with its wrench"),
            new Attack(10, "Heavy Wrench Attack", "held up its wrench and brutally hit you in the stomach"),
            new Attack(5, "Pistol Shot", "fired a bullet from its pistol"),
            new Attack(11, "Machine Gun Shot", "fired multiple times from its machine gun"),
            new Attack(7, "Hook Throw", "threw a bright orange hook at you")
    );

    private final List<Splicer> splicers = Arrays.asList(
            new Splicer(15, "Thuggish Splicer", "A fast moving splicer who focuses on melee attacks",
                    Arrays.asList(attacks.get(0), attacks.get(1)), 15),
            new Splicer(25, "Leadhead Splicer", "A tough splicer who attacks from a distance using pistols and machine guns",
                    Arrays.asList(attacks.get(2), attacks.get(3)), 5),
            new Splicer(10, "Spider Splicer", "This splicer can crawl on ceilings and throw hooks at you from afar. They are also fast and hard to hit",
                    Arrays.asList(attacks.get(4)), 30)
    );

    public Splicer create() {
        return splicers.get(new Random().nextInt(splicers.size()));
    }
}
