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
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Parser {

    public static void parseGlobalBlock(GlobalBlock block) throws SyntaxException {
        ArrayList<String> lines = block.getLines();
        int i = 0;
        while (i < lines.size()) {
            i = globalLineHandler(i, lines, block);
        }
    }

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
            ArrayList<Variable> variables = VariableFactory.createVariables(line);
            block.addVariables(variables);
            return index + 1;
        }
        throw new SyntaxException();
    }

    private static MethodBlock getMethodBlock(GlobalBlock block, int startLine,
                                              ArrayList<String> lines) throws SyntaxException {
        ArrayList<String> methodLines = getLines(startLine, lines);
        Method method = MethodFactory.createMethod(lines.get(startLine), block);
        Pattern p = Pattern.compile(Regex.RETURN_LINE);
        Matcher m = p.matcher(lines.get(startLine + methodLines.size() - 2));
        if (!m.matches()) {
            throw new MethodException();
        }
        return new MethodBlock(block, method, methodLines);
    }


    /**
     * @param block
     * @throws SyntaxException
     */
    public static void parseBlock(Block block, GlobalBlock global) throws SyntaxException {

        ArrayList<String> lines = block.getLines();
        for (int i = 0; i < lines.size(); i++) {

        }
    }

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
            ArrayList<Variable> variables = VariableFactory.createVariables(line);
            block.addVariables(variables);
            return index + 1;
        }
        p = Pattern.compile(Regex.VARIABLE_ASSIGNMENT);
        m = p.matcher(line);
        if (m.matches()) {
            String variableName = m.group(1);
            String variableValue = m.group(1);
            Variable variable = findVariable(variableName, block);
            Pattern legalValuePattern = Pattern.compile(Regex.legalValue.get(variable.getType()));
            Matcher legalValueMatcher = legalValuePattern.matcher(variableValue);
            if (!legalValueMatcher.matches()) {
                throw new VariableException();
            }
            return index + 1;
        }
        p = Pattern.compile(Regex.CALL_TO_METHOD);
        m = p.matcher(line);
        if (m.matches()) {
            String methodName = m.group(1);
            String vars = m.group(2);
            ArrayList<String> types = new ArrayList<>();
            if (vars != null) {
                String[] v = vars.split(",");
                for (String var : v) {
                    ArrayList<String> s = new ArrayList<>(Arrays.asList(var.split(Regex.AT_LEAST_ONE_SPACE)));
                    if (s.get(0).equals("")) {
                        s.remove(0);
                    }
                    int size = types.size();
                    for (String value : Regex.legalValue.values()) {
                        Pattern legalValuePattern = Pattern.compile(value);
                        Matcher legalValueMatcher = legalValuePattern.matcher(s.get(0));
                        if (legalValueMatcher.matches()) {
                            types.add(value);
                        }
                    }
                    if (types.size() == size) {
                        Variable variable = findVariable(s.get(0), block);
                        types.add(variable.getType());
                    }
                }
            }
            for (MethodBlock methodBlock : global.getMethods()) {
                Method method = methodBlock.getMethod();
                if (method.getName().equals(methodName)) {
                    if (types.size() != method.getParameters().size()) {
                        throw new SyntaxException();
                    }
                    for (int i = 0; i < types.size(); i++) {
                        if (!types.get(i).equals(method.getParameters().get(i).getType())) {
                            throw new SyntaxException();
                        }
                    }
                }
            }
            return index + 1;
        }
        p = Pattern.compile(Regex.IF_WHILE);
        m = p.matcher(line);
        if (m.matches()) {
            String if_while = m.group(1);
            String booleanValue = m.group(2);
            if (booleanValue != null) {
                String[] booleans = booleanValue.split("(&&|\\|\\|)");
                for (String b : booleans) {
                    ArrayList<String> s = new ArrayList<>(Arrays.asList(b.split(Regex.AT_LEAST_ONE_SPACE)));
                    if (s.get(0).equals("")) {
                        s.remove(0);
                    }
                    Pattern legalValuePattern = Pattern.compile(Regex.LEGAL_BOOLEAN_VALUE);
                    Matcher legalValueMatcher = legalValuePattern.matcher(s.get(0));
                    if (!legalValueMatcher.matches()) {
                        Variable variable = findVariable(s.get(0), block);
                        String type = variable.getType();
                        if (!(type.equals(Variable.BOOLEAN_TYPE) || type.equals(Variable.DOUBLE_TYPE)
                                || type.equals(Variable.INT_TYPE))) {
                            throw new SyntaxException();
                        }
                    }
                }
            }
            Block conditionalBlock = create_if_while_block(if_while, block, index, lines);
            parseBlock(conditionalBlock, global);
            return index + conditionalBlock.getLines().size() + 2;
        }
        throw new SyntaxException();
    }

    private static Block create_if_while_block(String if_while, Block block, int startLine, ArrayList<String> lines) throws BlockException {
        ArrayList<String> scopeLines = getLines(startLine, lines);
        switch (if_while) {
            case "if":
                return new IfBlock(block, scopeLines);
            case "while":
                return new WhileBlock(block, scopeLines);
        }
        return null;
    }

    private static Variable findVariable(String name, Block block) throws VariableException {
        int i;
        for (i = block.getVariables().size() - 1; i >= 0; i--) {
            Variable variable = block.getVariables().get(i);
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        throw new VariableException();
    }

    private static ArrayList<String> getLines(int startLine, ArrayList<String> lines) throws BlockException {
        ArrayList<String> scopeLines = new ArrayList<>();
        int i = startLine + 1;
        int openCounter = 1;
        int closeCounter = 0;
        Pattern openPattern = Pattern.compile(Regex.OPEN_SCOPE);
        Pattern closerPattern = Pattern.compile(Regex.CLOSE_SCOPE);
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
        if (i == lines.size()) {
            throw new BlockException();
        }
        return scopeLines;
    }
}
