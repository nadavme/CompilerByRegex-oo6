package oop.ex6.parser;

import oop.ex6.blocks.*;
import oop.ex6.main.SyntaxException;
import oop.ex6.method.Method;
import oop.ex6.method.MethodException;
import oop.ex6.method.MethodFactory;
import oop.ex6.regex.Regex;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableException;
import oop.ex6.variable.VariableFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the parser class.
 */
public class Parser {

	/**
	 * parse the global block.
	 * @param block the global block
	 * @throws SyntaxException if the syntax is wrong.
	 */
    public static void parseGlobalBlock(GlobalBlock block) throws SyntaxException {
        ArrayList<String> lines = block.getLines();
        int i = 0;
        while (i < lines.size()) {
            i = globalLineHandler(i, lines, block);
        }
    }

    /*
     * global line handler.
     */
    private static int globalLineHandler(int index, ArrayList<String> lines,
                                         GlobalBlock block) throws SyntaxException {
        String line = lines.get(index);
        Pattern p = Pattern.compile(Regex.EMPTY_LINE);
        Matcher m = p.matcher(line);
        if (m.matches()) {
            return index + 1;
        }
        p = Pattern.compile(Regex.COMMENT_LINE);
        m = p.matcher(line);
        if (m.matches()) {
            return index + 1;
        }
        p = Pattern.compile(Regex.METHOD);
        m = p.matcher(line);
        if (m.matches()) {
            MethodBlock methodBlock = getMethodBlock(block, index, lines);
            block.addMethod(methodBlock);
            return index + methodBlock.getLines().size() + 2;
        }
        p = Pattern.compile(Regex.VARIABLE_DECLARATION);
        m = p.matcher(line);
        if (m.matches()) {
            VariableFactory.createVariables(line, block);
            return index + 1;
        }
        p = Pattern.compile(Regex.VARIABLE_ASSIGNMENT);
        m = p.matcher(line);
        if (m.matches()) {
            VariableFactory.checkAssignment(line, block);
            return index + 1;
        }
        throw new SyntaxException();
    }

    /*
     * return method block form the start lines and the global block lines.
     */
    private static MethodBlock getMethodBlock(GlobalBlock block, int startLine,
                                              ArrayList<String> lines) throws SyntaxException {
        ArrayList<String> methodLines = getLines(startLine, lines);
        Method method = MethodFactory.createMethod(lines.get(startLine));
        Pattern p = Pattern.compile(Regex.RETURN_LINE);
        Matcher m = p.matcher(methodLines.get(methodLines.size() - 1));
        if (!m.matches()) {
            throw new MethodException();
        }
        return new MethodBlock(block, method, methodLines);
    }


	/**
	 * parse the given block.
	 * @param block the block.
	 * @param global the global block.
	 * @throws SyntaxException if the syntax is wrong.
	 */
	public static void parseBlock(Block block, GlobalBlock global) throws SyntaxException {

        ArrayList<String> lines = block.getLines();
        int i = 0;
	    while (i < lines.size()) {
		    i = lineHandler(i, lines, block, global);
	    }
    }

	/*
	 * line handler.
	 */
    private static int lineHandler(int index, ArrayList<String> lines,
                                   Block block, GlobalBlock global) throws SyntaxException {
        String line = lines.get(index);
        Pattern p = Pattern.compile(Regex.EMPTY_LINE);
        Matcher m = p.matcher(line);
        if (m.matches()) {
            return index + 1;
        }
        p = Pattern.compile(Regex.COMMENT_LINE);
        m = p.matcher(line);
        if (m.matches()) {
            return index + 1;
        }
        p = Pattern.compile(Regex.RETURN_LINE);
        m = p.matcher(line);
        if (m.matches()) {
            return index + 1;
        }
        p = Pattern.compile(Regex.VARIABLE_DECLARATION);
        m = p.matcher(line);
        if (m.matches()) {
            VariableFactory.createVariables(line, block);
            return index + 1;
        }
        p = Pattern.compile(Regex.VARIABLE_ASSIGNMENT);
        m = p.matcher(line);
        if (m.matches()) {
            VariableFactory.checkAssignment(line, block);
            return index + 1;
        }
        p = Pattern.compile(Regex.CALL_TO_METHOD);
        m = p.matcher(line);
        if (m.matches()) {
            MethodFactory.checkMethodCall(line, block, global);
            return index + 1;
        }
        p = Pattern.compile(Regex.IF_WHILE);
        m = p.matcher(line);
        if (m.matches()) {
	        String if_while = m.group(1);
            checkIfWhileBlock(line, block);
            Block conditionalBlock = create_if_while_block(if_while, block, index, lines);
            parseBlock(conditionalBlock, global);
            return index + conditionalBlock.getLines().size() + 2;
        }
        throw new SyntaxException();
    }

    /*
     * check if/while block.
     */
    private static void checkIfWhileBlock(String line, Block block) throws VariableException {
	    Pattern p = Pattern.compile(Regex.IF_WHILE);
	    Matcher m = p.matcher(line);
	    m.matches();
	    String booleanValue = m.group(2);
	    if (booleanValue != null) {
		    String[] booleans = booleanValue.split("(&&|\\|\\|)");
		    for (String b : booleans) {
			    b = b.replaceAll("\\s", "");
			    Variable variable = block.getVariableWithParentBlocks(b);
			    if (Pattern.compile(Regex.legalValue.get(Variable.BOOLEAN_TYPE))
					    .matcher(b).matches()) {
				    continue;
			    }
			    if (variable == null) {
				    throw new VariableException();
			    }
			    if (!variable.getType().equals(Variable.BOOLEAN_TYPE) &&
					    !variable.getType().equals(Variable.DOUBLE_TYPE) &&
					    !variable.getType().equals(Variable.INT_TYPE)) {
				    throw new VariableException();
			    }
			    if (!variable.isInitialized()) {
				    throw new VariableException();
			    }
		    }
	    }
    }

    /*
     * create if/while block from the given parameters.
     */
    private static Block create_if_while_block(String if_while, Block block,
                                               int startLine, ArrayList<String> lines) throws BlockException {
        ArrayList<String> scopeLines = getLines(startLine, lines);
        switch (if_while) {
            case "if":
                return new IfBlock(block, scopeLines);
            case "while":
                return new WhileBlock(block, scopeLines);
        }
        return null;
    }

    /*
     * get the lines of the scope starts in the start line.
     */
    private static ArrayList<String> getLines(int startLine, ArrayList<String> lines) throws BlockException {
        ArrayList<String> scopeLines = new ArrayList<>();
        int i = startLine + 1;
        int openCounter = 1;
        int closeCounter = 0;
        Pattern openPattern = Pattern.compile(Regex.OPEN_SCOPE);
        Pattern closerPattern = Pattern.compile(Regex.CLOSE_SCOPE_LINE);
        while (openCounter != closeCounter && i < lines.size()) {
            Matcher open = openPattern.matcher(lines.get(i));
            Matcher closer = closerPattern.matcher(lines.get(i));
            if (open.find()) {
                openCounter++;
            } else if (closer.matches()) {
                closeCounter++;
                if (openCounter == closeCounter) {
                    continue;
                }
            }
            scopeLines.add(lines.get(i));
            i++;
        }
        if (openCounter > closeCounter) {
            throw new BlockException();
        }
        return scopeLines;
    }
}
