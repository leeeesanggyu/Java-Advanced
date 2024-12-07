package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());

        System.out.println("Absolute path = " + file.getAbsolutePath());    // 절대 경로
        System.out.println("Canonical path = " + file.getCanonicalPath());  // 정규 경로

        File[] files = file.listFiles();
        for (File f: files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }

        System.out.println("====================");

        Path path = Path.of("temp/..");
        Stream<Path> list = Files.list(path);
        List<Path> paths = list.toList();

        for(Path p: paths) {
            System.out.println((Files.isRegularFile(p) ? "F" : "D") + " | " + p.getFileName());
        }

    }
}
