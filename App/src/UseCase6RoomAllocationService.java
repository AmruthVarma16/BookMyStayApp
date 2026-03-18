import java.util.*;

// Reservation (from queue)
class ReservationUC6 {
    private String guestName;
    private String roomType;

    public ReservationUC6(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service
class InventoryUC6 {
    private Map<String, Integer> availability;

    public InventoryUC6() {
        availability = new HashMap<>();
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailable(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

// Booking Service
class BookingServiceUC6 {

    private Queue<ReservationUC6> queue;
    private Map<String, Set<String>> allocatedRooms; // roomType → roomIDs
    private Set<String> usedRoomIds;

    public BookingServiceUC6(Queue<ReservationUC6> queue) {
        this.queue = queue;
        this.allocatedRooms = new HashMap<>();
        this.usedRoomIds = new HashSet<>();
    }

    // Generate unique room ID
    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 1).toUpperCase() + (int)(Math.random() * 100);
        } while (usedRoomIds.contains(id)); // ensure uniqueness

        usedRoomIds.add(id);
        return id;
    }

    // Process bookings
    public void processBookings(InventoryUC6 inventory) {

        System.out.println("===== PROCESSING BOOKINGS =====\n");

        while (!queue.isEmpty()) {

            ReservationUC6 r = queue.poll(); // FIFO
            String type = r.getRoomType();

            if (inventory.getAvailable(type) > 0) {

                String roomId = generateRoomId(type);

                // store in map
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // update inventory
                inventory.reduceRoom(type);

                System.out.println("Booking CONFIRMED for " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Allocated Room ID: " + roomId + "\n");

            } else {
                System.out.println("Booking FAILED for " + r.getGuestName() +
                        " (No " + type + " rooms available)\n");
            }
        }

        System.out.println("===== END =====");
    }
}

// Main Class (must match file name)
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Step 1: Create booking queue (FIFO)
        Queue<ReservationUC6> queue = new LinkedList<>();

        queue.offer(new ReservationUC6("Ashika", "Single"));
        queue.offer(new ReservationUC6("Rahul", "Double"));
        queue.offer(new ReservationUC6("Priya", "Single"));
        queue.offer(new ReservationUC6("Ankit", "Suite"));
        queue.offer(new ReservationUC6("Neha", "Double")); // may fail

        // Step 2: Inventory
        InventoryUC6 inventory = new InventoryUC6();

        // Step 3: Booking service
        BookingServiceUC6 service = new BookingServiceUC6(queue);

        // Step 4: Process bookings
        service.processBookings(inventory);
    }
}


