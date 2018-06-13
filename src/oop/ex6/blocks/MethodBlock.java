package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

public class MethodBlock extends Block {

    private Method method;

    public MethodBlock(Block parent, Method method, ArrayList<String> lines) {
        super(parent, lines);
        this.method = method;
        variables.addAll(method.getParameters());
    }

    @Override
    public void addMethod(Method method) throws BlockException {
        throw new BlockException();
    }
}
