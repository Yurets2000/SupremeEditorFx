package com.yube.events;

import com.yube.configuration.models.actions.Action;
import com.yube.configuration.models.actions.Implementor;
import javafx.event.Event;
import javafx.event.EventType;
import lombok.Getter;

public class CustomActionEvent extends Event {

    private static final long serialVersionUID = -3995835255297959463L;

    public static final EventType<CustomActionEvent> ANY = new EventType<>("CUSTOM_ACTION");

    @Getter
    private Action action;
    @Getter
    private Implementor implementingTarget;

    public CustomActionEvent(Action action, Implementor implementingTarget) {
        super(ANY);
        this.action = action;
        this.implementingTarget = implementingTarget;
    }
}
