package src.main.java.characters;

import src.main.java.inventory.*;;

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

    // Construtor de cópia
    public Character(Character other) {
        this.name = other.name;
        this.healthPoints = other.healthPoints;
        this.attackPower = other.attackPower;
        this.defense = other.defense;
        this.level = other.level;
        this.inventory = new Inventory(other.inventory);
    }

    // Getters e setters
    public String getName() {
        return name; 
    }

    public float getHealthPoints() {
        return healthPoints;
    }
    public float getAttackPower() {
        return attackPower;
    }
    public float getDefense() {
        return defense;
    }
    public int getLevel() {
        return level;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public abstract void specialAttack(Character enemy);
}
