package com.mycompany.practica1compiladorer.Utils;

import java.util.Arrays;
import java.util.List;

public class FormatRegex {
    /**
     * Transform regular expression by inserting a '.' as explicit concatenation
     * operator.
     */
    public static String formatRegEx(String regex) {
        String res = new String();
        List<Character> allOperators = Arrays.asList('|', '?', '+', '*', '^', '.');
        List<Character> binaryOperators = Arrays.asList('^', '|', '.');

        for (int i = 0; i < regex.length(); i++) {
            Character c1 = regex.charAt(i);

            if (i + 1 < regex.length()) {
                Character c2 = regex.charAt(i + 1);

                res += c1;

                if (!c1.equals('(') && !c2.equals(')') && !allOperators.contains(c2) && !binaryOperators.contains(c1)) {
                    res += '.';
                }
            }
        }
        res += regex.charAt(regex.length() - 1);
        return res;
    }

    public static String addParenthesis(String regex, int index) {
        char c = regex.charAt(index);
        if ("|+*.".contains(c + "")) {
            if (c == '|') {
                int auxIndex = index;
                index = index - 2;
                return "or(" + addParenthesis(regex, auxIndex - 1) + ", " + addParenthesis(regex, auxIndex - 2) + ")";
            } else if (c == '.') {
                int auxIndex = index;
                index = index - 2;
                return "and(" + addParenthesis(regex, auxIndex - 1) + ", " + addParenthesis(regex, auxIndex - 2) + ")";
            } else if (c == '*') {
                int auxIndex = index;
                index--;
                return "ast(" + addParenthesis(regex, auxIndex - 1) + ")";
            } else {
                int auxIndex = index;
                index--;
                return "plus(" + addParenthesis(regex, auxIndex - 1) + ")";
            }
        } else {
            return c + "";
        }
    }

}
