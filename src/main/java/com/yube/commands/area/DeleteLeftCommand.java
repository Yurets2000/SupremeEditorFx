package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "deleteLeft")
public class DeleteLeftCommand extends DeleteCommand {

    public DeleteLeftCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container, false);
    }
}
