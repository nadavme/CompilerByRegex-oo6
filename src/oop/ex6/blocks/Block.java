package oop.ex6.blocks;

import oop.ex6.main.SyntaxException;
import oop.ex6.method.Method;
import oop.ex6.parser.Parser;
import oop.ex6.variable.Variable;

import java.util.ArrayList;

/**
 *
 */
public abstract class Block {

    protected Block parent;
    protected ArrayList<String> lines;

    protected ArrayList<Variable> variables;

    /**
     * @param parent
     * @param lines
     */
    public Block(Block parent, ArrayList<String> lines) {
        this.parent = parent;
        this.lines = lines;
        variables = new ArrayList<>();
        if (parent != null) {
            variables.addAll(parent.variables);
        }
    }

    /**
     *
     * @return
     */
    public Block getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Block parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getLines() {
        return lines;
    }

    /**
     *
     * @param lines
     */
    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    /**
     *
     * @param variable
     */
    public void addVariable(Variable variable) {
        variables.add(variable);
    }

    public void addVariables(ArrayList<Variable> variables) {
        this.variables.addAll(variables);
    }

    /**
     *
     * @return
     */
    public ArrayList<Variable> getVariables() {
        return variables;
    }
}
