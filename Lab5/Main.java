package Lab5;

import java.io.*;
import java.util.Properties;

public class Main {
    private static String TEST_FILE = "test.properties";

    public static void main(String[] args) {
        try (OutputStream output = new FileOutputStream(TEST_FILE)) {
            Properties properties = new Properties();
            properties.setProperty("first_name", "Sergey");
            properties.setProperty("last_name", "Kozhin");
            properties.setProperty("date_of_birth", "12.07.2000");

            properties.store(output, null);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (InputStream input = new FileInputStream(TEST_FILE)){
            Properties properties = new Properties();
            properties.load(input);
            properties.list(System.out);
        } catch (FileNotFoundException e) {
            System.err.println("No such file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal symbols in input" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
