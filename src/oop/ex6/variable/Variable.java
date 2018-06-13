package oop.ex6.variable;

public abstract class Variable {

    protected String name;
    protected String type;

    protected boolean isFinal;
    protected boolean isInitialized;

    public Variable(String name, String type, boolean isFinal, boolean isInitialized) {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.isInitialized = isInitialized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
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
