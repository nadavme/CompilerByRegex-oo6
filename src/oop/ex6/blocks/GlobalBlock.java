package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

/**
 *
 */
public class GlobalBlock extends Block {

    /**
     * @param parent
     * @param lines
     */
    public GlobalBlock(Block parent, ArrayList<String> lines) {
        super(parent, lines);
    }
}
