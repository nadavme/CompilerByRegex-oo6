package oop.ex6.method;

import oop.ex6.variable.Variable;
import java.util.ArrayList;

/**
 * represent a method in s-java.
 */
public class Method {

	/* the name of the method. */
    private String name;
	/* the parameters of the method. */
    private ArrayList<Variable> parameters;

	/**
	 * Constructor.
	 * @param name the name of the method.
	 * @param parameters the parameters of the method.
	 */
	public Method(String name, ArrayList<Variable> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

	/**
	 * return the name of the method.
	 * @return the name of the method.
	 */
	public String getName() {
        return name;
    }

    /**
     * return the list of the parameters.
     * @return the list of the parameters.
     */
    public ArrayList<Variable> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Method)) {
            return false;
        }
        Method method = (Method) o;
        boolean nameEqual = name.equals(method.name);
        boolean parametersEqual = true;
        for (int i = 0; i < parameters.size(); i++) {
            if (!parameters.get(i).equals(method.parameters.get(i))) {
                parametersEqual = false;
            }
        }
        return nameEqual && parametersEqual;
    }
}
