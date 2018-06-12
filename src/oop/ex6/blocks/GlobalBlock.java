package oop.ex6.blocks;

import oop.ex6.method.Method;

import java.util.ArrayList;

public class GlobalBlock extends Block {

    private ArrayList<Method> methods;

    public GlobalBlock(Block parent, ArrayList<String> lines) {
        super(parent, lines);
        methods = new ArrayList<>();
    }

    public void addMethod(Method method) {
        methods.add(method);
    }
}
