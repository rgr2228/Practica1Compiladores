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
 * @authors Raúl Gómez, Alejandro Gallego
 */
public class ToDeterministic {
    private Thompson thompson;
    private List<Node> nodeList = new ArrayList<Node>();
    private List<State> states = new ArrayList<State>();
    private List<Transititon> transitions = new ArrayList<Transititon>();

    public ToDeterministic(Thompson thompson) {
        this.thompson = thompson;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<State> getStates() {
        return states;
    }

    public List<Transititon> getTransitions() {
        return transitions;
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
            states.add(state);
        }
    }
    
    public State findStateByName(int name){
        for(int i =0;i<states.size();i++){
            if(states.get(i).getName()==name){
                return states.get(i);
            }
        }
        return null;
    }
    
    public State findSymbol(State s, String inputSymbol,State goTo){
        for(int i =0;i<s.getNodes().size();i++){
            if(s.getNodes().get(i).getLeftExpression().equals(inputSymbol)){
                State goToAux = new State();
                goToAux = this.findStateByName(s.getNodes().get(i).getLeftLink().getName());
                goTo.getNodes().addAll(goToAux.getNodes());
                if(goToAux.getState()==1){
                    goTo.setState(1);
                }
            }
        }
        goTo.setName(Integer.parseInt(inputSymbol));
        return goTo;
    }
    
    public void transitionGenerator(State s){
        String inputSymbol= null;
        List<String> readyTerms = new ArrayList<String>();
        for(int i =0;i<s.getNodes().size();i++){
            if((!readyTerms.contains(inputSymbol))&&(s.getNodes().get(i).getLeftLink()!=null)&&(!s.getNodes().get(i).getLeftExpression().equals("!"))){
                State goTo = new State();
                inputSymbol = s.getNodes().get(i).getLeftExpression();
                goTo = this.findSymbol(s, inputSymbol,goTo);
                transitions.add(new Transititon(s, inputSymbol, goTo));
                readyTerms.add(inputSymbol);
                this.transitionGenerator(goTo);
            }
            readyTerms.clear();
        }
    }
}
