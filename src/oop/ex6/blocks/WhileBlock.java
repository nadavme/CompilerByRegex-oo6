package oop.ex6.blocks;

import java.util.ArrayList;

/**
 * represents an if block.
 * @see Block
 */
public class WhileBlock extends Block {

	/**
	 * Constructor.
	 * @param parent the parent block.
	 * @param lines the lines of the block.
	 * @throws BlockException if the parent block is not the global block.
	 */
    public WhileBlock(Block parent, ArrayList<String> lines) throws BlockException {
        super(parent, lines);
        if (parent instanceof GlobalBlock) {
            throw new BlockException();
        }
    }
}
