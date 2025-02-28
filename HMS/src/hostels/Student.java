package hostels;

import java.util.Scanner;

public class Student {
    private String studentID;
    private Hmsfees hmsFees;
    private Maintenance ma;
    private Notification no;
    private EventManagement ev;
    private UserType us;
    Scanner sc = new Scanner(System.in);

    public Student(String studentID) {
        this.studentID = studentID;
        this.hmsFees = new Hmsfees();
    }

    public void manageStudent(Scanner sc) {
        while (sc.hasNext()) {
            System.out.println("\n----- Student Portal -----");
            System.out.println("1) View Room Details");
            System.out.println("2) Set Food Preference");
            System.out.println("3) Pay Fees");
            System.out.println("4) View Fees");
            System.out.println("5) Raise Complaints");
            System.out.println("6) View Notifications");
            System.out.println("7) View Upcoming Events");
            System.out.println("8) Update Profile");
            System.out.println("9) Contact Warden");
            System.out.println("10) Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());
            switch (choice) {
                case 1:
                    viewRoomDetails();
                    break;
                case 2:
                    setFoodPreference(sc);
                    break;
                case 3:
                    payFees(sc);
                    break;
                case 4:
                    viewFees(studentID);
                    break;
                case 5:
                    raiseComplaints();
                    break;
                case 6:
                    viewNotifications();
                    break;
                case 7:
                    viewUpcomingEvents();
                    break;
                case 8:
                    profileUpdation();
                    break;
                case 9:
                    contact();
                    break;
                case 10:
                    System.out.println("Exiting Student Portal...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void viewRoomDetails() {
        System.out.println("Room details for Student ID: " + studentID);
    }

    public void setFoodPreference(Scanner sc) {
        hmsFees.setFoodPreference(sc, studentID);
    }

    public void payFees(Scanner sc) {
        hmsFees.payFees(sc, studentID);
    }

    public void viewFees(String studentID) {
        hmsFees.viewFees(studentID);
    }

    public void raiseComplaints() {
        System.out.print("Enter the issue: ");
        String issue = sc.nextLine();
        System.out.println("Complaint raised successfully");
    }

    public void viewNotifications() {
        System.out.println("No new notifications.");
    }

    public void viewUpcomingEvents() {
        ev.viewEvents();
        System.out.println("Upcoming Event: Hostel Cultural Fest on Sunday!");
    }

    public void profileUpdation() {
        us.updateProfile();
    }

    public void contact() {
        System.out.println("Contact Admin at: admin@hostel.com");
        System.out.println("Contact Warden at: warden@hostel.com");
    }
}