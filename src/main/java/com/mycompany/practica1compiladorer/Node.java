/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Node {
    private int name=0;
    private String LeftExpression;
    private Node LeftLink;
    private String RightExpression;
    private Node RightLink;
    private int state;

    public Node(String LeftExpression, Node LeftLink, String RightExpression, Node RightLink, int state) {
        this.LeftExpression = LeftExpression;
        this.LeftLink = LeftLink;
        this.RightExpression = RightExpression;
        this.RightLink = RightLink;
        this.state = state;
    }

    public Node(String LeftExpression, Node LeftLink, int state) {
        this.LeftExpression = LeftExpression;
        this.LeftLink = LeftLink;
        this.state = state;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    
    public String getLeftExpression() {
        return LeftExpression;
    }

    public Node getLeftLink() {
        return LeftLink;
    }

    public String getRightExpression() {
        return RightExpression;
    }

    public Node getRightLink() {
        return RightLink;
    }

    public int getState() {
        return state;
    }

    public void setLeftExpression(String LeftExpression) {
        this.LeftExpression = LeftExpression;
    }

    public void setLeftLink(Node LeftLink) {
        this.LeftLink = LeftLink;
    }

    public void setRightExpression(String RightExpression) {
        this.RightExpression = RightExpression;
    }

    public void setRightLink(Node RightLink) {
        this.RightLink = RightLink;
    }

    public void setState(int state) {
        this.state = state;
    }
    
}
