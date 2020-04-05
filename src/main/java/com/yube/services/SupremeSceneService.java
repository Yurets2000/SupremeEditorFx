package com.yube.services;

import com.yube.custom.SupremeMenuItem;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class SupremeSceneService {

    private SceneService sceneService;

    @Autowired
    public SupremeSceneService(SceneService sceneService){
        this.sceneService = sceneService;
    }

    public List<SupremeMenuItem> getSupremeMenuItems(Scene scene){
        try {
            List<Menu> menus = sceneService.getMenus(scene);
            return menus.stream().flatMap((m) -> m.getItems().stream())
                    .filter(m -> m instanceof SupremeMenuItem).map(m -> (SupremeMenuItem) m).collect(Collectors.toList());
        } catch (Exception e){
            throw new IllegalArgumentException("Scene has no SupremeMenuItem elements", e);
        }
    }
}
