package com.mycompany.practica1compiladorer.Utils;

public class ExpressionConverter {

    private String prefixExp = "";

    public String infixToPrefix(String infixExp) {
        String formattedRegEx = FormatRegex.formatRegEx(infixExp);
        int infixLegth = formattedRegEx.length();
        Stack stack = new Stack(infixLegth);

        for (infixLegth = infixLegth - 1; infixLegth >= 0; infixLegth--) {
            char ch = formattedRegEx.charAt(infixLegth);
            switch (ch) {
                case '|':
                    gotOperator(ch, 1, ')', stack);
                    break;
                case '.':
                    gotOperator(ch, 2, ')', stack);
                    break;
                case '+':
                case '*':
                    gotOperator(ch, 3, ')', stack);
                    break;
                case ')':
                    stack.push(ch);
                    break;
                case '(':
                    gotParenthesis(')', stack);
                    break;
                default:
                    prefixExp = ch + prefixExp;
                    break;

            }
        }

        while (!stack.isEmpty()) {
            prefixExp = stack.pop() + prefixExp;
        }
        String prefixExpAux = prefixExp;
        prefixExp = "";
        return prefixExpAux;
    }

    private void gotOperator(char opThis, int precedence1, char x, Stack stack) {
        while (!stack.isEmpty()) {
            char opTop = stack.pop();
            if (opTop == x) {
                stack.push(opTop);
                break;
            } else {
                int precedence2;
                if (opTop == '|') {
                    precedence2 = 1;
                } else if (opTop == '.') {
                    precedence2 = 2;
                } else {
                    precedence2 = 3;
                }
                if (precedence2 < precedence1 && x == '(') {
                    stack.push(opTop);
                    break;
                } else if (precedence2 <= precedence1 && x == ')') {
                    stack.push(opTop);
                    break;
                } else {
                    if (x == ')')
                        prefixExp = opTop + prefixExp;
                    else
                        prefixExp = prefixExp + opTop;
                }
            }
        }
        stack.push(opThis);
    }

    private void gotParenthesis(char x, Stack stack) {
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            if (ch == x)
                break;
            else {
                if (x == ')')
                    prefixExp = ch + prefixExp;
                else
                    prefixExp = prefixExp + ch;
            }
        }
    }


}
