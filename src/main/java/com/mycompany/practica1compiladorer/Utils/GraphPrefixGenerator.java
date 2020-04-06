package com.mycompany.practica1compiladorer.Utils;

import com.mycompany.practica1compiladorer.Logic.Thompson;
import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.Single;

import java.util.List;

public class GraphPrefixGenerator {
    private Thompson thompson;
    private String regularExpression;
    private int index;

    public GraphPrefixGenerator(Thompson thompson, String regularExpression) {
        this.thompson = thompson;
        this.regularExpression = regularExpression;
        this.index = 0;
    }

    public List<Node> generateGraph() {
        char c = regularExpression.charAt(index);
        if ("|+*.".contains(c + "")) {
            if (c == '|') {
                index++;
                List<Node> op1 = generateGraph();
                index++;
                List<Node> op2 = generateGraph();
                return thompson.union(op1, op2);
            } else if (c == '.') {
                index++;
                List<Node> op1 = generateGraph();
                index++;
                List<Node> op2 = generateGraph();
                return thompson.concatenation(op1, op2);
            } else if (c == '*') {
                index++;
                List<Node> op1 = generateGraph();
                return thompson.asterisk(op1);
            } else {
                index++;
                List<Node> op1 = generateGraph();
                return thompson.sum(op1);
            }
        } else {
            return new Single().define(c + "");
        }
    }
}
