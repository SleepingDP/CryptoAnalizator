import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CaesarCipher {
    private static final int CAESAR_OFFSET = 3;

    public static void decryption() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(readInput("Введите путь к зашифрованному файлу: "))))) {
            StringBuilder content = new StringBuilder();
            String line;


            while ((line = reader.readLine()) != null) {
                char[] symbols = line.toCharArray();

                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] = (char) (symbols[i] - CAESAR_OFFSET);
                }

                content.append(symbols);
                content.append(System.lineSeparator());
            }

            String decryptedFilePath = readInput("Enter the path to save the decrypted file: ");
            File decryptedFile = new File(decryptedFilePath);
            FileWriter writer = new FileWriter(decryptedFile);
            writer.write(content.toString());
            writer.close();

            System.out.println("The decrypted file is saved along the path: " + decryptedFilePath);
        }
    }

    public static void encryption() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(readInput("Enter the path to the source file:\n "))))) {
            StringBuilder content = new StringBuilder();
            String line;


            while ((line = reader.readLine()) != null) {
                char[] symbols = line.toCharArray();

                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] = (char) (symbols[i] + CAESAR_OFFSET);
                }

                content.append(symbols);
                content.append(System.lineSeparator());
            }

            String encryptedFilePath = readInput("Enter the path to write the encrypted file: ");
            File encryptedFile = new File(encryptedFilePath);
            FileWriter writer = new FileWriter(encryptedFile);
            writer.write(content.toString());
            writer.close();

            System.out.println("The encrypted file is saved along the path:\n " + encryptedFile.getAbsolutePath());
        }
    }

    private static String readInput(String message) throws IOException {
        System.out.println(message);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }}