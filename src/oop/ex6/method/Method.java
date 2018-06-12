package oop.ex6.method;

import oop.ex6.variable.Variable;

import java.util.ArrayList;

public class Method {

    private String name;
    private ArrayList<Variable> parameters;

    public Method(String name, ArrayList<Variable> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Variable> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Variable> parameters) {
        this.parameters = parameters;
    }
}
