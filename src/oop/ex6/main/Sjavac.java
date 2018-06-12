package oop.ex6.main;

public class Sjavac {

    private static final String SPACE_REGEX = "\\b";
    private static final String NAME_REGEX = "([a-zA-Z]+|(_)+)[\\w]+";
    private static final String TYPE_REGEX = "(int|double|String|char|boolean)";
    private static final String SIGNATURE_REGEX = SPACE_REGEX + "*void" + SPACE_REGEX + "+" + NAME_REGEX + SPACE_REGEX + "*";
    private static final String PARAMETERS_REGEX = "\\((" + NAME_REGEX + ")*\\)" + SPACE_REGEX + "*\\{" + "";

    public static void main(String[] args) {

        System.out.println("1        1".split("    ")[1]);

    }
}




