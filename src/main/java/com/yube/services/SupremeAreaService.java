package com.yube.services;

import com.yube.configuration.models.settings.GuiConfig;
import com.yube.configuration.processors.settings.GuiConfigProcessor;
import com.yube.configuration.processors.styling.LexerStyleProcessor;
import com.yube.custom.SupremeArea;
import com.yube.main.StageContainer;
import com.yube.misc.SupremeAreaSearcher;
import javafx.scene.input.MouseButton;
import javafx.stage.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SupremeAreaService {

    private GuiConfigProcessor guiConfigProcessor;
    private LexerStyleProcessor lexerStyleProcessor;
    private StyleClassedTextAreaService styleClassedTextAreaService;

    @Autowired
    public SupremeAreaService(GuiConfigProcessor guiConfigProcessor,
                              LexerStyleProcessor lexerStyleProcessor,
                              StyleClassedTextAreaService styleClassedTextAreaService){
        this.guiConfigProcessor = guiConfigProcessor;
        this.lexerStyleProcessor = lexerStyleProcessor;
        this.styleClassedTextAreaService = styleClassedTextAreaService;
    }

    public SupremeArea createArea(String content, StageContainer container) {
        SupremeArea area = new SupremeArea();
        area.appendText(content);
        GuiConfig highlighting = guiConfigProcessor.getGuiConfig("highlighting");
        if(highlighting.getAdditionalAttributes().get("enable").equals("true")){
            area.enableHighlighting(lexerStyleProcessor.getLexerStyle("java"));
        }
        GuiConfig autocomplete = guiConfigProcessor.getGuiConfig("auto-completion");
        if(autocomplete.getAdditionalAttributes().get("enable").equals("true")){
            area.enableAutocomplete();
        }
        styleClassedTextAreaService.addKeyEventFilter(area, container);
        SupremeAreaSearcher supremeAreaSearcher = new SupremeAreaSearcher(area);
        Popup searcherPopup = supremeAreaSearcher.createPopup();
        area.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                searcherPopup.show(container.getStage(), event.getX(), event.getY());
            }
        });
        styleClassedTextAreaService.addCustomActionEventFilter(area, container);
        styleClassedTextAreaService.bindActionsToTextArea(area, container);
        return area;
    }
}
