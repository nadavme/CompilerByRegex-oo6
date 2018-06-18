package oop.ex6.regex;

import java.util.*;

/**
 * This class holds the regular expressions that the program uses to verify the code correctness.
 */
public class Regex {

    /**************************************Genera ************************************************/

    /** optional space */
    public static final String OPTIONAL_SPACE = "\\s*";
    /** at least one space. */
    public static final String AT_LEAST_ONE_SPACE = "\\s+";
    /** optional final. */
    public static final String OPTIONAL_FINAL = "((final)" + AT_LEAST_ONE_SPACE + ")?";

    /** legal variable name */
    public static final String LEGAL_VARIABLE_NAME = "([a-zA-Z]+[\\w]*|_+[\\w]+)";
    /** legal method name */
    public static final String LEGAL_METHOD_NAME = "([a-zA-Z]+[\\w]*)";

    /** open bracket */
    public static final String OPEN_BRACKET = "\\(";
    /** close bracket */
    public static final String CLOSE_BRACKET = "\\)";
    /** open scope */
    public static final String OPEN_SCOPE = "\\{";
    /** close scope */
    public static final String CLOSE_SCOPE = "\\}";

    /** semicolon */
    public static final String SEMICOLON = ";";
    /** equals */
    public static final String EQUALS = "=";
    /** comment */
    public static final String COMMENT = "\\/\\/";

    /** void */
    public static final String VOID = "void";
    /** return */
    public static final String RETURN = "return";

    /** comment line */
    public static final String COMMENT_LINE = COMMENT + ".*";
    /** empty line */
    public static final String EMPTY_LINE = OPTIONAL_SPACE;
    /** close scope line */
    public static final String CLOSE_SCOPE_LINE = OPTIONAL_SPACE + CLOSE_SCOPE + OPTIONAL_SPACE;

    /**************************************Types ************************************************/

    public static final String INT = "(int)";
    public static final String DOUBLE = "(double)";
    public static final String STRING = "(String)";
    public static final String CHAR = "(char)";
    public static final String BOOLEAN = "(boolean)";

    public static final String VARIABLE_TYPE = "(int|double|boolean|String|char)";

    /**************************************Legal Values ******************************************/

    /** int legal value */
    public static final String LEGAL_INT_VALUE = "-?\\d+";
    /** double legal value */
    public static final String LEGAL_DOUBLE_VALUE = "-?\\d+([.](\\d)+)?";
    /** char legal value */
    public static final String LEGAL_CHAR_VALUE = "'.'";
    /** String legal value */
    public static final String LEGAL_STRING_VALUE = "\\\".*\\\"";
    /** boolean legal value */
    public static final String LEGAL_BOOLEAN_VALUE = "((true|false)|" + LEGAL_DOUBLE_VALUE + ")";
    /** legal value */
    public static final String LEGAL_VALUE = "(" + LEGAL_INT_VALUE + "|" + LEGAL_DOUBLE_VALUE + "|" +
            LEGAL_CHAR_VALUE + "|" + LEGAL_STRING_VALUE + "|" + LEGAL_BOOLEAN_VALUE + "|" + LEGAL_VARIABLE_NAME + ")";

    /** map with the legal values */
    public static final HashMap<String, String> legalValue = new HashMap<>();

    static {
        legalValue.put("int", LEGAL_INT_VALUE);
        legalValue.put("double", LEGAL_DOUBLE_VALUE);
        legalValue.put("String", LEGAL_STRING_VALUE);
        legalValue.put("char", LEGAL_CHAR_VALUE);
        legalValue.put("boolean", LEGAL_BOOLEAN_VALUE);
    }


    /***********************************Variable Declaration *****************************************/

    /** variable declaration */
    public static final String VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_VARIABLE_NAME + ")|(" + LEGAL_VARIABLE_NAME + ")" + OPTIONAL_SPACE +
                    EQUALS + OPTIONAL_SPACE + LEGAL_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," +
                    OPTIONAL_SPACE + "((" + LEGAL_VARIABLE_NAME + ")|(" + LEGAL_VARIABLE_NAME + ")" +
                    OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE + LEGAL_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" +
                    SEMICOLON + OPTIONAL_SPACE;

    /** variable assignment */
    public static final String VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + "(" + LEGAL_VARIABLE_NAME + ")" +
            OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE + "(" + LEGAL_VALUE + ")" + OPTIONAL_SPACE + SEMICOLON +
            OPTIONAL_SPACE;

    /** variable optional assignment */
    public static final String VARIABLE_OPTIONAL_ASSIGNMENT = OPTIONAL_SPACE + "(" + LEGAL_VARIABLE_NAME + ")" +
            OPTIONAL_SPACE + "(" + EQUALS + OPTIONAL_SPACE + "(" + LEGAL_VALUE + "))?" + OPTIONAL_SPACE;


    /**************************************If / While ************************************************/

    /** if or while */
    public static final String IF_WHILE = OPTIONAL_SPACE + "(if|while)" + OPTIONAL_SPACE + OPEN_BRACKET +
            "(" + OPTIONAL_SPACE + "(" + LEGAL_BOOLEAN_VALUE + "|" + LEGAL_VARIABLE_NAME + ")" + OPTIONAL_SPACE +
            "((&&|\\|\\|)" + OPTIONAL_SPACE + "(" + LEGAL_BOOLEAN_VALUE + "|" + LEGAL_VARIABLE_NAME + ")" +
            OPTIONAL_SPACE + ")*)" + CLOSE_BRACKET + OPTIONAL_SPACE + OPEN_SCOPE + OPTIONAL_SPACE;

    /**************************************Method ************************************************/

    /** method parameter */
    public static final String PARAMETER = OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE +
            AT_LEAST_ONE_SPACE + "(" + LEGAL_VARIABLE_NAME + ")" + OPTIONAL_SPACE;
    /** list of parameters */
    public static final String LIST_OF_PARAMETERS = "((" + PARAMETER + ")" + "(," + PARAMETER + ")*)?";
    /** variable sent to method */
    public static final String VARIABLE_TO_METHOD = "(" + LEGAL_VARIABLE_NAME + "|" + LEGAL_VALUE + ")";
    /** list of variables */
    public static final String LIST_OF_VARIABLES = OPTIONAL_SPACE + "((" + VARIABLE_TO_METHOD + ")" +
            OPTIONAL_SPACE + "(," + OPTIONAL_SPACE + VARIABLE_TO_METHOD + OPTIONAL_SPACE + ")*" + ")?" +
            OPTIONAL_SPACE;
    /** method */
    public static final String METHOD = OPTIONAL_SPACE + VOID + AT_LEAST_ONE_SPACE + LEGAL_METHOD_NAME +
            OPTIONAL_SPACE + OPEN_BRACKET + OPTIONAL_SPACE + LIST_OF_PARAMETERS +
            OPTIONAL_SPACE + CLOSE_BRACKET + OPTIONAL_SPACE + OPEN_SCOPE + OPTIONAL_SPACE;
    /** call to method */
    public static final String CALL_TO_METHOD = OPTIONAL_SPACE + LEGAL_METHOD_NAME + OPTIONAL_SPACE + OPEN_BRACKET +
            LIST_OF_VARIABLES + CLOSE_BRACKET + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;
    /** return line */
    public static final String RETURN_LINE = OPTIONAL_SPACE + RETURN + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;
}








