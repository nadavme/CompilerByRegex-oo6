package oop.ex6.method;

import oop.ex6.blocks.GlobalBlock;
import oop.ex6.blocks.MethodBlock;
import oop.ex6.regex.Regex;
import oop.ex6.variable.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodFactory {

    /**
     * @param line
     * @return
     */
    public static Method createMethod(String line, GlobalBlock globalBlock) throws MethodException {

        ArrayList<Variable> parameters = new ArrayList<>();

        Pattern methodPattern = Pattern.compile(Regex.METHOD);
        Matcher m = methodPattern.matcher(line);
        m.matches();
        String methodName = m.group(1);

        for (MethodBlock methodBlock : globalBlock.getMethods()) {
            Method method = methodBlock.getMethod();
            if (method.getName().equals(methodName)) {
                throw new MethodException();
            }
        }
        String methodParameters = m.group(2);
        if (methodParameters != null) {
            String[] parametersA = methodParameters.split(",");

            for (String parameter : parametersA) {
                ArrayList<String> s = new ArrayList<>(Arrays.asList(parameter.split(Regex.AT_LEAST_ONE_SPACE)));
                if (s.get(0).equals("")) {
                    s.remove(0);
                }
                boolean isFinal = false;
                String type;
                String parameterName;
                if (s.get(0).equals("final")) {
                    isFinal = true;
                    type = s.get(1);
                    parameterName = s.get(2);
                } else {
                    type = s.get(0);
                    parameterName = s.get(1);
                }

                Variable variable = new Variable(parameterName, type, isFinal, true);
                parameters.add(variable);
            }
        }
        return new Method(methodName, parameters);
    }
}
