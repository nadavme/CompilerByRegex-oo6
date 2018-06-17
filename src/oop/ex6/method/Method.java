package oop.ex6.method;

import oop.ex6.variable.Variable;
import java.util.ArrayList;

/**
 *
 */
public class Method {

    private String name;
    private ArrayList<Variable> parameters;

    /**
     * @param name
     * @param parameters
     */
    public Method(String name, ArrayList<Variable> parameters) {
        this.name = name;
        this.parameters = parameters;
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
    public ArrayList<Variable> getParameters() {
        return parameters;
    }

    /**
     *
     * @param parameters
     */
    public void setParameters(ArrayList<Variable> parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @param o
     * @return
     */
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
