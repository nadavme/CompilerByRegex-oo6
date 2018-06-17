package oop.ex6.parser;

import oop.ex6.blocks.Block;
import oop.ex6.blocks.MethodBlock;
import oop.ex6.main.SyntaxException;
import oop.ex6.method.Method;
import oop.ex6.method.MethodException;
import oop.ex6.method.MethodFactory;
import oop.ex6.regex.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Parser {

    /**
     * @param block
     * @throws SyntaxException
     */
    public static void parseBlock(Block block) throws SyntaxException {

        ArrayList<Method> methods = new ArrayList<>();

        ArrayList<String> lines = block.getLines();
        Pattern methodRegex = Pattern.compile(Regex.METHOD_WITH_PARAMETERS);
        for (int i = 0; i < lines.size(); i++) {
            Matcher m = methodRegex.matcher(lines.get(i));
            if (m.matches()) {
                MethodBlock methodBlock = getMethodBlock(block, i, lines);
                methods.add(methodBlock.getMethod());
                block.addMethod(methodBlock.getMethod());
            }
        }

        for (String line : lines) {

        }
    }

    private static MethodBlock getMethodBlock(Block parentBlock, int startLine, ArrayList<String> lines) throws SyntaxException {
        ArrayList<String> methodLines = new ArrayList<>();
        int i = startLine + 1;
        int openCounter = 1;
        int closeCounter = 0;
        Method method = MethodFactory.createMethod(lines.get(startLine));
        Pattern openPattern = Pattern.compile(Regex.OPEN_SCOPE);
        Pattern closerPattern = Pattern.compile(Regex.CLOSE_SCOPE);
        while (openCounter != closeCounter && i < lines.size()) {
            Matcher open = openPattern.matcher(lines.get(i));
            Matcher closer = closerPattern.matcher(lines.get(i));
            if (open.matches()) {
                openCounter++;
            } else if (closer.matches()) {
                closeCounter++;
                if (openCounter == closeCounter) {
                    continue;
                }
            }
            methodLines.add(lines.get(i));
            i++;
        }
        if (i == lines.size()) {
            throw new MethodException();
        }
        return new MethodBlock(parentBlock, method, methodLines);
    }
}
