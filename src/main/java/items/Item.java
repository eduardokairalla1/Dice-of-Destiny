package src.main.java.items;

public class Item {
    private static final int MAX_STACK = 20;

    private String name;
    private String description;
    private EffectType effect;
    private int amount;

    public Item(String name, String description, EffectType effect, int amount) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.amount = amount;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public EffectType getEffect() {
        return this.effect;
    }

    public int getAmount() {
        return this.amount;
    }

    public void addAmount(int value) throws IllegalArgumentException {

        if (value < 0) {
            throw new IllegalArgumentException("Value to add must be non-negative");
        }

        if (this.amount + value > MAX_STACK) {
            throw new IllegalArgumentException("You cannot have more than " + MAX_STACK + " items of the same type.");
        }

        this.amount += value;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Item other = (Item) obj;

        if (this.amount != other.amount) return false;
        if (this.effect != other.effect) return false;

        if (this.name == null) {
            if (other.name != null) return false;
        } else if (!this.name.equals(other.name)) return false;

        if (this.description == null) {
            if (other.description != null) return false;
        } else if (!this.description.equals(other.description)) return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int hash = 33;
 
        hash = hash * 2 + (this.name == null ? 0 : this.name.hashCode());
        hash = hash * 5 + (this.description == null ? 0 : this.description.hashCode());
        hash = hash * 7 + (this.effect == null ? 0 : this.effect.hashCode());
        hash = hash * 11 + Integer.hashCode(this.amount);

        if (hash < 0) hash = -hash;

        return hash;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", effect=" + effect +
                ", amount=" + amount +
                '}';
    }
}
