package com.yube.encoding.factory;

import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.configuration.processors.encodings.MappingProcessor;
import com.yube.encoding.exceptions.EncodingHandlerInitializationException;
import com.yube.encoding.exceptions.UnsupportedEncodingException;
import com.yube.encoding.hierarchy.EncodingHandler;
import com.yube.encoding.hierarchy.TxtMappingHandler;
import com.yube.encoding.hierarchy.UTF8Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncodingHandlerFactory {

    private EncodingsProcessor encodingsProcessor;
    private MappingProcessor mappingProcessor;

    @Autowired
    public EncodingHandlerFactory(EncodingsProcessor encodingsProcessor, MappingProcessor mappingProcessor){
        this.encodingsProcessor = encodingsProcessor;
        this.mappingProcessor = mappingProcessor;
    }

    public EncodingHandler getEncodingHandler(String encoding) throws EncodingHandlerInitializationException {
        EncodingHandler handler;
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
