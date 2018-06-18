package oop.ex6.file_reader;

import java.io.IOException;

/**
 * represents illegal file type exception.
 * @see IOException
 */
public class IllegalFileTypeException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public IllegalFileTypeException() {
        super("Warning: Running on a non s-java file");
    }
}
