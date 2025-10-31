/*
 * Represents the main game logic.
 */

package src.main.java.game;


// --- IMPORTS ---
import java.util.*;
import src.main.java.characters.Character;
import src.main.java.characters.enemies.Enemy;
import src.main.java.characters.heroes.*;
import src.main.java.inventory.Inventory;
import src.main.java.items.*;


// --- CODE ---
public class Game {

    // --- ATTRIBUTES ---
    private Character player;
    private Room currentRoom;
    private List<Room> allRooms;
    private Scanner input;
    private boolean gameRunning;

    // --- CONSTRUCTORS ---
    public Game() {
        this.input = new Scanner(System.in);
        this.allRooms = new ArrayList<>();
        this.gameRunning = true;
    }

    // --- METHODS ---
    // Start the game
    public void start() {
        displayIntro();
        createCharacter();
        buildWorld();
        gameLoop();
    }

    // Display game introduction
    private void displayIntro() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("⚔️  DICE OF DESTINY - A Text-Based RPG Adventure  ⚔️");
        System.out.println("=".repeat(70));
        System.out.println("\nWelcome, brave adventurer!");
        System.out.println("In this world, your fate is determined by the roll of dice.");
        System.out.println("Battle monsters, find treasure, and survive deadly traps!");
        System.out.println();
    }

    // Create the player's character
    private void createCharacter() {
        System.out.print("Enter your hero's name: ");
        String name = this.input.nextLine();

        System.out.println("\nChoose your class:");
        System.out.println("1. ⚔️  Warrior (HP: 100, ATK: 15, DEF: 10) - Balanced fighter");
        System.out.println("2. 🏹 Archer  (HP: 90,  ATK: 14, DEF: 8)  - Fast and precise");
        System.out.println("3. 🔮 Wizard  (HP: 80,  ATK: 18, DEF: 6)  - High damage, low defense");
        System.out.print("\nChoose (1-3): ");

        int choice;
        try {
            choice = Integer.parseInt(this.input.nextLine());
        
        // Default to Warrior on invalid input
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Defaulting to Warrior.");
            choice = 1;
        }

        // Basic items add to starting inventory
        Inventory startingInventory = new Inventory();
        startingInventory.addItem(new Item("Healing Potion", "Restores 20 HP", EffectType.HEAL, 3));
        startingInventory.addItem(new Item("Strength Elixir", "Increases attack power", EffectType.ATTACK_BOOST, 1));

        // Define player based on choice
        switch (choice) {
            case 2:
                player = new Archer(name, startingInventory);
                System.out.println("\n🏹 You are now an Archer!");
                break;
            case 3:
                player = new Wizard(name, startingInventory);
                System.out.println("\n🔮 You are now a Wizard!");
                break;
            default:
                player = new Warrior(name, startingInventory);
                System.out.println("\n⚔️ You are now a Warrior!");
                break;
        }

        System.out.println("Good luck, " + name + "!");
    }

    // Build the game world with rooms and events
    private void buildWorld() {
        // Create rooms
        Room entrance = new Room("Cave Entrance",
            "You stand at the entrance of a dark, foreboding cave. " +
            "The air is cold and damp, and you can hear strange noises echoing from within.");

        Room hallway = new Room("Long Hallway",
            "A long, narrow hallway stretches before you. " +
            "Torch sconces line the walls, casting flickering shadows.");

        Room treasury = new Room("Treasury Room",
            "This room sparkles with the reflection of gold and jewels! " +
            "However, you sense danger lurking nearby...");

        Room armory = new Room("Ancient Armory",
            "Rusty weapons and armor are scattered around this room. " +
            "Some items might still be useful.");

        Room throneRoom = new Room("Throne Room",
            "A magnificent throne room with towering pillars. " +
            "This must be where the dungeon's master resides!");

        Room garden = new Room("Underground Garden",
            "Surprisingly, a beautiful garden grows here, lit by magical crystals. " +
            "The air smells fresh and rejuvenating.");

        // Connect rooms
        entrance.addConnectedRoom(hallway);
        entrance.addConnectedRoom(garden);

        hallway.addConnectedRoom(entrance);
        hallway.addConnectedRoom(treasury);
        hallway.addConnectedRoom(armory);

        treasury.addConnectedRoom(hallway);
        treasury.addConnectedRoom(throneRoom);

        armory.addConnectedRoom(hallway);
        armory.addConnectedRoom(throneRoom);

        throneRoom.addConnectedRoom(treasury);
        throneRoom.addConnectedRoom(armory);

        garden.addConnectedRoom(entrance);

        // Add events to rooms
        createEvents(entrance, hallway, treasury, armory, throneRoom, garden);

        // Add to list and set starting room
        allRooms.add(entrance);
        allRooms.add(hallway);
        allRooms.add(treasury);
        allRooms.add(armory);
        allRooms.add(throneRoom);
        allRooms.add(garden);

        currentRoom = entrance;
    }

    // Create events for each room
    private void createEvents(Room entrance, Room hallway, Room treasury, Room armory, Room throneRoom, Room garden) {

        /*
         * Entrance
         * Enemies: Goblin Scout
         * Item: Rusty Dagger
         */
        Inventory goblinInv = new Inventory();
        goblinInv.addItem(new Item("Rusty Dagger", "Old but sharp", EffectType.ATTACK_BOOST, 1));
        Enemy goblin = new Enemy("Goblin Scout", 30, 6, 3, 1, goblinInv);
        entrance.addEvent(new Event("Goblin Ambush", "A small goblin jumps out!", goblin));

        /**
         * Hallway - Trap and decision
         * Enemies: None
         * Item: Spike Trap, Mysterious Doors
         */
        hallway.addEvent(new Event("Spike Trap", "Spikes shoot from the walls!", 15.0f));

        Event doorDecision = new Event("Mysterious Doors", "You see three doors. Which one will you open?", EventType.DECISION);
        doorDecision.addChoice("Green Door", "💥 A blast of fire hits you! (lose 10 HP)");
        doorDecision.addChoice("Black Door", "✨ A healing mist surrounds you! (gain 15 HP)");
        doorDecision.addChoice("Red Door", "🚪 The door opens safely. Nothing happens.");
        hallway.addEvent(doorDecision);

        /**
         * Treasury - Treasure and strong enemy
         * Enemies: Ogre Guardian
         * Items: Gold Coins, Magic Shield
         */
        List<Item> treasureItems = new ArrayList<>();
        treasureItems.add(new Item("Gold Coins", "Shiny gold coins", EffectType.HEAL, 5));
        treasureItems.add(new Item("Magic Shield", "Increases defense", EffectType.DEFENSE_BOOST, 1));
        treasury.addEvent(new Event("Treasure Chest", "You found a chest!", treasureItems));

        Inventory ogreInv = new Inventory();
        ogreInv.addItem(new Item("Giant Club", "Massive weapon", EffectType.ATTACK_BOOST, 1));
        ogreInv.addItem(new Item("Healing Potion", "Restores health", EffectType.HEAL, 2));
        Enemy ogre = new Enemy("Ogre Guardian", 60, 12, 6, 2, ogreInv);
        treasury.addEvent(new Event("Guardian Encounter", "An ogre blocks your path!", ogre));

        /**
         * Armory - Items and trap
         * Enemies: None
         * Items: Iron Sword, Chain Mail, Poison Dart Trap
         */
        List<Item> armoryItems = new ArrayList<>();
        armoryItems.add(new Item("Iron Sword", "Sharp blade", EffectType.ATTACK_BOOST, 1));
        armoryItems.add(new Item("Chain Mail", "Protective armor", EffectType.DEFENSE_BOOST, 1));
        armory.addEvent(new Event("Weapon Cache", "You found weapons!", armoryItems));
        armory.addEvent(new Event("Poison Dart Trap", "Poisoned darts fly at you!", 20.0f));

        /**
         * Throne Room - Boss battle
         * Enemies: Dark Lord
         * Items: Legendary Sword, Dragon Scale Armor, Healing Potion
         */
        Inventory bossInv = new Inventory();
        bossInv.addItem(new Item("Legendary Sword", "Powerful weapon", EffectType.ATTACK_BOOST, 1));
        bossInv.addItem(new Item("Dragon Scale Armor", "Ultimate protection", EffectType.DEFENSE_BOOST, 1));
        bossInv.addItem(new Item("Healing Potion", "Restores health", EffectType.HEAL, 3));
        Enemy boss = new Enemy("Dark Lord", 100, 20, 8, 5, bossInv);
        throneRoom.addEvent(new Event("FINAL BATTLE", "The Dark Lord rises from his throne! This is the final battle!", boss));

        /**
         * Garden - Rest event
         * Enemies: None
         * Items: None
         */
        garden.addEvent(new Event("Peaceful Garden", "The magical garden rejuvenates your spirit.", EventType.REST));
    }

    // Main game loop
    private void gameLoop() {

        // Enter the starting room
        currentRoom.enter();

        // Start game loop
        while (gameRunning && player.getHealthPoints() > 0) {
            System.out.println("\n" + "─".repeat(60));

            // Display menu
            displayMenu();

            // Get player choice
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                continue;
            }

            // Handle choice
            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    move();
                    break;
                case 3:
                    player.getInventory().listItems();
                    break;
                case 4:
                    displayStatus();
                    break;
                case 5:
                    System.out.println("\n👋 Thanks for playing Dice of Destiny!");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please choose 1-5.");
            }

            if (player.getHealthPoints() <= 0) {
                gameOver();
            }
        }

        // Close input scanner
        input.close();
    }

    // Display the main menu
    private void displayMenu() {
        System.out.println("\n📜 What would you like to do?");
        System.out.println("1. 🔍 Explore current room");
        System.out.println("2. 🚶 Move to another room");
        System.out.println("3. 🎒 Check inventory");
        System.out.println("4. 📊 View character status");
        System.out.println("5. 🚪 Exit game");
        System.out.print("\nYour choice: ");
    }

    // Explore the current room
    private void explore() {

        // Trigger a random event in the current room
        Event event = currentRoom.getRandomEvent();

        // No event found
        if (event == null) {
            System.out.println("\n🔍 You search the room but find nothing of interest.");
            System.out.println("Perhaps you should move to another location?");
            return;
        }

        // Execute the event
        boolean survived = event.execute(player, input);

        // Check if player survived the event
        if (!survived) {
            gameOver();
        }
    }

    // Move to another room
    private void move() {

        // Display connected rooms
        List<Room> connectedRooms = currentRoom.getConnectedRooms();

        // No connected rooms
        if (connectedRooms.isEmpty()) {
            System.out.println("\n🚫 There are no exits from this room!");
            return;
        }

        // List connected rooms
        System.out.println("\n🚪 Available exits:");
        for (int i = 0; i < connectedRooms.size(); i++) {
            System.out.println((i + 1) + ". " + connectedRooms.get(i));
        }
        System.out.println("0. Cancel");
        System.out.print("\nWhere do you want to go? ");

        try {

            // Get player choice
            int choice = Integer.parseInt(input.nextLine());
            
            // Cancel move
            if (choice == 0) {
                return;
            }
            
            // Move to chosen room
            if (choice > 0 && choice <= connectedRooms.size()) {
                currentRoom = connectedRooms.get(choice - 1);
                currentRoom.enter();

            // Invalid choice
            } else {
                System.out.println("❌ Invalid choice!");
            }

        // Invalid input
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
        }
    }

    // Display the player's current status
    private void displayStatus() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 CHARACTER STATUS");
        System.out.println("═".repeat(60));
        System.out.println("Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("💚 Health: " + String.format("%.1f", player.getHealthPoints()));
        System.out.println("⚔️  Attack: " + String.format("%.1f", player.getAttackPower()));
        System.out.println("🛡️  Defense: " + String.format("%.1f", player.getDefense()));
        System.out.println("📍 Current Location: " + currentRoom.getName());
        System.out.println("═".repeat(60));
    }

    // Handle game over scenario
    private void gameOver() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("💀 GAME OVER 💀");
        System.out.println("═".repeat(60));
        System.out.println("You have fallen in the dungeon...");
        System.out.println("Your adventure ends here, " + player.getName() + ".");
        System.out.println("\nThank you for playing Dice of Destiny!");
        gameRunning = false;
    }

    // --- OVERRIDES ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Game g = (Game)obj;

        if (this.player!=null && g.player!=null) {
            if (!this.player.equals(g.player))
                return false;
        } else if (this.player!=null || g.player!=null) {
            return false;
        }

        if (this.gameRunning != g.gameRunning)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        if (this.player!=null)
            resp = resp * 2 + this.player.hashCode();

        resp = resp * 3 + ((Boolean)this.gameRunning).hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public String toString()
    {
        String playerInfo = (this.player!=null) ? this.player.getName() : "No player";
        String roomInfo = (this.currentRoom!=null) ? this.currentRoom.getName() : "No room";
        String status = this.gameRunning ? "Running" : "Stopped";

        return "Game [Player: " + playerInfo +
               ", Current Room: " + roomInfo +
               ", Status: " + status + "]";
    }
}
