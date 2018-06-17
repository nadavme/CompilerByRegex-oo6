package oop.ex6.variable;

/**
 *
 */
public class Variable {

    public static final String INT_TYPE = "int";
    public static final String DOUBLE_TYPE = "double";
    public static final String STRING_TYPE = "String";
    public static final String CHAR_TYPE = "char";
    public static final String BOOLEAN_TYPE = "boolean";

    protected String name;
    protected String type;

    protected boolean isFinal;
    protected boolean isInitialized;

    /**
     * @param name
     * @param type
     * @param isFinal
     * @param isInitialized
     */
    public Variable(String name, String type, boolean isFinal, boolean isInitialized) {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.isInitialized = isInitialized;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     *
     * @param aFinal
     */
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    /**
     *
     * @return
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     *
     * @param initialized
     */
    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable variable = (Variable) o;
        boolean nameEqual = name.equals(variable.name);
        boolean typeEqual = type.equals(variable.type);
        return nameEqual && typeEqual;
    }
}
