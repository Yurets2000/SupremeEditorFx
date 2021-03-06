package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "selectWord")
public class SelectWordCommand extends AreaCommand {

    public SelectWordCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Select Word");
        area.selectWord();
        return null;
    }
}
