package com.yube.commands.area;

import com.yube.commands.Recallable;
import com.yube.logic.StageContainer;
import javafx.scene.control.IndexRange;
import org.fxmisc.richtext.StyleClassedTextArea;

public class DeleteCommand extends AreaCommand implements Recallable {

    private boolean rtl;
    private int start;
    private String deleted;

    public DeleteCommand(StyleClassedTextArea area, StageContainer container, boolean rtl) {
        super(area, container);
        this.rtl = rtl;
    }

    @Override
    public Void execute() {
        System.out.println("Delete");
        IndexRange selection = area.getSelection();
        if (selection.getLength() == 0) {
            if (!rtl && (selection.getEnd() < area.getLength())) {
                selection = new IndexRange(selection.getStart(), selection.getStart() + 1);
            } else if (rtl && (selection.getStart() > 0)) {
                selection = new IndexRange(selection.getStart() - 1, selection.getEnd());
            }
        }
        saveState(selection);
        area.deleteText(selection.getStart(), selection.getEnd());
        return null;
    }

    public void saveState(IndexRange selection) {
        start = selection.getStart();
        deleted = area.getText(selection);
    }

    @Override
    public void undo() {
        area.insertText(start, deleted);
    }
}
