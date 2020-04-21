package com.yube.services;

import com.yube.configuration.models.actions.Action;
import com.yube.configuration.processors.actions.ActionsProcessor;
import com.yube.custom.SupremeMenuItem;
import com.yube.events.CustomActionEvent;
import com.yube.main.StageContainer;
import com.yube.observables.ObservableProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public final class SupremeMenuItemService {

    private ActionsProcessor actionsProcessor;
    private EventService eventService;

    @Autowired
    public SupremeMenuItemService(ActionsProcessor actionsProcessor, EventService eventService) {
        this.actionsProcessor = actionsProcessor;
        this.eventService = eventService;
    }

    public void initMenuItems(List<SupremeMenuItem> items, StageContainer stageContainer) {
        bindMenuItemsToActions(items, stageContainer.getActionsMap());
        bindActionsToMenuItems(items, stageContainer);
    }

    private void bindMenuItemsToActions(List<SupremeMenuItem> items, Map<String, ObservableProperty<Boolean>> actionsMap) {
        for (SupremeMenuItem item : items) {
            String action = item.getAction();
            if (action != null) {
                actionsMap.get(item.getAction()).addObserver((o, arg) -> {
                    item.setDisable(!((ObservableProperty<Boolean>) o).getProperty());
                });
                //item.disableProperty().bind(Bindings.not(actionsMap.get(item.getAction())));
            }
        }
    }

    private void bindActionsToMenuItems(List<SupremeMenuItem> items, StageContainer stageContainer) {
        items.forEach(i -> {
            i.setOnAction(event -> {
                Action action = actionsProcessor.getAction(i.getAction());
                action.getImplementors().forEach(implementor -> {
                    eventService.propagateToTargets(stageContainer, new CustomActionEvent(action, implementor));
                });
            });
        });
    }
}
