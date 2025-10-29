package src.main.java;

import src.main.java.characters.*;
import src.main.java.characters.heroes.*;
import src.main.java.inventory.*;
import src.main.java.items.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Welcome to Dice of Destiny ===");
        System.out.print("Enter your hero's name: ");
        String name = input.nextLine();

        Inventory playerInventory = new Inventory();
        playerInventory.addItem(new Item("Healing Potion", "Restores 20 HP", EffectType.HEAL, 3));

        Warrior player = new Warrior(name, playerInventory);
        Enemy goblin = new Enemy("Goblin", 50, 8, 4, 1, new Inventory());

        goblin.getInventory().addItem(new Item("Rusty Sword", "Increases attack power slightly", EffectType.ATTACK_BOOST, 1));

        player.battle(goblin);

        input.close();
    }
}
