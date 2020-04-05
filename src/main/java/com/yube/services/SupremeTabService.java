package com.yube.services;

import com.yube.configuration.models.sessions.SessionFile;
import com.yube.custom.SupremeArea;
import com.yube.custom.SupremeTab;
import com.yube.logic.StageContainer;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SupremeTabService {

    private SupremeAreaService supremeAreaService;

    @Autowired
    public SupremeTabService(SupremeAreaService supremeAreaService){
        this.supremeAreaService = supremeAreaService;
    }

    public SupremeTab createTab(StageContainer container){
        SupremeTab tab = new SupremeTab();
        createTabContent(tab, container);
        return tab;
    }

    public SupremeTab createTab(SessionFile sessionFile, StageContainer container){
        SupremeTab tab = new SupremeTab();
        tab.setFileId(sessionFile.getId());
        tab.setText(sessionFile.getName());
        createTabContent(tab, container);
        return tab;
    }

    private void createTabContent(SupremeTab tab, StageContainer container){
        SupremeArea area = supremeAreaService.createArea("Content here", container);
        area.setPrefHeight(2000);
        VirtualizedScrollPane<SupremeArea> scrollPane = new VirtualizedScrollPane<>(area);
        tab.setContent(scrollPane);
    }
}
