package oop.ex6.blocks;

import oop.ex6.main.SyntaxException;
import oop.ex6.method.Method;
import oop.ex6.parser.Parser;

import java.util.ArrayList;

public class MethodBlock extends Block {

    private Method method;

    public MethodBlock(Block parent, Method method, ArrayList<String> lines) throws SyntaxException {
        super(parent, lines);
        this.method = method;
        variables.addAll(method.getParameters());
        Parser.parseBlock(this);
    }

    @Override
    public void addMethod(Method method) throws BlockException {
        throw new BlockException();
    }

    public Method getMethod() {
        return method;
    }
}
