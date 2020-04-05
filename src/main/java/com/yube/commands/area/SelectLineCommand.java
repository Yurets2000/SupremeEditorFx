package com.yube.commands.area;

import com.yube.logic.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

public class SelectLineCommand extends AreaCommand {

    public SelectLineCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Select Line");
        area.selectLine();
        return null;
    }
}
