package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.main.StageContainer;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "selectLine")
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
