package com.yube.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParsingResult {
    private boolean success;
    private List<Token> tokens;
}
