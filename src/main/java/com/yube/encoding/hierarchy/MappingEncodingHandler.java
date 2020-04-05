package com.yube.encoding.hierarchy;

import com.yube.configuration.models.encodings.Mapping;
import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.configuration.processors.encodings.MappingProcessor;

import java.io.File;

public abstract class MappingEncodingHandler extends AbstractEncodingHandler {

    protected File mappingFile;
    protected Mapping mapping;

    protected MappingProcessor mappingProcessor;

    public MappingEncodingHandler(String encoding, EncodingsProcessor encodingsProcessor, MappingProcessor mappingProcessor) {
        super(encoding, encodingsProcessor);
        this.mappingProcessor = mappingProcessor;
        this.mapping = mappingProcessor.getMapping(encoding);
    }

}
