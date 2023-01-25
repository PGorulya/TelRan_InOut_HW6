package input.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Task4 {
    public static void main(String[] args) {
        String pathInput = "task4.txt";
        Map<Integer, String> map = new HashMap<>();
        System.out.println("The words from stream with the longest length:");
        
        try (Stream<String> stream = Files.lines(Paths.get(pathInput))) {
            stream
                    .peek(e -> {
                        map.merge(e.length(), e, (e1, e2) -> e1 + ", " + e2);
                    })
                    .sorted().limit(1)
                    .forEach(e -> System.out.println(map.get(e.length())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
