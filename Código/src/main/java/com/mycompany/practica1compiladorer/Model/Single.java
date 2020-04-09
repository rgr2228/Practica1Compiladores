/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Single {
    List<Node> singleList;
    
    public Single(){
        this.singleList = new ArrayList<Node>();
    }
    
    public List<Node> define(String s){
        Node secondNode = new Node("", null, 0);
        Node firstNode = new Node(s, secondNode, 0);
        singleList.add(firstNode);
        singleList.add(secondNode);
        return singleList;
    }
    
}
