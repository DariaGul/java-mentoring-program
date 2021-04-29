package task1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import task2.SearcherUsingChar;

public class SearcherUsingByte {

    public void keywordsSearcherByteStreams(String fileName) {
        writeInFile(readFile(fileName));
    }

    private List<String> readFile(String fileName) {
        List<String> keywords = new ArrayList<>();
        int bytes = 0;
        StringBuilder word = new StringBuilder();
        File file = new File(fileName);

        try (FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis)) {
            while (bytes != -1) {
                bytes = inputStream.read();
                Character currentChar = (char) bytes;
                new SearcherUsingChar().addJavaKeywords(keywords, word, currentChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return keywords;
    }

    private void writeInFile(List<String> keywords) {

        try (FileOutputStream fos = new FileOutputStream("keywords_byte.txt");
            BufferedOutputStream outputStream = new BufferedOutputStream(fos)) {
            keywords.stream()
                .map(String::getBytes)
                .collect(Collectors.toList()).forEach(b -> {
                try {
                    outputStream.write(b);
                    outputStream.write(32);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            String count = "\nКоличество ключевых слов: " + keywords.size();
            outputStream.write(count.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
