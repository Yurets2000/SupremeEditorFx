package com.yube.logic;

import com.yube.commands.Command;
import com.yube.commands.Recallable;
import javafx.beans.property.BooleanProperty;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class StageContainer {

    @Getter
    private Stage stage;
    @Getter
    private final String qualifier;
    @Getter
    private Deque<Recallable> commandHistory = new ArrayDeque<>();
    @Getter
    private Map<String, BooleanProperty> actionsMap = new HashMap<>();

    public StageContainer(Stage stage, String qualifier){
        this.stage = stage;
        this.qualifier = qualifier;
    }

    public void executeCommand(Command command) {
        command.execute();
        if (command instanceof Recallable) {
            commandHistory.push((Recallable)command);
        }
    }

    public void undoCommand() {
        if(commandHistory.size() > 0) {
            Recallable command = commandHistory.pop();
            command.undo();
        }
    }
}
