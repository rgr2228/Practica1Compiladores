package com.mycompany.practica1compiladorer.Utils;

import com.mycompany.practica1compiladorer.Logic.Thompson;
import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Model.Single;

import java.util.List;

public class ThompsonConstructor {
    private Thompson thompson;
    private String regularExpression;
    private int index;

    public ThompsonConstructor(Thompson thompson, String regularExpression) {
        this.thompson = thompson;
        this.regularExpression = regularExpression;
        this.index = regularExpression.length() - 1;
    }

    public List<Node> addParenthesis() {
        char c = regularExpression.charAt(index);
        if ("|+*.".contains(c + "")) {
            if (c == '|') {
                index--;
                List<Node> op1 = addParenthesis();
                index--;
                List<Node> op2 = addParenthesis();
                return thompson.union(op1, op2);
            } else if (c == '.') {
                index--;
                List<Node> op1 = addParenthesis();
                index--;
                List<Node> op2 = addParenthesis();
                return thompson.concatenation(op1, op2);
            } else if (c == '*') {
                index--;
                List<Node> op1 = addParenthesis();
                return thompson.asterisk(op1);
            } else {
                index--;
                List<Node> op1 = addParenthesis();
                return thompson.sum(op1);
            }
        } else {
            return new Single().define(c + "");
        }
    }

}
