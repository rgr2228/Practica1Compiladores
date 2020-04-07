/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer.Logic;

import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Model.Transititon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @authors Raúl Gómez, Alejandro Gallego
 */
public class ToDeterministic {
    private List<Node> nodeList = new ArrayList<Node>();
    private List<State> states = new ArrayList<State>();
    private State errorState = new State(0,0);
    private List<Transititon> transitions = new ArrayList<Transititon>();
    int finalName=1;

    public ToDeterministic() {
        
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
                /*System.out.println("Entra nuevo----");
                for(Node nodePrueba:goToAux.getNodes()){
                    System.out.println(nodePrueba.getName());
                }*/
                for(Node nodeAux: goToAux.getNodes()){
                    if(!goTo.getNodes().contains(nodeAux)){
                        goTo.getNodes().add(nodeAux);
                    }
                }
                //goTo.getNodes().addAll(goToAux.getNodes());
                if(goToAux.getState()==1){
                    goTo.setState(1);
                }
            }
        }
        return goTo;
    }
    
    public List<State> transitionGenerator(State s,List<State> readyStates){
        String inputSymbol= null;
        List<String> readyTerms = new ArrayList<String>();
        for(int i =0;i<s.getNodes().size();i++){
            if((!readyTerms.contains(inputSymbol))&&(s.getNodes().get(i).getLeftLink()!=null)&&(!s.getNodes().get(i).getLeftExpression().equals("!"))){
                State goTo = new State();
                inputSymbol = s.getNodes().get(i).getLeftExpression();
                goTo = this.findSymbol(s, inputSymbol,goTo);
                if(s.getNodes().equals(goTo.getNodes()) && s.getState()==goTo.getState()){
                    transitions.add(new Transititon(s, inputSymbol, s));
                    goTo=s;
                }else{
                    int readyStateBool=0;
                    for(State readyState:readyStates){
                        for(Node readyNode:readyState.getNodes()){
                            System.out.println("Nodo listo:"+readyNode.getName());
                        }
                        for(Node readyNode:goTo.getNodes()){
                            System.out.println("goTo listo:"+readyNode.getName());
                        }
                        if(readyState.getNodes().equals(goTo.getNodes()) && readyState.getState()==goTo.getState()){
                            transitions.add(new Transititon(s, inputSymbol, readyState));
                            readyStateBool=1;
                            goTo=readyState;
                        }
                    }
                    if(readyStateBool==0){
                        finalName=finalName+1;
                        goTo.setName(finalName);
                        transitions.add(new Transititon(s, inputSymbol, goTo));
                    }
                }
                readyTerms.add(inputSymbol);
                readyStates.add(s);
                if(!readyStates.contains(goTo)){
                    readyStates = this.transitionGenerator(goTo,readyStates);
                }
            }
            readyTerms.clear();
        }
        return readyStates;
    }
    
    public void noTransitions(List<State> st,List<String> names){
        List<State> auxStates = new ArrayList<State>();
        for(State forSt:st){
            boolean verificator = false;
            for(Transititon forTra:transitions){
                if(forTra.getState().equals(forSt)){
                    verificator=true;
                }
            }
            if(verificator==false){
                auxStates.add(forSt);
            }            
        }
        if(auxStates.size()!=0){
            for(String name:names){
                for(State ax:auxStates){
                    transitions.add(new Transititon(ax, name, errorState)); 
                }
            }
        }
    }
    
    public void goToError(List<State> st,List<String> names){
        for(String name:names){
            for(State ax:st){
                boolean verificator=false;
                for(Transititon forTra:transitions){
                    if(forTra.getState().equals(ax) && forTra.getInputSymbol().equals(name)){
                        verificator=true;
                    }
                }
                if(verificator==false){
                    transitions.add(new Transititon(ax, name, errorState)); 
                }
            }
        }
    }
}
