/**
 * Start point of the application
 * 
 * @Authors: Eduardo Kairalla and Tomas Cubeiro
 */

package src.main.java;

// --- IMPORTS ---
import src.main.java.game.Game;


// --- CODE ---
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
