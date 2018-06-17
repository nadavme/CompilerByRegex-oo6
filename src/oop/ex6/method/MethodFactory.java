package oop.ex6.method;

import oop.ex6.variable.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodFactory {

    private static final String FINAL = "final";
    private static final String SPACE_REGEX = "\\b";
    private static final String NAME_REGEX = "([a-zA-Z]+|(_)+)[\\w]+";
    private static final String TYPE_REGEX = "(int|double|String|char|boolean)";
    private static final String SIGNATURE_REGEX = SPACE_REGEX + "*void" + SPACE_REGEX + "+" + NAME_REGEX + SPACE_REGEX + "*";
    private static final String PARAMETERS_REGEX = "";
    private static final String METHOD_REGEX = "void[ ]*";

    /**
     * @param line
     * @return
     */
    public static Method createMethod(String line) {
        int i1 = line.indexOf("(");
        int i2 = line.indexOf(")");
        String parametersStr = line.substring(i1 + 1, i2);
        String[] words = line.split(" ");
        String methodName = words[1];
        String inputParameters[] = parametersStr.split(",");
        String[][] parametersSeparated = new String[inputParameters.length][3];
        for (int i = 0; i < inputParameters.length; i++) {
            String[] parameter = inputParameters[i].split(" ");
            int separatedIndex = 0;
            for (String aParameter : parameter) {
                aParameter.replaceAll(" ", "");
                if (!aParameter.equals("")) {
                    parametersSeparated[i][separatedIndex] = aParameter;
                    separatedIndex++;
                }
            }
        }
        ArrayList<Variable> parameters = new ArrayList<>();
        for (String[] s : parametersSeparated) {
            Variable variable;
            if (s[0].equals(FINAL)) {
                variable = createVariable(true, s[1], s[2]);
            } else {
                variable = createVariable(true, s[0], s[1]);
            }
            parameters.add(variable);
        }
        return new Method(methodName, parameters);
    }

    private static Variable createVariable(boolean isFinal, String type, String name) {
        switch (type) {
            case IntVariable.intType:
                return new IntVariable(name, isFinal, true);
            case CharVariable.charType:
                return new CharVariable(name, isFinal, true);
            case BooleanVariable.booleanType:
                return new BooleanVariable(name, isFinal, true);
            case DoubleVariable.doubleType:
                return new DoubleVariable(name, isFinal, true);
            case StringVariable.stringType:
                return new StringVariable(name, isFinal, true);
        }
        // unreachable statement
        return null;
    }
}
