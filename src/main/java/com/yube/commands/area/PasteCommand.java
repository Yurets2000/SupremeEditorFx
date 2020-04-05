package com.yube.commands.area;

import com.yube.commands.Recallable;
import com.yube.logic.StageContainer;
import javafx.scene.control.IndexRange;
import javafx.scene.input.Clipboard;
import org.fxmisc.richtext.StyleClassedTextArea;

public class PasteCommand extends AreaCommand implements Recallable {

    private IndexRange pasteRange;

    public PasteCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Paste");
        String content = Clipboard.getSystemClipboard().getString();
        if(content != null){
            int position = area.getCaretPosition();
            saveState(position, content.length());
            area.insertText(position, content);
        }
        return null;
    }

    public void saveState(int start, int length) {
        pasteRange = new IndexRange(start, start + length);
    }

    @Override
    public void undo() {
        area.deleteText(pasteRange.getStart(), pasteRange.getEnd());
    }
}
