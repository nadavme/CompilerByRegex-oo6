package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

public class WhileBlock extends Block {

    public WhileBlock(Block parent, ArrayList<String> lines) {
        super(parent, lines);
    }

    @Override
    public void addMethod(Method method) throws BlockException {
        throw new BlockException();
    }
}