package oop.ex6.regex;

import oop.ex6.method.Method;
import oop.ex6.variable.Variable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    // ---------- GENERAL ----------

    public static final String OPTIONAL_SPACE = "\\s*";
    public static final String AT_LEAST_ONE_SPACE = "\\s+";
    public static final String LEGAL_NAME = "([a-zA-Z]+[\\w]*|_+[\\w]+)";
    public static final String OPTIONAL_FINAL = "((final)" + AT_LEAST_ONE_SPACE + ")?";

    public static final String OPEN_BRACKET = "\\(";
    public static final String CLOSE_BRACKET = "\\)";
    public static final String OPEN_SCOPE = "\\{";
    public static final String CLOSE_SCOPE = "\\}";

    public static final String SEMICOLON = ";";
    public static final String EQUALS = "=";

    public static final String COMMENT = "\\/\\/";

    public static final String VOID = "void";
    public static final String RETURN = "return";

    public static final String COMMENT_LINE = COMMENT + ".*";
    public static final String EMPTY_LINE = OPTIONAL_SPACE;

    // ---------- TYPES ----------

    public static final String INT = "(int)";
    public static final String DOUBLE = "(double)";
    public static final String STRING = "(String)";
    public static final String CHAR = "(char)";
    public static final String BOOLEAN = "(boolean)";

    public static final String VARIABLE_TYPE = "(int|double|boolean|String|char)";

    // ---------- LEGAL VALUES ----------

    public static final String LEGAL_INT_VALUE = "\\d+";
    public static final String LEGAL_DOUBLE_VALUE = "\\d+(.(\\d)+)?";
    public static final String LEGAL_CHAR_VALUE = "'.'";
    public static final String LEGAL_STRING_VALUE = "\\\".*\\\"";
    public static final String LEGAL_BOOLEAN_VALUE = "((true|false)|" + LEGAL_DOUBLE_VALUE + ")";
    public static final String LEGAL_VALUE = "(" + LEGAL_INT_VALUE + "|" + LEGAL_DOUBLE_VALUE + "|" +
            LEGAL_CHAR_VALUE + "|" + LEGAL_STRING_VALUE + "|" + LEGAL_BOOLEAN_VALUE + ")";

    public static final HashMap<String, String> legalValue = new HashMap<>();

    static {
        legalValue.put("int", LEGAL_INT_VALUE);
        legalValue.put("double", LEGAL_DOUBLE_VALUE);
        legalValue.put("String", LEGAL_STRING_VALUE);
        legalValue.put("char", LEGAL_CHAR_VALUE);
        legalValue.put("boolean", LEGAL_BOOLEAN_VALUE);
    }


    // ---------- VARIABLE DECLARATION ----------

    public static final String VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String INT_VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + INT + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_INT_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_INT_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String DOUBLE_VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_DOUBLE_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_DOUBLE_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String CHAR_VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_CHAR_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_CHAR_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String STRING_VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_STRING_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_STRING_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String BOOLEAN_VARIABLE_DECLARATION =
            OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE + AT_LEAST_ONE_SPACE +
                    "(((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_BOOLEAN_VALUE + ")" + OPTIONAL_SPACE + "(" + OPTIONAL_SPACE + "," + OPTIONAL_SPACE +
                    "((" + LEGAL_NAME + ")|(" + LEGAL_NAME + ")" + OPTIONAL_SPACE + EQUALS + OPTIONAL_SPACE +
                    LEGAL_BOOLEAN_VALUE + ")" + ")*" + OPTIONAL_SPACE + ")" + SEMICOLON + OPTIONAL_SPACE;

    public static final String VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String INT_VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_INT_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String DOUBLE_VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_DOUBLE_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String STRING_VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_STRING_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String CHAR_VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_CHAR_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String BOOLEAN_VARIABLE_ASSIGNMENT = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + EQUALS +
            OPTIONAL_SPACE + LEGAL_BOOLEAN_VALUE + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;


    // ---------- IF, WHILE ----------

    public static final String IF_WHILE = OPTIONAL_SPACE + "(if|while)" + OPTIONAL_SPACE + OPEN_BRACKET +
            "(" + OPTIONAL_SPACE + "(" + LEGAL_BOOLEAN_VALUE + "|" + LEGAL_NAME + ")" + OPTIONAL_SPACE + "((&&|\\|\\|)" +
            OPTIONAL_SPACE + "(" + LEGAL_BOOLEAN_VALUE + "|" + LEGAL_NAME + ")" + OPTIONAL_SPACE + ")*)" +
            CLOSE_BRACKET + OPTIONAL_SPACE + OPEN_SCOPE + OPTIONAL_SPACE;

    // ---------- METHOD ----------

    public static final String PARAMETER = OPTIONAL_SPACE + OPTIONAL_FINAL + OPTIONAL_SPACE + VARIABLE_TYPE +
            AT_LEAST_ONE_SPACE + "(" + LEGAL_NAME + ")" + OPTIONAL_SPACE;
    public static final String LIST_OF_PARAMETERS = "((" + PARAMETER + ")" + "(," + PARAMETER + ")*)?";
    public static final String VARIABLE_TO_METHOD = "(" + LEGAL_NAME + "|" + LEGAL_VALUE + ")";
    public static final String LIST_OF_VARIABLES = OPTIONAL_SPACE + "((" + VARIABLE_TO_METHOD + ")" +
            OPTIONAL_SPACE + "(," + OPTIONAL_SPACE + VARIABLE_TO_METHOD + OPTIONAL_SPACE + ")*" + ")?" +
            OPTIONAL_SPACE;

    public static final String METHOD = OPTIONAL_SPACE + VOID + AT_LEAST_ONE_SPACE + LEGAL_NAME +
            OPTIONAL_SPACE + OPEN_BRACKET + OPTIONAL_SPACE + LIST_OF_PARAMETERS +
            OPTIONAL_SPACE + CLOSE_BRACKET + OPTIONAL_SPACE + OPEN_SCOPE + OPTIONAL_SPACE;

    public static final String CALL_TO_METHOD = OPTIONAL_SPACE + LEGAL_NAME + OPTIONAL_SPACE + OPEN_BRACKET +
            LIST_OF_VARIABLES + CLOSE_BRACKET + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;

    public static final String RETURN_LINE = OPTIONAL_SPACE + RETURN + OPTIONAL_SPACE + SEMICOLON + OPTIONAL_SPACE;
}








