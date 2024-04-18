package com.example.project.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private static final Properties prop = new Properties();

    public String property(String namePropertyFile, String pathFieldProperty) throws IOException {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(namePropertyFile);
            prop.load(fileReader);

            return prop.getProperty(pathFieldProperty);
        } catch (FileNotFoundException e) {
            System.out.println("Not found file: " + namePropertyFile);
            throw new RuntimeException(e);
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
}
