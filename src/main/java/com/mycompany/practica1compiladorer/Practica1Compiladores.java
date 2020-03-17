/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

import com.mycompany.practica1compiladorer.Logic.RowRecognition;
import com.mycompany.practica1compiladorer.Logic.ToDeterministic;
import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Logic.Thompson;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {
    
    public static void main(String[] args) {
        /*Stack<String> stack = new Stack<String>();
        String testString="((((())))))()-";
        boolean counter=RowRecognition.parenthesisCounter(testString);
        System.out.println("counter:"+counter);
        stack = RowRecognition.parenthesisOrder(testString,stack);
        if(!stack.isEmpty()){
            System.out.println("tope:"+stack.lastElement());
            System.out.println("order:false");
        }else{
            System.out.println("order:true");
        }
        //System.out.println("order:"+order);
        boolean finalSeq = RowRecognition.finalSequence(testString);
        System.out.println("final:"+finalSeq);*/
        
        Thompson prueba = new Thompson(new Node("", null, "", null, 0), new Node("", null, "", null, 1));
        prueba.union("0","2");
        Thompson prueba2 = new Thompson(prueba.getFirstNode().getLeftLink(),prueba.getFirstNode().getLeftLink().getLeftLink());
        prueba2.concatenation("0", "1");
        Thompson prueba3 = new Thompson(prueba.getFirstNode().getRightLink(),prueba.getFirstNode().getRightLink().getLeftLink());
        prueba3.concatenation("2", "3");
        ToDeterministic toDet = new ToDeterministic(prueba);
        toDet.setNodeNames(prueba.getFirstNode(), 1);
        toDet.stateGenerator();
        for(int i =0;i<toDet.getStates().size();i++){
            System.out.println("Estados:"+toDet.getStates().get(i).getName() +","+toDet.getStates().get(i).getState());
           for(int j=0;j<toDet.getStates().get(i).getNodes().size();j++){
                System.out.println("Nodos estado "+(i+1)+":"+toDet.getStates().get(i).getNodes().get(j).getName());
            }
        }
        List<State> readyStates = new ArrayList<State>();
        readyStates = toDet.transitionGenerator(toDet.getStates().get(0),readyStates);
        System.out.println("# Transiciones:"+toDet.getTransitions().size());
        System.out.println("# Ready States:"+readyStates.size());
        /*
        for(int k=0;k<toDet.getTransitions().get(0).getState().getNodes().size();k++){
                System.out.println("Transición " +"ini" + "," + toDet.getTransitions().get(0).getState().getState()
                        + ":" + toDet.getTransitions().get(0).getState().getNodes().get(k).getName() );
            }
        for(int i =0;i<toDet.getTransitions().size();i++){
            for(int j=0;j<toDet.getTransitions().get(i).getGoTo().getNodes().size();j++){
                System.out.println("Transición " +i + "," + toDet.getTransitions().get(i).getGoTo().getState()
                        + ":" + toDet.getTransitions().get(i).getGoTo().getNodes().get(j).getName() );
            }
            System.out.println("Llega:"+toDet.getTransitions().get(i).getInputSymbol());
        }
        */
        List<String> readyTerms = new ArrayList<String>();
        for(int z=0;z<toDet.getTransitions().size();z++){
         
        }
        
        /*FiniteAutomat principal=new FiniteAutomat(toDet.getTransitions());
        principal.setLocationRelativeTo(null);
        principal.setTitle("Autómata");
        principal.setVisible(true);*/
    }
}
