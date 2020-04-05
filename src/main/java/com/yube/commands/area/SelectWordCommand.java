package com.yube.commands.area;

import com.yube.logic.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

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
