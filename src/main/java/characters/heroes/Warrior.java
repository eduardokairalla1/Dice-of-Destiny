package src.main.java.characters.heroes;

import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;

public class Warrior extends Character {
    public Warrior(String name, Inventory inventory) {
        super(name, 100, 15, 10, 1, inventory);
    }

    @Override
    public void specialAttack(Character enemy) {
        float damage = this.attackPower * 1.5f - enemy.getDefense();
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        System.out.printf("%s uses POWER STRIKE and deals %.1f damage!%n", this.name, damage);
    }
}
