/**
 * Defines the Enemy class.
 */

package src.main.java.characters.enemies;

// --- IMPORTS ---
import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;


// --- CODE ---
public class Enemy extends Character {

    // --- CONSTRUCTOR ---
    public Enemy(String name, float healthPoints, float attackPower, float defense, int level, Inventory inventory) {
        super(name, healthPoints, attackPower, defense, level, inventory);
    }

    // --- SPECIAL ATTACK: FRENZIED ATTACK ---
    @Override
    public void specialAttack(Character hero) {

        // Calculate damage with a 1.2x multiplier
        float damage = this.attackPower * 1.2f - hero.getDefense();

        // Apply damage if greater than 0
        if (damage > 0) hero.setHealthPoints(hero.getHealthPoints() - damage);

        // Increment special attack counter
        this.specialAttackCounter++;

        // --- SPECIAL ATTACK: FRENZIED ATTACK ---
        System.out.printf("%s performs a FRENZIED ATTACK dealing %.1f damage!%n", this.name, damage);
    }
}
