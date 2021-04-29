package task2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.axis2.util.JavaUtils;
import org.apache.commons.lang3.StringUtils;

public class SearcherUsingChar {

    public void keywordsSearcher(String fileName) {
        writeInFile(readFile(fileName));
    }

    public void addJavaKeywords(List<String> keywords, StringBuilder word, Character currentChar) {
        boolean isSeparator = StringUtils
            .equalsAny(currentChar.toString(), "\t", " ", "\n", "\r", ",", ".", ";", "(");
        if (isSeparator) {
            if (JavaUtils.isJavaKeyword(word.toString())) {
                keywords.add(word.toString());
            }
            word.setLength(0);
        } else {
            word.append(currentChar);
        }
    }

    private List<String> readFile(String fileName) {
        List<String> keywords = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName)) {
            while (fileReader.ready()) {
                Character currentChar = (char) fileReader.read();
                addJavaKeywords(keywords, word, currentChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return keywords;
    }

    private void writeInFile(List<String> keywords) {
        try (FileWriter outputStream = new FileWriter("keywords_char.txt")) {
            outputStream.write(keywords.toString() + "\nКоличество ключевых слов: " + keywords.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
