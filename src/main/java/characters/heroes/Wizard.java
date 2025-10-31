/**
 * Defines the Wizard hero class.
 */

package src.main.java.characters.heroes;


// --- IMPORTS ---
import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;


// --- CODE ---
public class Wizard extends Character {

    // --- CONSTRUCTOR ---
    public Wizard(String name, Inventory inventory) {
        super(name, 80, 18, 6, 1, inventory);
    }


    // --- SPECIAL ATTACK: FIREBALL ---
    @Override
    public void specialAttack(Character enemy) {
        // Calculate damage with a 1.8x multiplier
        float damage = this.attackPower * 1.8f - enemy.getDefense();

        // Apply damage if greater than 0
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);

        // Increment special attack counter
        this.specialAttackCounter++;

        System.out.printf("%s casts FIREBALL and deals %.1f damage!%n", this.name, damage);
    }
}
