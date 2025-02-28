package hostels;
import hostels.Hmsfees;
import hostels.Admin;
import hostels.Maintenance;
import hostels.Warden;
import hostels.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HostelManagementSystem {
    public static void main(String[] args) {
        Scanner sc;
        File file = new File("HMS//input111.txt");
        if (file.exists()) {
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Switching to manual input.");
                sc = new Scanner(System.in);
            }
        } else {
            System.out.println("input.txt not found. Using manual input.");
            sc = new Scanner(System.in);
        }

        try {
            while (true) {
                System.out.println("Enter the application as:\n1. Admin\n2. Warden\n3. Student\n4. Exit the application");
                int role;
                try {
                    role = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number (1-4).\n");
                    sc.nextLine(); // Consume the invalid input
                    continue;
                }

                switch (role) {
                    case 1:
                        adminOperations(sc);
                        break;
                    case 2:
                        wardenOperations(sc);
                        break;
                    case 3:
                        studentOperations(sc);
                        break;
                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter 1-4.\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void adminOperations(Scanner sc) {
        Hmsfees f = new Hmsfees();
        Admin admin = new Admin();
        Maintenance m = new Maintenance();
        while (sc.hasNext()) {
            int adminChoice = Integer.parseInt(sc.nextLine().trim());
            switch (adminChoice) {
                case 1:
                    admin.createEvent(sc);
                    break;
                case 2:
                    admin.displayEvents();
                    break;
                case 3:
                    f.viewFees(sc.nextLine().trim());
                    break;
                case 4:
                    m.viewRequests(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void wardenOperations(Scanner sc) {
        Warden warden = new Warden();
        warden.manageRooms(sc);
    }

    private static void studentOperations(Scanner sc) {
        String studentID = sc.nextLine().trim();
        Student student = new Student(studentID);
        while (sc.hasNext()) {
            int studentChoice = Integer.parseInt(sc.nextLine().trim());
            switch (studentChoice) {
                case 1:
                    student.viewRoomDetails();
                    break;
                case 2:
                    student.setFoodPreference(sc);
                    break;
                case 3:
                    student.payFees(sc);
                    break;
                case 4:
                    student.viewFees(studentID);
                    break;
                case 5:
                    student.raiseComplaints();
                    break;
                case 6:
                    student.viewNotifications();
                    break;
                case 7:
                    student.viewUpcomingEvents();
 
                    break;
                case 8:
                    student.profileUpdation();
                    break;
                case 9:
                    student.contact();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
