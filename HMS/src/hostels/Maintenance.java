package hostels;

import java.util.ArrayList;
import java.util.Scanner;

public class Maintenance {
    private static ArrayList<Request> requests = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void manageRequests(Scanner sc) {
        while (sc.hasNext()) {
            System.out.println("\nRequest Management System");
            System.out.println("1. Raise a Request (Student)");
            System.out.println("2. View Requests (Admin/Warden)");
            System.out.println("3. Update Request Status (Warden)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());
            switch (choice) {
                case 1:
                    raiseRequest(sc);
                    break;
                case 2:
                    viewRequests();
                    break;
                case 3:
                    updateRequestStatus(sc);
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void raiseRequest(Scanner sc) {
        System.out.print("Enter issue description: ");
        String issueDescription = sc.nextLine();
        int requestID = requests.size() + 1;  // Unique Request ID
        requests.add(new Request(requestID, issueDescription));
        System.out.println("Request raised successfully! Request ID: " + requestID);
    }

    void viewRequests(Scanner sc) {
        if (requests.isEmpty()) {
            System.out.println("No complaints available.");
            return;
        }
        System.out.println("\n===== List of Maintenance Requests =====");
        for (Request req : requests) {
            System.out.println("Request ID: " + req.requestID);
            System.out.println("Issue: " + req.issueDescription);
            System.out.println("Status: " + req.status);
            System.out.println("------------------------------------");
        }
    }

    private void updateRequestStatus(Scanner sc) {
        if (requests.isEmpty()) {
            System.out.println("No requests available.");
            return;
        }
        System.out.print("Enter Request ID to update: ");
        int requestID = Integer.parseInt(sc.nextLine().trim());
        for (Request req : requests) {
            if (req.requestID == requestID) {
                System.out.print("Enter new status (Pending/Resolved): ");
                String newStatus = sc.nextLine();
                req.status = newStatus;
                System.out.println("Request updated successfully!");
                return;
            }
        }
        System.out.println("Request ID not found.");
    }
}

class Request {
    int requestID;
    String issueDescription;
    String status;

    public Request(int requestID, String issueDescription) {
        this.requestID = requestID;
        this.issueDescription = issueDescription;
        this.status = "Pending"; // Default status
    }
}