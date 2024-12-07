package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Files 클래스가 File 클래스가 기능적으로 더 발달한 형태이다.
 * File, FileInputStream, FileWriter의 사용을 고민하기 전에 Files에 기능을 먼저 찾아보자. 성능도 좋고 편리성도 좋다.
 */
public class NewFilesMain {

    public static void main(String[] args) {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + " file already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + " directory already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
