import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void saveToFile(String filename, List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public static List<String> loadFromFile(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
        return data;
    }
}