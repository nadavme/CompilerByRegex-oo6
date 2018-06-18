package oop.ex6.variable;

/**
 * represents a variable.
 */
public class Variable {

	/** the int type */
    public static final String INT_TYPE = "int";
	/** the double type */
    public static final String DOUBLE_TYPE = "double";
	/** the String type */
    public static final String STRING_TYPE = "String";
	/** the char type */
    public static final String CHAR_TYPE = "char";
	/** the boolean type */
    public static final String BOOLEAN_TYPE = "boolean";

    /* the variable name */
    private String name;
    /* the variable type */
	private String type;
	/* is final */
	private boolean isFinal;
	/* is global initialized */
	private boolean isGlobalInitialized;
	/* is initialized */
	private boolean isInitialized;

	/**
	 * Constructor.
	 * @param name the variable name.
	 * @param type the variable type.
	 * @param isFinal is the variable final.
	 * @param isGlobalInitialized is global initialized
	 */
    public Variable(String name, String type, boolean isFinal, boolean isGlobalInitialized) {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.isGlobalInitialized = isGlobalInitialized;
	    this.isInitialized = isGlobalInitialized;
    }

	/**
	 * return the variable name.
	 * @return the variable name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * return the variable type.
	 * @return the variable type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * return true if the variable is final.
	 * @return true if the variable is final, false otherwise.
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * return true if the variable is initialized.
	 * @return true if the variable is initialized, false otherwise.
	 */
	public boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * initialized setter.
	 * @param initialized the update value.
	 */
	public void setInitialized(boolean initialized) {
		isInitialized = initialized;
	}

	/**
	 * reset the local initialization.
	 */
	public void resetScopeInit() {
    	isInitialized = isGlobalInitialized;
	}

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
