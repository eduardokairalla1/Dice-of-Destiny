package src.main.java.characters.heroes;

import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;

public class Archer extends Character {
    public Archer(String name, Inventory inventory) {
        super(name, 90, 14, 8, 1, inventory);
    }

    @Override
    public void specialAttack(Character enemy) {
        float damage = this.attackPower * 1.3f - enemy.getDefense();
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        System.out.printf("%s shoots a PRECISION ARROW and deals %.1f damage!%n", this.name, damage);
    }
}
