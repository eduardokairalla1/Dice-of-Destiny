package src.main.java.characters;

import src.main.java.inventory.Inventory;

public class Enemy extends Character {
    public Enemy(String name, float healthPoints, float attackPower, float defense, int level, Inventory inventory) {
        super(name, healthPoints, attackPower, defense, level, inventory);
    }

    @Override
    public void specialAttack(Character enemy) {
        float damage = this.attackPower * 1.2f - enemy.getDefense();
        if (damage > 0) enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        System.out.printf("%s performs a FRENZIED ATTACK dealing %.1f damage!%n", this.name, damage);
    }
}
