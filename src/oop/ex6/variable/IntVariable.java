package oop.ex6.variable;

public class IntVariable extends Variable {

    private static final String intType = "int";

    public IntVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, intType, isFinal, isInitialized);
    }
}
