/**
 * Represents an item in the game.
 */

package src.main.java.items;


// --- CODE ---
public class Item implements Comparable<Item>, Cloneable {

    // --- ATTRIBUTES ---
    private String name;
    private String description;
    private EffectType effect;
    private int quantity;


    // --- CONSTRUCTORS ---
    public Item(String name, String description, EffectType effect, int quantity) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.quantity = quantity;
    }

    // Copy constructor
    public Item(Item other) throws IllegalArgumentException {

        if (other == null) {
            throw new IllegalArgumentException("Cannot copy null Item");
        }

        this.name = other.name;
        this.description = other.description;
        this.effect = other.effect;
        this.quantity = other.quantity;
    }


    // --- GETTERS ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EffectType getEffect() {
        return effect;
    }

    public int getQuantity() {
        return quantity;
    }


    // --- METHODS ---
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        this.quantity = Math.max(0, this.quantity - amount);
    }


    // --- OVERRIDDEN METHODS ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Item i = (Item)obj;

        if (!i.name.equals(this.name) ||
            !i.description.equals(this.description) ||
            i.effect != this.effect)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        resp = resp * 2 + this.name.hashCode();
        resp = resp * 3 + this.description.hashCode();

        if (this.effect!=null) resp = resp * 5 + this.effect.hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public int compareTo(Item other) {

        // Check for same object
        if (this == other) return 0;

        // Compare by name
        int result = this.name.compareToIgnoreCase(other.name);

        if (result < 0) return -1;
        if (result > 0) return 1;

        return 0;
    }

    @Override
    public Object clone() {
        return new Item(this);
    }

    @Override
    public String toString()
    {
        return this.name + " (" + this.description + ") x" + this.quantity;
    }
}
