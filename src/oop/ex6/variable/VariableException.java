package oop.ex6.variable;

import oop.ex6.main.SyntaxException;

/**
 * represent a variable exception.
 * @see SyntaxException
 */
public class VariableException extends SyntaxException {

    /**
     * Constructor.
     */
    public VariableException() {
        super("Variable Exception");
    }
}
