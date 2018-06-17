package oop.ex6.file_reader;

import java.io.IOException;

/**
 *
 */
public class IllegalFileTypeException extends IOException {

    /**
     *
     */
    public IllegalFileTypeException() {
        super("Warning: Running on a non s-java file");
    }
}
