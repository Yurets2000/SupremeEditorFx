package com.yube.main;

import com.yube.commands.Command;
import com.yube.commands.Recallable;
import com.yube.observables.ObservableProperty;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public final class StageContainer {

    @Getter
    private Stage stage;
    @Getter
    private final String qualifier;
    @Getter
    private Deque<Command> commandHistory = new ArrayDeque<>();
    @Getter
    private Deque<Recallable> recallables = new ArrayDeque<>();
    @Getter
    private Map<String, ObservableProperty<Boolean>> actionsMap = new HashMap<>();

    public StageContainer(Stage stage, String qualifier){
        this.stage = stage;
        this.qualifier = qualifier;
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
        if(command instanceof Recallable){
            recallables.push((Recallable) command);
        }
    }

    public void undoCommand() {
        if(recallables.size() > 0) {
            Recallable command = recallables.pop();
            command.undo();
        }
    }
}
