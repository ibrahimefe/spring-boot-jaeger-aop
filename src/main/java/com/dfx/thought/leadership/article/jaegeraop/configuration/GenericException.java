package com.dfx.thought.leadership.article.jaegeraop.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericException extends RuntimeException{

    private String sourceMessage;
}
