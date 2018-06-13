package oop.ex6.variable;

public class BooleanVariable extends Variable {

    public static final String booleanType = "boolean";

    public BooleanVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, booleanType, isFinal, isInitialized);
    }
}
