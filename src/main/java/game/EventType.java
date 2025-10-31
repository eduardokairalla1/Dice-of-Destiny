/**
 * Enumeration representing different types of events in the game.
 */

package src.main.java.game;


// --- CODE ---
/**
 * BATTLE: Combat encounter
 * TRAP: Trap that damages player
 * TREASURE: Find items
 * DECISION: Player makes a choice
 * REST: Rest to heal
 * MYSTERY: Random event
 */
public enum EventType {
    BATTLE,
    TRAP,
    TREASURE,
    DECISION,
    REST,
    MYSTERY
}
