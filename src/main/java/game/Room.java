/**
 * Represents a room in the game.
 */
package src.main.java.game;


// --- IMPORTS ---
import java.util.*;


// --- CODE ---
public class Room {

    // --- ATTRIBUTES ---
    private String name;
    private String description;
    private List<Event> events;
    private boolean visited;
    private List<Room> connectedRooms;


    // --- CONSTRUCTOR ---
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.events = new ArrayList<>();
        this.visited = false;
        this.connectedRooms = new ArrayList<>();
    }


    // --- GETTERS AND SETTERS ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean isVisited() {
        return visited;
    }

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }

    // --- METHODS ---
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void addConnectedRoom(Room room) throws IllegalArgumentException {

        // Room is duplicate: raise exception
        if (this.connectedRooms.contains(room)) {
            throw new IllegalArgumentException("Room already connected: " + room.getName());
        }

        // Add connected room
        this.connectedRooms.add(room);
    }

    public void enter() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📍 " + this.name);
        System.out.println("=".repeat(60));
        System.out.println(this.description);

        // Check if room has been visited before
        if (visited) {
            System.out.println("\n💭 You have been here before...");
            return;
        }

        System.out.println("\n✨ This is a new location!");
        this.visited = true;
    }

    public Event getRandomEvent() {

        // Check if there are events in the room
        if (events.isEmpty()) {
            return null;
        }

        // Select a random event
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }

    // --- OVERRIDES ---
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Room r = (Room)obj;

        if (!r.name.equals(this.name) || !r.description.equals(this.description))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int resp = 1;

        resp = resp*2 + this.name.hashCode();
        resp = resp*3 + this.description.hashCode();

        if (resp < 0) resp = -resp;

        return resp;
    }

    @Override
    public String toString()
    {
        return this.name + (this.visited ? " (visited)" : " (new)");
    }
}
