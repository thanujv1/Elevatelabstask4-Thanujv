import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to write note into file
    private static void writeNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("✅ Note saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // Method to read notes from file
    private static void readNotes() {
        System.out.println("\n--- Saved Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasNotes = false;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasNotes = true;
            }
            if (!hasNotes) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found yet)");
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
}
