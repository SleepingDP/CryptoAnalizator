import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Brute Force Decryption");
        System.out.println("2. Caesar's Cipher");
        int choice = readIntWithPrompt(scanner);

        switch (choice) {
            case 1 -> {
                System.out.println("Brute Force Decryption");
                BruteForce.decryption();
            }
            case 2 -> {
                System.out.println("Caesar's Cipher");
                handleCaesarsCipher(scanner);
            }
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }

        scanner.close();
    }

    private static int readIntWithPrompt(Scanner scanner) {
        int input;

        do {
            System.out.print("Enter your choice: ");
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input = -1;
            } finally {
                scanner.nextLine();
            }
        } while (input == -1);

        return input;
    }


    private static void handleCaesarsCipher(Scanner scanner) throws IOException {
        System.out.println("Select action:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int actionChoice = readIntWithPrompt(scanner);

        switch (actionChoice) {
            case 1:
                CaesarCipher.encryption();
                break;
            case 2:
                CaesarCipher.decryption();
                break;
            default:
                System.out.println("Invalid action. Please select a valid action.");
        }
    }
}
