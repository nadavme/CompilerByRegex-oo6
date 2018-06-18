package oop.ex6.blocks;

import oop.ex6.method.Method;
import java.util.ArrayList;

/**
 * represents a method block.
 * @see Block
 */
public class MethodBlock extends Block {

	/* the method */
    private Method method;

	/**
	 * Constructor.
	 * @param parent the parent block.
	 * @param method the method.
	 * @param lines the lines of the block.
	 */
    public MethodBlock(Block parent, Method method, ArrayList<String> lines) {
        super(parent, lines);
        this.method = method;
        variables.addAll(method.getParameters());
    }

	/**
	 * return the method.
	 * @return the method.
	 */
	public Method getMethod() {
        return method;
    }
}
