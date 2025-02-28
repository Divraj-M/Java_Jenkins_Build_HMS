package hostels;

import java.util.ArrayList;
import java.util.Scanner;

class User1 {
    String userID, name, role;
    ArrayList<String> messages = new ArrayList<>();

    User1(String userID, String name, String role) {
        this.userID = userID;
        this.name = name;
        this.role = role;
    }

    public void receiveMessage(String message) {
        messages.add(message);
        System.out.println("New message for " + name + ": " + message);
    }

    public void showMessages() {
        System.out.println("Messages for " + name + ":");
        for (String msg : messages) {
            System.out.println("- " + msg);
        }
    }
}

class NotificationService {
    static void sendNotification(User1 user, String notification) {
        System.out.println("Notification to " + user.name + ": " + notification);
        user.receiveMessage("[Notification] " + notification);
    }
}

class CommunicationService {
    static void sendMessage(User1 sender, User1 receiver, String message) {
        if (sender.role.equalsIgnoreCase("Admin")) {
            receiver.receiveMessage("Message from Admin " + sender.name + ": " + message);
        } else {
            System.out.println("Only admins can send messages.");
        }
    }
}

public class Notification {
    private static ArrayList<User1> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User1 admin = new User1("A1", "AdminUser", "Admin");
        User1 student = new User1("S1", "StudentUser", "Student");
        users.add(admin);
        users.add(student);

        while (true) {
            System.out.println("\n1. Send Message (Admin only)");
            System.out.println("2. Send Notification");
            System.out.println("3. View Messages");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1:
                    sendMessage();
                    break;
                case 2:
                    sendNotification();
                    break;
                case 3:
                    viewMessages();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void sendMessage() {
        System.out.print("Enter Admin ID: ");
        String adminID = scanner.nextLine();
        User1 admin = findUserByID(adminID);

        if (admin == null || !admin.role.equalsIgnoreCase("Admin")) {
            System.out.println("Invalid admin ID.");
            return;
        }
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        User1 student = findUserByID(studentID);

        if (student == null || !student.role.equalsIgnoreCase("Student")) {
            System.out.println("Invalid student ID.");
            return;
        }

        System.out.print("Enter Message: ");
        String message = scanner.nextLine();
        CommunicationService.sendMessage(admin, student, message);
    }

    private static void sendNotification() {
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        User1 user = findUserByID(userID);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Enter Notification: ");
        String notification = scanner.nextLine();
        NotificationService.sendNotification(user, notification);
    }

    private static void viewMessages() {
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        User1 user = findUserByID(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        user.showMessages();
    }

    private static User1 findUserByID(String userID) {
        for (User1 user : users) {
            if (user.userID.equals(userID)) {
                return user;
            }
        }
        return null;
    }
}