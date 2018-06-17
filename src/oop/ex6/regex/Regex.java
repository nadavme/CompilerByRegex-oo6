package oop.ex6.regex;

/**
 *
 */
public class Regex {// TODO: 6/14/18 check again all this regex patterns. and refactor it as fields, not long and uninformative strings.

    /******************************General*************************************************/

    public static final String ONE_SPACE = "(\\b)+";
    public static final String SPACE = "(\\b)*";
    public static final String OPEN_SCOPE = SPACE + "(\\{)" + SPACE;
    public static final String CLOSE_SCOPE = SPACE + "(\\})" + SPACE;
    //    private static final String CLOSE_SCOPE = "^\\s*}\\s*$";
    public static final String LEGAL_NAME = "(([A-Za-z]+)\\w*)|((_)*(\\\\w)+)";
    //    public static final String LEGAL_NAME = "([a-zA-Z]+(\\w)*|(_)+(\\w)+)";


    /******************************Variable*************************************************/

    public static final String VARIABLE_FULL_DECLERATION = "^\\s*(final )?\\s*((int )|(double )|(Char )|" +
            "(String )|(boolean ))\\s*([A-Za-z0-9_]+)\\s*(;|=\\s*((\\d*)|([A-Za-z0-9_]))\\s*;)\\s*$";
    public static String VARIABLE_BASIC_DECLARATION = "^\\s*(final )?\\s*([A-Za-z]+)\\s*(.*)";
    public static final String ASSIGN = "^\\s*([A-Za-z_]+\\w*)\\s*=\\s*(.*)\\s*;\\s*$";
    public static final String BOOLEAN_VALUE = "true|false|-?\\d+.?\\d+|-?\\d+$";
    public static final String VARIABLE_TYPE = "(int|double|boolean|String|char)";


    /******************************Conditionals*************************************************/

    public static final String IFWHILE = "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{\\s*$";
    public static final String OPEN = SPACE + "(\\()" + SPACE;
    public static final String CLOSE = SPACE + "(\\))" + SPACE;

    /******************************Method*************************************************/

    public static final String RETURN = "^\\s*return\\s*;\\s*$";
    public static final String COMMENT = "^//.*";
    public static final String CALLING_METHOD = "^\\s*([A-Za-z]\\w*)\\s*\\((.*)\\)\\s*;\\s*$";
    public static String METHOD_WITH_PARAMETERS = "\\s*(final )?\\s*([A-Za-z]+)\\s+([A-Za-z_]\\w*)\\s*";
    public static final String NON_PARAMETERS_METHOD = "^\\s*(void)\\s+([A-Za-z]\\w*)\\s*\\((.*)" +
            "\\)\\s*\\{\\s*$";
    public static final String PARAMETER = SPACE + "(final" + ONE_SPACE + ")?" +
            SPACE + VARIABLE_TYPE + ONE_SPACE + LEGAL_NAME + SPACE;// TODO: 6/14/18 fix it, its not right yet.


}










