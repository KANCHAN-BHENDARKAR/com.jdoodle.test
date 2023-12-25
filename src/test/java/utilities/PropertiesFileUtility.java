package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileUtility {
    public static String getValueFromConfigPropertiesFile(String propertyName) {
        String value = null;
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/properties/config.properties";
            FileInputStream input = new FileInputStream(path);

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            value = prop.getProperty(propertyName);
            input.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return value;
    }
}











