package hostels;

import java.util.Scanner;

class Visitor {
    String name;
    String inTime;
    String outTime;

    void enterDetails(Scanner sc) {
        System.out.print("Enter Visitor Name: ");
        name = sc.nextLine();
        System.out.print("Enter InTime (HH:MM): ");
        inTime = sc.nextLine();
    }

    void exitDetails(Scanner sc) {
        System.out.print("Enter OutTime (HH:MM) for " + name + ": ");
        outTime = sc.nextLine();
    }

    void displayDetails() {
        System.out.println("Visitor Name: " + name);
        System.out.println("InTime: " + inTime);
        System.out.println("OutTime: " + (outTime != null ? outTime : "Not recorded"));
    }
}

public class VistorManagement extends Visitor {
    public static void logEntry(Scanner sc) {
        Visitor visitor = new Visitor();
        visitor.enterDetails(sc);
        System.out.println("Visitor entry logged successfully.");
    }

    public static void logExit(Scanner sc) {
        Visitor visitor = new Visitor();
        visitor.exitDetails(sc);
        System.out.println("Visitor exit logged successfully.");
    }

    public static void viewVisitorDetails(Scanner sc) {
        Visitor visitor = new Visitor();
        visitor.displayDetails();
    }
}