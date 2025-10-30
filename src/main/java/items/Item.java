package src.main.java.items;

import java.util.Objects;

public class Item implements Comparable<Item>, Cloneable {
    private String name;
    private String description;
    private EffectType effect;
    private int quantity;

    public Item(String name, String description, EffectType effect, int quantity) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.quantity = quantity;
    }

    public Item(Item other) {
        this.name = other.name;
        this.description = other.description;
        this.effect = other.effect;
        this.quantity = other.quantity;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public EffectType getEffect() { return effect; }
    public int getQuantity() { return quantity; }

    public void increaseQuantity(int amount) { this.quantity += amount; }
    public void decreaseQuantity(int amount) { this.quantity = Math.max(0, this.quantity - amount); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && effect == item.effect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, effect);
    }

    @Override
    public int compareTo(Item other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public Item clone() {
        return new Item(this);
    }

    @Override
    public String toString() {
        return name + " (" + description + ") x" + quantity;
    }
}
