/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {
    
    public static void main(String[] args) {
        Thompson prueba = new Thompson(new Node("", null, "", null, 0), new Node("", null, "", null, 1));
        prueba.union("0","1");
        ToDeterministic toDet = new ToDeterministic(prueba);
        toDet.setNodeNames(prueba.getFirstNode(), 1);
        /*for(int i =0;i<toDet.getNodeList().size();i++){
            System.out.println("Nodos:" + toDet.getNodeList().get(i).getName() + "," + toDet.getNodeList().get(i).getState());
        }*/
        toDet.stateGenerator();
        for(int i =0;i<toDet.getStates().size();i++){
            System.out.println("Estados:"+toDet.getStates().get(i).getName() +","+toDet.getStates().get(i).getState());
           for(int j=0;j<toDet.getStates().get(i).getNodes().size();j++){
                System.out.println("Nodos estado "+(i+1)+":"+toDet.getStates().get(i).getNodes().get(j).getName());
            }
        }
        List<State> readyStates = new ArrayList<State>();
        toDet.transitionGenerator(toDet.getStates().get(0),readyStates);
        System.out.println(toDet.getTransitions().size());
        for(int k=0;k<toDet.getTransitions().get(0).getState().getNodes().size();k++){
                System.out.println("Transición " +"ini" + "," + toDet.getTransitions().get(0).getState().getState()
                        + ":" + toDet.getTransitions().get(0).getState().getNodes().get(k).getName() );
            }
        for(int i =0;i<toDet.getTransitions().size();i++){
            for(int j=0;j<toDet.getTransitions().get(i).getGoTo().getNodes().size();j++){
                System.out.println("Transición " +i + "," + toDet.getTransitions().get(i).getGoTo().getState()
                        + ":" + toDet.getTransitions().get(i).getGoTo().getNodes().get(j).getName() );
            }
        }
    }
}
