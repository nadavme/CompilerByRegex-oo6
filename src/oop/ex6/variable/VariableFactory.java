package oop.ex6.variable;

import oop.ex6.blocks.Block;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableFactory {

    private static final String VARIABLE_REGEX = "";
    private static final String VARIABLE_DECLARATION_REGEX = "";

    public static Variable createVariable(String line, Block parent) throws VariableException {

        Pattern variableRegex = Pattern.compile(VARIABLE_REGEX);
        Matcher m1 = variableRegex.matcher(line);
        variableRegex = Pattern.compile(VARIABLE_DECLARATION_REGEX);
        Matcher m2 = variableRegex.matcher(line);
        if (!m1.matches() && !m2.matches()) {
            throw new VariableException();
        }
        return null;
    }
}
