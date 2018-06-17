package oop.ex6.variable;

/**
 *
 */
public class DoubleVariable extends Variable {

    public static final String doubleType = "double";

    /**
     * @param name
     * @param isFinal
     * @param isInitialized
     */
    public DoubleVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, doubleType, isFinal, isInitialized);
    }
}
