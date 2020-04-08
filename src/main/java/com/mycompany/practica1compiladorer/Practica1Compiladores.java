/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;


import com.mycompany.practica1compiladorer.Logic.Thompson;
import com.mycompany.practica1compiladorer.Logic.ToDeterministic;
import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Model.Transititon;
import com.mycompany.practica1compiladorer.Utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {

    public static void main(String[] args) {
        ExpressionConverter ev1 = new ExpressionConverter();

        List<String> expressions = new ArrayList<>();
//        expressions.add("ab");
//        expressions.add("bc|a");
//        expressions.add("a|(b.c)");
        expressions.add("(1+01*0)*");
//        expressions.add("(a.b|c)*.d");
//        expressions.add("c.x|a+.b+.c");
//        expressions.add("((1|01)*|1)+");
//        expressions.add("((1|0.1)*|1)+.(1|0.1)*");
//        expressions.add("(GO|GOTO|TOO|ON)*ON.TOO");
//        expressions.add("A|((B*.(C|D)+).((((((A.X)|(X.Y)+)*.E).F)|H+)+.G)*)");
/*
        for (String expression : expressions) {
            String postFixExp = RegExConverter.infixToPostfix(expression);
            Thompson thompson = new Thompson();
            GraphGenerator graphGenerator = new GraphGenerator(thompson, postFixExp);
            List<Node> graph = graphGenerator.generateGraph();
            System.out.println("Infix Expression: " + expression
                            + "\nPostfix Expression: " + postFixExp
//                    + "\nOrder postfix Expression: " + aux + "\n"
            );
            String a = "";
            graph.get(graph.size() - 1).setState(1);
            ToDeterministic toDet = new ToDeterministic();
            toDet.setNodeNames(graph.get(0), 1);
            toDet.stateGenerator();
            List<State> readyStates = new ArrayList<State>();
            toDet.transitionGenerator(toDet.getStates().get(0), readyStates);
            List<String> auxTerms = new ArrayList<String>();
            List<State> auxStates = new ArrayList<State>();
            for (int z = 0; z < toDet.getTransitions().size(); z++) {
                if (!auxTerms.contains(toDet.getTransitions().get(z).getInputSymbol())) {
                    auxTerms.add(toDet.getTransitions().get(z).getInputSymbol());
                }
            }
            for (int z = 0; z < toDet.getTransitions().size(); z++) {
                if (!auxStates.contains(toDet.getTransitions().get(z).getState())) {
                    auxStates.add(toDet.getTransitions().get(z).getState());
                }
                if (!auxStates.contains(toDet.getTransitions().get(z).getGoTo())) {
                    auxStates.add(toDet.getTransitions().get(z).getGoTo());
                }
            }
            toDet.noTransitions(auxStates, auxTerms);
            toDet.goToError(auxStates, auxTerms);
            String[][] matriz = new String[1 + auxStates.size()][2 + auxTerms.size()];
            matriz[0][0] = "Estado";
            int head = 0;
            while (head < auxTerms.size()) {
                matriz[0][head + 1] = auxTerms.get(head);
                head++;
            }
            matriz[0][head + 1] = "Aceptación?";
            for (int s = 0; s < auxStates.size(); s++) {
                for (int t = 0; t < toDet.getTransitions().size(); t++) {
                    if (toDet.getTransitions().get(t).getState().equals(auxStates.get(s))) {
                        matriz[s + 1][0] = String.valueOf(toDet.getTransitions().get(t).getState().getName());
                        matriz[s + 1][1 + auxTerms.size()] = String.valueOf(toDet.getTransitions().get(t).getState().getState());
                        for (int header = 1; header < (1 + auxTerms.size()); header++) {
                            if (matriz[0][header].equals(toDet.getTransitions().get(t).getInputSymbol())) {
                                matriz[s + 1][header] = String.valueOf(toDet.getTransitions().get(t).getGoTo().getName());
                            }
                        }
                    }
                }
            }

            arrayReverse(matriz[0], matriz[0].length - 1, 0);
            String aux = matriz[0][0];
            matriz[0][0] = matriz[0][matriz[0].length - 1];
            matriz[0][matriz[0].length - 1] = aux;


            String titles[] = new String[(2 + auxTerms.size())];
            for (int tlt = 0; tlt < (2 + auxTerms.size()); tlt++) {
                titles[tlt] = matriz[tlt][0];
            }


            FiniteAutomat af = new FiniteAutomat(matriz, titles);
            af.setLocationRelativeTo(null);
            af.setTitle("Autómata Finito");
            af.setVisible(true);
        }*/
        
        
        /*Stack<String> stack = new Stack<String>();
        String testString="-";

        Stack<String> stack = new Stack<String>();
        String testString="(a|(b.c))|d*-";
        boolean counter=RowRecognition.parenthesisCounter(testString);
        System.out.println("counter:"+counter);
        stack = RowRecognition.parenthesisOrder(testString,stack);
        if(!stack.isEmpty()){
            System.out.println("tope:"+stack.lastElement());
            System.out.println("order:false");
        }else{
            System.out.println("order:true");
        }
        String finalSeq = RowRecognition.finalSequence(testString);
        System.out.println("final:"+finalSeq);
        boolean testBool =RowRecognition.noSpaces(testString);
        System.out.println("Espacios:"+testBool);
        List<String> listS = new ArrayList<String>();
        listS=RowRecognition.runRegex(finalSeq, listS);
        System.out.println("Saleeee");
        for(String s : listS){
            System.out.println("Dato:"+s);
        }
        System.out.println("Nuevo nivel");
        //PARTE DE PRUEBA
        RowRecognition.runRegexLevel(listS);
*/

        //String expression="(0.1)+";
        String expression = "(01)+|2";
        expression = FormatRegex.formatRegEx(expression);
        RegexValidator regexValidator = new RegexValidator(expression);
        boolean isValid = regexValidator.validateInput("000001");
        boolean isValid2 = regexValidator.validateInput("2");
        //String postFixExp = RegExConverter.infixToPostfix(expression);
        String prefixExp = new ExpressionConverter().infixToPrefix(expression);
        Thompson thompson = new Thompson();
//            GraphGenerator graphGenerator = new GraphGenerator(thompson, postFixExp);
        GraphPrefixGenerator graphGenerator = new GraphPrefixGenerator(thompson, prefixExp);
        List<Node> graph = graphGenerator.generateGraph();
        System.out.println("Infix Expression: " + expression
//                        + "\nPostfix Expression: " + postFixExp
                        + "\nPrefix Expression: " + prefixExp
//                    + "\nOrder postfix Expression: " + aux + "\n"
        );
        graph.get(graph.size() - 1).setState(1);

        ToDeterministic toDet = new ToDeterministic();
        toDet.setNodeNames(graph.get(0), 1);
        toDet.stateGenerator();
        for (int i = 0; i < toDet.getStates().size(); i++) {
            System.out.println("Estados:" + toDet.getStates().get(i).getName() + "," + toDet.getStates().get(i).getState());
            for (int j = 0; j < toDet.getStates().get(i).getNodes().size(); j++) {
                System.out.println("Nodos estado " + (i + 1) + ":" + toDet.getStates().get(i).getNodes().get(j).getName());
            }
        }
        List<State> readyStates = new ArrayList<State>();
        toDet.transitionGenerator(toDet.getStates().get(0), readyStates);
        for (int k = 0; k < toDet.getTransitions().get(0).getState().getNodes().size(); k++) {
            System.out.println("Transición " + "ini" + "," + toDet.getTransitions().get(0).getState().getState()
                    + ":" + toDet.getTransitions().get(0).getState().getNodes().get(k).getName());
        }
        for (int i = 0; i < toDet.getTransitions().size(); i++) {
            for (int j = 0; j < toDet.getTransitions().get(i).getGoTo().getNodes().size(); j++) {
                System.out.println("Transición " + i + "," + toDet.getTransitions().get(i).getGoTo().getState()
                        + ":" + toDet.getTransitions().get(i).getGoTo().getNodes().get(j).getName());
            }
            System.out.println("Llega:" + toDet.getTransitions().get(i).getInputSymbol());
        }
        List<String> auxTerms = new ArrayList<String>();
        List<State> auxStates = new ArrayList<State>();
        for (int z = 0; z < toDet.getTransitions().size(); z++) {
            if (!auxTerms.contains(toDet.getTransitions().get(z).getInputSymbol())) {
                auxTerms.add(toDet.getTransitions().get(z).getInputSymbol());
            }
        }
        for (int z = 0; z < toDet.getTransitions().size(); z++) {
            if (!auxStates.contains(toDet.getTransitions().get(z).getState())) {
                auxStates.add(toDet.getTransitions().get(z).getState());
            }
            if (!auxStates.contains(toDet.getTransitions().get(z).getGoTo())) {
                auxStates.add(toDet.getTransitions().get(z).getGoTo());
            }
        }

        System.out.println("# Transiciones:" + toDet.getTransitions().size());

        toDet.noTransitions(auxStates, auxTerms);
        toDet.goToError(auxStates, auxTerms);

        System.out.println("# States:" + auxStates.size());
        System.out.println("# Términos:" + auxTerms.size());
        System.out.println("# Transiciones:" + toDet.getTransitions().size());
        for (Transititon trans : toDet.getTransitions()) {
            System.out.print("Ini:" + trans.getState().getName() + ", ");
            System.out.print("Símbolo:" + trans.getInputSymbol() + ", ");
            System.out.print("Fin:" + trans.getGoTo().getName() + ", ");
            System.out.println("Estado:" + trans.getState().getState());
        }

        String[][] matriz = new String[1 + auxStates.size()][2 + auxTerms.size()];
        matriz[0][0] = "Estado";
        int head = 0;
        while (head < auxTerms.size()) {
            matriz[0][head + 1] = auxTerms.get(head);
            head++;
        }
        matriz[0][head + 1] = "Aceptación?";
        for (int s = 0; s < auxStates.size(); s++) {
            for (int t = 0; t < toDet.getTransitions().size(); t++) {
                if (toDet.getTransitions().get(t).getState().equals(auxStates.get(s))) {
                    matriz[s + 1][0] = String.valueOf(toDet.getTransitions().get(t).getState().getName());
                    matriz[s + 1][1 + auxTerms.size()] = String.valueOf(toDet.getTransitions().get(t).getState().getState());
                    for (int header = 1; header < (1 + auxTerms.size()); header++) {
                        if (matriz[0][header].equals(toDet.getTransitions().get(t).getInputSymbol())) {
                            matriz[s + 1][header] = String.valueOf(toDet.getTransitions().get(t).getGoTo().getName());
                        }
                    }
                }
            }
        }


        for (int header2 = 0; header2 < (1 + auxStates.size()); header2++) {
            for (int header = 0; header < (2 + auxTerms.size()); header++) {
                System.out.println("header " + header2 + ":" + matriz[header2][header]);
            }
        }
/*
        arrayReverse(matriz[0], matriz[0].length - 1, 0);
        String aux = matriz[0][0];
        matriz[0][0] = matriz[0][matriz[0].length - 1];
        matriz[0][matriz[0].length - 1] = aux;
*/

        String titles[] = new String[(2 + auxTerms.size())];
        for (int tlt = 0; tlt < (2 + auxTerms.size()); tlt++) {
            titles[tlt] = matriz[tlt][0];
        }

        FiniteAutomat af = new FiniteAutomat(matriz, titles);
        af.setLocationRelativeTo(null);
        af.setTitle("Autómata Finito");
        af.setVisible(true);

    }

    private static void arrayReverse(String[] array, int indice, int pos) {
        if (indice > pos) { // change
            String tmp = array[pos];
            array[pos] = array[indice];
            array[indice] = tmp;
            arrayReverse(array, indice - 1, pos + 1);
        }
    }

}
