package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {
    private static FileInputStream fileInputStream;
    private static Properties properties;

    public static String readProperty(String propertyKey) {
        try {
            fileInputStream = new FileInputStream("src/test/resources/testData.properties");
            properties = new Properties();
            properties.load(fileInputStream);

            if(fileInputStream != null){
                fileInputStream.close();
                fileInputStream = null;
            }
        } catch (IOException exc){
            System.out.println(exc);
        }
    return properties.getProperty(propertyKey);
    }
}
