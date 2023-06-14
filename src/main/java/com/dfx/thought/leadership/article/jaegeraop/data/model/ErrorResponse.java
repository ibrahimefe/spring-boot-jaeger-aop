package com.dfx.thought.leadership.article.jaegeraop.data.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -2758263676622574388L;

    private String sourceMessage;
    private boolean success;
}
