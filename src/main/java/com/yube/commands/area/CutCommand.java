package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.commands.Recallable;
import com.yube.main.StageContainer;
import javafx.scene.control.IndexRange;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "cut")
public class CutCommand extends AreaCommand implements Recallable {

    private int start;
    private String deleted;

    public CutCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Cut");
        IndexRange selection = area.getSelection();
        if(selection.getLength() > 0) {
            saveState(selection);
            String content = area.getSelectedText();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(content);
            Clipboard.getSystemClipboard().setContent(clipboardContent);
            area.deleteText(selection.getStart(), selection.getEnd());
        }
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
