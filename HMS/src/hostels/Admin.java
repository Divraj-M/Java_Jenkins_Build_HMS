package hostels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Admin {
    private static final int MAX_EVENTS = 10;
    private String[] eventNames = new String[MAX_EVENTS];
    private String[] eventDates = new String[MAX_EVENTS];
    private String[] eventVenues = new String[MAX_EVENTS];
    private int eventCount = 0;
    Scanner scanner = new Scanner(System.in);

    public void createEvent(Scanner sc) {
        if (eventCount >= MAX_EVENTS) {
            System.out.println("Event limit reached! Cannot add more events.");
            return;
        }

        System.out.print("Enter Event Name: ");
        eventNames[eventCount] = sc.nextLine();

        // Validate Date
        String date;
        while (true) {
            System.out.print("Enter Event Date (DD/MM/YYYY): ");
            date = sc.nextLine();
            if (isValidDate(date)) {
                break;
            } else {
                System.out.println("Invalid date format. Please enter date in DD/MM/YYYY format.");
            }
        }
        eventDates[eventCount] = date;

        // Validate Time
        String time;
        while (true) {
            System.out.print("Enter Event Time (HH:MM): ");
            time = sc.nextLine();
            if (isValidTime(time)) {
                break;
            } else {
                System.out.println("Invalid time format. Please enter time in HH:MM format.");
            }
        }

        System.out.print("Enter Event Venue: ");
        eventVenues[eventCount] = sc.nextLine();

        eventCount++;
        System.out.println("Event Created Successfully!");
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidTime(String time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);
        try {
            timeFormat.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void displayEvents() {
        if (eventCount == 0) {
            System.out.println("No events available.");
            return;
        }
        System.out.println("\n--- Event List ---");
        for (int i = 0; i < eventCount; i++) {
            System.out.println("Event " + (i + 1));
            System.out.println("Name: " + eventNames[i]);
            System.out.println("Date: " + eventDates[i]);
            System.out.println("Venue: " + eventVenues[i]);
            System.out.println("----------------------");
        }
    }
}