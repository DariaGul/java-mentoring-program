package task1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesHelper {

    private static final String PATH = "./unit6/src/main/resources/%s.properties";
    private Map<Object, Object> properties;

    public PropertiesHelper(String fileName) {
        Properties propertiesFromFile = new Properties();
        File file = new File(String.format(PATH, fileName));
        try (FileReader fr = new FileReader(file)) {
            propertiesFromFile.load(fr);
            properties = propertiesFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Object, Object> getProperties() {
        return properties;
    }

    public Object getProperty(Object key) {
        return properties.get(key);
    }
}
