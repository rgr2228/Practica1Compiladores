/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Logic;

import java.util.List;
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
    
    public static String runParenthesis(String s){
        Stack<String> stack = new Stack<>();
        String aux=  new String();
        stack.push("(");
        aux= aux.concat(s.substring(0,1));
        //System.out.println(aux);
       for(int i=1;i<s.length();i++){            
            if((s.substring(i,i+1).equals("(")) && (!stack.isEmpty())){
                aux=aux.concat(s.substring(i,i+1));
                stack.push("(");
                //System.out.println("Aquí");
            }
            if((s.substring(i,i+1).equals("(")) && (stack.isEmpty())){
                return aux;
            }
            if((!stack.isEmpty())&&(stack.lastElement().equals("("))&&(s.substring(i,i+1).equals(")"))){
                aux=aux.concat(s.substring(i,i+1));
                stack.pop();
                //System.out.println("Aquí3:" + aux);
            }
            if(!(s.substring(i,i+1).equals("(")) && !(s.substring(i,i+1).equals(")")) && (!stack.isEmpty())){
                aux=aux.concat(s.substring(i,i+1));
                //System.out.println("Aquí4");
            }
        }
        return aux; 
    }
    
    public static List<String> runRegex(String s, List<String> listS){
        int i =0;
        if((s.substring(s.length()-1,s.length()).equals("|")|| s.substring(s.length()-1,s.length()).equals("."))||(s.substring(0,1).equals("|")|| s.substring(0,1).equals("."))){
            System.out.println("Error");
            return listS;
        }
        else{
            while(i<s.length()){
            if((s.substring(i,i+1).equals("|") || s.substring(i,i+1).equals("."))){
                listS.add(s.substring(0,i));
                listS.add(s.substring(i,i+1));
                listS = RowRecognition.runRegex(s.substring(i+1,s.length()), listS);
                return listS;
            }
            if(s.substring(i, i+1).equals("(")){
                String aux= RowRecognition.runParenthesis(s.substring(i,s.length()));
                listS.add(aux);
                i=i+aux.length();
                //System.out.println("i:"+i+", len:"+s.length());
                if(i<s.length()){
                    listS.add(s.substring(i,i+1));
                    listS = RowRecognition.runRegex(s.substring(i+1,s.length()), listS);
                    return listS;
                }else{
                    return listS;
                }
            }
            i++;
        }
        listS.add(s.substring(0,s.length()));
        return listS;
        }
    }
}
