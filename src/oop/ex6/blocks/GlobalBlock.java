package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

/**
 *
 */
public class GlobalBlock extends Block {

    protected ArrayList<MethodBlock> blocks;

    /**
     * @param parent
     * @param lines
     */
    public GlobalBlock(Block parent, ArrayList<String> lines) {
        super(parent, lines);
        blocks = new ArrayList<>();
    }

    /**
     * @param method
     * @throws BlockException
     */
    public void addMethod(MethodBlock method) throws BlockException {
        blocks.add(method);
    }

    public ArrayList<MethodBlock> getMethods() {
        return blocks;
    }
}
