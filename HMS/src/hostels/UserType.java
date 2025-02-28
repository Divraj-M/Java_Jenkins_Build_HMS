package hostels;

import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    String userID, name, email, password, phoneNumber;

    User(String userID, String name, String email, String password, String phoneNumber) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}

public class UserType {
    private static final int Max = 50;
    private static User[] users = new User[Max];
    private static int userCount = 0;
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nHostel Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Profile");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(s.nextLine().trim());
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    updateProfile();
                    break;
                case 4:
                    System.out.println("Exit. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void register() {
        if (userCount >= Max) {
            System.out.println("User limit reached.");
            return;
        }
        System.out.print("Enter User ID: ");
        String userID = s.nextLine();
        if (findUserByID(userID) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = s.nextLine();

        String email;
        do {
            System.out.print("Enter Email: ");
            email = s.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (!isValidEmail(email));

        String password;
        do {
            System.out.print("Enter Password: ");
            password = s.nextLine();
            if (!isValidPassword(password)) {
                System.out.println("Invalid password! Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a digit, and a special character.");
            }
        } while (!isValidPassword(password));

        String phoneNumber;
        do {
            System.out.print("Enter Phone Number: ");
            phoneNumber = s.nextLine();
            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number! Phone number must be exactly 10 digits.");
            }
        } while (!isValidPhoneNumber(phoneNumber));

        users[userCount++] = new User(userID, name, email, password, phoneNumber);
    }

    public static void login() {
        System.out.print("Enter User ID: ");
        String userID = s.nextLine();
        System.out.print("Enter Password: ");
        String password = s.nextLine();

        User user = findUserByID(userID);
        if (user != null && user.password.equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid User ID or Password.");
        }
    }

    public static void updateProfile() {
        System.out.print("Enter User ID: ");
        String userID = s.nextLine();
        User user = findUserByID(userID);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter New Name (Leave blank to keep current): ");
        String newName = s.nextLine();
        if (!newName.isEmpty()) user.name = newName;

        String newEmail;
        do {
            System.out.print("Enter New Email (Leave blank to keep current): ");
            newEmail = s.nextLine();
            if (newEmail.isEmpty() || isValidEmail(newEmail)) break;
            System.out.println("Invalid email format.");
        } while (true);
        if (!newEmail.isEmpty()) user.email = newEmail;

        String newPassword;
        do {
            System.out.print("Enter New Password (Leave blank to keep current): ");
            newPassword = s.nextLine();
            if (newPassword.isEmpty() || isValidPassword(newPassword)) break;
            System.out.println("Invalid password format.");
        } while (true);
        if (!newPassword.isEmpty()) user.password = newPassword;

        String newPhoneNumber;
        do {
            System.out.print("Enter New Phone Number (Leave blank to keep current): ");
            newPhoneNumber = s.nextLine();
            if (newPhoneNumber.isEmpty() || isValidPhoneNumber(newPhoneNumber)) break;
            System.out.println("Invalid phone number format.");
        } while (true);
        if (!newPhoneNumber.isEmpty()) user.phoneNumber = newPhoneNumber;

        System.out.println("Profile updated successfully!");
    }

    public static User findUserByID(String userID) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].userID.equals(userID)) {
                return users[i];
            }
        }
        return null;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{10}$";
        return Pattern.matches(phoneRegex, phoneNumber);
    }
}