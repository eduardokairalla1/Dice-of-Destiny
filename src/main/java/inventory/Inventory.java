/**
 * Represents the player's inventory.
 */

package src.main.java.inventory;


// --- IMPORTS ---
import java.util.*;
import src.main.java.items.Item;


// --- CODE ---
public class Inventory implements Cloneable {

    // --- ATTRIBUTES ---
    private final List<Item> items;


    // --- CONSTRUCTORS ---
    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Copy constructor
    public Inventory(Inventory other) {

        // Deep copy of items
        this.items = new ArrayList<>();

        // Clone each item
        for (Item item : other.items) {
            this.items.add((Item)item.clone());
        }
    }


    // --- GETTERS ---
    public List<Item> getItems() {
        return items;
    }


    // --- METHODS ---
    public void addItem(Item newItem) {

        // Iterate through existing items
        for (Item item : items) {

            // Check if item already exists; if so, increase quantity
            if (item.equals(newItem)) {
                item.increaseQuantity(newItem.getQuantity());
                return;
            }
        }

        // If item does not exist, add it to inventory
        items.add(newItem);
    }

    public void removeOneItem(Item itemToRemove) throws NoSuchElementException {

        for (Item item : this.items) {

            // Actual item matches the one to remove: decrease quantity by 1
            if (item.equals(itemToRemove)) {
                item.decreaseQuantity(1);

                // Remove from list if quantity is zero
                if (item.getQuantity() <= 0) {
                    items.remove(item);
                }

                return;
            }
        }

        // Raise exception if item not found
        throw new NoSuchElementException("Item not found in inventory");
    }

    public void listItems() {
        /**
         * Lists all items in the inventory
         */

        // Create a copy to sort
        List<Item> sortedItems = new ArrayList<>(items);

        // Sort items
        Collections.sort(sortedItems);

        // Print each item
        for (int i = 0; i < sortedItems.size(); i++) {
            System.out.println((i + 1) + " - " + sortedItems.get(i));
        }
    }

    public boolean isEmpty() {
        /*
         * Checks if the inventory is empty
         */
        return items.isEmpty();
    }


    // --- OVERRIDDEN METHODS ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Inventory i = (Inventory) obj;

        if (!i.items.equals(this.items)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        if (this.items != null) resp = resp * 5 + this.items.hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public String toString()
    {
        return "Inventory: " + items;
    }

    @Override
    public Object clone() {
        return new Inventory(this);
    }
}
