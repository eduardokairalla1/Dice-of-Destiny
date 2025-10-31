/**
 * Represents the various effects an item can have in the game.
 */

package src.main.java.items;


// --- CODE ---
public enum EffectType {

    // --- ENUMERATED TYPES ---
    HEAL("Restores health points (HP)"),
    ATTACK_BOOST("Increases attack power"),
    DEFENSE_BOOST("Increases defense"),
    REGENERATION("Gradually restores HP over time");

    // --- ATTRIBUTES ---
    private final String description;


    // --- CONSTRUCTOR ---
    EffectType(String description) {
        this.description = description;
    }


    // --- GETTER ---
    public String getDescription() {
        return description;
    }
}
