package hostels;

import java.util.Scanner;

public class Warden {
    private Scanner sc;
    private Room roomManager;

    public Warden() {
        this.roomManager = new Room();
    }

    public void manageRooms(Scanner sc) {
        this.sc = sc;
        while (sc.hasNext()) {
            System.out.println("\n-----Warden Room Management-----");
            System.out.println("1) View Rooms");
            System.out.println("2) Allocate a Room");
            System.out.println("3) Vacate a Room");
            System.out.println("4) Log Visitors");
            System.out.println("5) View Visitor Details");
            System.out.println("6) Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    roomManager.displayRooms();
                    break;
                case 2:
                    System.out.println("Do you want to allocate\n1)Single room\n2)Shared room (4 in a room)");
                    int ch = Integer.parseInt(sc.nextLine().trim());
                    if (ch == 1) {
                        System.out.print("Enter room label to allocate a full room: ");
                        String guestRoom = sc.nextLine().trim();
                        roomManager.allocateGuestRoom(guestRoom);
                    } else if (ch == 2) {
                        System.out.print("Enter room Label to book: ");
                        String roomNumber = sc.nextLine().trim();
                        roomManager.allocateRoom(roomNumber);
                    }
                    break;
                case 3:
                    System.out.println("Do you want to vacate\n1)Single room\n2)Shared room (4 in a room)");
                    int c = Integer.parseInt(sc.nextLine().trim());
                    if (c == 1) {
                        System.out.print("Enter room label to vacate a full room: ");
                        String guestRoomVacate = sc.nextLine().trim();
                        roomManager.vacateGuestRoom(guestRoomVacate);
                    } else if (c == 2) {
                        System.out.print("Enter room number to vacate: ");
                        String vacateRoomNumber = sc.nextLine().trim();
                        System.out.print("Enter bed number (1 to 4): ");
                        int bedNumber = Integer.parseInt(sc.nextLine().trim());
                        roomManager.vacateRoom(vacateRoomNumber, bedNumber);
                    }
                    break;
                case 4:
                    System.out.println("1) Log entry of visitor\n2) Log exit of visitor");
                    int vis = Integer.parseInt(sc.nextLine().trim());
                    if (vis == 1) {
                        VisitorManagement.logEntry(sc);
                    } else if (vis == 2) {
                        VisitorManagement.logExit(sc);
                    } else {
                        System.out.println("Enter a valid value");
                    }
                    break;
                case 5:
                    VisitorManagement.viewVisitorDetails(sc);
                    break;
                case 6:
                    System.out.println("Exiting room management.");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}