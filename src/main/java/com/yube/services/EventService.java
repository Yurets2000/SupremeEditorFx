package com.yube.services;

import com.yube.configuration.processors.actions.ActionsProcessor;
import com.yube.events.CustomActionEvent;
import com.yube.main.StageContainer;
import com.yube.utils.NodeHelper;
import javafx.event.Event;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private ActionsProcessor actionsProcessor;

    @Autowired
    public EventService(ActionsProcessor actionsProcessor){
        this.actionsProcessor = actionsProcessor;
    }

    public void propagateToTargets(StageContainer stageContainer, CustomActionEvent event){
        String type = event.getImplementingTarget().getType();
        if(type.equals("javafx.stage.Stage")){
            Event.fireEvent(stageContainer.getStage(), event);
        }else if(type.equals("javafx.scene.Scene")){
            Event.fireEvent(stageContainer.getStage().getScene(), event);
        }else{
            propagateToTargets(stageContainer.getStage().getScene().getRoot(), event);
        }
    }

    private void propagateToTargets(Parent root, CustomActionEvent event){
        try {
            NodeHelper.getAllNodesWithType(root, event.getImplementingTarget().getType())
                    .forEach(n -> Event.fireEvent(n, event));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
