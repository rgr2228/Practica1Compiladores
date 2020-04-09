package com.mycompany.practica1compiladorer.Logic;

import java.util.Arrays;
import java.util.List;

public class FormatRegex {
    /**
     * Transform regular expression by inserting a '.' as explicit concatenation
     * operator.
     */
    public static String formatRegEx(String regex) {
        String res = "";
        String auxRegex = regex.replaceAll("\\s|\\n|\\t", "");
        auxRegex = auxRegex.replaceAll(System.getProperty("line.separator", ""), "");
        List<Character> allOperators = Arrays.asList('|', '?', '+', '*', '^', '.');
        List<Character> binaryOperators = Arrays.asList('^', '|', '.');

        for (int i = 0; i < auxRegex.length(); i++) {
            Character c1 = auxRegex.charAt(i);

            if (i + 1 < auxRegex.length()) {
                Character c2 = auxRegex.charAt(i + 1);

                res += c1;

                if (!c1.equals('(') && !c2.equals(')') && !allOperators.contains(c2) && !binaryOperators.contains(c1)) {
                    res += '.';
                }
            }
        }
        res += auxRegex.charAt(auxRegex.length() - 1);
        return res;
    }

    public static String addParenthesis(String regex, int index) {
        char c = regex.charAt(index);
        if ("|+*.".contains(c + "")) {
            if (c == '|') {
                index = index - 2;
                return "or(" + addParenthesis(regex, index - 1) + ", " + addParenthesis(regex, index - 2) + ")";
//                return "or(" + addParenthesis(regex, auxIndex - 1) + ", " + addParenthesis(regex, auxIndex - 2) + ")";
            } else if (c == '.') {
                index = index - 2;
                String out = "and(" + addParenthesis(regex, index - 1) + ", " + addParenthesis(regex, index - 2) + ")";
                return out;
            } else if (c == '*') {

                index--;
                String out = "ast(" + addParenthesis(regex, index - 1) + ")";
                return out;
            } else {

                index--;
                String out = "plus(" + addParenthesis(regex, index - 1) + ")";
                return out;
            }
        } else {
//            index--;
//            return regex.charAt(index)+"";
            return c + "";
        }
    }
}
