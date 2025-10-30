package src.main.java.characters;

import src.main.java.inventory.Inventory;
import java.util.Random;

public abstract class Character {
    protected String name;
    protected float healthPoints;
    protected float attackPower;
    protected float defense;
    protected int level;
    protected Inventory inventory;

    public Character() {}

    public Character(String name, float healthPoints, float attackPower, float defense, int level, Inventory inventory) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = level;
        this.inventory = inventory;
    }

    // Copy constructor
    public Character(Character other) {
        this.name = other.name;
        this.healthPoints = other.healthPoints;
        this.attackPower = other.attackPower;
        this.defense = other.defense;
        this.level = other.level;
        this.inventory = new Inventory(other.inventory);
    }

    public String getName() { return name; }
    public float getHealthPoints() { return healthPoints; }
    public float getAttackPower() { return attackPower; }
    public float getDefense() { return defense; }
    public int getLevel() { return level; }
    public Inventory getInventory() { return inventory; }

    public void setHealthPoints(float healthPoints) { this.healthPoints = healthPoints; }

    public abstract void specialAttack(Character enemy);

    // Basic battle method
    public void battle(Character enemy) {
        Random random = new Random();
        System.out.println("\n⚔️ Battle starts: " + this.name + " vs " + enemy.name);

        while (this.healthPoints > 0 && enemy.healthPoints > 0) {
            int playerRoll = random.nextInt(6) + 1;
            int enemyRoll = random.nextInt(6) + 1;

            float playerDamage = (this.attackPower + playerRoll) - enemy.defense;
            float enemyDamage = (enemy.attackPower + enemyRoll) - this.defense;

            if (playerDamage > 0) enemy.healthPoints -= playerDamage;
            if (enemyDamage > 0) this.healthPoints -= enemyDamage;

            System.out.printf("%s rolls %d and deals %.1f damage! (Enemy HP: %.1f)%n",
                    this.name, playerRoll, Math.max(playerDamage, 0), enemy.healthPoints);
            System.out.printf("%s rolls %d and deals %.1f damage! (Your HP: %.1f)%n",
                    enemy.name, enemyRoll, Math.max(enemyDamage, 0), this.healthPoints);

            System.out.println("-----------------------------");
        }

        if (this.healthPoints <= 0 && enemy.healthPoints <= 0)
            System.out.println("🤝 It's a draw!");
        else if (this.healthPoints <= 0)
            System.out.println("💀 " + this.name + " was defeated!");
        else
            System.out.println("🏆 " + enemy.name + " was defeated!");
    }
}
