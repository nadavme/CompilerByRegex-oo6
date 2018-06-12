package oop.ex6.blocks;

import oop.ex6.variable.Variable;

import java.util.ArrayList;

public abstract class Block {

    protected Block parent;
    protected ArrayList<String> lines;
    protected ArrayList<Variable> variables;

    public Block(Block parent, ArrayList<String> lines) {
        this.parent = parent;
        this.lines = lines;
        variables = new ArrayList<>();
    }

    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public void addVariable(Variable variable) {
        variables.add(variable);
    }
}
