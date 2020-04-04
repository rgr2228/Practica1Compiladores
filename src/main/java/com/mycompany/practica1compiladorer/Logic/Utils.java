/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        
package com.mycompany.practica1compiladorer.Logic;
import java.util.Stack;
/**
 *
 * @author alejandro.gallego
 */
public class Utils {

    private static int precedence(char c){
        switch (c){
            case '|':
                return 1;
            case '.':
                return 2;
            case '-':
            case '+':
                return 3;
            case '*':
                return 4;
        }
        return -1;
    }

    public static String infixToPreFix(String expression){

        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(expression);
        input.reverse();
        Stack<Character> stack = new Stack<Character>();

        char [] charsExp = new String(input).toCharArray();
        for (int i = 0; i < charsExp.length; i++) {

            if (charsExp[i] == '(') {
                charsExp[i] = ')';
                i++;
            }
            else if (charsExp[i] == ')') {
                charsExp[i] = '(';
                i++;
            }
        }
        for (int i = 0; i <charsExp.length ; i++) {
            char c = charsExp[i];

            //check if char is operator or operand
            if(precedence(c)>0){
                while(!stack.isEmpty() && precedence(stack.peek())>=precedence(c)){
                    result.append(stack.pop());
                }
                stack.push(c);
            }else if(c==')'){
                char x = stack.pop();
                while(x!='('){
                    result.append(x);
                    x = stack.pop();
                }
            }else if(c=='('){
                stack.push(c);
            }else{
                //character is neither operator nor "("
                result.append(c);
            }
        }

        for (int i = 0; i <=stack.size() ; i++) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

}
