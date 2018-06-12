package oop.ex6.variable;

public class DoubleVariable extends Variable {

    private static final String doubleType = "double";

    public DoubleVariable(String name, boolean isFinal, boolean isInitialized) {
        super(name, doubleType, isFinal, isInitialized);
    }
}
