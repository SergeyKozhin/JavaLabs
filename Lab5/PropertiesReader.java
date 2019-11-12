package Lab5;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader {
    private Map<String, String> data = new HashMap<>();

    public PropertiesReader(String fileName) throws IOException {
        try (InputStream input = new FileInputStream(fileName)) {
            Properties properties = new Properties();
            properties.load(input);
            properties.forEach((key, value) -> data.put(key.toString(), value.toString()));
        }
    }

    public String getProperty(String key) {
        return data.get(key);
    }

    public String getProperty(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    public Set<String> stringPropertyNames() {
        return data.keySet();
    }

    public void list(PrintStream out) {
        data.forEach((key, value) -> out.println(key + '=' + value));
    }

    public void list(PrintWriter out) {
        data.forEach((key, value) -> out.println(key + '=' + value));
    }

    public Map<String, String> asMap() {
        return data;
    }
}
