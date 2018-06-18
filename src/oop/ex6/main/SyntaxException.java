package oop.ex6.main;

/**
 * represent a syntax exception.
 */
public class SyntaxException extends Exception {

    /**
     * Constructor.
     */
    public SyntaxException() {
        super("Syntax Exception");
    }

	/**
	 * Constructor.
	 * @param message the message.
	 */
	public SyntaxException(String message) {
        super(message);
    }
}
