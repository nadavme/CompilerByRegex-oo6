package oop.ex6.variable;

import oop.ex6.blocks.Block;
import oop.ex6.regex.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the variable factory
 */
public class VariableFactory {

	/**
	 * create variable from the given parameters.
	 * @param declaration the declaration line.
	 * @param block the parent block.
	 * @throws VariableException if the syntax is wrong.
	 */
    public static void createVariables(String declaration, Block block) throws VariableException {

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
    		String value = varMatcher.group(4);
    		boolean isInit = value != null;

		    Variable variable = new Variable(name, type, isFinal, isInit);

    		if (block.getVariableCurrentBlock(variable.getName()) != null) {
			    throw new VariableException();
		    }

		    if (isFinal && !isInit) {
			    throw new VariableException();
		    }

		    if (value != null) {

			    if (Pattern.compile(Regex.legalValue.get(type)).matcher(value).matches()) {
				    block.addVariable(variable);
				    continue;
			    }

			    Variable val = block.getVariableWithParentBlocks(value);
			    if (val == null) {
				    throw new VariableException();
			    }
			    if (!typesMatch(type, val.getType())) {
				    throw new VariableException();
			    }
			    if (!val.isInitialized()) {
				    throw new VariableException();
			    }
		    }

		    block.addVariable(variable);
	    }
    }

	/**
	 * check variable assignment.
	 * @param assignment the assignment line.
	 * @param block the parent block.
	 * @throws VariableException if the syntax is wrong.
	 */
	public static void checkAssignment(String assignment, Block block) throws VariableException {

		Pattern p = Pattern.compile(Regex.VARIABLE_ASSIGNMENT);
		Matcher m = p.matcher(assignment);
		m.matches();

		String name = m.group(1);
		String value = m.group(3);

		Variable variable = block.getVariableWithParentBlocks(name);
		if (variable == null) {
			throw new VariableException();
		}

		if (variable.isFinal()) {
			throw new VariableException();
		}

		if (!isTypeMatchToValue(variable.getType(), value)) {
			throw new VariableException();
		}

		variable.setInitialized(true);
	}

	/*
	 * return true if the type matches the value.
	 */
	private static boolean isTypeMatchToValue(String type, String value) {
    	switch (type) {
		    case Variable.INT_TYPE:
		    	return Pattern.compile(Regex.legalValue.get(Variable.INT_TYPE)).matcher(value).matches();
		    case Variable.DOUBLE_TYPE:
			    return Pattern.compile(Regex.legalValue.get(Variable.INT_TYPE)).matcher(value).matches() ||
					    Pattern.compile(Regex.legalValue.get(Variable.DOUBLE_TYPE)).matcher(value).matches();
		    case Variable.STRING_TYPE:
			    return Pattern.compile(Regex.legalValue.get(Variable.STRING_TYPE)).matcher(value).matches();
		    case Variable.CHAR_TYPE:
			    return Pattern.compile(Regex.legalValue.get(Variable.CHAR_TYPE)).matcher(value).matches();
		    case Variable.BOOLEAN_TYPE:
			    return Pattern.compile(Regex.legalValue.get(Variable.INT_TYPE)).matcher(value).matches() ||
					    Pattern.compile(Regex.legalValue.get(Variable.DOUBLE_TYPE)).matcher(value).matches() ||
					    Pattern.compile(Regex.legalValue.get(Variable.BOOLEAN_TYPE)).matcher(value).matches();
	    }
	    return false;
	}

	/*
	 * return true if the two types match.
	 */
	private static boolean typesMatch(String typeRecieve, String typeGive) {
		switch (typeRecieve) {
			case Variable.INT_TYPE:
				return typeGive.equals(Variable.INT_TYPE);
			case Variable.DOUBLE_TYPE:
				return typeGive.equals(Variable.INT_TYPE) || typeGive.equals(Variable.DOUBLE_TYPE);
			case Variable.STRING_TYPE:
				return  typeGive.equals(Variable.STRING_TYPE);
			case Variable.CHAR_TYPE:
				return typeGive.equals(Variable.CHAR_TYPE);
			case Variable.BOOLEAN_TYPE:
				return typeGive.equals(Variable.INT_TYPE) || typeGive.equals(Variable.DOUBLE_TYPE) ||
						typeGive.equals(Variable.BOOLEAN_TYPE);
		}
		return false;
	}
}
