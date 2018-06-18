package oop.ex6.file_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the file reader
 */
public class FileReader {

	/**
	 * read the file.
	 * @param path the file path.
	 * @return the lines of the file.
	 * @throws FileNotFoundException IO error.
	 * @throws IllegalFileTypeException if the file is not sjava file.
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
