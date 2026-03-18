// Use Case 4: Room Search & Availability Check

// Abstract Room Class
abstract class Room {
    private String type;
    private int beds;
    private double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

// Concrete Room Classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

// Inventory Class (State Holder)
class Inventory {
    private int singleAvailable;
    private int doubleAvailable;
    private int suiteAvailable;

    public Inventory(int single, int dbl, int suite) {
        this.singleAvailable = single;
        this.doubleAvailable = dbl;
        this.suiteAvailable = suite;
    }

    // Read-only getters
    public int getSingleAvailable() {
        return singleAvailable;
    }

    public int getDoubleAvailable() {
        return doubleAvailable;
    }

    public int getSuiteAvailable() {
        return suiteAvailable;
    }
}

// Search Service (Read-only logic)
class SearchService {

    public void searchAvailableRooms(Inventory inventory, Room[] rooms) {

        System.out.println("===== AVAILABLE ROOMS =====\n");

        for (Room room : rooms) {

            int availability = 0;

            // Map room type to inventory
            if (room instanceof SingleRoom) {
                availability = inventory.getSingleAvailable();
            } else if (room instanceof DoubleRoom) {
                availability = inventory.getDoubleAvailable();
            } else if (room instanceof SuiteRoom) {
                availability = inventory.getSuiteAvailable();
            }

            // Validation: show only available rooms
            if (availability > 0) {
                room.displayDetails();
                System.out.println("Available: " + availability);
                System.out.println();
            }
        }

        System.out.println("===== END OF SEARCH =====");
    }
}

// Main Class
class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Step 1: Create Room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Step 2: Create Inventory (centralized state)
        Inventory inventory = new Inventory(5, 0, 2);
        // Double room is unavailable (0)

        // Step 3: Search (READ-ONLY)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory, rooms);
    }
}
