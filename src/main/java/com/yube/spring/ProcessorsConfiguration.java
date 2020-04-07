package com.yube.spring;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.configuration.factory.XmlConfigurationFactory;
import com.yube.configuration.hierarchy.XmlConfiguration;
import com.yube.configuration.processors.actions.ActionsProcessor;
import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.configuration.processors.encodings.MappingProcessor;
import com.yube.configuration.processors.sessions.SessionFileProcessor;
import com.yube.configuration.processors.sessions.SessionProcessor;
import com.yube.configuration.processors.settings.GuiConfigProcessor;
import com.yube.configuration.processors.settings.ShortcutConfigProcessor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class ProcessorsConfiguration {

    @Bean(name = "standardShortcutConfigProcessor")
    public ShortcutConfigProcessor getStandardShortcutConfigProcessor() throws ConfigurationInitializationException {
        XmlConfiguration settingsConfiguration = XmlConfigurationFactory.getConfiguration("settings");
        return new ShortcutConfigProcessor(settingsConfiguration.getDocument());
    }

    @Bean(name = "standardGuiConfigProcessor")
    public GuiConfigProcessor getStandardGuiConfigProcessor() throws ConfigurationInitializationException {
        XmlConfiguration settingsConfiguration = XmlConfigurationFactory.getConfiguration("settings");
        return new GuiConfigProcessor(settingsConfiguration.getDocument());
    }

    @Bean(name = "standardSessionFileProcessor")
    public SessionFileProcessor getStandardSessionFileProcessor() throws ConfigurationInitializationException {
        XmlConfiguration sessionsConfiguration = XmlConfigurationFactory.getConfiguration("sessions");
        return new SessionFileProcessor(sessionsConfiguration.getDocument());
    }

    @Bean(name = "standardSessionProcessor")
    public SessionProcessor getStandardSessionProcessor() throws ConfigurationInitializationException {
        XmlConfiguration sessionsConfiguration = XmlConfigurationFactory.getConfiguration("sessions");
        return new SessionProcessor(sessionsConfiguration.getDocument());
    }

    @Bean(name = "standardActionsProcessor")
    public ActionsProcessor getStandardActionsProcessor() throws ConfigurationInitializationException {
        XmlConfiguration actionsConfiguration = XmlConfigurationFactory.getConfiguration("actions");
        return new ActionsProcessor(actionsConfiguration.getDocument());
    }

    @Bean(name = "standardEncodingsProcessor")
    public EncodingsProcessor getStandardEncodingsProcessor() throws ConfigurationInitializationException {
        XmlConfiguration encodingsConfiguration = XmlConfigurationFactory.getConfiguration("encodings");
        return new EncodingsProcessor(encodingsConfiguration.getDocument());
    }

    @Bean(name = "standardMappingProcessor")
    public MappingProcessor getStandardMappingProcessor() throws ConfigurationInitializationException {
        XmlConfiguration encodingsConfiguration = XmlConfigurationFactory.getConfiguration("encodings");
        return new MappingProcessor(encodingsConfiguration.getDocument());
    }
}
