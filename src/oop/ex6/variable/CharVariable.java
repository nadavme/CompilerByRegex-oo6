package oop.ex6.variable;

/**
 *
 */
public class CharVariable extends Variable {

    public static final String charType = "char";

    /**
     * @param name
     * @param isFinal
     * @param isInitialized
     */
    public CharVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, charType, isFinal, isInitialized);
    }
}
