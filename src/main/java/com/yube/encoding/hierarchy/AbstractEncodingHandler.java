package com.yube.encoding.hierarchy;

import com.yube.configuration.models.encodings.Encoding;
import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.encoding.exceptions.EncodingHandlerInitializationException;

public abstract class AbstractEncodingHandler implements EncodingHandler {

    protected Encoding encoding;

    protected EncodingsProcessor encodingsProcessor;

    public AbstractEncodingHandler(String encoding, EncodingsProcessor encodingsProcessor) {
        this.encodingsProcessor = encodingsProcessor;
        boolean supported = encodingsProcessor.getSupportedEncodings().stream()
                .map(Encoding::getName).anyMatch(eName -> eName.equals(encoding));
        if (!supported)
            throw new EncodingHandlerInitializationException("Passed encoding is not supported now");
        this.encoding = encodingsProcessor.getEncoding(encoding);
    }
}
