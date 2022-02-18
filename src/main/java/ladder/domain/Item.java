package ladder.domain;

public class Item {
    private String name;

    private Item() {
    }

    public static Item of(String name) {
        Item item = new Item();
        item.name = name;

        return item;
    }

    public String getName() {
        return name;
    }
}
