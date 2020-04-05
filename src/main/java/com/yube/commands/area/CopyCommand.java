package com.yube.commands.area;

import com.yube.logic.StageContainer;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.fxmisc.richtext.StyleClassedTextArea;

public class CopyCommand extends AreaCommand {

    public CopyCommand(StyleClassedTextArea area, StageContainer container) {
        super(area, container);
    }

    @Override
    public Void execute() {
        System.out.println("Copy");
        if(area.getSelection().getLength() > 0) {
            String content = area.getSelectedText();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(content);
            Clipboard.getSystemClipboard().setContent(clipboardContent);
        }
        return null;
    }
}
