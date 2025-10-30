package src.main.java.characters.heroes;

import src.main.java.characters.Character;
import src.main.java.inventory.Inventory;

public class Wizard extends Character {
    public Wizard(String name, Inventory inventory) {
        super(name, 80, 18, 6, 1, inventory);
    }

    @Override
    public void specialAttack(Character enemy) {
        float damage = this.attackPower * 1.8f - enemy.getDefense();
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        System.out.printf("%s casts FIREBALL and deals %.1f damage!%n", this.name, damage);
    }
}
