package oop.ex6.variable;

/**
 *
 */
public class IntVariable extends Variable {

    public static final String intType = "int";

    /**
     * @param name
     * @param isFinal
     * @param isInitialized
     */
    public IntVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, intType, isFinal, isInitialized);
    }
}
