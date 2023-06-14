package com.dfx.thought.leadership.article.jaegeraop.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = -2758263676622574388L;

    private Long productId;
    private Integer piece;

    public Order() {
        this.productId = 1L;
        this.piece = 10;
    }
}
