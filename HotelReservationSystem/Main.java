package HotelReservationSystem;
import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean available;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String category;

    Booking(String customerName, int roomNumber, String category) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
    }
}

public class Main {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(202, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 3000));

        loadBookings();

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    saveBookings();
                    System.out.println("Thank You!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {
            if (room.available) {
                System.out.println("Room No: " + room.roomNumber +
                        " | Category: " + room.category +
                        " | Price: ₹" + room.price);
            }
        }
    }

    static void bookRoom() {

        viewRooms();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.available) {

                System.out.println("Payment Amount: ₹" + room.price);
                System.out.println("Payment Successful!");

                room.available = false;

                bookings.add(new Booking(
                        name,
                        room.roomNumber,
                        room.category));

                saveBookings();

                System.out.println("Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelBooking() {

        System.out.print("Enter Room Number to Cancel: ");
        int roomNo = sc.nextInt();

        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.roomNumber == roomNo) {

                iterator.remove();

                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.available = true;
                    }
                }

                saveBookings();

                System.out.println("Booking Cancelled!");
                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        System.out.println("\nBooking Details:");

        for (Booking booking : bookings) {
            System.out.println("Customer: " + booking.customerName +
                    " | Room: " + booking.roomNumber +
                    " | Category: " + booking.category);
        }
    }

    static void saveBookings() {
        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("bookings.txt"));

            for (Booking booking : bookings) {
                writer.write(
                        booking.customerName + "," +
                        booking.roomNumber + "," +
                        booking.category);
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error Saving File");
        }
    }

    static void loadBookings() {
        try {
            File file = new File("bookings.txt");

            if (!file.exists())
                return;

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[0];
                int roomNo = Integer.parseInt(data[1]);
                String category = data[2];

                bookings.add(
                        new Booking(name, roomNo, category));

                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.available = false;
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error Loading File");
        }
    }
}