package hostels;

public class Room {
    private static final int capacity = 60;
    private static final int roomsPerFloor = 20;
    private static final int bedsPerRoom = 4;
    private static final int guestRoom = 41;
    private boolean[][] rooms;
    private boolean[] guests;
    private int bookedBeds;
    private int bookedRooms;

    public Room() {
        this.rooms = new boolean[capacity][bedsPerRoom];
        this.guests = new boolean[capacity];
        this.bookedBeds = 0;
    }

    private int getRoomNumber(String roomLabel) {
        char floor = roomLabel.charAt(0);
        int roomNumber = Integer.parseInt(roomLabel.substring(1));
        if (roomNumber < 1 || roomNumber > roomsPerFloor) {
            return -1;
        }
        switch (floor) {
            case 'A':
                return roomNumber - 1;
            case 'B':
                return roomsPerFloor + roomNumber - 1;
            case 'C':
                return (2 * roomsPerFloor) + roomNumber - 1;
            default:
                return -1;
        }
    }

    public void displayRooms() {
        System.out.println("-----Available Rooms and Beds-----");
        for (int floor = 0; floor < 3; floor++) {
            char label = (char) (floor + 'A');
            System.out.println("Floor " + label);
            for (int i = 1; i <= roomsPerFloor; i++) {
                int index = (floor * roomsPerFloor) + (i - 1);
                String roomLabel = label + Integer.toString(i);
                if (floor == 0 || floor == 1) {
                    System.out.print(roomLabel + "[");
                    for (int bed = 0; bed < bedsPerRoom; bed++) {
                        System.out.print(rooms[index][bed] ? "X" : "O");
                        if (bed < bedsPerRoom - 1)
                            System.out.print(",");
                    }
                    System.out.print("]\t");
                } else {
                    if (guests[index]) {
                        System.out.print("-\t");
                    } else {
                        System.out.print(roomLabel + "\t");
                    }
                }
                if (i % 10 == 0) {
                    System.out.println();
                }
            }
        }
    }

    public void allocateRoom(String roomNumber) {
        int roomIndex = getRoomNumber(roomNumber);
        if (roomIndex == -1) {
            System.out.println("Invalid room number, Please choose a valid number");
        }
        if (roomIndex >= guestRoom - 1) {
            System.out.println("Use 'Book a full room' option for C floor");
            return;
        }
        for (int i = 0; i < bedsPerRoom; i++) {
            if (!rooms[roomIndex][i]) {
                rooms[roomIndex][i] = true;
                bookedBeds++;
                System.out.println("Bed " + (i + 1) + " in room " + roomNumber + " is booked");
                return;
            }
        }
        System.out.println(roomNumber + " is fully occupied");
    }

    public void vacateRoom(String roomNumber, int bedNumber) {
        int roomIndex = getRoomNumber(roomNumber);
        if (roomIndex == -1) {
            System.out.println("Invalid room number, Please choose a valid number");
        } else if (!rooms[roomIndex][bedNumber - 1]) {
            System.out.println("Bed " + bedNumber + " in Room " + roomNumber + " is already vacant");
        } else {
            rooms[roomIndex][bedNumber - 1] = false;
            bookedBeds--;
            System.out.println("Bed " + bedNumber + " in Room " + roomNumber + " vacated successfully");
        }
    }

    public void allocateGuestRoom(String roomLabel) {
        int roomIndex = getRoomNumber(roomLabel);
        if (roomIndex == -1 || roomIndex < guestRoom - 1) {
            System.out.println("Invalid room! Only C1-C20 can be booked as full rooms.");
            return;
        }
        if (guests[roomIndex]) {
            System.out.println("Room " + roomLabel + " is already booked.");
        } else {
            guests[roomIndex] = true;
            bookedRooms++;
            System.out.println("Room " + roomLabel + " booked successfully!");
        }
    }

    public void vacateGuestRoom(String roomLabel) {
        int roomIndex = getRoomNumber(roomLabel);
        if (roomIndex == -1 || roomIndex < guestRoom - 1) {
            System.out.println("Invalid room! Only C1-C20 can be vacated as full rooms.");
            return;
        }

        if (!guests[roomIndex]) {
            System.out.println("Room " + roomLabel + " is already vacant.");
        } else {
            guests[roomIndex] = false;
            bookedRooms--;
            System.out.println("Room " + roomLabel + " vacated successfully!");
        }
    }
}