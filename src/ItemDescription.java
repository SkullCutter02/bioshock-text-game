public class ItemDescription {
    Integer price;
    String description;

    public ItemDescription(Integer price, String description) {
        this.price = price;
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
