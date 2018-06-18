package oop.ex6.method;

import oop.ex6.blocks.Block;
import oop.ex6.blocks.GlobalBlock;
import oop.ex6.blocks.MethodBlock;
import oop.ex6.main.SyntaxException;
import oop.ex6.regex.Regex;
import oop.ex6.variable.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the method factory.
 */
public class MethodFactory {

	/**
	 * generate a method from a s-java method declaration line.
	 * @param line the s-java line.
	 * @return the generated method.
	 * @throws SyntaxException if the syntax is wrong.
	 */
	public static Method createMethod(String line) throws SyntaxException {

	    ArrayList<Variable> parameters = new ArrayList<>();

	    Pattern p = Pattern.compile(Regex.METHOD);
	    Matcher m = p.matcher(line);
	    m.matches();
	    String methodName = m.group(1);
	    String v = m.group(2);

	    if (v != null) {

		    String[] vars = v.split(",");

		    for (String var : vars) {
			    ArrayList<Variable> variable = createDeclaredVariables(var + ";", parameters);
			    parameters.addAll(variable);
		    }
	    }

	    return new Method(methodName, parameters);
    }

	/**
	 * check a call to a method in s-java.
	 * @param call the call line to the method.
	 * @param block the parent block.
	 * @param globalBlock the global block.
	 * @throws SyntaxException if the syntax is wrong.
	 */
	public static void checkMethodCall(String call, Block block, GlobalBlock globalBlock) throws SyntaxException {

		ArrayList<Variable> parameters = new ArrayList<>();

		Pattern p = Pattern.compile(Regex.CALL_TO_METHOD);
		Matcher m = p.matcher(call);
		m.matches();
		String methodName = m.group(1);
		String v = m.group(2);

		String[] vars;

		if (v == null) {
			vars = new String[0];
		}
		else {
			vars = v.split(",");
		}

		Method method = findMethod(methodName, globalBlock);

		if (method == null) {
			throw new MethodException();
		}
		if (method.getParameters().size() != vars.length) {
			throw new MethodException();
		}

		for (int i = 0; i < vars.length; i++) {
			vars[i] = vars[i].replaceAll("\\s", "");
			Variable variable = block.getVariableWithParentBlocks(vars[i]);
			if (Pattern.compile(Regex.legalValue.get(method.getParameters().get(i).getType()))
					.matcher(vars[i]).matches()) {
				continue;
			}
			if (variable == null) {
				throw new VariableException();
			}
			if (!variable.getType().equals(method.getParameters().get(i).getType())) {
				throw new VariableException();
			}
			if (!variable.isInitialized()) {
				throw new VariableException();
			}

		}
	}

	/*
	 * find a method in the global block by the name.
	 */
	private static Method findMethod(String name, GlobalBlock globalBlock) {
		ArrayList<MethodBlock> methodBlocks = globalBlock.getMethods();
		for (MethodBlock methodBlock: methodBlocks) {
			Method method = methodBlock.getMethod();
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	/*
	 * create variables from declaration line.
	 */
	private  static ArrayList<Variable> createDeclaredVariables(String declaration,
	                                                          ArrayList<Variable> variables) throws VariableException {

		ArrayList<Variable> newVariables = new ArrayList<>();

		Pattern p = Pattern.compile(Regex.VARIABLE_DECLARATION);
		Matcher m = p.matcher(declaration);
		m.matches();
		boolean isFinal = m.group(1) != null;
		String type = m.group(3);

		String[] vars = m.group(4).split(",");

		for(String var: vars) {
			Pattern varPattern = Pattern.compile(Regex.VARIABLE_OPTIONAL_ASSIGNMENT);
			Matcher varMatcher = varPattern.matcher(var);
			varMatcher.matches();
			String name = varMatcher.group(1);

			Variable variable = new Variable(name, type, isFinal, true);

			if (isVariableExist(variable.getName(), variables)) {
				throw new VariableException();
			}

			newVariables.add(variable);
		}

		return newVariables;
	}

	/*
	 * check if variable with name exist in the variables list.
	 */
	private static boolean isVariableExist(String name, ArrayList<Variable> variables) {
		for (Variable variable: variables) {
			if (name.equals(variable.getName())) {
				return true;
			}
		}
		return false;
	}
}
