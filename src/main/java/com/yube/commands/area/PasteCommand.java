package com.yube.commands.area;

import com.yube.annotations.ActionHandler;
import com.yube.commands.Recallable;
import com.yube.main.StageContainer;
import javafx.scene.control.IndexRange;
import javafx.scene.input.Clipboard;
import org.fxmisc.richtext.StyleClassedTextArea;

@ActionHandler(action = "paste")
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
            int start = area.getCaretPosition();
            area.insertText(start, content);
            int end = area.getCaretPosition();
            saveState(start, end);
        }
        return null;
    }

    public void saveState(int start, int end) {
        pasteRange = new IndexRange(start, end);
    }

    @Override
    public void undo() {
        area.deleteText(pasteRange.getStart(), pasteRange.getEnd());
    }
}
