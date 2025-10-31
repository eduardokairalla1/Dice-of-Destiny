/**
 * Represents an event that can occur in a room.
 */

package src.main.java.game;


// --- IMPORTS ---
import java.util.*;
import src.main.java.characters.Character;
import src.main.java.characters.enemies.Enemy;
import src.main.java.items.Item;


// --- CODE ---
public class Event {

    // --- ATTRIBUTES ---
    private String name;
    private String description;
    private EventType type;
    private Enemy enemy;
    private List<Item> treasureItems;
    private float trapDamage;
    private Map<String, String> choices;


    // --- CONSTRUCTORS ---
    public Event(String name, String description, EventType type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.treasureItems = new ArrayList<>();
        this.choices = new HashMap<>();
    }

    // Constructor for BATTLE event
    public Event(String name, String description, Enemy enemy) {
        this(name, description, EventType.BATTLE);
        this.enemy = enemy;
    }

    // Constructor for TRAP event
    public Event(String name, String description, float trapDamage) {
        this(name, description, EventType.TRAP);
        this.trapDamage = trapDamage;
    }

    // Constructor for TREASURE event
    public Event(String name, String description, List<Item> items) {
        this(name, description, EventType.TREASURE);
        this.treasureItems = new ArrayList<>(items);
    }


    // --- GETTERS AND SETTERS ---
    public String getName() { return name; }
    public String getDescription() { return description; }
    public EventType getType() { return type; }
    public Enemy getEnemy() { return enemy; }
    public List<Item> getTreasureItems() { return treasureItems; }
    public float getTrapDamage() { return trapDamage; }
    public Map<String, String> getChoices() { return choices; }

    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
    public void setTrapDamage(float damage) { this.trapDamage = damage; }
    public void addTreasureItem(Item item) { this.treasureItems.add(item); }
    public void addChoice(String choice, String outcome) { this.choices.put(choice, outcome); }


    // --- METHODS ---
    // Execute the event
    public boolean execute(Character player, Scanner input) {
        System.out.println("\n" + "━".repeat(60));
        System.out.println("🎲 EVENT: " + this.name);
        System.out.println("━".repeat(60));
        System.out.println(this.description);
        System.out.println();

        switch (this.type) {
            case BATTLE:
                return executeBattle(player, input);

            case TRAP:
                return executeTrap(player);

            case TREASURE:
                return executeTreasure(player);

            case DECISION:
                return executeDecision(player, input);

            case REST:
                return executeRest(player);

            case MYSTERY:
                return executeMystery(player);

            default:
                System.out.println("❓ Nothing happens...");
                return true;
        }
    }

    // Execute BATTLE event
    private boolean executeBattle(Character player, Scanner input) {
        // Check if enemy is set
        if (this.enemy == null) {
            System.out.println("⚠️ No enemy found!");
            return true;
        }

        // Start battle
        System.out.println("⚔️ A wild " + this.enemy.getName() + " appears!");
        System.out.println("Prepare for battle!\n");

        // Small delay before battle starts
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Execute battle
        return player.battle(this.enemy, input);
    }

    // Execute TRAP event
    private boolean executeTrap(Character player) {
        System.out.println("⚠️ It's a trap!");

        // Roll the dice
        Random random = new Random();
        int dodgeRoll = random.nextInt(6) + 1;

        System.out.println("🎲 Rolling to dodge... (rolled " + dodgeRoll + ")");

        // Check dodge success (33% chance)
        if (dodgeRoll >= 5) {
            System.out.println("✅ You dodged the trap!");
            return true;
        
        // Apply trap damage
        } else {
            float actualDamage = this.trapDamage;

            player.setHealthPoints(player.getHealthPoints() - actualDamage);

            System.out.println("💥 The trap hits you for " + actualDamage + " damage!");
            System.out.println("💚 Current HP: " + player.getHealthPoints());

            // Check if player is dead
            if (player.getHealthPoints() <= 0) {
                System.out.println("\n💀 You died from the trap!");
                return false;
            }

            return true;
        }
    }

    // Execute TREASURE event
    private boolean executeTreasure(Character player) {

        // Check if there are items in the treasure
        if (this.treasureItems.isEmpty()) {
            System.out.println("📦 The chest is empty...");
            return true;
        }

        // Give items to player
        System.out.println("💎 You found treasure!");
        for (Item item : this.treasureItems) {
            player.getInventory().addItem((Item)item.clone());
            System.out.println("✅ Obtained: " + item.getName() + " x" + item.getQuantity());
        }

        return true;
    }

    // Execute DECISION event
    private boolean executeDecision(Character player, Scanner input) {

        // Check if there are choices available
        if (this.choices.isEmpty()) {
            System.out.println("🤔 Nothing to decide...");
            return true;
        }

        System.out.println("🤔 What will you do?");

        // Display choices
        List<String> choiceList = new ArrayList<>(this.choices.keySet());
        for (int i = 0; i < choiceList.size(); i++) {
            System.out.println((i + 1) + ". " + choiceList.get(i));
        }

        // Get player choice
        System.out.print("\nChoose (1-" + choiceList.size() + "): ");
        try {
            int choice = Integer.parseInt(input.nextLine());

            // Validate choice
            if (choice > 0 && choice <= choiceList.size()) {
                
                // Get selected choice
                String selectedChoice = choiceList.get(choice - 1);

                // Execute selected choice
                String outcome = this.choices.get(selectedChoice);

                System.out.println("\n" + outcome);

                return true;
            } else {
                System.out.println("❌ Invalid choice!");
                return executeDecision(player, input);
            }

        // Handle unexpected errors
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
            return executeDecision(player, input);
        }
    }

    // Execute REST event
    private boolean executeRest(Character player) {
        
        // Check if player is at full health
        if (player.getHealthPoints() >= 100) {
            System.out.println("💤 You are already at full health!");
            return true;
        }
        
        // Set heal amount
        float healAmount = 30.0f;

        // Store old HP for calculation
        float oldHp = player.getHealthPoints();

        // Heal player
        player.setHealthPoints(Math.min(100, player.getHealthPoints() + healAmount));

        // Calculate actual healed amount
        float actualHeal = player.getHealthPoints() - oldHp;

        System.out.println("😴 You rest for a moment...");
        System.out.println("✨ Restored " + actualHeal + " HP!");
        System.out.println("💚 Current HP: " + player.getHealthPoints());
        return true;
    }

    // Execute MYSTERY event
    private boolean executeMystery(Character player) {

        // Roll dice
        Random random = new Random();
        int roll = random.nextInt(3);

        System.out.println("✨ Something mysterious happens...");

        switch (roll) {

            // Trap event
            case 0:
                float damage = 10.0f;
                player.setHealthPoints(player.getHealthPoints() - damage);
                System.out.println("💥 You triggered a hidden curse! Lost " + damage + " HP!");
                return player.getHealthPoints() > 0;

            // Heal event
            case 1:
                float heal = 15.0f;
                player.setHealthPoints(Math.min(100, player.getHealthPoints() + heal));
                System.out.println("✨ A magical aura heals you for " + heal + " HP!");
                return true;

            // Attack boost event
            case 2:
                player.setAttackPower(player.getAttackPower() + 2);
                System.out.println("⚔️ You feel stronger! Attack increased by 2!");
                return true;

            // Default event
            default:
                return true;
        }
    }

    // --- OVERRIDES ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Event e = (Event)obj;

        if (!e.name.equals(this.name) || !e.description.equals(this.description) || e.type != this.type) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        resp = resp * 2 + this.name.hashCode();
        resp = resp * 3 + this.description.hashCode();

        if (this.type != null) resp = resp * 5 + this.type.hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public String toString()
    {
        return this.name + " (" + this.type + ")";
    }
}
