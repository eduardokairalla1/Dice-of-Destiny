/*
 * Defines the abstract Character class with battle mechanics.
 */

package src.main.java.characters;


// --- IMPORTS ---
import java.util.*;
import src.main.java.inventory.Inventory;
import src.main.java.items.Item;


// --- CODE ---
public abstract class Character {

    // Attributes
    protected int specialAttackCounter = 0;
    protected String name;
    protected float healthPoints;
    protected float attackPower;
    protected float defense;
    protected int level;
    protected Inventory inventory;

    // --- CONSTRUCTORS ---
    public Character(String name, float healthPoints, float attackPower, float defense, int level, Inventory inventory) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = level;
        this.inventory = new Inventory(inventory);
    }

    // Copy constructor
    public Character(Character other) {
        this.name = other.name;
        this.healthPoints = other.healthPoints;
        this.attackPower = other.attackPower;
        this.defense = other.defense;
        this.level = other.level;
        this.inventory = new Inventory(other.inventory);
    }


    // --- GETTERS & SETTERS ---
    public String getName() {
        return name; 
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public float getDefense() {
        return defense; 
    }

    public int getLevel() {
        return level; 
    }

    public Inventory getInventory() { 
        return inventory; 
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    // --- METHODS ---
    // Abstract method for special attack
    public abstract void specialAttack(Character enemy);

    // Apply item effect to character
    public void useItem(Item item) {

        // Check if item is available
        if (item == null || item.getQuantity() <= 0) {
            System.out.println("❌ Item not available!");
            return;
        }

        // Apply item effect
        System.out.println("\n💊 Using " + item.getName() + "...");

        // Select effect based on item type
        switch (item.getEffect()) {
            case HEAL:
                float healAmount = 20.0f;

                this.healthPoints +=  20.0f;

                System.out.println("✨ Restored " + healAmount + " HP! Current HP: " + this.healthPoints);
                break;

            case ATTACK_BOOST:
                float attackBoost = 5.0f;

                this.attackPower += attackBoost;

                System.out.println("⚔️ Attack increased by " + attackBoost + "! Current Attack: " + this.attackPower);
                break;

            case DEFENSE_BOOST:
                float defenseBoost = 5.0f;

                this.defense += defenseBoost;

                System.out.println("🛡️ Defense increased by " + defenseBoost + "! Current Defense: " + this.defense);
                break;

            case REGENERATION:
                float regenAmount = 10.0f;

                this.healthPoints += regenAmount;

                System.out.println("💚 Regenerated " + regenAmount + " HP! Current HP: " + this.healthPoints);
                break;

            default:
                System.out.println("❌ Unknown item effect!");
                break;
        }

        // Decrease item quantity in inventory
        inventory.removeOneItem(item);
    }

    // Loot items from defeated enemy
    public void lootEnemy(Character enemy) {

        // Announce looting
        System.out.println("\n💰 Looting " + enemy.getName() + "'s inventory...");

        // Transfer items
        List<Item> enemyItems = enemy.getInventory().getItems();

        // Check if enemy has items
        if (enemyItems.isEmpty()) {
            System.out.println("📦 No items found.");
            return;
        }

        // Add each item to player's inventory
        for (Item enemyItem : enemyItems) {

            // Clone the item to avoid reference issues
            Item item = (Item)enemyItem.clone();

            // Add item to inventory
            this.inventory.addItem(item);

            // Announce obtained item
            System.out.println("✅ Obtained: " + item.getName() + " x" + item.getQuantity());
        }
    }

    // Reset special attack counter
    public void resetSpecialAttackCounter() {
        this.specialAttackCounter = 0;
    }

    // Interactive battle method with items and escape
    public boolean battle(Character enemy, Scanner input) {

        // Initialize random number generator
        Random random = new Random();

        // Battle loop
        System.out.println("\n⚔️ Battle starts: " + this.name + " vs " + enemy.name);
        System.out.println("Enemy HP: " + enemy.healthPoints + " | Your HP: " + this.healthPoints);

        // Continue until one character is defeated
        while (this.healthPoints > 0 && enemy.healthPoints > 0) {

            // Display status and options
            System.out.println("\n" + "=".repeat(50));
            System.out.println("💚 Your HP: " + String.format("%.1f", this.healthPoints) +
                             " | 💀 " + enemy.name + " HP: " + String.format("%.1f", enemy.healthPoints));
            System.out.println("=".repeat(50));
            System.out.println("\nWhat will you do?");
            System.out.println("1. ⚔️  Attack");
            System.out.println("2. ✨ Special Attack");
            System.out.println("3. 💊 Use Item");
            System.out.println("4. 🏃 Try to Escape");
            System.out.print("\nChoose an action (1-4): ");

            // Get player choice
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                continue;
            }

            // Flag to check if player acted
            boolean playerActed = false;

            // Handle player choice
            switch (choice) {

                // Normal Attack
                case 1:

                    // Roll a 6-sided die for attack variation
                    int playerRoll = random.nextInt(6) + 1;

                    // Calculate damage
                    float playerDamage = Math.max(0, (this.attackPower + playerRoll) - enemy.defense);

                    // Check for critical hit on a roll of 6
                    if (playerRoll == 6) {
                        System.out.println("💥 Critical hit!");
                        playerDamage *= 1.5;
                    }

                    // Apply damage to enemy
                    enemy.healthPoints -= playerDamage;

                    // Announce attack result
                    System.out.printf("\n⚔️ %s rolls %d and deals %.1f damage!%n", this.name, playerRoll, playerDamage);

                    // Mark that player has acted
                    playerActed = true;
                    break;

                // Special Attack
                case 2:

                    // Check special attack cooldown
                    if (this.specialAttackCounter >= 3) {
                        System.out.println("\n❌ Special attack is on cooldown! You can use it again after 3 turns.");
                        continue;
                    }

                    System.out.println("\n✨ " + this.name + " uses special attack!");
                    this.specialAttack(enemy);
                    playerActed = true;
                    break;

                // Use Item
                case 3:

                    // Check if inventory is empty
                    if (this.inventory.isEmpty()) {
                        System.out.println("\n❌ Your inventory is empty!");
                        continue;
                    }

                    // Get list of items in inventory
                    List<Item> itemList = this.inventory.getItems();

                    // Display items and prompt for selection
                    System.out.println("\n📦 Select an item to use:");
                    
                    // Print items
                    this.inventory.listItems();

                    System.out.println("0. Cancel");
                    System.out.print("\nSelect item: ");

                    try {

                        // Get item choice
                        int itemChoice = Integer.parseInt(input.nextLine());

                        // Handle cancellation
                        if (itemChoice == 0) {
                            continue;
                        }

                        // Validate and use selected item
                        if (itemChoice > 0 && itemChoice <= itemList.size()) {
                            this.useItem(itemList.get(itemChoice - 1));
                            playerActed = true;
                        } 
                        
                        // Invalid selection
                        else {
                            System.out.println("❌ Invalid item selection!");
                            continue;
                        }

                    // Handle invalid input
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("❌ Invalid input!");
                        continue;
                    }
                    break;

                // Try to Escape
                case 4:

                    // Roll to determine escape success
                    int escapeRoll = random.nextInt(6) + 1;

                    // Announce escape attempt
                    System.out.println("\n🏃 Attempting to escape... (rolled " + escapeRoll + ")");

                    // Check escape success (50% chance)
                    if (escapeRoll >= 4) {
                        System.out.println("✅ You escaped successfully!");
                        
                        // Escaped - didn't win
                        return false;
                    
                    // Escape failed
                    } else {
                        System.out.println("❌ Failed to escape! The enemy attacks!");
                        playerActed = true; // Enemy still attacks
                    }
                    break;

                // Invalid choice
                default:
                    System.out.println("❌ Invalid choice! Please choose 1-4.");
                    continue;
            }

            // Enemy's turn (if player acted)
            if (playerActed && enemy.healthPoints > 0) {
                try {
                    // Dramatic pause
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Enemy rolls a 6-sided die
                int enemyRoll = random.nextInt(6) + 1;

                // Calculate damage
                float enemyDamage = Math.max(0, (enemy.attackPower + enemyRoll) - this.defense);
                
                // Check for enemy critical hit
                if (enemyRoll == 6) {
                    System.out.println("💥 Enemy lands a critical hit!");
                    enemyDamage *= 1.5;
                }

                // Apply damage to player
                this.healthPoints -= enemyDamage;
                System.out.printf("\n💥 %s rolls %d and deals %.1f damage!%n", enemy.name, enemyRoll, enemyDamage);
            }
        }

        System.out.println("\n" + "=".repeat(50));

        // Battle results
        if (this.healthPoints <= 0 && enemy.healthPoints <= 0) {
            System.out.println("🤝 It's a draw!");
            return false;
        } 
        else if (this.healthPoints <= 0) {
            System.out.println("💀 " + this.name + " was defeated!");
            return false;
        } 
        else {
            System.out.println("🏆 Victory! " + enemy.name + " was defeated!");
            this.lootEnemy(enemy);
            return true;
        }
    }

    // --- OVERRIDES ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Character c = (Character)obj;

        if (!c.name.equals(this.name) || c.healthPoints != this.healthPoints || c.attackPower != this.attackPower || c.defense != this.defense || c.level != this.level) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        resp = resp * 2 + this.name.hashCode();
        resp = resp * 3 + ((Float)this.healthPoints).hashCode();
        resp = resp * 5 + ((Float)this.attackPower).hashCode();
        resp = resp * 7 + ((Float)this.defense).hashCode();
        resp = resp * 11 + ((Integer)this.level).hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | HP: %.1f | Attack: %.1f | Defense: %.1f | Level: %d", this.name, this.healthPoints, this.attackPower, this.defense, this.level);
    }
}
