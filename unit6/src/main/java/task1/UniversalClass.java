package task1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class UniversalClass {

    private static final String PATH = "C:\\Users\\d.gulenkova\\IdeaProjects\\unit6\\src\\main\\resources\\%s"
        + ".properties";

    public Map<Object, Object> readProp(String fileName) {
        Properties properties = new Properties();
        File file = new File(String.format(PATH, fileName));
        try {
            properties.load(new FileReader(file));
            String str = properties.keySet()
                .stream()
                .map(key -> String.format("%s = %s", key, properties.get(key)))
                .collect(Collectors.joining(", "));
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
