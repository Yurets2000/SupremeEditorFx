package com.yube.services;

import com.yube.custom.SupremeArea;
import com.yube.logic.StageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SupremeAreaService {

    private StyleClassedTextAreaService styleClassedTextAreaService;

    @Autowired
    public SupremeAreaService(StyleClassedTextAreaService styleClassedTextAreaService){
        this.styleClassedTextAreaService = styleClassedTextAreaService;
    }

    public SupremeArea createArea(String content, StageContainer container) {
        SupremeArea area = new SupremeArea();
        area.appendText(content);
        styleClassedTextAreaService.addEventFilters(area, container);
        styleClassedTextAreaService.bindActionsToTextArea(area, container);
        return area;
    }
}
