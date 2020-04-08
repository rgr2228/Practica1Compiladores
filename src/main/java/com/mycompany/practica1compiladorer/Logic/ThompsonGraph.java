package com.mycompany.practica1compiladorer.Logic;

import com.mycompany.practica1compiladorer.Model.Node;
import com.mycompany.practica1compiladorer.Utils.ExpressionConverter;
import com.mycompany.practica1compiladorer.Utils.PrefixToThompsonConverter;

import java.util.List;

public class ThompsonGraph {
    private List<Node> nodes;

    public ThompsonGraph(String regularExpression) {
        String prefixExp = new ExpressionConverter().infixToPrefix(regularExpression);
        Thompson thompson = new Thompson();
        PrefixToThompsonConverter graphGenerator = new PrefixToThompsonConverter(thompson, prefixExp);
        nodes = graphGenerator.generateThompsonGraph();
        nodes.get(nodes.size() - 1).setState(1);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
