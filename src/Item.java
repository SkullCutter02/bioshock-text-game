public class Item {
    private final String name;
    private final String target;

    public Item(String name, String target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getTarget() {
        return target;
    }
}
