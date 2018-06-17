package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

/**
 *
 */
public class IfBlock extends Block {

    /**
     * @param parent
     * @param lines
     */
    public IfBlock(Block parent, ArrayList<String> lines) throws BlockException {
        super(parent, lines);
        if (parent instanceof GlobalBlock) {
            throw new BlockException();
        }
    }
}
