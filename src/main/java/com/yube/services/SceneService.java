package com.yube.services;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SceneService {

    public Object getFocusedElement(Scene scene){
        return scene.focusOwnerProperty().getValue();
    }

    public List<Menu> getMenus(Scene scene){
        MenuBar menuBar = (MenuBar) scene.lookup("MenuBar");
        if(menuBar == null) return null;
        return menuBar.getMenus();
    }


}
