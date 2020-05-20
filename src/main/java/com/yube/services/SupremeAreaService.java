package com.yube.services;

import com.yube.configuration.models.settings.GuiConfig;
import com.yube.configuration.processors.settings.GuiConfigProcessor;
import com.yube.configuration.processors.styling.LexerStyleProcessor;
import com.yube.custom.SupremeArea;
import com.yube.main.StageContainer;
import com.yube.misc.SupremeAreaSearcher;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        area.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.S && event.isControlDown()) {
                Optional<Bounds> optionalBounds = area.getCaretBounds();
                if(optionalBounds.isPresent()) {
                    Bounds bounds = optionalBounds.get();
                    searcherPopup.show(container.getStage(), bounds.getMaxX() + 10, bounds.getMaxY() + 10);
                }
            }
        });
        styleClassedTextAreaService.addCustomActionEventFilter(area, container);
        styleClassedTextAreaService.bindActionsToTextArea(area, container);
        return area;
    }
}
