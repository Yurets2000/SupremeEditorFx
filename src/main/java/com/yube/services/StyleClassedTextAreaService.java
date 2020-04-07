package com.yube.services;

import com.yube.commands.area.*;
import com.yube.commands.general.UndoCommand;
import com.yube.configuration.models.actions.Action;
import com.yube.configuration.processors.actions.ActionsProcessor;
import com.yube.controls.ShortcutHandler;
import com.yube.events.CustomActionEvent;
import com.yube.main.StageContainer;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public final class StyleClassedTextAreaService {

    private ActionsProcessor actionsProcessor;
    private ShortcutHandler shortcutHandler;
    private EventService eventService;

    @Autowired
    public StyleClassedTextAreaService(ShortcutHandler shortcutHandler,
                                       ActionsProcessor actionsProcessor,
                                       EventService eventService) {
        this.shortcutHandler = shortcutHandler;
        this.actionsProcessor = actionsProcessor;
        this.eventService = eventService;
    }

    public void bindActionsToTextArea(StyleClassedTextArea styleClassedTextArea, StageContainer container){
        Map<String, BooleanProperty> actionsMap = container.getActionsMap();
        List<String> textAreaActions = Arrays.asList("copy", "cut", "deleteLeft",
                                             "deleteRight", "paste", "selectWord",
                                             "selectLine", "selectAll", "undo");
        textAreaActions.forEach(action -> actionsMap.get(action).bind(styleClassedTextArea.focusedProperty()));
    }

    public void addKeyEventFilter(StyleClassedTextArea area, StageContainer container){
        KeyCodeCombination copyCombination = shortcutHandler.getKeyCodeCombination("copy");
        KeyCodeCombination cutCombination = shortcutHandler.getKeyCodeCombination("cut");
        KeyCodeCombination deleteLeftCombination = shortcutHandler.getKeyCodeCombination("deleteLeft");
        KeyCodeCombination deleteRightCombination = shortcutHandler.getKeyCodeCombination("deleteRight");
        KeyCodeCombination pasteCombination = shortcutHandler.getKeyCodeCombination("paste");
        KeyCodeCombination selectAllCombination = shortcutHandler.getKeyCodeCombination("selectAll");
        KeyCodeCombination selectLineCombination = shortcutHandler.getKeyCodeCombination("selectLine");
        KeyCodeCombination selectWordCombination = shortcutHandler.getKeyCodeCombination("selectWord");
        KeyCodeCombination undoCombination = shortcutHandler.getKeyCodeCombination("undo");

        area.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            Action action = null;
            if(copyCombination != null && copyCombination.match(event)) {
                action = actionsProcessor.getAction("copy");
            }else if(cutCombination != null && cutCombination.match(event)){
                action = actionsProcessor.getAction("cut");
            }else if(deleteLeftCombination != null && deleteLeftCombination.match(event)){
                action = actionsProcessor.getAction("deleteLeft");
            }else if(deleteRightCombination != null && deleteRightCombination.match(event)){
                action = actionsProcessor.getAction("deleteRight");
            }else if(pasteCombination != null && pasteCombination.match(event)){
                action = actionsProcessor.getAction("paste");
            }else if(selectAllCombination != null && selectAllCombination.match(event)){
                action = actionsProcessor.getAction("selectAll");
            }else if(selectLineCombination != null && selectLineCombination.match(event)){
                action = actionsProcessor.getAction("selectLine");
            }else if(selectWordCombination != null && selectWordCombination.match(event)){
                action = actionsProcessor.getAction("selectWord");
            }else if(undoCombination != null && undoCombination.match(event)){
                action = actionsProcessor.getAction("undo");
            }
            final Action finalAction = action;
            if(action != null) {
                action.getImplementors().forEach(implementor -> {
                    eventService.propagateToTargets(container, new CustomActionEvent(finalAction, implementor));
                });
            }
        });
    }

    public void addCustomActionEventFilter(StyleClassedTextArea area, StageContainer container) {
        area.addEventFilter(CustomActionEvent.ANY, event -> {
            if(event.getImplementingTarget().isRelatedTo(StyleClassedTextArea.class.getName(), null)){
                switch (event.getAction().getName()){
                    case "copy":
                        container.executeCommand(new CopyCommand(area, container));
                        break;
                    case "cut":
                        container.executeCommand(new CutCommand(area, container));
                        break;
                    case "deleteLeft":
                        container.executeCommand(new DeleteLeftCommand(area, container));
                        break;
                    case "deleteRight":
                        container.executeCommand(new DeleteRightCommand(area, container));
                        break;
                    case "paste":
                        container.executeCommand(new PasteCommand(area, container));
                        break;
                    case "selectAll":
                        container.executeCommand(new SelectAllCommand(area, container));
                        break;
                    case "selectLine":
                        container.executeCommand(new SelectLineCommand(area, container));
                        break;
                    case "selectWord":
                        container.executeCommand(new SelectWordCommand(area, container));
                        break;
                    case "undo":
                        container.executeCommand(new UndoCommand(container));
                        break;
                }
            }
        });
    }
}
