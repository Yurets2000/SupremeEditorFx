package com.yube.utils;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.web.HTMLEditor;

public class HTMLEditorHelper {

    public static void hideHTMLEditorToolbars(HTMLEditor editor) {
        editor.setVisible(false);
        Platform.runLater(() -> {
            Node[] nodes = editor.lookupAll(".tool-bar").toArray(new Node[0]);
            for(Node node : nodes) {
                node.setVisible(false);
                node.setManaged(false);
            }
            editor.setVisible(true);
        });
    }
}
