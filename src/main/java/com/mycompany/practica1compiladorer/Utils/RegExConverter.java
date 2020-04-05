package com.mycompany.practica1compiladorer.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RegExConverter {
    /**
     * Operators precedence map.
     */
    private static final Map<Character, Integer> precedenceMap;

    static {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 1);
        map.put('|', 2);
        map.put('.', 3); // explicit concatenation operator
        map.put('?', 4);
        map.put('*', 4);
        map.put('+', 4);
        map.put('^', 5);
        precedenceMap = Collections.unmodifiableMap(map);
    }

    ;

    /**
     * Get character precedence.
     *
     * @param c character
     * @return corresponding precedence
     */
    private static Integer getPrecedence(Character c) {
        Integer precedence = precedenceMap.get(c);
        return precedence == null ? 6 : precedence;
    }

    /**
     * Convert regular expression from infix to postfix notation using
     * Shunting-yard algorithm.
     *
     * @param regex infix notation
     * @return postfix notation
     */
    public static String infixToPostfix(String regex) {
        String postfix = new String();

        Stack<Character> stack = new Stack<Character>();

        String formattedRegEx = FormatRegex.formatRegEx(regex);

        for (Character c : formattedRegEx.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;

                case ')':
                    while (!stack.peek().equals('(')) {
                        postfix += stack.pop();
                    }
                    stack.pop();
                    break;

                default:
                    while (stack.size() > 0) {
                        Character peekedChar = stack.peek();

                        Integer peekedCharPrecedence = getPrecedence(peekedChar);
                        Integer currentCharPrecedence = getPrecedence(c);

                        if (peekedCharPrecedence >= currentCharPrecedence) {
                            postfix += stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(c);
                    break;
            }

        }

        while (stack.size() > 0)
            postfix += stack.pop();

        return postfix;
    }
}
