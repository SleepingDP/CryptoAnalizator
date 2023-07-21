import java.io.*;
import java.util.Scanner;

public class BruteForce {

    public static void main(String[] args) throws IOException {
        decryption();
    }

    public static void decryption() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the encrypted file: ");
        String encryptedFilePath = scanner.nextLine();
        String encryptedContent = readEncryptedContent(encryptedFilePath);

        if (encryptedContent == null) {
            System.out.println("Failed to read encrypted file. Check the path and existence of the file.");
            return;
        }

        int attempt = 1; // Счетчик попыток
        boolean found = false; // Флаг для обозначения того, что найден правильный offset

        while (!found && attempt <= 65535) {
            StringBuilder content = new StringBuilder(encryptedContent);

            for (int i = 0; i < content.length(); i++) {
                char currentChar = content.charAt(i);
                char decryptedChar = (char) (currentChar - attempt);

                if (currentChar == '1' && decryptedChar != '.') {
                    break; // Проверяем следующее значение offset, если символ '1' не становится '.'
                }

                content.setCharAt(i, decryptedChar);
            }

            if (!content.toString().contains("1") && content.toString().contains(".")) {
                String decryptedFilePath = getDecryptedFilePath(encryptedFilePath, attempt);
                File decryptedFile = new File(decryptedFilePath);

                try (FileWriter writer = new FileWriter(decryptedFile)) {
                    writer.write(content.toString());
                } catch (IOException e) {
                    System.out.println("Error writing decrypted file: " + e.getMessage());
                }

                System.out.println("The decrypted file is saved along the path: " + decryptedFilePath);
                System.out.println("Found offset value: " + attempt);
                found = true; // Устанавливаем флаг, что найден правильный offset
            }

            attempt++;
        }

        if (!found) {
            System.out.println("Key not found. Try a different approach.");
        }
    }

    private static String readEncryptedContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            return content.toString();
        } catch (IOException e) {
            return null;
        }
    }

    private static String getDecryptedFilePath(String originalFilePath, int attempt) {
        int lastDotIndex = originalFilePath.lastIndexOf('.');
        String nameWithoutExtension = lastDotIndex > 0 ? originalFilePath.substring(0, lastDotIndex) : originalFilePath;
        return nameWithoutExtension + "_attempt" + attempt + ".txt";
    }
}
