package com.yube.services;

import com.yube.commands.area.*;
import com.yube.commands.general.UndoCommand;
import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.configuration.factory.XmlConfigurationFactory;
import com.yube.configuration.hierarchy.XmlConfiguration;
import com.yube.configuration.processors.settings.ShortcutConfigProcessor;
import com.yube.controls.ShortcutHandler;
import com.yube.logic.StageContainer;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public final class StyleClassedTextAreaService {

    private ShortcutHandler shortcutHandler;

    public StyleClassedTextAreaService() {
        XmlConfiguration settingsConfiguration;
        XmlConfiguration actionsConfiguration;
        try {
            actionsConfiguration = XmlConfigurationFactory.getConfiguration("actions");
            settingsConfiguration = XmlConfigurationFactory.getConfiguration("settings");
        } catch (ConfigurationInitializationException e) {
            throw new RuntimeException("ImplementorException during reading shortcut configuration");
        }
        ShortcutConfigProcessor shortcutConfigProcessor = new ShortcutConfigProcessor(settingsConfiguration.getDocument());
        this.shortcutHandler = new ShortcutHandler(shortcutConfigProcessor);
    }

    public void bindActionsToTextArea(StyleClassedTextArea styleClassedTextArea, StageContainer container){
        Map<String, BooleanProperty> actionsMap = container.getActionsMap();
        actionsMap.get("copy").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("cut").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("deleteLeft").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("deleteRight").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("paste").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("selectWord").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("selectLine").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("selectAll").bind(styleClassedTextArea.focusedProperty());
        actionsMap.get("undo").bind(styleClassedTextArea.focusedProperty());
    }

    public void addEventFilters(StyleClassedTextArea area, StageContainer container) {
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
            if(copyCombination != null && copyCombination.match(event)){
                container.executeCommand(new CopyCommand(area, container));
            }else if(cutCombination != null && cutCombination.match(event)){
                container.executeCommand(new CutCommand(area, container));
            }else if(deleteLeftCombination != null && deleteLeftCombination.match(event)){
                container.executeCommand(new DeleteCommand(area, container, true));
            }else if(deleteRightCombination != null && deleteRightCombination.match(event)){
                container.executeCommand(new DeleteCommand(area, container, false));
            }else if(pasteCombination != null && pasteCombination.match(event)){
                container.executeCommand(new PasteCommand(area, container));
            }else if(selectAllCombination != null && selectAllCombination.match(event)){
                container.executeCommand(new SelectAllCommand(area, container));
            }else if(selectLineCombination != null && selectLineCombination.match(event)){
                container.executeCommand(new SelectLineCommand(area, container));
            }else if(selectWordCombination != null && selectWordCombination.match(event)){
                container.executeCommand(new SelectWordCommand(area, container));
            }else if(undoCombination != null && undoCombination.match(event)){
                container.executeCommand(new UndoCommand(container));
            }
        });
    }
}
