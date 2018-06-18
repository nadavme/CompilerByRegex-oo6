package oop.ex6.blocks;

import oop.ex6.variable.Variable;
import java.util.ArrayList;

/**
 * represents a Global block.
 * @see Block
 */
public class GlobalBlock extends Block {

	/* the list of the methods */
    private ArrayList<MethodBlock> blocks;

	/**
	 * Constructor.
	 * @param lines the lines of the block.
	 */
	public GlobalBlock(ArrayList<String> lines) {
        super(null, lines);
        blocks = new ArrayList<>();
    }

	/**
	 * add a method to the list of the methods.
	 * @param method the method to add
	 */
	public void addMethod(MethodBlock method) {
        blocks.add(method);
    }

	/**
	 * reset the global variables.
	 */
	public void resetScopeInit() {
    	for (Variable variable: variables) {
    		variable.resetScopeInit();
	    }
    }

	/**
	 * return the list of the methods.
	 * @return the list of the methods.
	 */
	public ArrayList<MethodBlock> getMethods() {
        return blocks;
    }
}
