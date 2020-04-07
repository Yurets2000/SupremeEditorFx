package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "selectAll")
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
