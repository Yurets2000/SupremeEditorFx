package com.yube.encoding.factory;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.configuration.factory.XmlConfigurationFactory;
import com.yube.configuration.hierarchy.XmlConfiguration;
import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.configuration.processors.encodings.MappingProcessor;
import com.yube.encoding.exceptions.EncodingHandlerInitializationException;
import com.yube.encoding.exceptions.UnsupportedEncodingException;
import com.yube.encoding.hierarchy.EncodingHandler;
import com.yube.encoding.hierarchy.TxtMappingHandler;
import com.yube.encoding.hierarchy.UTF8Handler;

public class EncodingHandlerFactory {

    public static EncodingHandler getEncodingHandler(String encoding) throws EncodingHandlerInitializationException {
        EncodingHandler handler;
        XmlConfiguration encodingsConfiguration;
        try {
            encodingsConfiguration = XmlConfigurationFactory.getConfiguration("encodings");
        } catch (ConfigurationInitializationException e) {
            throw new EncodingHandlerInitializationException("Problems with reading encodings configuration", e);
        }
        EncodingsProcessor encodingsProcessor = new EncodingsProcessor(encodingsConfiguration.getDocument());
        String type = encodingsProcessor.getEncoding(encoding).getType();
        switch (type) {
            case "algorithmic":
                switch (encoding){
                    case "UTF-8":
                        handler = new UTF8Handler(encoding, encodingsProcessor);
                        break;
                    default:
                        throw new UnsupportedEncodingException("Passed encoding has unsupported mapping type");
                }
                break;
            case "mapping":
                MappingProcessor mappingProcessor = new MappingProcessor(encodingsConfiguration.getDocument());
                switch (encoding){
                    case "txt":
                        handler = new TxtMappingHandler(encoding, encodingsProcessor, mappingProcessor);
                        break;
                    default:
                        throw new UnsupportedEncodingException("Passed encoding has unsupported mapping type");
                }
                break;
            default:
                throw new UnsupportedEncodingException("Passed encoding has unsupported type");
        }
        return handler;
    }
}
