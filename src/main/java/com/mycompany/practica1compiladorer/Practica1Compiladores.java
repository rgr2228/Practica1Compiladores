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
        prueba.setR("0");
        prueba.sum("0+12");
        Thompson prueba2 = new Thompson(prueba.getFirstNode().getLeftLink(), prueba.getFirstNode().getLeftLink().getLeftLink());
        prueba2.concatenation("0", "12");
        Thompson prueba3= new Thompson(prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink(),
                              prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink().getLeftLink());
        prueba3.union("1", "2");
        System.out.println("imprima:"+prueba.getFirstNode().getLeftLink().getLeftLink().getLeftLink().getLeftLink()
        .getLeftLink().getLeftLink().getLeftLink().getLeftLink().getLeftExpression());
    }
    
}
