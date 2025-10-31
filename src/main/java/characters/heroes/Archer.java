/**
 * Defines the Archer hero class.
 */

package src.main.java.characters.heroes;


// --- IMPORTS ---
import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;


// --- CODE ---
public class Archer extends Character {

    // --- CONSTRUCTOR ---
    public Archer(String name, Inventory inventory) {
        super(name, 90, 14, 8, 1, inventory);
    }


    // --- SPECIAL ATTACK: PRECISION ARROW ---
    @Override
    public void specialAttack(Character enemy) {
        // Calculate damage with a 1.3x multiplier
        float damage = this.attackPower * 1.3f - enemy.getDefense();

        // Apply damage if greater than 0
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);

        // Increment special attack counter
        this.specialAttackCounter++;

        System.out.printf("%s shoots a PRECISION ARROW and deals %.1f damage!%n", this.name, damage);
    }
}
