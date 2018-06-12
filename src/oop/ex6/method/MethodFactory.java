package oop.ex6.method;

import oop.ex6.variable.Variable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodFactory {

    private static final String SPACE_REGEX = "\\b";
    private static final String NAME_REGEX = "([a-zA-Z]+|(_)+)[\\w]+";
    private static final String TYPE_REGEX = "(int|double|String|char|boolean)";
    private static final String SIGNATURE_REGEX = SPACE_REGEX + "*void" + SPACE_REGEX + "+" + NAME_REGEX + SPACE_REGEX + "*";
    private static final String PARAMETERS_REGEX = "";
    private static final String METHOD_REGEX = "void[ ]*";

    public static Method createMethod(String line) throws MethodException {
        Pattern method = Pattern.compile(METHOD_REGEX);
        Matcher m = method.matcher(line);
        if (!m.matches()) {
            throw new MethodException();
        }
        int i1 = line.indexOf("(");
        int i2 = line.indexOf(")");
        String parametersStr = line.substring(i1 + 1, i2);
        String[] words = line.split(" ");
        String methodName = words[1];
        String inputParameters[] = parametersStr.split(",");
        for (String s : inputParameters) {

        }
        ArrayList<Variable> parameters = new ArrayList<>();
        for (int i = 2; i < line.length(); i += 2) {
        }
        return null;
    }
}
