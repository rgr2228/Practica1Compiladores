/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;


import com.mycompany.practica1compiladorer.Logic.StringValidator;
import com.mycompany.practica1compiladorer.Logic.ThompsonGraph;
import com.mycompany.practica1compiladorer.Logic.ThompsonToDeterministicConverter;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Model.Transititon;
import com.mycompany.practica1compiladorer.View.MainMenu;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setTitle("Menú principal");
        mainMenu.setVisible(true);
        /*String regularExpression = "(0.1)+.";
        String expression = "";

        boolean isValid = validateStringByRegex(regularExpression, expression);

        ThompsonGraph thompsonGraph = new ThompsonGraph(regularExpression);

        Triplet<List<String>,List<State>, List<Transititon>> afdElements = getAFDElements(thompsonGraph);
        Pair<String[],String[][]> matrixAFDElements = getAFDMatrixElements(afdElements);

        FiniteAutomat af = new FiniteAutomat(matrixAFDElements);
        af.setLocationRelativeTo(null);
        af.setTitle("Autómata Finito");
        af.setVisible(true);*/
    }

    public static Pair<String[], String[][]> getAFDMatrixElements(Triplet<List<String>, List<State>, List<Transititon>> afdElements) {
        List<String> terms = afdElements.getValue0();
        List<State> states = afdElements.getValue1();
        List<Transititon> transititons = afdElements.getValue2();

        String[][] matriz = new String[1 + states.size()][2 + terms.size()];
        matriz[0][0] = "Estado";
        int head = 0;
        while (head < terms.size()) {
            matriz[0][head + 1] = terms.get(head);
            head++;
        }
        matriz[0][head + 1] = "Aceptación?";
        for (int s = 0; s < states.size(); s++) {
            for (Transititon transititon : transititons) {
                if (transititon.getState().equals(states.get(s))) {
                    matriz[s + 1][0] = String.valueOf(transititon.getState().getName());
                    matriz[s + 1][1 + terms.size()] = String.valueOf(transititon.getState().getState());
                    for (int header = 1; header < (1 + terms.size()); header++) {
                        if (matriz[0][header].equals(transititon.getInputSymbol())) {
                            matriz[s + 1][header] = String.valueOf(transititon.getGoTo().getName());
                        }
                    }
                }
            }
        }

        String[] titles = new String[(2 + terms.size())];
        for (int tlt = 0; tlt < (2 + terms.size()); tlt++) {
            titles[tlt] = matriz[tlt][0];
        }

        return new Pair<>(titles,matriz);
    }

    public static Triplet<List<String>, List<State>, List<Transititon>> getAFDElements(ThompsonGraph thompsonGraph) {
        ThompsonToDeterministicConverter toDeterministic = new ThompsonToDeterministicConverter(thompsonGraph);
        toDeterministic.stateGenerator();
        List<State> readyStates = new ArrayList<>();
        toDeterministic.transitionGenerator(toDeterministic.getStates().get(0), readyStates);
        List<String> auxTerms = new ArrayList<>();
        List<State> auxStates = new ArrayList<>();
        for (int z = 0; z < toDeterministic.getTransitions().size(); z++) {
            if (!auxTerms.contains(toDeterministic.getTransitions().get(z).getInputSymbol())) {
                auxTerms.add(toDeterministic.getTransitions().get(z).getInputSymbol());
            }
        }
        for (int z = 0; z < toDeterministic.getTransitions().size(); z++) {
            if (!auxStates.contains(toDeterministic.getTransitions().get(z).getState())) {
                auxStates.add(toDeterministic.getTransitions().get(z).getState());
            }
            if (!auxStates.contains(toDeterministic.getTransitions().get(z).getGoTo())) {
                auxStates.add(toDeterministic.getTransitions().get(z).getGoTo());
            }
        }
        toDeterministic.noTransitions(auxStates, auxTerms);
        toDeterministic.goToError(auxStates, auxTerms);

        return new Triplet<>(auxTerms,auxStates,toDeterministic.getTransitions());
    }

    public static boolean validateStringByRegex(String regularExpression, String expression) {
        StringValidator stringValidator = new StringValidator(regularExpression);
        return stringValidator.validateInput(expression);
    }
}
