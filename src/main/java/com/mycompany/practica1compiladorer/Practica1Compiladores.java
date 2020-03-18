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
import com.mycompany.practica1compiladorer.Model.Transititon;
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
        prueba.union("0","1");
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
        toDet.transitionGenerator(toDet.getStates().get(0),readyStates);
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
        List<String> auxTerms = new ArrayList<String>();
        List<State> auxStates = new ArrayList<State>();
        for(int z=0;z<toDet.getTransitions().size();z++){
           if(!auxTerms.contains(toDet.getTransitions().get(z).getInputSymbol())){
               auxTerms.add(toDet.getTransitions().get(z).getInputSymbol());
           }
        }
        for(int z=0;z<toDet.getTransitions().size();z++){
           if(!auxStates.contains(toDet.getTransitions().get(z).getState())){
               auxStates.add(toDet.getTransitions().get(z).getState());
           }
           if(!auxStates.contains(toDet.getTransitions().get(z).getGoTo())){
               auxStates.add(toDet.getTransitions().get(z).getGoTo());
           }
        }
        System.out.println("# Transiciones:"+toDet.getTransitions().size());
        toDet.noTransitions(auxStates,auxTerms);
        toDet.goToError(auxStates, auxTerms);
        System.out.println("# States:"+auxStates.size());
        System.out.println("# Términos:"+auxTerms.size());
        System.out.println("# Transiciones:"+toDet.getTransitions().size());
        for(Transititon trans:toDet.getTransitions()){
            System.out.print("Ini:"+trans.getState().getName()+", ");
            System.out.print("Símbolo:"+trans.getInputSymbol()+", ");
            System.out.print("Fin:"+trans.getGoTo().getName()+", ");
            System.out.println("Estado:"+trans.getState().getState());
        }
        String[][] matriz = new String[2+auxTerms.size()][1+auxStates.size()];
        matriz[0][0]="Estado";
        int head =0;
        while(head<auxTerms.size()){
            matriz[head+1][0]=auxTerms.get(head);
            head++;
        }
        matriz[head+1][0]="Aceptación?";
        for(int s =0;s<auxStates.size();s++){
            for(int t =0;t<toDet.getTransitions().size();t++){
                if(toDet.getTransitions().get(t).getState().equals(auxStates.get(s))){
                    matriz[0][s+1]= String.valueOf(toDet.getTransitions().get(t).getState().getName());
                    matriz[1+auxTerms.size()][s+1]= String.valueOf(toDet.getTransitions().get(t).getState().getState());
                    for(int header=1;header<(1+auxTerms.size());header++){
                        if(matriz[header][0].equals(toDet.getTransitions().get(t).getInputSymbol())){
                            matriz[header][s+1]=String.valueOf(toDet.getTransitions().get(t).getGoTo().getName());
                        }
                    }
                }
            }
        }
        String titles[] = new String[(2+auxTerms.size())];
        for(int tlt=0;tlt<(2+auxTerms.size());tlt++){
            titles[tlt]=matriz[tlt][0];
        }
        
        for(int header2=0;header2<(1+auxStates.size());header2++){
            for(int header=0;header<(2+auxTerms.size());header++){
                System.out.println("header "+header2+":"+matriz[header][header2]);
            }
        }
         
       /*FiniteAutomat principal=new FiniteAutomat(matriz,titles);
        principal.setLocationRelativeTo(null);
        principal.setTitle("Autómata");
        principal.setVisible(true);*/
    }
}
