import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.*;

// In FileHandler.java
public class FileHandler {
    public static void saveToFile(String filename, List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file " + filename + ": " + e.getMessage());
        }
    }

    public static List<String> loadFromFile(String filename) {
        List<String> data = new ArrayList<>();
        File file = new File(filename);
        
        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file " + filename + ": " + e.getMessage());
            }
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file " + filename + ": " + e.getMessage());
        }
        return data;
    }
}