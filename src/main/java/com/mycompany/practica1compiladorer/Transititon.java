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
public class Transititon {
    private State state;
    private String inputSymbol;
    private State goTo;

    public Transititon() {
    }

    public Transititon(State state, String inputSymbol, State goTo) {
        this.state = state;
        this.inputSymbol = inputSymbol;
        this.goTo = goTo;
    }

    public State getState() {
        return state;
    }

    public String getInputSymbol() {
        return inputSymbol;
    }

    public State getGoTo() {
        return goTo;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setInputSymbol(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    public void setGoTo(State goTo) {
        this.goTo = goTo;
    }
}
