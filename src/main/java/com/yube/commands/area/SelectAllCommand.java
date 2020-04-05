package com.yube.commands.area;

import com.yube.logic.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

public class SelectAllCommand extends AreaCommand {

    public SelectAllCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Select All");
        area.selectAll();
        return null;
    }
}
