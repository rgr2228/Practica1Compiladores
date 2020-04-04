/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Logic;
import com.mycompany.practica1compiladorer.Model.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Thompson {
    private Node firstNode;
    private Node secondNode;

    public Thompson(Node firstNode, Node secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }
    
    public void nullSequence(){
        firstNode.setLeftLink(secondNode);
        firstNode.setLeftExpression("!");
    }
    
    public void setR(String r){
        firstNode.setLeftLink(secondNode);
        firstNode.setLeftExpression(r);
    }
    
    public void concatenation(String r, String s){
        Node nodeS = new Node(s, secondNode, 0);
        Node nodeR = new Node("!", nodeS, 0);
        firstNode.setLeftLink(nodeR);
        firstNode.setLeftExpression(r);
    }
    
    public void union(String r, String s){
        Node nodeRS = new Node("!", secondNode, 0);
        Node secondNodeR = new Node("!", nodeRS, 0);
        Node firstNodeR = new Node(r, secondNodeR, 0);
        Node secondNodeS = new Node("!", nodeRS, 0);
        Node firstNodeS = new Node(s, secondNodeS, 0);
        firstNode.setLeftLink(firstNodeR);
        firstNode.setLeftExpression("!");
        firstNode.setRightLink(firstNodeS);
        firstNode.setRightExpression("!");
    }
    
    public void asterisk(String r){
        Node secondNodeR = new Node("!", null, "!", secondNode, 0);
        Node firstNodeR = new Node(r, secondNodeR, 0);
        secondNodeR.setLeftLink(firstNodeR);
        firstNode.setLeftExpression("!");
        firstNode.setLeftLink(firstNodeR);
        firstNode.setRightExpression("!");
        firstNode.setRightLink(secondNode);
    }
    
    public void sum(String r){
        Node secondNodeR = new Node("!", null, "!", secondNode, 0);
        Node firstNodeR = new Node(r, secondNodeR, 0);
        secondNodeR.setLeftLink(firstNodeR);
        firstNode.setLeftExpression("!");
        firstNode.setLeftLink(firstNodeR);
    }
    
    public void regexOperator(List<String> listS){
        String aux = new String();
        if(listS.size()==1){
            aux= listS.get(0);
            if(aux.substring(aux.length()-1).equals("*")){
               this.asterisk(aux.substring(0,aux.length()-1));
            }
            if(aux.substring(aux.length()-1).equals("+")){
               this.sum(aux.substring(0,aux.length()-1)); 
            }
        }else{
            for(int i =0;i<listS.size()-2;i++){
                aux=aux.concat(listS.get(i));
            }        
            if(listS.get(listS.size()-2).equals("|")){
               this.union(aux,listS.get(listS.size()-1) );
            }
            if(listS.get(listS.size()-2).equals(".")){
                this.concatenation(aux,listS.get(listS.size()-1));
            }
        }
    }
}
