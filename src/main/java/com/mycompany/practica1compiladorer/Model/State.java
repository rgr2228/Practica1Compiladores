/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Model;

import com.mycompany.practica1compiladorer.Model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class State {
    private int name=0;
    private List<Node> nodes = new ArrayList<Node>();
    private int state=0;

    public State() {
    }
    
    public State(int name,int state){
        this.name=name;
        this.state=state;
    }

    public int getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int getState() {
        return state;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setState(int state) {
        this.state = state;
    }
}
