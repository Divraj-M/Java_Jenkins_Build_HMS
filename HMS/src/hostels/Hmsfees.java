package hostels;

import java.util.Scanner;

public class Hmsfees {
    private static final int SHARING_ROOM_FEES = 5000;
    private static final int SINGLE_ROOM_FEES = 10000;
    private static final int VEG_FOOD_FEES = 3000;
    private static final int NON_VEG_FOOD_FEES = 5000;

    private static int totalFees = 0;
    private static int balanceFees = 0;
    private static String[] studentIDs = new String[100];
    private static String[] paymentMethods = new String[100];
    private static String[] accountDetails = new String[100];
    private static int studentCount = 0;
    private static boolean foodPreferenceSet = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your student ID: ");
        String studentID = scanner.next();
        System.out.println("Enter room type (1 for Sharing Room, 2 for Single Room): ");
        int roomType = scanner.nextInt();

        if (roomType == 1) {
            totalFees += SHARING_ROOM_FEES;
        } else if (roomType == 2) {
            totalFees += SINGLE_ROOM_FEES;
        } else {
            System.out.println("Invalid room type selected.");
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Set Food Preference");
            System.out.println("2. Pay Fees");
            System.out.println("3. View Fees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    setFoodPreference(scanner, studentID);
                    break;
                case 2:
                    payFees(scanner, studentID);
                    break;
                case 3:
                    viewFees(studentID);
                    break;
                case 4:
                    System.out.println("Exit.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void setFoodPreference(Scanner scanner, String studentID) {
        if (foodPreferenceSet) {
            System.out.println("Food preference already set.");
            return;
        }

        System.out.println("Do you want food? (1 for Yes, 2 for No): ");
        int foodChoice = scanner.nextInt();

        if (foodChoice == 1) {
            System.out.println("Choose food type (1 for Veg, 2 for Non-Veg): ");
            int foodType = scanner.nextInt();
            if (foodType == 1) {
                totalFees += VEG_FOOD_FEES;
                System.out.println("Food preference set to Vegetarian.");
            } else if (foodType == 2) {
                totalFees += NON_VEG_FOOD_FEES;
                System.out.println("Food preference set to Non-Vegetarian.");
            } else {
                System.out.println("Invalid food type selected.");
            }
        } else if (foodChoice == 2) {
            System.out.println("No food preference set.");
        } else {
            System.out.println("Invalid choice.");
        }

        foodPreferenceSet = true;
    }

    public static void payFees(Scanner scanner, String studentID) {
        System.out.println("\nPayment Methods:");
        System.out.println("1. Net Banking");
        System.out.println("2. UPI");
        System.out.println("3. Credit Card");
        System.out.println("4. Debit Card");
        System.out.print("Choose payment method: ");
        int paymentMethod = scanner.nextInt();
        int studentIndex = findStudentIndex(studentID);
        if (studentIndex != -1 && paymentMethods[studentIndex].equals(getPaymentMethodName(paymentMethod))) {
            System.out.println("Payment of " + totalFees + " via " + getPaymentMethodName(paymentMethod) + " already made.");
            return;
        }

        switch (paymentMethod) {
            case 1:
                processNetBanking(scanner, studentID);
                break;
            case 2:
                processUPI(scanner, studentID);
                break;
            case 3:
                processCreditCard(scanner, studentID);
                break;
            case 4:
                processDebitCard(scanner, studentID);
                break;
            default:
                System.out.println("Invalid payment method selected.");
                return;
        }

        System.out.print("Enter the amount you want to pay: ");
        int paymentAmount = scanner.nextInt();

        if (paymentAmount > totalFees - balanceFees) {
            int excessAmount = paymentAmount - (totalFees - balanceFees);
            balanceFees = totalFees;
            System.out.println("Payment of " + paymentAmount + " successful. Excess amount returned: " + excessAmount);
        } else {
            balanceFees += paymentAmount;
            System.out.println("Payment of " + paymentAmount + " successful. Remaining balance: " + (totalFees - balanceFees));
        }
    }

    private static String getPaymentMethodName(int method) {
        switch (method) {
            case 1: return "Net Banking";
            case 2: return "UPI";
            case 3: return "Credit Card";
            case 4: return "Debit Card";
            default: return "";
        }
    }

    public static void processNetBanking(Scanner scanner, String studentID) {
        int studentIndexNetBanking = findStudentIndex(studentID);
        if (findStudentIndex(studentID) != -1 && paymentMethods[findStudentIndex(studentID)].equals("Net Banking")) {
            System.out.println("Payment via Net Banking already made.");
            return;
        }
        String bankName = "";
        String accountNumber = "";

        while (true) {
            System.out.print("Enter bank name: ");
            bankName = scanner.next();
            if (!bankName.trim().isEmpty()) {
                break;
            }
            System.out.println("Bank name cannot be empty. Please enter a valid bank name.");
        }
        while (true) {
            System.out.print("Enter account number (must be 12 to 16 digits): ");
            accountNumber = scanner.next();
            if (accountNumber.matches("\\d{12,16}")) {
                break;
            }
            System.out.println("Invalid account number. It must be between 12 and 16 digits.");
        }

        studentIDs[studentCount] = studentID;
        paymentMethods[studentCount] = "Net Banking";
        accountDetails[studentCount] = bankName + " - " + accountNumber;
        studentCount++;
    }

    public static void processUPI(Scanner scanner, String studentID) {
        int studentIndexUPI = findStudentIndex(studentID);
        if (findStudentIndex(studentID) != -1 && paymentMethods[findStudentIndex(studentID)].equals("UPI")) {
            System.out.println("Payment via UPI already made.");
            return;
        }

        String upiID = "";

        while (true) {
            System.out.print("Enter your UPI ID: ");
            upiID = scanner.next();
            if (!upiID.trim().isEmpty()) {
                break;
            }
            System.out.println("UPI ID cannot be empty. Please enter a valid UPI ID.");
        }
        studentIDs[studentCount] = studentID;
        paymentMethods[studentCount] = "UPI";
        accountDetails[studentCount] = upiID;
        studentCount++;
    }

    public static void processCreditCard(Scanner scanner, String studentID) {
        int studentIndexCreditCard = findStudentIndex(studentID);
        if (findStudentIndex(studentID) != -1 && paymentMethods[findStudentIndex(studentID)].equals("Credit Card")) {
            System.out.println("Payment via Credit Card already made.");
            return;
        }

        String cardNumber = "";
        String cardHolderName = "";
        String expiryDate = "";
        while (true) {
            System.out.print("Enter your credit card number (16 digits): ");
            cardNumber = scanner.next();
            if (cardNumber.matches("\\d{16}")) {
                break;
            }
            System.out.println("Invalid credit card number. It must be 16 digits.");
        }
        while (true) {
            System.out.print("Enter cardholder name: ");
            cardHolderName = scanner.next();
            if (!cardHolderName.trim().isEmpty()) {
                break;
            }
            System.out.println("Cardholder name cannot be empty. Please enter a valid name.");
        }
        while (true) {
            System.out.print("Enter expiry date (MM/YY): ");
            expiryDate = scanner.next();
            if (expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                break;
            }
            System.out.println("Invalid expiry date. Please use the format MM/YY.");
        }
        studentIDs[studentCount] = studentID;
        paymentMethods[studentCount] = "Credit Card";
        accountDetails[studentCount] = cardNumber + " - " + cardHolderName + " - " + expiryDate;
        studentCount++;
    }

    public static void processDebitCard(Scanner scanner, String studentID) {
        int studentIndexDebitCard = findStudentIndex(studentID);
        if (findStudentIndex(studentID) != -1 && paymentMethods[findStudentIndex(studentID)].equals("Debit Card")) {
            System.out.println("Payment via Debit Card already made.");
            return;
        }

        String cardNumber = "";
        String cardHolderName = "";
        String expiryDate = "";

        while (true) {
            System.out.print("Enter your debit card number (16 digits): ");
            cardNumber = scanner.next();
            if (cardNumber.matches("\\d{16}")) {
                break;
            }
            System.out.println("Invalid debit card number. It must be 16 digits.");
        }

        while (true) {
            System.out.print("Enter cardholder name: ");
            cardHolderName = scanner.next();
            if (!cardHolderName.trim().isEmpty()) {
                break;
            }
            System.out.println("Cardholder name cannot be empty. Please enter a valid name.");
        }
        while (true) {
            System.out.print("Enter expiry date (MM/YY): ");
            expiryDate = scanner.next();
            if (expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                break;
            }
            System.out.println("Invalid expiry date. Please use the format MM/YY.");
        }

        studentIDs[studentCount] = studentID;
        paymentMethods[studentCount] = "Debit Card";
        accountDetails[studentCount] = cardNumber + " - " + cardHolderName + " - " + expiryDate;
        studentCount++;
    }

    public static void viewFees(String studentID) {
        int remainingFees = totalFees - balanceFees;
        System.out.println("Total Fees: " + totalFees);
        System.out.println("Balance Fees: " + remainingFees);
        if (remainingFees > 0) {
            System.out.println("You still need to pay: " + remainingFees);
        } else {
            System.out.println("All fees have been paid.");
        }
    }

    public static int findStudentIndex(String studentID) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(studentID)) {
                return i;
            }
        }
        return -1;
    }
}