package com.yube.commands.general;

import com.yube.commands.AbstractCommand;
import com.yube.logic.StageContainer;

public class UndoCommand extends AbstractCommand<Void> {

    public UndoCommand(StageContainer container) {
        super(container);
    }

    @Override
    public Void execute() {
        System.out.println("Undo");
        container.undoCommand();
        return null;
    }
}
