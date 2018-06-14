package oop.ex6.main;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import oop.ex6.regex.Regex;

public class Sjavac {

    public static void main(String[] args) {
        System.out.println(Regex.METHOD);
        System.out.println(java.util.regex.Pattern.compile(Regex.METHOD).matcher("void foo() {").matches());

    }
}




