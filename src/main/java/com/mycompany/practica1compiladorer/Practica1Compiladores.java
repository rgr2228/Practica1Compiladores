/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

import com.mycompany.practica1compiladorer.Logic.RowRecognition;
import com.mycompany.practica1compiladorer.Logic.ToDeterministic;
import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Model.Transititon;
import com.mycompany.practica1compiladorer.Logic.Thompson;
import com.mycompany.practica1compiladorer.Utils.ExpressionConverter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {
    
    public static void main(String[] args) {
        ExpressionConverter ev1 = new ExpressionConverter();

//        String infixExpression = "(a.b|c)*.d";
//        String infixExpression = "((1|0.1)*|1)+";
        String infixExpression = "A|((B*.(C|D)+).((((((A.X)|(X.Y)+)*.E).F)|H+)+.G)*)";
        System.out.println("Infix Expression: " + infixExpression);

        String prefixExpression1 = ev1.infixToPrefix(infixExpression);
        System.out.println("\n**Prefix Expression: " + prefixExpression1);
    }
}
