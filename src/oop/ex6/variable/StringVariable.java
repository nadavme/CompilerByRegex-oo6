package oop.ex6.variable;

public class StringVariable extends Variable {

    private static final String stringType = "String";

    public StringVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, stringType, isFinal, isInitialized);
    }
}
