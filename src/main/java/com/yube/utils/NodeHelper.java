package com.yube.utils;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;

public class NodeHelper {

    public static ArrayList<Node> getAllNodesWithType(Parent root, String className) throws ClassNotFoundException {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes, Class.forName(className));
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes, Class clazz) {
        for (Node node : parent.getChildrenUnmodifiable()){
            if(clazz.isInstance(node)) nodes.add(node);
            if (node instanceof Parent) addAllDescendents((Parent) node, nodes, clazz);
        }
    }


}
