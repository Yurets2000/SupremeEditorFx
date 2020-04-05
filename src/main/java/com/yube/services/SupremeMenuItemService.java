package com.yube.services;

import com.yube.custom.SupremeMenuItem;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupremeMenuItemService {

    public void bindMenuItemsToActions(List<SupremeMenuItem> items, Map<String, BooleanProperty> actionsMap){
        for (SupremeMenuItem item : items){
            String action = item.getAction();
            if(action != null) {
                item.disableProperty().bind(Bindings.not(actionsMap.get(item.getAction())));
            }
        }
    }
}
