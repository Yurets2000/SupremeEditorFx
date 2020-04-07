package com.yube.commands.area;

import com.yube.commands.AbstractCommand;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

public abstract class AreaCommand extends AbstractCommand<Void> {

    protected StyleClassedTextArea area;

    public AreaCommand(StyleClassedTextArea area, StageContainer container) {
        super(container);
        this.area = area;
    }
}
