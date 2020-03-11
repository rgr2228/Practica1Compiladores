/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

/**
 *
 * @author Dulfary
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
       
        
        /*Prueba * y + 
        Node test = prueba.getFirstNode().getRightLink();
        System.out.println("Imprima:"+test.getName() + "," + test.getState() + "," + test.getRightExpression());
        */
        
        /* prueba concatenación y unión
        Node test = prueba.getFirstNode();
        while(test!=null){
            System.out.println("Lado izquierdo:"+test.getName() + "," + test.getState() + "," + test.getLeftExpression());
            test = test.getLeftLink();
        }
        test = prueba.getFirstNode();
        System.out.println("Lado derecho:"+test.getName() + "," + test.getState() + "," + test.getRightExpression());
        test = test.getRightLink();
        while(test!=null){
            System.out.println("Lado derecho:"+test.getName() + "," + test.getState() + "," + test.getLeftExpression());
            test = test.getLeftLink();
        }*/
        
        /*
        prueba.sum("0+12");
        Thompson prueba2 = new Thompson(prueba.getFirstNode().getLeftLink(), prueba.getFirstNode().getLeftLink().getLeftLink());
        prueba2.concatenation("0", "12");
        Thompson prueba3= new Thompson(prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink(),
                              prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink().getLeftLink());
        prueba3.union("1", "2");
        System.out.println("imprima:"+prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink().getLeftLink()
        .getLeftLink().getLeftLink().getLeftLink().getLeftLink().getLeftExpression());*/
    }
    
}
