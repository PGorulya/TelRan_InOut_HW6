package input.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Task3 {
    public static void main(String[] args) {
        String pathInput = "task3.txt";
        File file = new File(pathInput);

        try (FileInputStream inputStream = new FileInputStream(file)) {

            byte[] bytesArray = new byte[(int) file.length()];
            inputStream.read(bytesArray);

            String str = new String(bytesArray);

            System.out.println(str);

            Supplier<Stream<Character>> streamSupplier =
                    () -> str.chars()
                            .mapToObj(c -> (char) c);

            long countUpper = (streamSupplier.get().filter(Character::isUpperCase).count());

            long countLower = (streamSupplier.get().filter(Character::isLowerCase).count());

            System.out.println("Count letters with upperCase = " + countUpper);
            System.out.println("Count letters with LowerCase = " + countLower);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
