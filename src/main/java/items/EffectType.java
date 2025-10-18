package src.main.java.items;

public enum EffectType {
    HEAL("Restores health points (HP)"),
    DAMAGE("Deals damage to target"),
    ATTACK_BOOST("Increases attack power"),
    DEFENSE_BOOST("Increases defense"),
    SPEED_BOOST("Increases movement or attack speed"),
    CRITICAL_CHANCE_BOOST("Increases critical hit chance"),
    POISON("Deals damage over time"),
    BURN("Deals fire damage over time"),
    FREEZE("Prevents target from acting for a short time"),
    STUN("Stuns target, disabling actions"),
    SHIELD("Adds temporary hit points or absorbs damage"),
    REGENERATION("Gradually restores HP over time"),
    MANA_RESTORE("Restores mana points (MP)"),
    INVISIBILITY("Makes target invisible or untargetable"),
    CONFUSION("Causes target to act randomly"),
    BLEED("Deals physical damage over time");

    private final String description;

    EffectType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
