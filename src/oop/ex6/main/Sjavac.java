package oop.ex6.main;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import oop.ex6.regex.Regex;

/**
 *
 */
public class Sjavac {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Regex.NON_PARAMETERS_METHOD);
        System.out.println(java.util.regex.Pattern.compile(Regex.NON_PARAMETERS_METHOD).matcher("void foo() {").matches());

    }
}




