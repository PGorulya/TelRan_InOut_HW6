package input.output;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

public class CountWords {
    public static void main(String[] args) {

        String pathInput = "Black_Swan.txt";

        System.out.println("Count of Words from Book");
        long start = System.currentTimeMillis();
        long count = countWords(pathInput);
        System.out.println("Time = " + (System.currentTimeMillis() - start));
        System.out.println("Number of Words without Stream: " + count);
        System.out.println("=============================");

        start = System.currentTimeMillis();
        count = countWords1(pathInput);
        System.out.println("Time = " + (System.currentTimeMillis() - start));
        System.out.println("Number of Words without Stream(1): " + count);
        System.out.println("=============================");

        start = System.currentTimeMillis();
        count = countWords2(pathInput);
        System.out.println("Time = " + (System.currentTimeMillis() - start));
        System.out.println("Number of Words with Stream: " + count);
        System.out.println("=============================");

        start = System.currentTimeMillis();
        count = countWords3(pathInput);
        System.out.println("Time = " + (System.currentTimeMillis() - start));
        System.out.println("Number of Words with Stream(1): " + count);
        System.out.println("=============================");

    }

    public static long countWords(String pathInput) {
        long count = 0;
        File file = new File(pathInput);

        try (FileInputStream inputStream = new FileInputStream(file)) {

            byte[] bytesArray = new byte[(int) file.length()];
            inputStream.read(bytesArray);

            String str = new String(bytesArray);
            String[] words = str.split("[ ,.!?\r\n]");
            for (String w: words) {
                if(w.length() > 0) count++;
            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static long countWords1(String pathInput) {
        long count = 0;
        String[] words = null;

        try {
            BufferedReader bufreader = new BufferedReader(new FileReader(pathInput));

            String str;
            while ((str = bufreader.readLine()) != null) {
                words = str.split("[ ,.!?\r\n]");
                for (String w: words) {
                    if (w.length() > 0) count++;
                }

            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long countWords2(String pathInput) {

        try (Stream<String> stream = Files.lines(Paths.get(pathInput))) {
            return stream
                    .map(str -> str.split("[ ,.!?\r\n]"))
                    .flatMap(Stream::of)
                    .filter(str -> str.length() > 0)
                    .count();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long countWords3(String pathInput) {

        try (Stream<String> stream = Files.lines(Paths.get(pathInput))) {
            return stream
                    .flatMap(str -> Stream.of(str.split("[ ,.!?\r\n]")))
                    .filter(str -> str.length() > 0)
                    .count();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
