package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class RealTextFileV2 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "abc\n가나다";
        System.out.println("== Write String ==");;
        System.out.println(writeString);

        Path path = Path.of(PATH);

        Files.writeString(path, writeString, StandardCharsets.UTF_8);
        List<String> readString = Files.readAllLines(path, StandardCharsets.UTF_8);

        System.out.println("== Read String ==");;
        System.out.println(readString);

        System.out.println("== Read Stream String ==");;
        try(Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8)) {
            lineStream.forEach(System.out::println);
        }
    }
}
