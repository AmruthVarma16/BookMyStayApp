import java.util.HashMap;
import java.util.Map;

// RoomInventory class - handles centralized inventory
 class RoomInventory {

    // HashMap to store room type and availability
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Method to add room type with count
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Method to get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update availability (book/cancel)
    public void updateAvailability(String roomType, int change) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            int updated = current + change;

            if (updated >= 0) {
                inventory.put(roomType, updated);
            } else {
                System.out.println("Error: Not enough rooms available for " + roomType);
            }
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Method to display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.addRoomType("Standard", 10);
        inventory.addRoomType("Deluxe", 5);
        inventory.addRoomType("Suite", 2);

        // Display initial inventory
        inventory.displayInventory();

        // Simulate booking
        System.out.println("\nBooking 2 Deluxe rooms...");
        inventory.updateAvailability("Deluxe", -2);

        // Simulate cancellation
        System.out.println("Cancelling 1 Suite room...");
        inventory.updateAvailability("Suite", +1);

        // Display updated inventory
        inventory.displayInventory();

        // Check availability
        System.out.println("\nAvailable Standard rooms: " +
                inventory.getAvailability("Standard"));
    }
}
