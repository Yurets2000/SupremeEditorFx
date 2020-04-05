package com.yube.encoding.hierarchy;

import com.yube.configuration.processors.encodings.EncodingsProcessor;

public abstract class AlgorithmicEncodingHandler extends AbstractEncodingHandler {

    public AlgorithmicEncodingHandler(String encoding, EncodingsProcessor encodingsProcessor) {
        super(encoding, encodingsProcessor);
    }
}
