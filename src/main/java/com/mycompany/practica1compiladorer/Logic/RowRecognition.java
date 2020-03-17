/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Logic;

import java.util.Stack;
/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 * ! secuencia nula
 * - fin de secuencia
 */
public class RowRecognition {
    
    public static boolean finalSequence(String s){
        for(int i =0;i<s.length();i++){
            if(s.substring(i,i+1).equals("-")){
                return true;
            }
        }
        return false;
    }

    public static boolean parenthesisCounter(String s){
        int opened=0;
        int closed=0;
        for(int i =0;i<s.length();i++){
            if(s.substring(i,i+1).equals("(")){
                opened++;
            }
            if(s.substring(i,i+1).equals(")")){
                closed++;
            }
        }
        System.out.println("opened:"+opened +", closed:"+closed);
        if(opened==closed){
            return true;
        }else{
            return false;
        }
    }
    
    public static Stack<String> parenthesisOrder(String s , Stack<String> stack){
        for(int i=1;i<=s.length();i++){            
            if(s.substring(i-1,i).equals("(")){
               stack.push("(");
            }
            if(stack.isEmpty()&&(s.substring(i-1,i).equals(")"))){
                stack.push("e");
            }
            if((!stack.isEmpty())&&(stack.lastElement().equals("("))&&(s.substring(i-1,i).equals(")"))){
                stack.pop();
            }
        }
        return stack;
    }
}
