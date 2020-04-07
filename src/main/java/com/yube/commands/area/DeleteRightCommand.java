package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "deleteRight")
public class DeleteRightCommand extends DeleteCommand {

    public DeleteRightCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container, true);
    }
}
