package oop.ex6.variable;

/**
 *
 */
public enum Type {
    BOOLEAN("boolean"),
    CHAR("char"),
    DOUBLE("double"),
    INT("int"),
    STRING("String");

    private final String type;

    /**
     * @param type
     */
    Type(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }
}
