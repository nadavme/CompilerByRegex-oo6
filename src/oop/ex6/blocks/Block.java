package oop.ex6.blocks;

import oop.ex6.variable.Variable;
import java.util.ArrayList;

/**
 * represents a block in s-java.
 * this is an abstract class.
 */
public abstract class Block {

	/** the parent block. */
    protected Block parent;
	/** the lines of the block. */
	protected ArrayList<String> lines;

	/** the variables of the block. */
    protected ArrayList<Variable> variables;

	/**
	 * Constructor.
	 * @param parent the parent block.
	 * @param lines the lines of the block.
	 */
	public Block(Block parent, ArrayList<String> lines) {
        this.parent = parent;
        this.lines = lines;
        variables = new ArrayList<>();
    }

	/**
	 * return the parent block of the block.
	 * @return the parent block of the block.
	 */
	public Block getParent() {
        return parent;
    }

	/**
	 * return the lines of the block.
	 * @return the lines of the block.
	 */
	public ArrayList<String> getLines() {
        return lines;
    }

    /**
     * add the variable to the list of the the variables.
     * @param variable the variable to add
     */
    public void addVariable(Variable variable) {
        variables.add(variable);
    }

	/**
	 * add all the variables to the list of the variables.
	 * @param variables the variable to add.
	 */
	public void addVariables(ArrayList<Variable> variables) {
        this.variables.addAll(variables);
    }

	/**
	 * return the list of the variables.
	 * @return ArrayList of the variables.
	 */
	public ArrayList<Variable> getVariables() {
        return variables;
    }

	/**
	 * return the variable with the name, also search in the patent scopes.
	 * if such doesn't exist return null.
	 * @param name the name of the variable to search
	 * @return the variable with the name.
	 */
	public Variable getVariableWithParentBlocks(String name) {
    	for (Variable variable: variables) {
    		if (variable.getName().equals(name)) {
    			return variable;
		    }
	    }
	    if (parent != null) {
    		return parent.getVariableWithParentBlocks(name);
	    }
	    return null;
    }

	/**
	 * return the variable with the name, do not search in the patent scopes.
	 * if such doesn't exist return null.
	 * @param name the name of the variable to search
	 * @return the variable with the name.
	 */
	public Variable getVariableCurrentBlock(String name) {
		for (Variable variable: variables) {
			if (variable.getName().equals(name)) {
				return variable;
			}
		}
		return null;
	}
}
