package com.airtribe.meditrack.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    private CSVUtil() {
        // prevent instantiation
    }

    // Write single line to file
    public static void writeLine(String filePath, String data) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(data);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read all lines from file
    public static List<String> readAll(String filePath) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }
}