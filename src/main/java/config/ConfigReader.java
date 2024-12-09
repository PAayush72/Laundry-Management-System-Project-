/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author LENOVO
 */
public class ConfigReader {
    
    private static final String CONFIG_FILE = "config/config.properties";

    public static String getUploadFolderPath() {
        Properties prop = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return null;
            }
            prop.load(input);
            return prop.getProperty("upload.folder");
        } catch (Exception e) {
            e.printStackTrace(); // Handle this exception properly in a real application
            return null;
        }
    }
    
}