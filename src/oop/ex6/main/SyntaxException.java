package oop.ex6.main;

/**
 *
 */
public class SyntaxException extends Exception {

    /**
     *
     */
    public SyntaxException() {
        super("Syntax Exception");
    }

    /**
     * @param message
     */
    public SyntaxException(String message) {
        super(message);
    }
}
