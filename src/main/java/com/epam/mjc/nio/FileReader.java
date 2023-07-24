package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line.substring(line.indexOf(" ") + 1));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(lines.get(0), Integer.parseInt(lines.get(1)), lines.get(2), Long.parseLong(lines.get(3)));
    }
}
