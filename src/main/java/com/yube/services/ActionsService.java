package com.yube.services;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.configuration.factory.XmlConfigurationFactory;
import com.yube.configuration.hierarchy.XmlConfiguration;
import com.yube.configuration.processors.actions.ActionsProcessor;
import org.springframework.stereotype.Service;

@Service
public final class ActionsService {

    private ActionsProcessor actionsProcessor;

    public ActionsService() {
        XmlConfiguration actionsConfiguration;
        try {
            actionsConfiguration = XmlConfigurationFactory.getConfiguration("actions");
        } catch (ConfigurationInitializationException e) {
            throw new RuntimeException("ImplementorException during reading shortcut configuration");
        }
        this.actionsProcessor = new ActionsProcessor(actionsConfiguration.getDocument());
    }
}
