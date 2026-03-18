import java.util.LinkedList;
import java.util.Queue;

// Reservation class (represents booking request)
class ReservationUC5 {
    private String guestName;
    private String roomType;

    public ReservationUC5(String guestName, String roomType) {
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

// Booking Queue (FIFO)
class BookingQueueUC5 {

    private Queue<ReservationUC5> queue;

    public BookingQueueUC5() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(ReservationUC5 reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for: " + reservation.getGuestName());
    }

    // Display all requests (without removing)
    public void showRequests() {
        System.out.println("\n===== BOOKING QUEUE =====");

        for (ReservationUC5 r : queue) {
            System.out.println("Guest: " + r.getGuestName() +
                    " | Room: " + r.getRoomType());
        }

        System.out.println("===== END OF QUEUE =====");
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueueUC5 bookingQueue = new BookingQueueUC5();

        // Simulating booking requests (arrival order)
        bookingQueue.addRequest(new ReservationUC5("Ashika", "Single Room"));
        bookingQueue.addRequest(new ReservationUC5("Rahul", "Double Room"));
        bookingQueue.addRequest(new ReservationUC5("Priya", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.showRequests();
    }
}


