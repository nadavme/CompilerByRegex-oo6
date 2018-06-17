package oop.ex6.file_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class FileReader {

    /**
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IllegalFileTypeException
     */
    public static ArrayList<String> readFile(String path) throws FileNotFoundException, IllegalFileTypeException {
        if (!path.endsWith(".sjava")) {
            throw new IllegalFileTypeException();
        }
        File file = new File(path);
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
