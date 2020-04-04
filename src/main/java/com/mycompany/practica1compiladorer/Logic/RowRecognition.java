/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Logic;

import com.mycompany.practica1compiladorer.Model.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 * ! secuencia nula
 * - fin de secuencia
 */
public class RowRecognition {
    
    public static String finalSequence(String s){
        for(int i =0;i<s.length();i++){
            if(s.substring(i,i+1).equals("-")){
                System.out.println("final sequence:"+s.substring(0,i));
                return s.substring(0,i);
            }
        }
        return null;
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
    
    public static boolean noSpaces(String s){
        boolean spaces = false;
        for(int i=0;i<s.length();i++){            
            if(s.substring(i,i+1).isBlank()){
                spaces=true;
            }
            
        }
        return spaces;
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
            if((stack.isEmpty()) && ((s.substring(i,i+1).equals("*")) || (s.substring(i,i+1).equals("+")))){
                aux=aux.concat(s.substring(i,i+1));
                return aux;
            }
            if((stack.isEmpty())){
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
            System.out.println("Secuencia inválida1");
            listS.clear();
            return listS;
        }
        while(i<s.length()){
            if((s.substring(i,i+1).equals("|") || s.substring(i,i+1).equals("."))){
                listS.add(s.substring(0,i));
                listS.add(s.substring(i,i+1));
                listS = RowRecognition.runRegex(s.substring(i+1,s.length()), listS);
                return listS;
            }
            if(s.substring(i,i+1).equals("(")){
                if(i!=0 && !s.substring(i-1,i).equals(null)){
                    System.out.println("Secuencia inválida 2");
                    listS.clear();
                    return listS;
                }
                String aux= RowRecognition.runParenthesis(s.substring(i,s.length()));
                listS.add(aux);
                i=i+aux.length();
                if(i<s.length()){
                    if(s.substring(i,i+1).equals("|") || s.substring(i,i+1).equals(".")){
                        listS.add(s.substring(i,i+1));
                        listS = RowRecognition.runRegex(s.substring(i+1,s.length()), listS);
                        return listS;
                    }else{
                        System.out.println("Secuencia inválida 3");
                        listS.clear();
                        return listS;
                    }
                }else{
                    return listS;
                }
            }
            i++;
        }
        listS.add(s.substring(0,s.length()));
        return listS;
    }
    
    public static void runRegexLevel(List<String> listS){
        System.out.println("Nuevo nivel interno");
        if(listS.size()==1){
            if(listS.get(0).substring(0,1).equals("(") && listS.get(0).substring(listS.get(0).length()-1).equals(")")){
                String aux2= listS.get(0).substring(1,listS.get(0).length()-1);
                listS.clear();
                listS.add(aux2);
            }
            if((listS.get(0).substring(listS.get(0).length()-1).equals("*") || listS.get(0).substring(listS.get(0).length()-1).equals("+"))
                    && listS.get(0).length()>1){
                String aux = listS.get(0);
                listS.clear();
                listS.add(aux.substring(0,aux.length()-1));
                listS.add(aux.substring(aux.length()-1));
                System.out.println("¿Qué agregué?:"+aux.substring(0,aux.length()-1)+","+aux.substring(aux.length()-1)+","+listS.size());
                RowRecognition.runRegexLevel(listS);
            }else{
                System.out.println("Fondo:"+listS.get(0));
            }
        }else{
            for(String prueba : listS){
                
            }
            for(String prueba : listS){
                System.out.println("Prueba:"+prueba);
                if(!(prueba.equals(".")) && !(prueba.equals("|"))){
                    List<String> pruebas = new ArrayList<String>();
                    if(prueba.substring(0,1).equals("(") && prueba.substring(prueba.length()-1).equals(")")){
                        prueba = prueba.substring(1, prueba.length()-1);
                        //System.out.println("Prueba sin paréntesis:"+prueba);
                    }
                    pruebas = RowRecognition.runRegex(prueba, pruebas);
                    RowRecognition.runRegexLevel(pruebas);
                }
                System.out.println("Nuevo dato mismo nivel");
            }
        }
    }
}
