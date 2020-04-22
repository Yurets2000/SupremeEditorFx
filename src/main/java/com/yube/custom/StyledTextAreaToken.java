package com.yube.custom;

import lombok.Data;

@Data
public class StyledTextAreaToken {
    private final int begin, end;
    private final String token;
}
