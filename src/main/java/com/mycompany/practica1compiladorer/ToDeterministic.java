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
 * @author Dulfary
 */
public class ToDeterministic {
    private Thompson thompson;
    private List<Node> nodeList = new ArrayList<Node>();
    private List<State> states = new ArrayList<State>();

    public ToDeterministic(Thompson thompson) {
        this.thompson = thompson;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<State> getStates() {
        return states;
    }
    
    public int setNodeNames(Node n,int futureName){
        if(n.getName()==0){
            n.setName(futureName);
            nodeList.add(n);
            if(n.getLeftLink()!=null){
                futureName++;
                futureName = this.setNodeNames(n.getLeftLink(),futureName);
            }
            if(n.getRightLink()!=null){
                futureName++;
                futureName = this.setNodeNames(n.getRightLink(), futureName);
            }
        }else{
            futureName--;
        }
        return futureName;
    }
    
    public State findNull(State s,Node n){
        if(!s.getNodes().contains(n)){
            s.getNodes().add(n);
            if(n.getState()==1){
                s.setState(1);
            }
            if((n.getLeftLink()!= null)&&(n.getLeftExpression().equals("!"))){
                s = this.findNull(s, n.getLeftLink());
            }
            if((n.getRightLink()!= null)&&(n.getRightExpression().equals("!"))){
                s = this.findNull(s, n.getRightLink());
            }
        }
        return s;
    }
    
    public void stateGenerator(){
        for(int i=0;i<nodeList.size();i++){
            State state = new State();
            state = this.findNull(state, nodeList.get(i));
            state.setName(i+1);
            /*
            for(int j = 0;j<state.getNodes().size();j++){
                System.out.println("Estado"+(i+1)+":"+state.getNodes().get(j).getName());
            }
            System.out.println("Estado#:"+state.getName()+","+state.getState());*/
            states.add(state);
        }
    }
    
    
}
