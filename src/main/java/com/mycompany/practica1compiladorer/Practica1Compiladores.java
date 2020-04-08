/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;


import com.mycompany.practica1compiladorer.Logic.ThompsonGraph;
import com.mycompany.practica1compiladorer.Logic.ToDeterministic;
import com.mycompany.practica1compiladorer.Model.State;
import com.mycompany.practica1compiladorer.Utils.StringValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raúl Gómez, Alejandro Gallego
 */
public class Practica1Compiladores {

    public static void main(String[] args) {
        String regularExpression = "(1|2)+(3|4)+";
        String expression = "";

        boolean isValid = validateStringByRegex(regularExpression, expression);

        ThompsonGraph thompsonGraph = new ThompsonGraph(regularExpression);


        ToDeterministic toDet = new ToDeterministic(thompsonGraph);
        toDet.stateGenerator();
        List<State> readyStates = new ArrayList<>();
        toDet.transitionGenerator(toDet.getStates().get(0), readyStates);
        List<String> auxTerms = new ArrayList<>();
        List<State> auxStates = new ArrayList<>();
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

        String titles[] = new String[(2 + auxTerms.size())];
        for (int tlt = 0; tlt < (2 + auxTerms.size()); tlt++) {
            titles[tlt] = matriz[tlt][0];
        }
        FiniteAutomat af = new FiniteAutomat(matriz, titles);
        af.setLocationRelativeTo(null);
        af.setTitle("Autómata Finito");
        af.setVisible(true);
    }

    private static boolean validateStringByRegex(String regularExpression, String expression) {
        StringValidator stringValidator = new StringValidator(regularExpression);
        return stringValidator.validateInput(expression);
    }
}
