package src.main.java.inventory;

import src.main.java.items.Item;
import java.util.*;

public class Inventory implements Cloneable {
    private Map<Item, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    // Copy constructor
    public Inventory(Inventory other) {
        this.items = new HashMap<>();
        for (Item item : other.items.keySet()) {
            this.items.put(item.clone(), other.items.get(item));
        }
    }

    public void addItem(Item newItem) {
        for (Item item : items.keySet()) {
            if (item.equals(newItem)) {
                item.increaseQuantity(newItem.getQuantity());
                return;
            }
        }
        items.put(newItem, newItem.getQuantity());
    }

    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            item.decreaseQuantity(1);
            if (item.getQuantity() <= 0) items.remove(item);
        }
    }

    public void listItems() {
        System.out.println("\n🎒 Inventory:");
        List<Item> sortedItems = new ArrayList<>(items.keySet());
        Collections.sort(sortedItems);
        for (Item item : sortedItems) {
            System.out.println(" - " + item);
        }
    }

    @Override
    public Inventory clone() {
        return new Inventory(this);
    }
}
