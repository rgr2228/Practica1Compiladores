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
    
    public void concatenation(List<Node> rs,List<Node> ss){
        rs.get(rs.size()-1).setLeftLink(ss.get(0));
        rs.get(rs.size()-1).setLeftExpression("!");
        rs.add(ss.get(ss.size()-1));
    }
    
    public void union(List<Node> rs,List<Node> ss){
        Node firstN = new Node("!", rs.get(0), 0);
        firstN.setRightLink(ss.get(0));
        firstN.setRightExpression("!");
        Node lastN = new Node("", null, 0);
        Node preLastN = new Node("!", lastN, 0);
        rs.get(rs.size()-1).setLeftLink(preLastN);
        rs.get(rs.size()-1).setLeftExpression("!");
        ss.get(ss.size()-1).setLeftLink(preLastN);
        ss.get(ss.size()-1).setLeftExpression("!");
        rs.add(lastN);
        rs.set(0, firstN);
    }
    
    public void asterisk(List<Node> rs){
        Node firstN = new Node("!", rs.get(0), 0);
        Node lastN = new Node("", null, 0);
        firstN.setRightLink(lastN);
        firstN.setRightExpression("!");
        rs.get(rs.size()-1).setLeftLink(rs.get(0));
        rs.get(rs.size()-1).setLeftExpression("!");
        rs.get(rs.size()-1).setRightLink(lastN);
        rs.get(rs.size()-1).setRightExpression("!");
        rs.add(lastN);
        rs.set(0, firstN);
    }
    
    public void sum(List<Node> rs){
        Node firstN = new Node("!", rs.get(0), 0);
        Node lastN = new Node("", null, 0);
        rs.get(rs.size()-1).setLeftLink(rs.get(0));
        rs.get(rs.size()-1).setLeftExpression("!");
        rs.get(rs.size()-1).setRightLink(lastN);
        rs.get(rs.size()-1).setRightExpression("!");
        rs.add(lastN);
        rs.set(0, firstN);
    }
    
    /*public void regexOperator(List<String> listS){
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
    }*/
}
