package com.yube.commands;

import com.yube.main.StageContainer;

public abstract class AbstractCommand<R> implements Command<R> {

    protected StageContainer container;

    public AbstractCommand(StageContainer container) {
        this.container = container;
    }
}
