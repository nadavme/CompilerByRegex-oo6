package oop.ex6.variable;

import oop.ex6.blocks.Block;
import oop.ex6.regex.Regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class VariableFactory {

    /**
     * @param line
     * @return
     * @throws VariableException
     */
    public static ArrayList<Variable> createVariables(String line) throws VariableException {
        Pattern variableRegex = Pattern.compile(Regex.VARIABLE_DECLARATION);
        Matcher m = variableRegex.matcher(line);
        m.matches();
        boolean isFinal = false;
        if (m.group(2) != null) {
            isFinal = true;
        }
        String type = m.group(3);
        String[] variablesNamesAndValues = m.group(4).split(",");

        ArrayList<Variable> variables = new ArrayList<>();

        for (String name : variablesNamesAndValues) {
            ArrayList<String> s = new ArrayList<>(Arrays.asList(name.split(Regex.AT_LEAST_ONE_SPACE)));
            if (s.get(0).equals("")) {
                s.remove(0);
            }
            String variableName = s.get(0);
            boolean isInit = false;
            if (s.size() > 1) {
                String value = s.get(2);
                if (!isValueMatchType(value, type)) {
                    throw new VariableException();
                }
                isInit = true;
            }
            if (isFinal && !isInit) {
                throw new VariableException();
            }

            Variable variable = new Variable(variableName, type, isFinal, isInit);
            variables.add(variable);
        }
        return variables;
    }

    private static boolean isValueMatchType(String value, String type) {
        switch (type) {
            case Variable.INT_TYPE:
                return Pattern.compile(Regex.LEGAL_INT_VALUE).matcher(value).matches();
            case Variable.DOUBLE_TYPE:
                return Pattern.compile(Regex.LEGAL_DOUBLE_VALUE).matcher(value).matches();
            case Variable.STRING_TYPE:
                return Pattern.compile(Regex.LEGAL_STRING_VALUE).matcher(value).matches();
            case Variable.CHAR_TYPE:
                return Pattern.compile(Regex.LEGAL_CHAR_VALUE).matcher(value).matches();
            case Variable.BOOLEAN_TYPE:
                return Pattern.compile(Regex.LEGAL_BOOLEAN_VALUE).matcher(value).matches();
        }
        return false;
    }
}
