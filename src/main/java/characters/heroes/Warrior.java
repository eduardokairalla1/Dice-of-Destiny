/**
 * Defines the Warrior hero class.
 */

package src.main.java.characters.heroes;

// --- IMPORTS ---
import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;


// --- Code ---
public class Warrior extends Character {

    // --- CONSTRUCTOR ---
    public Warrior(String name, Inventory inventory) {
        super(name, 100, 15, 10, 1, inventory);
    }


    // --- SPECIAL ATTACK: POWER STRIKE ---
    @Override
    public void specialAttack(Character enemy) {

        // Calculate damage with a 1.5x multiplier
        float damage = this.attackPower * 1.5f - enemy.getDefense();

        // Apply damage if greater than 0
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);

        // Increment special attack counter
        this.specialAttackCounter++;

        System.out.printf("%s uses POWER STRIKE and deals %.1f damage!%n", this.name, damage);
    }
}
